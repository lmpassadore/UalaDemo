package com.uala.demo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.uala.demo.App;
import com.uala.demo.R;
import com.uala.demo.adapter.BookAdapter;
import com.uala.demo.model.Book;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibraryActivity extends AppCompatActivity {

    private BookAdapter adapter;
    private ArrayList<Book> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        setUpRecyclerView();
        requestBooks();
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.library_recyclerview_books);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new BookAdapter(books);
        recyclerView.setAdapter(adapter);
    }

    private void requestBooks() {

        App.awsClient.getBooks().enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {

                ArrayList<Book> obtainedBooks = response.body();
                if (!response.isSuccessful() || obtainedBooks == null) {
                    Toast.makeText(LibraryActivity.this, getString(R.string.library_service_error), Toast.LENGTH_SHORT).show();
                    return;
                }

                books.addAll(obtainedBooks);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {
                Toast.makeText(LibraryActivity.this, getString(R.string.library_service_error), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
