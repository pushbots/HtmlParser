package com.htmlparser.adapter

import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }


}

@BindingAdapter("loadHtmlText")
fun loadHtml(textView: TextView, html: String?) {
    if (!html.isNullOrEmpty()) {
        textView.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }
}

@BindingAdapter("loadWebview")
fun loadWebview(webView: WebView, content: String?) {
    if (!content.isNullOrEmpty()) {
        webView.settings.javaScriptEnabled = true
        webView.settings.useWideViewPort = false
        webView.settings.setSupportZoom(false)
        webView.settings.builtInZoomControls = false
        webView.settings.displayZoomControls = false
        webView.isNestedScrollingEnabled = false
        webView.isHorizontalScrollBarEnabled = false
        webView.isVerticalScrollBarEnabled = false
        webView.loadDataWithBaseURL(
            null,
            getHtml(content),
            "text/html",
            "utf-8",
            null
        )
    }
}


fun getHtml(body: String): String {
    return """
            <div id="fb-root"></div>
            <script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v8.0" nonce="tvG4P5Om"></script>
            <script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
            <script async src="//www.instagram.com/embed.js"></script>
            <script async defer src="https://platform.instagram.com/en_US/embeds.js">
            <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no'>
            <html>
            
            <head>
                <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no'>
                <script async src="//www.instagram.com/embed.js"></script>
                <script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
                <script async defer src="https://platform.instagram.com/en_US/embeds.js">
                <div id="fb-root"></div>
                <script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v8.0" nonce="tvG4P5Om"></script>
                
                <style>
                
                body { width: 100%; max-width: 100%; margin: 8px; height: auto;}
                iframe { width: 100%; max-width: 100%; height: auto;}

                </style>
            </head>
            
            <body>
             $body
            </body>
           </html>
         
        """.trimIndent()
}