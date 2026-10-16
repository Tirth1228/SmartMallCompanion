package com.example.smartmallcompanion.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartmallcompanion.R;

public class MallMapFragment extends Fragment {

    private TextView tvFloorTitle;
    private Button btnGround, btnFirst, btnSecond;

    public MallMapFragment() {
        // Required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_mall_map, container, false);

        tvFloorTitle = view.findViewById(R.id.tvFloorTitle);

        btnGround = view.findViewById(R.id.btnGround);
        btnFirst = view.findViewById(R.id.btnFirst);
        btnSecond = view.findViewById(R.id.btnSecond);

        btnGround.setOnClickListener(v ->
                showFloor(view, "Ground Floor"));

        btnFirst.setOnClickListener(v ->
                showFloor(view, "First Floor"));

        btnSecond.setOnClickListener(v ->
                showFloor(view, "Second Floor"));

        // Show the ground floor initially
        showFloor(view, "Ground Floor");

        // Clickable sample store locations
        setStoreClick(view, R.id.mapFashion, "Fashion Hub");
        setStoreClick(view, R.id.mapDailyMart, "Daily Mart");
        setStoreClick(view, R.id.mapTech, "Tech World");
        setStoreClick(view, R.id.mapCafe, "Cafe Corner");
        setStoreClick(view, R.id.mapStyle, "Style Studio");

        return view;
    }

    private void showFloor(View currentView, String floor) {
        View root = currentView != null ? currentView : getView();
        if (root == null) {
            return;
        }

        tvFloorTitle.setText(floor);

        btnGround.setAlpha(
                floor.equals("Ground Floor") ? 1.0f : 0.55f);
        btnFirst.setAlpha(
                floor.equals("First Floor") ? 1.0f : 0.55f);
        btnSecond.setAlpha(
                floor.equals("Second Floor") ? 1.0f : 0.55f);

        // Show only the stores on the selected floor
        root.findViewById(R.id.mapFashion).setVisibility(
                floor.equals("Ground Floor") ? View.VISIBLE : View.GONE);
        root.findViewById(R.id.mapDailyMart).setVisibility(
                floor.equals("Ground Floor") ? View.VISIBLE : View.GONE);
        root.findViewById(R.id.mapTech).setVisibility(
                floor.equals("First Floor") ? View.VISIBLE : View.GONE);
        root.findViewById(R.id.mapCafe).setVisibility(
                floor.equals("First Floor") ? View.VISIBLE : View.GONE);
        root.findViewById(R.id.mapStyle).setVisibility(
                floor.equals("Second Floor") ? View.VISIBLE : View.GONE);
    }

    private void setStoreClick(View root, int id, String storeName) {
        root.findViewById(id).setOnClickListener(v ->
                Toast.makeText(requireContext(),
                        storeName, Toast.LENGTH_SHORT).show());
    }
}
