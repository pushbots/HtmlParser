package com.htmlparser.extractor

import org.jsoup.nodes.Element

class AudioExtractor(val e: Element) : Extractor<String>(e) {
    override fun extract(): String {
        return e.attr("src")
    }
}