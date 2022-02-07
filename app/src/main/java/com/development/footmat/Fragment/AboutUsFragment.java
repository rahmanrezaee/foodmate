package com.development.footmat.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import me.anwarshahriar.calligrapher.Calligrapher;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.development.footmat.databinding.FragmentAboutUsBinding;

public class AboutUsFragment extends Fragment {
    private FragmentAboutUsBinding binding;

    public static AboutUsFragment newInstance() {
        return new AboutUsFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAboutUsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        getActivity().setTitle("your name");

        Typeface face = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/font.ttf");

        binding.descriptionAboutus.setTypeface(face);
        binding.titleAboutUs.setTypeface(face);
        binding.versionAboutus.setTypeface(face);


//        final TextView textView = binding.textAboutus;
//        aboutUsViewMode.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}