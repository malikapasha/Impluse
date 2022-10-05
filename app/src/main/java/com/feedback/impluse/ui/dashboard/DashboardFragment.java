package com.feedback.impluse.ui.dashboard;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.feedback.impluse.MainActivity;
import com.feedback.impluse.MyWeb;
import com.feedback.impluse.databinding.FragmentDashboardBinding;

import de.hdodenhof.circleimageview.CircleImageView;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView faq = binding.faq;

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyWeb.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("theurl","https://www.impulse.win/shelf/faqs/");
                startActivity(intent);

            }
        });

        final TextView privacy = binding.privacy;

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyWeb.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("theurl","https://www.impulse.win/terms-of-use/");
                startActivity(intent);

            }
        });


        final CircleImageView tiktok = binding.tiktok;

        tiktok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.tiktok.com/@impulse.win/"));
                startActivity(intent);

            }
        });

        final CircleImageView twitter = binding.twitter;

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://twitter.com/impulsedrops/"));
                startActivity(intent);

            }
        });

        final CircleImageView instagram = binding.instagram;

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.instagram.com/impulse.win/"));
                startActivity(intent);

            }
        });






        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}