package com.service.shoppingcart.externalservice;

import retrofit2.Call;

import retrofit2.http.Path;
import retrofit2.http.GET;

import java.util.List;


/*
 * Inteface that contain the methods to retrieve the Items Information
 */
public interface IGetItems {

    @GET("/articles")
    Call<List<Item>> getArticles();
    
    @GET("/articles/{id}")
    Call<Item> getArticlesById(@Path("id") int id);
}