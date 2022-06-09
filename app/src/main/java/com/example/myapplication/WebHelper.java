package com.example.myapplication;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;
import java.util.List;


public class WebHelper {
    private WebView webView;
    private String[] urls = {
            "https://forvo.com/languages/ru/",
            "https://bkrs.info"
    };
    public enum Urls{
        forvo_com, translate_google
    }

    public WebHelper(View view,int indexWebView, Urls url) {
        webView = view.findViewById(indexWebView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true); // enable javascript
        webView.loadUrl(urls[url.ordinal()]);
        webView.setVisibility(View.GONE);
    }

    public WebHelper setActionOnPageFinished_FindWord(String word){
        webView.setWebViewClient(new WebViewClient(){
            public void onPageFinished(WebView view , String url){
                loadURL_PasteValueToId("word_search_header",word);
                loadURL_ClickFirstButton();
                setVisible();
            }
        });
        return this;
    }
    public WebHelper setActionOnPageFinished_TranslateWord(String word){
        webView.setWebViewClient(new WebViewClient(){
            public void onPageFinished(WebView view , String url){
                setVisible();
            }
        });
        return this;
    }

    public void loadURL_PasteValueToId(String id, String value){
        webView.loadUrl("javascript:(function(){document.getElementById('"+id+"').value = '"+value+"';})();");
    }
    public void loadURL_ClickFirstButton(){
        webView.loadUrl("javascript:(function(){document.getElementsByTagName(\"button\")[0].click();})();");
    }
    public void setVisible(){
        webView.setVisibility(View.VISIBLE);
    }


}
