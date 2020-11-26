package com.htmlparser.extractor

import org.jsoup.nodes.Element
import com.htmlparser.model.Table

class TableExtractor(val element: Element) : Extractor<Table>(element) {
    override fun extract(): Table {
        val tableList = ArrayList<List<String>>()
        for (ele in element.allElements) {
            if (ele.tagName() == "tr") {
                val list = ArrayList<String>()
                for (child in ele.children()) {
                    addToList(child, list)
                }
                tableList.add(list)
                continue
            }

        }
        return Table(tableList)
    }

    private fun addToList(
        element: Element,
        list: ArrayList<String>
    ) {
        if (element.hasAttr("th") || element.tagName() == "td" ||
            element.hasClass("th") || element.tagName() == "tr" || element.tagName() == "th"
        ) {
            list.add(element.text())
        }
    }
}