package com.example.e_serve;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class electricianprofiles extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricianprofiles);

        webview = findViewById(R.id.webview);

        // Enable JavaScript
        webview.getSettings().setJavaScriptEnabled(true);

        // Add JavaScriptInterface for potential communication (adjust as needed)
        webview.addJavascriptInterface(new MyJavaScriptInterface(), "Android");

        // Set a WebViewClient to monitor network requests
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                // Inspect request URL and headers here, if needed
                return super.shouldInterceptRequest(view, request);
            }
        });

        // Load your website URL
        webview.loadUrl("https://konkasudheerkumar.github.io/profile.github.io/one.html");
    }

    // Example JavaScriptInterface class (adjust methods as needed)
    private class MyJavaScriptInterface {
        @JavascriptInterface
        public void showCartDetails() {
            // Handle cart view actions from JavaScript here
        }
    }
}
