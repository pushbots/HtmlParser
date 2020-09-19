package com.htmlparser.elements

import com.htmlparser.model.AnchorLink
import com.htmlparser.model.DescriptionList

sealed class Element(val type: Int)

data class DescriptionListElement(val descriptionList: List<DescriptionList>) : Element(ElementType.DescriptionList.ordinal)
data class OrderListElement(val html: String, val list: Pair<String, List<String>>) : Element(ElementType.OrderedList.ordinal)
data class ImageElement(val ImageUrl: String) : Element(ElementType.Image.ordinal)
data class ParagraphElement(val paragraph: String) : Element(ElementType.Paragraph.ordinal)
data class UnOrderListElement(val html: String, val list: Pair<String, List<String>>) : Element(ElementType.UnorderedList.ordinal)
data class Heading1Element(val html: String, val text: String) : Element(ElementType.Heading1.ordinal)
data class Heading2Element(val html: String, val text: String) : Element(ElementType.Heading2.ordinal)
data class Heading3Element(val html: String, val text: String) : Element(ElementType.Heading3.ordinal)
data class Heading4Element(val html: String, val text: String) : Element(ElementType.Heading4.ordinal)
data class Heading5Element(val html: String, val text: String) : Element(ElementType.Heading5.ordinal)
data class Heading6Element(val html: String, val text: String) : Element(ElementType.Heading6.ordinal)
data class VideoElement(val videoSourceUrl: String, val videoThumbnailUrl: String = "") : Element(ElementType.Video.ordinal)
data class AudioElement(val audioSourceUrl: String) : Element(ElementType.Audio.ordinal)
data class AnchorLinkElement(val anchorUrl: AnchorLink) : Element(ElementType.AnchorLink.ordinal)
data class BlockQuoteElement(val data: String, val text: String) : Element(ElementType.BlockQuote.ordinal)
data class IFrameElement(val data: String, val url: String) : Element(ElementType.IFrame.ordinal)
data class UnknownElement(val html:String) : Element(ElementType.Unknown.ordinal)
data class FigureElement(val caption: String, val url: String): Element(ElementType.Figure.ordinal)

sealed class Paragraph

data class Body(val bodyText: String) : Paragraph()
data class AnchorLinkInParagraph(val text: String, val url: String) : Paragraph()
data class UnderLine(val text: String) : Paragraph()
data class Bold(val text: String) : Paragraph()
data class Emphasizes(val text: String) : Paragraph()
class Unknown : Paragraph()