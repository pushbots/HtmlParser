package com.htmlparser.elements

import android.util.Log
import com.htmlparser.extractor.*
import com.htmlparser.model.AnchorLink
import com.htmlparser.model.DescriptionList
import org.jsoup.nodes.Element
import org.jsoup.nodes.TextNode
import org.jsoup.select.Elements

class ElementIdentifier(private val element: Element) {
    fun identify(): ElementType = when (element.tagName()) {
        "h1" -> ElementType.Heading1
        "h2" -> ElementType.Heading2
        "h3" -> ElementType.Heading3
        "h4" -> ElementType.Heading4
        "h5" -> ElementType.Heading5
        "h6" -> ElementType.Heading6
        "blockquote" -> ElementType.BlockQuote
        "p" -> ElementType.Paragraph
        "iframe" -> ElementType.IFrame
        "a" -> ElementType.AnchorLink
        "img" -> ElementType.Image
        "video" -> ElementType.Video
        "audio" -> ElementType.Audio
        "ol" -> ElementType.OrderedList
        "ul" -> ElementType.UnorderedList
        "dl" -> ElementType.DescriptionList
        "div" -> ElementType.Div
        "section" -> ElementType.Section
        "figure" -> ElementType.Figure
        "br" -> ElementType.Br
        "strong" -> ElementType.Strong
        "span" -> ElementType.Span
        "b" -> ElementType.B
        "table" -> ElementType.Table
        else -> ElementType.Unknown
    }


