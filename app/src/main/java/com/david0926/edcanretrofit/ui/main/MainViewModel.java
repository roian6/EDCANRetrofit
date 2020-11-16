package com.david0926.edcanretrofit.ui.main;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.david0926.edcanretrofit.data.model.Food;
import com.david0926.edcanretrofit.data.repository.MainRepository;

public class MainViewModel extends ViewModel {

    public MutableLiveData<Boolean> isLoaded = new MutableLiveData<>(false);
    public ObservableArrayList<Food> foodList = new ObservableArrayList<>();

    public MutableLiveData<String> search = new MutableLiveData<>("");
    public MutableLiveData<Integer> page = new MutableLiveData<>(1);

    public MutableLiveData<String> error = new MutableLiveData<>("");

    public void refreshList() {
        isLoaded.setValue(false);
        MainRepository.getFoods(search.getValue(), page.getValue(), response -> {
            isLoaded.setValue(true);
            if (response == null) {
                error.setValue("null response");
                return;
            }
            if (page.getValue() == 1) foodList.clear();
            foodList.addAll(response.getList());
        }, errorBody -> {
            isLoaded.setValue(true);
            if (errorBody == null) return;
            error.setValue(errorBody.getResultCode() + ": " + errorBody.getResultMessage());
        }, t -> {
            isLoaded.setValue(true);
            error.setValue(t.getLocalizedMessage());
            t.printStackTrace();
        });
    }

    @BindingAdapter("bindFoodItems")
    public static void bindFoodItems(RecyclerView rcv, ObservableArrayList<Food> list) {
        MainRecyclerAdapter adapter = (MainRecyclerAdapter) rcv.getAdapter();
        if (adapter != null) adapter.setItems(list);
    }
}
