package com.alura_challange.LiterAlura.model.GutendexAPI;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GutendexAPI {
    @GET("books")
    Call<ResponseBody> getBooksByTitle(@Query("search") String title);
}
