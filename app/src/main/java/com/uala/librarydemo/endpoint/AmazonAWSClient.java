package com.uala.librarydemo.endpoint;

import com.uala.librarydemo.model.Book;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AmazonAWSClient {

    @GET("/test/books")
    Call<ArrayList<Book>> getBooks();

}
