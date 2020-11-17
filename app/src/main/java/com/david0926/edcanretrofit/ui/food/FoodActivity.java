package com.david0926.edcanretrofit.ui.food;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.david0926.edcanretrofit.R;
import com.david0926.edcanretrofit.data.model.Food;
import com.david0926.edcanretrofit.databinding.ActivityFoodBinding;

public class FoodActivity extends AppCompatActivity {

    private ActivityFoodBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_food);
        binding.setLifecycleOwner(this);

        binding.setFood((Food) getIntent().getSerializableExtra("food"));

        binding.toolbarFood.setNavigationOnClickListener(v -> finish());
    }
}