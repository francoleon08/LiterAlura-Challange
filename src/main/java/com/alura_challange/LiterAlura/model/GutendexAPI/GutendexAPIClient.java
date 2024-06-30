package com.alura_challange.LiterAlura.model.GutendexAPI;

import com.alura_challange.LiterAlura.model.entities.Book;
import com.alura_challange.LiterAlura.model.entities.Person;
import com.alura_challange.LiterAlura.model.exceptions.BookSearchException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class GutendexAPIClient {
    private static final String BOOK_NOT_FOUND_MESSAGE = "Error, book not found.";
    private static final String BASE_URL = "https://gutendex.com/";
    private final GutendexAPI gutendexAPI;

    public GutendexAPIClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gutendexAPI = retrofit.create(GutendexAPI.class);
    }

    public Book getBookByTitle(String title) throws BookSearchException {
        String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);
        Call<ResponseBody> call = gutendexAPI.getBooksByTitle(encodedTitle);
        Response<ResponseBody> response = executeCall(call);
        JsonObject jsonObject = parseResponse(response);
        JsonArray books = extractBooksArray(jsonObject);
        return createBookFromFirstResult(books);
    }

    private Response<ResponseBody> executeCall(Call<ResponseBody> call) throws BookSearchException {
        try {
            Response<ResponseBody> response = call.execute();
            if (!response.isSuccessful()) {
                throw new BookSearchException(BOOK_NOT_FOUND_MESSAGE);
            }
            return response;
        } catch (IOException e) {
            throw new BookSearchException("Error while connecting to the API.");
        }
    }

    private JsonObject parseResponse(Response<ResponseBody> response) throws BookSearchException {
        try {
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new BookSearchException(BOOK_NOT_FOUND_MESSAGE);
            }
            return new Gson().fromJson(responseBody.string(), JsonObject.class);
        } catch (IOException e) {
            throw new BookSearchException("Error parsing the API response.");
        }
    }

    private JsonArray extractBooksArray(JsonObject jsonObject) throws BookSearchException {
        JsonArray books = jsonObject.getAsJsonArray("results");
        if (books == null || books.isEmpty()) {
            throw new BookSearchException(BOOK_NOT_FOUND_MESSAGE);
        }
        return books;
    }

    private Book createBookFromFirstResult(JsonArray books) {
        JsonObject firstBook = books.get(0).getAsJsonObject();
        return createBook(firstBook);
    }

    private Book createBook(JsonObject jsonObject) {
        Book book = getBookFromJson(jsonObject);
        book.setSubjects(getAttributeFromJson(jsonObject.getAsJsonArray("subjects")));
        book.setLanguages(getAttributeFromJson(jsonObject.getAsJsonArray("languages")));
        book.setAuthors(List.of(getAuthorFromJson(jsonObject.getAsJsonArray("authors").get(0).getAsJsonObject())));
        return book;
    }

    private Book getBookFromJson(JsonObject jsonObject) {
        return Book.builder()
                .id(jsonObject.get("id").getAsLong())
                .title(jsonObject.get("title").getAsString())
                .nro_downloads(jsonObject.get("download_count").getAsInt())
                .build();
    }

    private List<String> getAttributeFromJson(JsonArray jsonArray) {
        List<String> attributes = new ArrayList<>();
        for (JsonElement jsonElement : jsonArray) {
            attributes.add(jsonElement.getAsString());
        }
        return attributes;
    }

    private Person getAuthorFromJson(JsonObject jsonObject) {
        return Person.builder()
                .name(jsonObject.get("name").getAsString())
                .birth_year(jsonObject.get("birth_year").getAsInt())
                .death_year(jsonObject.get("death_year").getAsInt())
                .build();
    }
}