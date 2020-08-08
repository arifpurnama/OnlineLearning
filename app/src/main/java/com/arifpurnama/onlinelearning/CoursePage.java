package com.arifpurnama.onlinelearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.arifpurnama.onlinelearning.adapter.CategoryAdapter;
import com.arifpurnama.onlinelearning.adapter.CourseAdapter;
import com.arifpurnama.onlinelearning.model.Category;
import com.arifpurnama.onlinelearning.model.Course;
import com.arifpurnama.onlinelearning.model.PlayList;
import com.arifpurnama.onlinelearning.retrofit.ApiInterface;
import com.arifpurnama.onlinelearning.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoursePage extends AppCompatActivity {

    RecyclerView courseRecylerView;
    CourseAdapter courseAdapter;
    ApiInterface apiInterface;

    TextView member, rating, name, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);

        apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
        courseRecylerView = findViewById(R.id.course_recycler);

        member = findViewById(R.id.members);
        rating = findViewById(R.id.rating);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);

        Call<List<Course>> call = apiInterface.getCourseContent();
        call.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                List<Course> courseList = response.body();

                getCoursesContent(courseList.get(0).getPlayList());

                member.setText(courseList.get(0).getMember());
                rating.setText(courseList.get(0).getRating());
                name.setText(courseList.get(0).getCourseName());
                price.setText(courseList.get(0).getPrice());

            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Toast.makeText(CoursePage.this, "No response from serve", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCoursesContent(List<PlayList> playLists) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        courseRecylerView.setLayoutManager(layoutManager);
        courseAdapter = new CourseAdapter(this, playLists);
        courseRecylerView.setAdapter(courseAdapter);
        courseAdapter.notifyDataSetChanged();
    }
}