package com.example.myweatherapp2.CityList.CityChoiceAdapter;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherapp2.CityList.CityChoiceAdapter.CityChoiceAdapter;
//用于处理删除以及拖拽

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private CityChoiceAdapter mAdapter;

    public SimpleItemTouchHelperCallback(CityChoiceAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN; // 上下拖拽
        int swipeFlags = ItemTouchHelper.LEFT;// 左滑删除
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDissmiss(viewHolder.getAdapterPosition());
    }
}
