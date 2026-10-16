
package com.example.smartmallcompanion.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.content.res.ColorStateList;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmallcompanion.R;
import com.example.smartmallcompanion.adapters.StoreAdapter;
import com.example.smartmallcompanion.models.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ExploreFragment extends Fragment {

    private StoreAdapter adapter;
    private EditText search;

    private Button btnAll, btnFashion, btnElectronics;
    private Button btnGroceries, btnFood, btnAccessories;

    private final List<Store> allStores = new ArrayList<>();
    private final List<Store> visibleStores = new ArrayList<>();

    private String selectedCategory = "All";

    public ExploreFragment() {
        // Required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_explore, container, false);

        RecyclerView recyclerView =
                view.findViewById(R.id.rvStores);

        search = view.findViewById(R.id.etStoreSearch);

        // Initialize category buttons
        btnAll = view.findViewById(R.id.btnAll);
        btnFashion = view.findViewById(R.id.btnFashion);
        btnElectronics = view.findViewById(R.id.btnElectronics);
        btnGroceries = view.findViewById(R.id.btnGroceries);
        btnFood = view.findViewById(R.id.btnFood);
        btnAccessories = view.findViewById(R.id.btnAccessories);

        // Sample mall stores
        allStores.clear();

        allStores.add(new Store(
                "Fashion Hub",
                "Clothing",
                "Ground Floor"));

        allStores.add(new Store(
                "Tech World",
                "Electronics",
                "First Floor"));

        allStores.add(new Store(
                "Daily Mart",
                "Groceries",
                "Ground Floor"));

        allStores.add(new Store(
                "Cafe Corner",
                "Food and Drinks",
                "First Floor"));

        allStores.add(new Store(
                "Style Studio",
                "Accessories",
                "Second Floor"));

        // Initialize RecyclerView
        visibleStores.clear();
        visibleStores.addAll(allStores);

        adapter = new StoreAdapter(
                requireContext(), visibleStores);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(requireContext()));

        recyclerView.setAdapter(adapter);

        // Search functionality
        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(
                    CharSequence s, int start,
                    int count, int after) {
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start,
                    int before, int count) {

                filterStores();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Category button listeners
        btnAll.setOnClickListener(
                v -> selectCategory("All"));

        btnFashion.setOnClickListener(
                v -> selectCategory("Clothing"));

        btnElectronics.setOnClickListener(
                v -> selectCategory("Electronics"));

        btnGroceries.setOnClickListener(
                v -> selectCategory("Groceries"));

        btnFood.setOnClickListener(
                v -> selectCategory("Food and Drinks"));

        btnAccessories.setOnClickListener(
                v -> selectCategory("Accessories"));

        // Default selection
        selectCategory("All");

        return view;
    }

    // Select a category
    private void selectCategory(String category) {
        selectedCategory = category;

        updateCategoryButtonStyles();
        filterStores();
    }

    // Highlight the selected category
    private void updateCategoryButtonStyles() {

        Button[] buttons = {
                btnAll,
                btnFashion,
                btnElectronics,
                btnGroceries,
                btnFood,
                btnAccessories
        };

        String[] categories = {
                "All",
                "Clothing",
                "Electronics",
                "Groceries",
                "Food and Drinks",
                "Accessories"
        };

        for (int i = 0; i < buttons.length; i++) {

            boolean selected =
                    categories[i].equals(selectedCategory);

            buttons[i].setBackgroundTintList(
                    ColorStateList.valueOf(
                            Color.parseColor(
                                    selected
                                            ? "#0284C7"
                                            : "#E0F2FE")));

            buttons[i].setTextColor(
                    Color.parseColor(
                            selected
                                    ? "#FFFFFF"
                                    : "#0F172A"));
        }
    }

    // Filter stores by search text and selected category
    private void filterStores() {

        visibleStores.clear();

        String query = search.getText()
                .toString()
                .toLowerCase(Locale.ROOT)
                .trim();

        for (Store store : allStores) {

            boolean matchesSearch =
                    store.getName()
                            .toLowerCase(Locale.ROOT)
                            .contains(query)
                            || store.getCategory()
                            .toLowerCase(Locale.ROOT)
                            .contains(query);

            boolean matchesCategory =
                    selectedCategory.equals("All")
                            || store.getCategory()
                            .equalsIgnoreCase(selectedCategory);

            if (matchesSearch && matchesCategory) {
                visibleStores.add(store);
            }
        }

        adapter.notifyDataSetChanged();
    }
}
