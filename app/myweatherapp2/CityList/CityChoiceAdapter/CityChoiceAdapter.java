package com.example.myweatherapp2.CityList.CityChoiceAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherapp2.R;
import com.example.myweatherapp2.db.DBhelper;

import java.util.ArrayList;
import java.util.Collections;

public class CityChoiceAdapter extends RecyclerView.Adapter<CityChoiceAdapter.CityChoiceViewHolder> implements ItemTouchHelper {
    ArrayList<CityChoiceModel> CityChoiceList;
    Context context;
    private DBhelper dBhelper;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public CityChoiceAdapter(Context context, ArrayList<CityChoiceModel> cityChoiceList) {
        this.CityChoiceList = cityChoiceList;
        this.context = context;
        this.dBhelper = new DBhelper(context);
    }

    @NonNull
    @Override
    public CityChoiceAdapter.CityChoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.choice_rv, parent, false);
        return new CityChoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityChoiceAdapter.CityChoiceViewHolder holder, int position) {
        holder.textView_city.setText(CityChoiceList.get(position).getCity());
        holder.textView_weather_feel.setText(CityChoiceList.get(position).getFeelTemp_weather());
        holder.textView_temp.setText(CityChoiceList.get(position).getTemp());
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.CityChoiceList.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(CityChoiceList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDissmiss(int position) {
        int cityId = CityChoiceList.get(position).getId();
        dBhelper.deleteCityById(cityId); // 删除数据库中的记录
        CityChoiceList.remove(position);
        notifyItemRemoved(position);
    }

    public class CityChoiceViewHolder extends RecyclerView.ViewHolder {
        TextView textView_city, textView_weather_feel, textView_temp;

        public CityChoiceViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_city = itemView.findViewById(R.id.textView_city);
            textView_weather_feel = itemView.findViewById(R.id.textView_weather_feel);
            textView_temp = itemView.findViewById(R.id.textView_temp);
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}