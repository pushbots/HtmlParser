package com.htmlparser.source

import androidx.annotation.WorkerThread
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

@WorkerThread
class NetworkSource(private val url: String) : Source {
    override fun get(): Document {
        return Jsoup.connect(url).get()
    }
}