    companion object {
        @JvmStatic
        fun extractData(
            elementList: MutableList<com.htmlparser.elements.Element>,
            elements: Elements, wholeData: String = ""
        ) {
            var wholeData = wholeData
            elements.forEach {
                Log.d("Elements", "extractData: $wholeData")
                when (ElementIdentifier(it).identify()) {
                    ElementType.Image -> {
                        val imageUrl = ImageExtractor(it).extract()
                        elementList.add(ImageElement(imageUrl))
                    }
                    ElementType.Heading1 -> {
                        val heading = HeadingExtractor(it).extract()
                        elementList.add(Heading1Element(it.toString(), heading))

                    }
                    ElementType.Heading2 -> {
                        val heading = HeadingExtractor(it).extract()
                        elementList.add(Heading2Element(it.toString(), heading))

                    }
                    ElementType.Heading3 -> {
                        val heading = HeadingExtractor(it).extract()
                        elementList.add(Heading3Element(it.toString(), heading))

                    }
                    ElementType.Heading4 -> {
                        val heading = HeadingExtractor(it).extract()
                        elementList.add(Heading4Element(it.toString(), heading))

                    }
                    ElementType.Heading5 -> {
                        val heading = HeadingExtractor(it).extract()
                        elementList.add(Heading5Element(it.toString(), heading))

                    }
                    ElementType.Heading6 -> {
                        val heading = HeadingExtractor(it).extract()
                        elementList.add(Heading6Element(it.toString(), heading))

                    }
                    ElementType.UnorderedList -> {
                        val listExtractor = ListExtractor(it).extract()
                        elementList.add(UnOrderListElement(it.toString(), listExtractor))
                    }
                    ElementType.OrderedList -> {
                        val listExtractor = ListExtractor(it).extract()
                        elementList.add(OrderListElement(it.toString(), listExtractor))
                    }
                    ElementType.Video -> {
                        val videoUrl = VideoExtractor(it).extract()
                        elementList.add(VideoElement(videoUrl))
                    }
                    ElementType.AnchorLink -> {
                        if (it.getElementsByTag("img").isNotEmpty()) {
                            extractData(elementList, it.children(), wholeData)
                        } else {
                            val anchorLink = AnchorLinkExtractor(it).extract()
                            elementList.add(
                                AnchorLinkElement(
                                    AnchorLink(
                                        anchorLink.first,
                                        anchorLink.second
                                    )
                                )
                            )
                        }
                    }
                    ElementType.DescriptionList -> {
                        val extract = DescriptionListExtractor(it).extract()
                        val descriptionList = mutableListOf<DescriptionList>()
                        extract.forEach {
                            descriptionList.add(DescriptionList(it.first, it.second))
                        }
                        val descriptionListElement = DescriptionListElement(descriptionList)
                        elementList.add(descriptionListElement)
                    }
                    ElementType.Div -> {
                        val children = it.children()
                        if (children.size > 0 &&
                            it.getElementsByClass("fb-post").isNotEmpty()
                        ) {
                            elementList.add(IFrameElement(it.toString(), it.toString()))
                        } else {
                            if (it.childrenSize() <= 0 && it.hasText()) elementList.add(ParagraphElement(it.toString()))
                            extractData(elementList, it.children(), wholeData)
                            // if has text need to be extracted!
                            // todo find a better approach
//                            if (it.hasText()) {
////                                val anchors = it.getElementsByTag("a")
////                                var divText = it.text()
////
////                                anchors.forEach {
////                                    val anchorLink = AnchorLinkExtractor(it).extract()
////                                    divText = divText.replace(anchorLink.first, it.toString(), true)
////                                }
////
////                                elementList.forEach {
////                                    if (it is ParagraphElement)
////                                        if (divText.contains(it.paragraph))
////                                            return
////                                }
////                                if (anchors.size > 0)
////                                    elementList.add(ParagraphElement(divText))
//                                if (!wholeData.contains(it.text(), true))
//                                    elementList.add(ParagraphElement(it.toString()))
//                                wholeData += it.toString()
//                            }
                        }

                    }
                    ElementType.Paragraph -> {
                        val children = it.children()
                        if (children.size > 0 &&
                            it.getElementsByTag("img").isNotEmpty() ||
                            it.getElementsByTag("audio").isNotEmpty() ||
                            it.getElementsByTag("video").isNotEmpty() ||
                            it.getElementsByTag("iframe").isNotEmpty() ||
                            it.getElementsByTag("blockquote").isNotEmpty()
                        ) {
                            extractData(elementList, children)
                            // sometimes <p still has some texts to get...
                            if (it.hasText()) elementList.add(ParagraphElement(it.text()))
                        } else {
                            elementList.add(ParagraphElement(it.toString()))
                        }
                    }
                    ElementType.BlockQuote -> {
                        elementList.add(BlockQuoteElement(it.toString(), it.text()))
                    }

                    ElementType.IFrame -> {
                        val iframe = IFrameExtractor(it).extract()
                        elementList.add(IFrameElement(it.toString(), iframe))
                    }
                    ElementType.Audio -> {
                        val audio = AudioExtractor(it).extract()
                        elementList.add(AudioElement(audio))
                    }
                    ElementType.Unknown -> {
                        val children = it.children()
                        if (children.size > 0)
                            extractData(elementList, children, wholeData)
                        elementList.add(UnknownElement(it.toString()))
                    }

                    ElementType.Section -> extractData(elementList, it.children(), wholeData)

                    ElementType.Figure -> {
                        if (it.getElementsByClass("wp-block-embed-youtube").isNotEmpty()) {
                            elementList.add(IFrameElement(it.toString(), it.toString()))
                        } else {
                            val figure = FigureExtractor(it).extract()
                            elementList.add(FigureElement(figure.first, figure.second))
                        }
                    }

                    ElementType.Br -> {
                        // bind it as Paragraph for now
                        if (it.hasText())
                            elementList.add(ParagraphElement(it.toString()))
                    }

                    ElementType.Table -> {
                        elementList.add(TableElement(it.toString(), TableExtractor(it).extract()))
                    }

                    else -> {
                        elementList.add(UnknownElement(it.toString()))
                    }
                }
            }
            elementList.map { it::class.java.simpleName }
        }
    }
}