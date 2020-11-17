package com.david0926.edcanretrofit.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.david0926.edcanretrofit.R;
import com.david0926.edcanretrofit.databinding.ActivityMainBinding;
import com.david0926.edcanretrofit.ui.food.FoodActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setViewModel(viewModel);

        //set LayoutManager
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(this, 2);
        binding.recyclerMain.setLayoutManager(gridLayoutManager);

        //set Adapter
        MainRecyclerAdapter recyclerAdapter = new MainRecyclerAdapter();
        recyclerAdapter.setOnItemClick(item -> {
            Intent intent = new Intent(MainActivity.this, FoodActivity.class);
            intent.putExtra("food", item);
            startActivity(intent);
        });
        binding.recyclerMain.setAdapter(recyclerAdapter);

        //set Search
        viewModel.search.observe(this, s -> {
            viewModel.page.setValue(1);
            viewModel.load();
        });

        //set SwipeRefresh
        binding.swipeMain.setOnRefreshListener(() -> {
            viewModel.page.setValue(1);
            viewModel.load();
        });
        viewModel.isLoaded.observe(this, isLoaded -> binding.swipeMain.setRefreshing(!isLoaded));

        //set Pagination
        binding.recyclerMain.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!viewModel.isLoaded.getValue()) return;

                int visibleItemCount = gridLayoutManager.getChildCount();
                int totalItemCount = gridLayoutManager.getItemCount();
                int firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= 10) {
                    viewModel.page.setValue(viewModel.page.getValue() + 1);
                    viewModel.load();
                }
            }
        });

        //set Error Observer
        viewModel.error.observe(this, s -> {
            if (s.isEmpty()) return;
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
            Log.d("baam", "onCreate: " + s);
        });

        //load Items
        viewModel.load();
    }
}