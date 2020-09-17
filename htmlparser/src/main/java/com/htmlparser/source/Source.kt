package com.htmlparser.source

import org.jsoup.nodes.Document

interface Source {
    fun get(): Document
}