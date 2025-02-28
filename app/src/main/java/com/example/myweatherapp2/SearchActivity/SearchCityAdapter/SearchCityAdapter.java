package com.example.myweatherapp2.SearchActivity.SearchCityAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherapp2.R;

import java.util.ArrayList;

public class SearchCityAdapter extends RecyclerView.Adapter<SearchCityAdapter.SearchCityHolder> {
    Context context;
    ArrayList<SearchCityData> SearchCityDataArrayList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // 设置点击事件监听器的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener; // 修正：使用 listener
    }

    public SearchCityAdapter(Context context, ArrayList<SearchCityData> searchCityDataArrayList) {
        this.context = context;
        SearchCityDataArrayList = searchCityDataArrayList;
    }

    @NonNull
    @Override
    public SearchCityAdapter.SearchCityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.search_city_rv, parent, false);
        return new SearchCityAdapter.SearchCityHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchCityAdapter.SearchCityHolder holder, int position) {
        holder.rv_city_locatian.setText(SearchCityDataArrayList.get(position).getLocation());
        if (onItemClickListener != null) {
            final int finalPosition = position; // 将 position 赋值给 final 变量
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(finalPosition);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.SearchCityDataArrayList.size();
    }

    public class SearchCityHolder extends RecyclerView.ViewHolder {
        TextView rv_city_locatian;

        public SearchCityHolder(@NonNull View itemView) {
            super(itemView);
            rv_city_locatian = itemView.findViewById(R.id.rv_city_locatian);
        }
    }
}
