package com.htmlparser.source

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class StringSource(private val html: String) : Source {
    override fun get(): Document {
        return Jsoup.parse(html)
    }
}