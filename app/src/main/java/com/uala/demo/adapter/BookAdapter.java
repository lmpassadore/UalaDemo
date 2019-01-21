package com.uala.demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.uala.demo.R;
import com.uala.demo.model.Book;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Context context;
    private ArrayList<Book> books;

    class BookViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewCover;
        TextView textViewTitle, textViewAuthor, textViewPopularity, textViewAvailability;

        BookViewHolder(@NonNull final View itemView) {
            super(itemView);

            imageViewCover = itemView.findViewById(R.id.book_imageview_cover);
            textViewTitle = itemView.findViewById(R.id.book_textview_title);
            textViewAuthor = itemView.findViewById(R.id.book_textview_author);
            textViewPopularity = itemView.findViewById(R.id.book_textview_popularity);
            textViewAvailability = itemView.findViewById(R.id.book_textview_availability);

        }

    }

    public BookAdapter(Context context, ArrayList<Book> books) {
        this.context = context;
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

        Book book = books.get(i);

        String coverImage = book.getImageUrl();
        if (!TextUtils.isEmpty(coverImage))
            Picasso.get()
                    .load(coverImage)
                    .fit()
                    .centerCrop()
                    .into(bookViewHolder.imageViewCover);

        if (!TextUtils.isEmpty(book.getTitle()))
            bookViewHolder.textViewTitle.setText(book.getTitle());

        if (!TextUtils.isEmpty(book.getAuthor()))
            bookViewHolder.textViewAuthor.setText(book.getAuthor());

        if (book.getPopularity() >= 0)
            bookViewHolder.textViewPopularity.setText(
                    String.format(context.getString(R.string.book_popularity_placeholder), book.getPopularity())
            );

        bookViewHolder.textViewAvailability.setText(
                context.getString(book.isAvailable() ? R.string.book_availability_available : R.string.book_availability_unavailable)
        );

    }

    @Override
    public int getItemCount() {
        return books != null ? books.size() : 0;
    }

}
