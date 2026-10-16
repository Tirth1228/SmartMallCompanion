package com.example.smartmallcompanion;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.smartmallcompanion.fragments.ProfileFragment;
import com.example.smartmallcompanion.fragments.ParkingFragment;
import com.example.smartmallcompanion.fragments.ExploreFragment;
import com.example.smartmallcompanion.fragments.MallMapFragment;

public class MainActivity extends AppCompatActivity {

    private View homeContent;
    private View fragmentContainer;
    private TextView selectedNavItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeContent = findViewById(R.id.homeContent);
        fragmentContainer = findViewById(R.id.fragmentContainer);

        setNavigation(R.id.navHome, "Home");
        setNavigation(R.id.navExplore, "Explore");
        setNavigation(R.id.navMap, "Indoor Map");
        setNavigation(R.id.navParking, "Parking");
        setNavigation(R.id.navProfile, "Profile");

        // Home is selected when the app opens
        selectNavigation(findViewById(R.id.navHome));
    }

    private void setNavigation(int id, String name) {
        TextView item = findViewById(id);

        item.setOnClickListener(v -> {

            if (id == R.id.navHome) {
                selectNavigation(item);

                fragmentContainer.setVisibility(View.GONE);
                homeContent.setVisibility(View.VISIBLE);

            } else if (id == R.id.navExplore) {
                selectNavigation(item);
                showFragment(new ExploreFragment());

            } else if (id == R.id.navMap) {
                selectNavigation(item);
                showFragment(new MallMapFragment());

            } else if (id == R.id.navParking) {
                selectNavigation(item);
                showFragment(new ParkingFragment());

            } else if (id == R.id.navProfile) {
            selectNavigation(item);
            showFragment(new ProfileFragment());
        }
        });
    }

    private void selectNavigation(TextView item) {

        // Reset the previously selected item
        if (selectedNavItem != null) {
            selectedNavItem.setBackgroundColor(Color.TRANSPARENT);
            selectedNavItem.setTextColor(Color.parseColor("#475569"));
        }

        // Highlight the newly selected item
        selectedNavItem = item;
        selectedNavItem.setBackgroundResource(
                R.drawable.bg_nav_selected
        );
        selectedNavItem.setTextColor(Color.WHITE);
    }

    private void showFragment(Fragment fragment) {
        homeContent.setVisibility(View.GONE);
        fragmentContainer.setVisibility(View.VISIBLE);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}
