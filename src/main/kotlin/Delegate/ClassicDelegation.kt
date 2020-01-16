package Delegate

/**
 * Class delegation is a good and more flexible alternative to implementation inheritance.
 * */

interface ClickListener{
    fun onClick(n: Int)
}

class View: ClickListener{
    override fun onClick(n: Int) {
        println("calculating value $n")
    }
}

object ViewObject: ClickListener{
    override fun onClick(n: Int) {
        println("calculating value $n")
    }

}

// Explicit delegation
class SomeView(val delegate: ClickListener): ClickListener{
    override fun onClick(n: Int) {
        delegate.onClick(n)
    }
}


// Implicit-anticipated delegation

class ButtonA(private val delegate: ClickListener): ClickListener by delegate{

    fun printDelagateClassName() {
        println(delegate.javaClass.simpleName)
    }
}


class ButtonB: ClickListener by ViewObject{

    fun printDelagateClassName() {
        println(ViewObject::class.java.simpleName)
    }
}


fun main() {
    val buttonA = ButtonA(View())
    buttonA.onClick(4) // calculating value 4 in MobilePhone
    buttonA.printDelagateClassName()

    println("-----------------------------")

    /*
    * If delegation is done via an object, there is no need to pass the delegate object in the constructor
    * */
    val buttonB = ButtonB()
    buttonB.onClick(5) // calculating value 5 in MobilePhoneObject
    buttonB.printDelagateClassName()



}




