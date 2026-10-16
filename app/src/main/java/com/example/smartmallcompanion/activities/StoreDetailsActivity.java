
package com.example.smartmallcompanion.activities;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartmallcompanion.R;

public class StoreDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);

        Intent intent = getIntent();

        TextView name = findViewById(R.id.tvDetailName);
        TextView category = findViewById(R.id.tvDetailCategory);
        TextView floor = findViewById(R.id.tvDetailFloor);
        TextView description = findViewById(R.id.tvDetailDescription);
        TextView offer = findViewById(R.id.tvDetailOffer);
        Button back = findViewById(R.id.btnBackToStores);

        name.setText(intent.getStringExtra("name"));
        category.setText(intent.getStringExtra("category"));
        floor.setText(intent.getStringExtra("floor"));
        description.setText(intent.getStringExtra("description"));
        offer.setText(intent.getStringExtra("offer"));

        back.setOnClickListener(v -> finish());
    }
}
