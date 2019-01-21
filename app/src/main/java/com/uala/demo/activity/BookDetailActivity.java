package com.uala.demo.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.uala.demo.R;
import com.uala.demo.model.Book;

public class BookDetailActivity extends AppCompatActivity {

    public static final String EXTRA_BOOK = "BOOK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        setUpActionBar();

        Bundle extras = getIntent().getExtras();
        if (extras == null)
            return;

        Book selectedBook = extras.getParcelable(EXTRA_BOOK);
        fillDetails(selectedBook);
    }

    private void setUpActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.bookdetail_title));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void fillDetails(Book book) {

        if (book == null)
            return;

        ImageView imageViewCover = findViewById(R.id.bookdetail_imageview_cover);
        TextView textViewTitle = findViewById(R.id.bookdetail_textview_title);
        TextView textViewAuthor = findViewById(R.id.bookdetail_textview_author);
        TextView textViewPopularity = findViewById(R.id.bookdetail_textview_popularity);
        TextView textViewAvailability = findViewById(R.id.bookdetail_textview_availability);


        String coverImage = book.getImageUrl();
        if (!TextUtils.isEmpty(coverImage))
            Picasso.get()
                    .load(coverImage)
                    .fit()
                    .centerCrop()
                    .into(imageViewCover);

        if (!TextUtils.isEmpty(book.getTitle()))
            textViewTitle.setText(book.getTitle());

        if (!TextUtils.isEmpty(book.getAuthor()))
            textViewAuthor.setText(book.getAuthor());

        if (book.getPopularity() >= 0)
            textViewPopularity.setText(
                    String.valueOf(book.getPopularity())
            );

        textViewAvailability.setText(
                getString(book.isAvailable() ? R.string.book_availability_available : R.string.book_availability_unavailable)
        );

    }

}
