package com.david0926.edcanretrofit.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

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

        binding.recyclerMain.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        MainRecyclerAdapter recyclerAdapter = new MainRecyclerAdapter();
        recyclerAdapter.setOnItemClick(item -> {
            Intent intent = new Intent(MainActivity.this, FoodActivity.class);
            intent.putExtra("food", item);
            startActivity(intent);
        });

        binding.recyclerMain.setAdapter(recyclerAdapter);

        viewModel.refreshList();
    }
}