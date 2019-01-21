package com.uala.demo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uala.demo.R;
import com.uala.demo.model.Book;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private ArrayList<Book> books;

    class BookViewHolder extends RecyclerView.ViewHolder {

        BookViewHolder(@NonNull final View itemView) {
            super(itemView);

        }

    }

    public BookAdapter(ArrayList<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View bookView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_library_book, viewGroup, false);

        return new BookViewHolder(bookView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder bookViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return books != null ? books.size() : 0;
    }

}
