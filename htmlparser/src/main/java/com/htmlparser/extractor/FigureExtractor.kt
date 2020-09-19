package com.htmlparser.extractor

import org.jsoup.nodes.Element

class FigureExtractor(val element: Element) : Extractor<Pair<String, String>>(element) {
    override fun extract(): Pair<String, String> {
        val image = element.getElementsByTag("img")
        val caption = element.getElementsByTag("figcaption")
        return Pair(
            caption.text(),
            image.attr("src")
        )
    }

}