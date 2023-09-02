package com.mukbang.map.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import com.mukbang.map.R;

public class webViewActivity extends AppCompatActivity {
    private WebView webView;
    private WebSettings WebSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView = findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient()); // 현재 앱을 나가서 새로운 브라우저를 열지 않도록 함.

        WebSettings = webView.getSettings(); // 웹뷰에서 webSettings를 사용할 수 있도록 함.
        WebSettings.setJavaScriptEnabled(true); //웹뷰에서 javascript를 사용하도록 설정
        WebSettings.setLoadsImagesAutomatically(true); //웹뷰에서 이미지 자동 로드
        WebSettings.setDatabaseEnabled(true); //database storage API 사용 여부
        WebSettings.setAllowContentAccess(true);
        WebSettings.setJavaScriptCanOpenWindowsAutomatically(true); //멀티윈도우 띄우는 것
        WebSettings.setAllowFileAccess(true); //파일 엑세스
        WebSettings.setLoadWithOverviewMode(true); // 메타태그
        WebSettings.setUseWideViewPort(true); //화면 사이즈 맞추기
        WebSettings.setSupportZoom(true); // 화면 줌 사용 여부
        WebSettings.setBuiltInZoomControls(true); //화면 확대 축소 사용 여부
        WebSettings.setDisplayZoomControls(true); //화면 확대 축소시, webview에서 확대/축소 컨트롤 표시 여부
        WebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 브라우저 캐시 사용 재정의 value : LOAD_DEFAULT, LOAD_NORMAL, LOAD_CACHE_ELSE_NETWORK, LOAD_NO_CACHE, or LOAD_CACHE_ONLY

        webView.setWebChromeClient(new WebChromeClient());
        webView.clearCache(true);
        webView.loadUrl(MainActivity.WEBVIEW_URL);
    }
}
