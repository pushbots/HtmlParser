package com.htmlparser

import com.htmlparser.elements.Element
import com.htmlparser.elements.ElementIdentifier
import com.htmlparser.source.Source

class HtmlParser private constructor(
    val source: Source?,
    val callBack: ParserCallbacks?,
    val embeddedStyleEnabled: Boolean?
) {
    data class Builder (
        var source: Source?
    ) {
        private var callback: ParserCallbacks? = null
        private var embeddedStyleEnabled: Boolean = true
        fun setSource(source: Source?) = apply { this.source = source }
        fun setEmbeddedStyleEnabled(embeddedStyleEnabled: Boolean) = apply { this.embeddedStyleEnabled = embeddedStyleEnabled }
        fun setCallback(callback: ParserCallbacks?) = apply {
            this.callback = callback }

        fun build() = HtmlParser(source, callback, embeddedStyleEnabled).also {
            val elements = ArrayList<Element>()
            ElementIdentifier.extractData(elements, source!!.get().body().children(), embeddedStyleEnabled = embeddedStyleEnabled)
            callback!!.onParseFinished(elements)
        }
    }

    interface ParserCallbacks {
        fun onParseFinished(list: List<Element>)
        fun onParseError(exception: Exception)
    }
}