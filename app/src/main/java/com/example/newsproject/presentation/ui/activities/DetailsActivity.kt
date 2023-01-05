package com.example.newsproject.presentation.ui.activities

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.newsproject.R
import com.example.newsproject.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private val binding: ActivityDetailsBinding by lazy {
        ActivityDetailsBinding.inflate(layoutInflater)
    }

    private lateinit var newsUrl: String

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        parseIntent()
        binding.apply {
            webView.webViewClient = MyWebClient()
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(newsUrl)
        }
    }

    inner class MyWebClient : WebViewClient() {
        @SuppressLint("ObsoleteSdkInt")
        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            view.loadUrl(request.url.toString())
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    private fun parseIntent() {
        newsUrl = intent.getStringExtra(NEWS_URL)!!
    }

    companion object {
        private const val NEWS_URL = "opened_news_url"

        fun launchDetailsActivity(context: Context, newsUrl: String): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(NEWS_URL, newsUrl)
            return intent
        }
    }
}