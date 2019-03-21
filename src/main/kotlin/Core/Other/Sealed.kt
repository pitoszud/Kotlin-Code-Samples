package Core.Other

sealed class Element
class Container(vararg val children: Element): Element()
class Text(val text: String): Element()


fun Element.extractText(): String{
    val sb = StringBuilder()
    fun extractText(e: Element){
        when(e){
            is Text -> sb.append(e.text)
            is Container -> e.children.forEach(::extractText)
        }
    }
    extractText(this)
    return sb.toString()
}

