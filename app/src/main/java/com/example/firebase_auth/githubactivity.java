package com.example.firebase_auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class githubactivity extends AppCompatActivity {
  WebView githubpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_githubactivity);
        githubpage=(WebView)findViewById(R.id.gitpage);
        githubpage.getSettings().setJavaScriptEnabled(true);
        githubpage.loadUrl("https://github.com/SaiBalaji22/FireBase_Tutorial_FireBase_Auth");
    }
}
