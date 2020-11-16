package com.david0926.edcanretrofit.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.david0926.edcanretrofit.data.model.Food;
import com.david0926.edcanretrofit.databinding.RowFoodBinding;

import java.util.ArrayList;
import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainRecyclerHolder> {

    private List<Food> items = new ArrayList<>();

    public void setItems(List<Food> items) {
        this.items = items;
    }

    public interface OnItemClick {
        void itemClick(Food item);
    }

    private OnItemClick onItemClick = position -> {
    };

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public class MainRecyclerHolder extends RecyclerView.ViewHolder {

        private RowFoodBinding binding;

        public MainRecyclerHolder(@NonNull RowFoodBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public MainRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainRecyclerHolder(RowFoodBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerHolder holder, int position) {
        Food item = items.get(position);
        holder.binding.setFood(item);
        holder.binding.getRoot().setOnClickListener(v -> onItemClick.itemClick(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
