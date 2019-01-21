package com.uala.demo.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.uala.demo.App;
import com.uala.demo.R;
import com.uala.demo.adapter.BookAdapter;
import com.uala.demo.model.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibraryActivity extends AppCompatActivity {

    private BookAdapter adapter;
    private ArrayList<Book> books = new ArrayList<>();          // Todos los libros
    private ArrayList<Book> displayedBooks = new ArrayList<>(); // Libros mostrados en la lista

    private boolean ascendingOrder; // Falso por defecto (Se deben mostrar de forma descendente por defecto)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        setUpActionBar();

        setUpRecyclerView();
        requestBooks();
    }

    private void setUpActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.library_welcome_message));
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.library_recyclerview_books);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new BookAdapter(this, displayedBooks);
        adapter.setClickListener(new BookAdapter.OnBookClickListener() {
            @Override
            public void onBookClick(View itemView, int position) {
                Intent intent = new Intent(LibraryActivity.this, BookDetailActivity.class);
                intent.putExtra(BookDetailActivity.EXTRA_BOOK, displayedBooks.get(position));
                startActivity(intent);
            }
        });

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
                loadAllBooks();

            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {
                Toast.makeText(LibraryActivity.this, getString(R.string.library_service_error), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadAllBooks() {
        displayedBooks.clear();
        displayedBooks.addAll(books);
        orderBooksByPopularity(ascendingOrder);
        adapter.notifyDataSetChanged();
    }

    private void reverseBookOrder() {
        ascendingOrder = !ascendingOrder;
        orderBooksByPopularity(ascendingOrder);
        adapter.notifyDataSetChanged();
    }

    private void filterBooksByAvailability(boolean available) {
        displayedBooks.clear();

        for (Book book : books)
            if (book.isAvailable() == available)
                displayedBooks.add(book);

        orderBooksByPopularity(ascendingOrder);
        adapter.notifyDataSetChanged();
    }

    private void orderBooksByPopularity(final boolean ascendingOrder) {
        Collections.sort(displayedBooks, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return ascendingOrder ? Integer.compare(o1.getPopularity(), o2.getPopularity()) : Integer.compare(o2.getPopularity(), o1.getPopularity());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_library, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_reverseorder:
                reverseBookOrder();
                break;
            case R.id.action_show_available:
                filterBooksByAvailability(true);
                break;
            case R.id.action_show_nonavailable:
                filterBooksByAvailability(false);
                break;
            case R.id.action_show_all:
                loadAllBooks();
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

}
