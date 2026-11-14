package de.astro.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/** Zeigt die Astro-Oberflaeche aus dem App-Paket im Vollbild. Geraete werden dort im Reiter Verbinden verbunden. */
public class MainActivity extends Activity {
    private static final String URL = "file:///android_asset/app.html";
    private WebView web;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        web = new WebView(this);
        web.setBackgroundColor(0xFF0A0E16);
        setContentView(web);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setMediaPlaybackRequiresUserGesture(false);
        s.setAllowFileAccess(true);
        s.setAllowUniversalAccessFromFileURLs(true);   // die Oberflaeche liegt im App-Paket und spricht mit dem Programm auf 127.0.0.1
        s.setCacheMode(WebSettings.LOAD_NO_CACHE);
        web.setWebChromeClient(new WebChromeClient());
        web.setWebViewClient(new WebViewClient());
        web.loadUrl(URL);
    }

    @Override
    public void onBackPressed() {
        if (web.canGoBack()) web.goBack();   // sonst nichts: versehentliches Beenden vermeiden
    }

    @Override
    protected void onResume() {
        super.onResume();
        web.onResume();
    }

    @Override
    protected void onPause() {
        web.onPause();
        super.onPause();
    }
}
