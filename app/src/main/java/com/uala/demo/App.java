package com.uala.demo;

import android.app.Application;

import com.uala.demo.endpoint.AmazonAWSClient;
import com.uala.demo.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    public static AmazonAWSClient awsClient;

    @Override
    public void onCreate() {
        super.onCreate();

        configureEndpoint();
    }

    private void configureEndpoint() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.AMAZONAWS_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        awsClient = retrofit.create(AmazonAWSClient.class);
    }

}
