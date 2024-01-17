package com.example.e_serve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class profileactivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileactivity);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_profile);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent intent = null;
            if (item.getItemId() == R.id.bottom_home) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
            } else if (item.getItemId() == R.id.bottom_search) {
                intent = new Intent(getApplicationContext(), searchactivity.class);
            } else if (item.getItemId() == R.id.bottom_settings) {
                intent = new Intent(getApplicationContext(), settingsactivity.class);
            }

            if (intent != null) {
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }

            return false;
        });

        webView = findViewById(R.id.webview);

        // Enable JavaScript
        webView.getSettings().setJavaScriptEnabled(true);

        // Add JavaScriptInterface for potential communication (adjust as needed)
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "Android");

        // Set a WebViewClient to monitor network requests
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                // Inspect request URL and headers here, if needed
                return super.shouldInterceptRequest(view, request);
            }
        });

        // Load your website URL
        webView.loadUrl("https://konkasudheerkumar.github.io/edit.github.io/profill.html");
    }

    // Example JavaScriptInterface class (adjust methods as needed)
    private class MyJavaScriptInterface {
        @JavascriptInterface
        public void showCartDetails() {
            // Handle cart view actions from JavaScript here
        }
    }
}
