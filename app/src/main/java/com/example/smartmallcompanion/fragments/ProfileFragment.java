
package com.example.smartmallcompanion.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartmallcompanion.R;

public class ProfileFragment extends Fragment {

    private EditText etName, etEmail;
    private TextView tvInitials;

    private SharedPreferences preferences;

    public ProfileFragment() {
        // Required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_profile, container, false);

        etName = view.findViewById(R.id.etProfileName);
        etEmail = view.findViewById(R.id.etProfileEmail);
        tvInitials = view.findViewById(R.id.tvProfileInitials);

        Button btnSave = view.findViewById(R.id.btnSaveProfile);

        preferences = requireContext().getSharedPreferences(
                "SmartMallProfile", Context.MODE_PRIVATE);

        // Load previously saved profile details
        etName.setText(preferences.getString("name", ""));
        etEmail.setText(preferences.getString("email", ""));

        updateInitials();

        btnSave.setOnClickListener(v -> saveProfile());

        etName.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                updateInitials();
            }
        });

        return view;
    }

    private void saveProfile() {

        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if (name.isEmpty()) {
            etName.setError("Enter your name");
            etName.requestFocus();
            return;
        }

        if (email.isEmpty() ||
                !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter a valid email address");
            etEmail.requestFocus();
            return;
        }

        preferences.edit()
                .putString("name", name)
                .putString("email", email)
                .apply();

        updateInitials();

        Toast.makeText(
                requireContext(),
                "Profile saved successfully",
                Toast.LENGTH_SHORT
        ).show();
    }

    private void updateInitials() {

        if (tvInitials == null || etName == null) {
            return;
        }

        String name = etName.getText().toString().trim();

        if (name.isEmpty()) {
            tvInitials.setText("?");
            return;
        }

        String[] words = name.split("\\s+");

        String initials = words[0].substring(0, 1).toUpperCase();

        if (words.length > 1) {
            initials += words[words.length - 1]
                    .substring(0, 1).toUpperCase();
        }

        tvInitials.setText(initials);
    }
}
