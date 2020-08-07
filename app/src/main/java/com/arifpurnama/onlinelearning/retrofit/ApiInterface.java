package com.arifpurnama.onlinelearning.retrofit;

import com.arifpurnama.onlinelearning.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("course.json")
    Call<List<Category>> getAllCategory();

    // we need to make model class for our data
    // first have a look on json structure.

}
