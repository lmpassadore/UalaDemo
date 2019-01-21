package com.uala.demo.endpoint;

import com.uala.demo.model.Book;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AmazonAWSClient {

    @GET("/test/books")
    Call<ArrayList<Book>> getBooks();

}
