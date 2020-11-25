package com.htmlparser.extractor

import org.jsoup.nodes.Element
import com.htmlparser.model.Table

class TableExtractor(val element: Element) : Extractor<Table>(element) {
    override fun extract(): Table {
        val tableList = ArrayList<List<String>>()
        for (allElement in element.children()) {
            val list = ArrayList<String>()
            for (element in allElement.children()) {
                // go in deep one more level if it's built with <thead & <tbody
                if (element.childrenSize() > 1) {
                    for (ele in element.children()) {
                        addToList(element, list)
                    }
                } else {
                    addToList(element, list)
                }
            }
            tableList.add(list)
        }
        return Table(tableList)
    }

    private fun addToList(
        element: Element,
        list: ArrayList<String>
    ) {
        if (element.hasAttr("th") || element.hasAttr("td") ||
            element.hasClass("th") || element.tagName() == "tr" || element.tagName() == "th"
        ) {
            list.add(element.text())
        }
    }
}