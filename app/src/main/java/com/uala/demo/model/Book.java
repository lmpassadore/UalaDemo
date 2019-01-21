package com.uala.demo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Book implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("nombre")
    private String title;

    @SerializedName("autor")
    private String author;

    @SerializedName("disponibilidad")
    private boolean availability;

    @SerializedName("popularidad")
    private int popularity;

    @SerializedName("imagen")
    private String imageUrl;

    public Book() {

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return availability;
    }

    public int getPopularity() {
        return popularity;
    }

    public String getImageUrl() {
        return imageUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.author);
        dest.writeByte(this.availability ? (byte) 1 : (byte) 0);
        dest.writeInt(this.popularity);
        dest.writeString(this.imageUrl);
    }

    protected Book(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.author = in.readString();
        this.availability = in.readByte() != 0;
        this.popularity = in.readInt();
        this.imageUrl = in.readString();
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

}
