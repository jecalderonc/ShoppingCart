package com.service.shoppingcart.externalservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * This class defines the Items service configurations
 */
@Configuration
public class GetItemsConfg {
	
	static final String API_BASE_URL = "http://challenge.getsandbox.com/";

    @Bean
    public IGetItems evasServiceIFClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );

        Retrofit retrofit =
                builder
                        .client(
                                httpClient.build()
                        )
                        .build();

        IGetItems client = retrofit.create(IGetItems.class);

        return client;
    }

}
