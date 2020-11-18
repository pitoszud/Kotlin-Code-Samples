package DesignPatterns.Decorator
/**
 * Decorators:
 * are more flexible than inheritance.
 * allow to dynamically compose behaviour using objects at runtime.
 * should add only a small behaviour (additional skin) to objects
 * should nest small number of objects
 * */
interface Message{
    fun getMessage(): String
}

class TextMessage(val msg: String): Message{

    override fun getMessage(): String {
        return msg
    }

}


// Decorator
class CurlyBracers(val msg: Message): Message {

    override fun getMessage(): String {
        return encode()
    }


    fun encode(): String{
        return "{ \n ${msg.getMessage()} \n}"
    }
}


// Decorator
class SquareBracers(val msg: Message): Message {

    override fun getMessage(): String {
        return encode()
    }


    fun encode(): String{
        return "[ \n ${msg.getMessage()} \n]"
    }
}


fun main() {
    val msg = TextMessage("My message for you")

    val curlyDecorator = CurlyBracers(msg)
    val squareDecorator = SquareBracers(curlyDecorator)
    println(squareDecorator.getMessage())
}