package com.example.sowmik.offline3;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class PurchaseTicketActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //remove title bar

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);



//        setContentView(R.layout.activity_purchase_ticket);
//
//        webView = findViewById(R.id.webViewId);
//
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//
//        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl("https://www.esheba.cnsbd.com/");

        try {

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.esheba.cnsbd.com/"));
            startActivity(browserIntent);

        } catch (ActivityNotFoundException e) {

            Toast.makeText(this, "No application can handle this request."
                    + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }


    }

//
//    @Override
//    public void onBackPressed() {
//
//        if(webView.canGoBack())
//        {
//            webView.goBack();
//        }
//        else
//        {
//            super.onBackPressed();
//        }
//    }

//    private static final String HTTPS = "https://";
//    private static final String HTTP = "http://";
//
//    public static void openBrowser(final Context context, String url) {
//
//        if (!url.startsWith(HTTP) && !url.startsWith(HTTPS)) {
//            url = HTTP + url;
//        }
//
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//        context.startActivity(Intent.createChooser(intent, "Choose browser"));// Choose browser is arbitrary :)
//
//    }




}
