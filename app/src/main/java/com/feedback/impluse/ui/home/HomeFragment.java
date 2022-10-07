package com.feedback.impluse.ui.home;

import static com.feedback.impluse.SplashActivity.deviceid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
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


        if(!isConnected(getContext()))
        {
            new AlertDialog.Builder(getContext()).setMessage("Internet not working!").setTitle("Alert!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }
        else {
            final WebView webview = binding.webview;

//        webview.setInitialScale(3);
//        webview.getSettings().setLoadWithOverviewMode(true);
//        webview.getSettings().setUseWideViewPort(true);

            //   webview.setScrollContainer(false);
//        webview.getSettings().setJavaScriptEnabled(true);


           // webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);


            WebSettings webSettings = webview.getSettings();
            webSettings.setJavaScriptEnabled(true);

            webSettings.setSupportZoom(true);
            webSettings.setBuiltInZoomControls(true);
            webSettings.setDisplayZoomControls(false);

            webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            webview.getSettings().setAppCacheEnabled(true);

            webview.getSettings().setAppCachePath(getContext().getCacheDir().getAbsolutePath());

            Log.e("url", "https://app.askimpulse.com/?token=" + deviceid);


            webview.setWebViewClient(new WebViewClient() {


                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                    super.onReceivedError(view, request, error);
                    //Toast.makeText(getActivity(), "An error Occurred, Try Again!", Toast.LENGTH_LONG).show();

                    Log.e("testweb", error.getDescription() + " " + error.getErrorCode());

                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);

//                callprogress();
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);

//                    webview.loadUrl("javascript:(function() { " +
//                            "var head = document.getElementsByClassName('header')[0].style.display='none'; " +
//                            "})()");

//

                    Log.e("testwebdata", "finish");


                    try {


                        Log.e("testwebdata", "try");
                    } catch (Exception ex) {
                        Log.e("testwebdata", "catch");
                    }

                }

                public boolean shouldOverrideUrlLoading(WebView view, String url) {

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);


//                try
//                {
//
//                    Log.e("testwebdata","overrideurl");
//
//                    webview.loadUrl(url+"");
//                }
//                catch (Exception ex)
//                {
//                    Log.e("testwebdata","catch");
//                }

                    return true;
                }

            });

            webview.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {


                }
            });
            webview.loadUrl("https://app.askimpulse.com/?token=" + deviceid);

        }

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