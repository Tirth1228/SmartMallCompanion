package com.example.smartmallcompanion.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartmallcompanion.R;

public class ParkingFragment extends Fragment {

    private TextView tvFloorTitle, tvAvailable, tvOccupied;
    private LinearLayout parkingSpaces;

    private final String[] floors = {
            "Ground Floor", "First Floor", "Second Floor"
    };

    // Demo data: true means occupied; false means available.
    private final boolean[][] parkingData = {
            {false, true, false, false, true, false,
                    true, false, false, true, false, false},

            {true, false, true, false, false, false,
                    false, true, false, false, true, false},

            {false, false, false, true, false, true,
                    false, false, true, false, false, false}
    };

    public ParkingFragment() {
        // Required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_parking, container, false);

        tvFloorTitle = view.findViewById(R.id.tvParkingFloor);
        tvAvailable = view.findViewById(R.id.tvAvailable);
        tvOccupied = view.findViewById(R.id.tvOccupied);
        parkingSpaces = view.findViewById(R.id.parkingSpaces);

        Button btnGround = view.findViewById(R.id.btnParkingGround);
        Button btnFirst = view.findViewById(R.id.btnParkingFirst);
        Button btnSecond = view.findViewById(R.id.btnParkingSecond);

        btnGround.setOnClickListener(v ->
                showFloor(0, btnGround, btnFirst, btnSecond));

        btnFirst.setOnClickListener(v ->
                showFloor(1, btnGround, btnFirst, btnSecond));

        btnSecond.setOnClickListener(v ->
                showFloor(2, btnGround, btnFirst, btnSecond));

        showFloor(0, btnGround, btnFirst, btnSecond);

        return view;
    }

    private void showFloor(int floorIndex, Button btnGround,
                           Button btnFirst, Button btnSecond) {

        tvFloorTitle.setText(floors[floorIndex]);

        Button[] floorButtons = {btnGround, btnFirst, btnSecond};

        for (int i = 0; i < floorButtons.length; i++) {
            floorButtons[i].setAlpha(i == floorIndex ? 1f : 0.55f);
        }

        parkingSpaces.removeAllViews();

        int available = 0;
        int occupied = 0;

        for (int i = 0; i < parkingData[floorIndex].length; i++) {

            boolean isOccupied = parkingData[floorIndex][i];

            if (isOccupied) {
                occupied++;
            } else {
                available++;
            }

            Button space = new Button(requireContext());
            space.setText("P" + (i + 1) + "\n"
                    + (isOccupied ? "Occupied" : "Available"));

            space.setTextSize(11);
            space.setAllCaps(false);
            space.setTextColor(Color.WHITE);
            space.setGravity(Gravity.CENTER);
            space.setTypeface(null, Typeface.BOLD);

            space.setBackgroundTintList(
                    android.content.res.ColorStateList.valueOf(
                            Color.parseColor(
                                    isOccupied ? "#D9534F" : "#279B68"
                            )
                    )
            );

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(
                            0,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            1f
                    );

            params.setMargins(4, 4, 4, 4);
            space.setLayoutParams(params);

            space.setOnClickListener(v ->
                    android.widget.Toast.makeText(
                            requireContext(),
                            isOccupied
                                    ? "This parking space is occupied."
                                    : "This parking space is available (demo).",
                            android.widget.Toast.LENGTH_SHORT
                    ).show()
            );

            // Add four spaces per row
            if (i % 4 == 0) {
                LinearLayout row = new LinearLayout(requireContext());
                row.setOrientation(LinearLayout.HORIZONTAL);
                parkingSpaces.addView(row);
            }

            LinearLayout row = (LinearLayout)
                    parkingSpaces.getChildAt(parkingSpaces.getChildCount() - 1);

            row.addView(space);
        }

        tvAvailable.setText("Available: " + available);
        tvOccupied.setText("Occupied: " + occupied);
    }
}
