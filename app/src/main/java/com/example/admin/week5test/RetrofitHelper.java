package com.example.admin.week5test;

import com.example.admin.week5test.model.FlickerClass;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Admin on 9/29/2017.
 */

public class RetrofitHelper {
    public static final String Base_URL = "http://api.flickr.com/services/feeds/";

    public static Retrofit create()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Base_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }

    public static Call<FlickerClass> getFlickerClass()
    {
        Retrofit retrofit = create();
        APIService apiService = retrofit.create(APIService.class);
        return apiService.getFlickerResults("kitten","json",1);
    }

    interface APIService
    {
        @GET("photos_public.gne")
        Call<FlickerClass> getFlickerResults (@Query("tag") String TAG,@Query("format") String Format, @Query("nojsoncallback") int NoJsonCallBack);
    }
}
