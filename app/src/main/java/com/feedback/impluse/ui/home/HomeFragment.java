package com.feedback.impluse.ui.home;

import static com.feedback.impluse.SplashActivity.deviceid;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.feedback.impluse.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final WebView webview = binding.webview;

        webview.setScrollContainer(false);
        webview.getSettings().setJavaScriptEnabled(true);


        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        webview.loadUrl("https://beta-app.askimpulse.com/?token="+deviceid);


        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Log.e("url","https://beta-app.askimpulse.com/?token="+deviceid);

        webview.setWebViewClient(new WebViewClient()
        {


            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Toast.makeText(getActivity(), "An error Occurred, Try Again!" , Toast.LENGTH_LONG).show();

                Log.e("testweb","error");

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

//                callprogress();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                webview.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('header')[0].style.display='none'; " +
                        "})()");

//                webview.loadUrl("javascript:(function() { " +
//                        "var head = document.getElementsByTagName('header')[0];"
//                        + "head.parentNode.removeChild(head);" +
//                        "})()");
//
//                webview.loadUrl("javascript:(function() { " +
//                        "var head = document.getElementsByTagName('footer')[0];"
//                        + "head.parentNode.removeChild(head);" +
//                        "})()");

                Log.e("testwebdata","finish");


                try
                {


                    Log.e("testwebdata","try");
                }
                catch (Exception ex)
                {
                    Log.e("testwebdata","catch");
                }

            }

            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {

                try
                {

                    Log.e("testwebdata","overrideurl");

                    webview.loadUrl(url+"");
                }
                catch (Exception ex)
                {
                    Log.e("testwebdata","catch");
                }

                return true;
            }

        });


        return root;
    }

    public static boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != cm) {
            NetworkInfo info = cm.getActiveNetworkInfo();

            return (info != null && info.isConnected());
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}