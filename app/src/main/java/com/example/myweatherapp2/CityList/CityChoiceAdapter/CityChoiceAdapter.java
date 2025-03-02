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

//初始化函数,传入上下文以及城市
    public CityChoiceAdapter(Context context, ArrayList<CityChoiceModel> cityChoiceList) {
        this.CityChoiceList = cityChoiceList;
        this.context = context;
        this.dBhelper = new DBhelper(context);
    }
//创建viewholder实例
    @NonNull
    @Override
    public CityChoiceAdapter.CityChoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.choice_rv, parent, false);
        return new CityChoiceViewHolder(view);
    }
//绑定数据,以及点击事件
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
//获取数量
    @Override
    public int getItemCount() {
        return this.CityChoiceList.size();
    }
//视图的移动
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(CityChoiceList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }
//视图的删除
    @Override
    public void onItemDissmiss(int position) {
        int cityId = CityChoiceList.get(position).getId();
        dBhelper.deleteCityById(cityId); // 删除数据库中的记录
        CityChoiceList.remove(position);
        notifyItemRemoved(position);
    }
//绑定控件
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