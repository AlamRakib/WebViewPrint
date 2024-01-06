package com.example.webviewprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        floatingActionButton = findViewById(R.id.btnPrint);

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("https://www.google.com");

        floatingActionButton.setOnClickListener(v -> {
            createWebPrint(webView);
        });

    }



    private void createWebPrint(WebView webView){
        PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
        PrintDocumentAdapter printDocumentAdapter = webView.createPrintDocumentAdapter();
        String jobName = getString(R.string.app_name) + "Hello";
        printManager.print(jobName,printDocumentAdapter, new PrintAttributes.Builder().build());
    }


}