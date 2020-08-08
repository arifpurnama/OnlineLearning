package com.arifpurnama.onlinelearning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arifpurnama.onlinelearning.R;
import com.arifpurnama.onlinelearning.model.Course;
import com.arifpurnama.onlinelearning.model.PlayList;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    Context context;
    List<PlayList> playLists;

    public CourseAdapter(Context context, List<PlayList> playLists) {
        this.context = context;
        this.playLists = playLists;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_list_row_items, parent, false);

        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        holder.contentNumber.setText(position + 1);
        holder.contentTime.setText(playLists.get(position).getTime());
        holder.contentName.setText(playLists.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return playLists.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {

        TextView contentNumber, contentTime, contentName;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            contentName = itemView.findViewById(R.id.content_title);
            contentNumber = itemView.findViewById(R.id.content_number);
            contentTime = itemView.findViewById(R.id.content_time);

        }
    }
}
