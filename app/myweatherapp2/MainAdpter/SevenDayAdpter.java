package com.example.myweatherapp2.MainAdpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherapp2.R;

import java.util.ArrayList;

public class SevenDayAdpter extends RecyclerView.Adapter<SevenDayAdpter.SevenDayViewHolder> {
    Context context;
    ArrayList<WeatherData> weatherDataArrayList;
    public SevenDayAdpter(Context context, ArrayList<WeatherData> weatherDataArrayList) {
        this.context=context;
        this.weatherDataArrayList=weatherDataArrayList;

    }

    @NonNull
    @Override
    public SevenDayAdpter.SevenDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(this.context).inflate(R.layout.main_rv,parent,false);
        return new SevenDayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SevenDayAdpter.SevenDayViewHolder holder, int position) {
        holder.textview_date.setText(weatherDataArrayList.get(position).getData());
        holder.textview_temperature.setText(weatherDataArrayList.get(position).getTemp());
        holder.imageview_weather_icon.setImageResource(weatherDataArrayList.get(position).getWeatherIcon());
    }

    @Override
    public int getItemCount() {
        return this.weatherDataArrayList.size();
    }

    public class SevenDayViewHolder extends RecyclerView.ViewHolder {
        TextView textview_date,textview_temperature;
        ImageView imageview_weather_icon;

        public SevenDayViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_date=itemView.findViewById(R.id.textview_date);
            textview_temperature=itemView.findViewById(R.id.textview_temperature);
            imageview_weather_icon=itemView.findViewById(R.id.imageview_weather_icon);

        }
    }
}
