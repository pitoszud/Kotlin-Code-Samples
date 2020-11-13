package DesignPatterns.Decorator



abstract class Pasta {
    var description = "Unknown Pasta"
    open fun prepare(): String = description

    abstract fun price(): Double
}


abstract class PastaDecorator: Pasta() {
    abstract override fun prepare(): String
}

class ServiceCharge: Pasta(){
    private val price = 2.5
    override fun prepare() = "Service charge (${price})"

    override fun price(): Double = 2.5
}


class Spagetti(val pasta: Pasta): PastaDecorator() {
    private val price = 13.50
    override fun prepare() = pasta.prepare() + " + Spagetti (${price})"

    override fun price(): Double = pasta.price() + price
}

class Lasagne(val pasta: Pasta): PastaDecorator() {
    private val price = 15.90
    override fun prepare() = pasta.prepare() + " + Lasagne (${price})"

    override fun price(): Double = pasta.price() + 15.90
}


class Carbonara(val pasta: Pasta): PastaDecorator() {
    private val price = 15.40
    override fun prepare() = pasta.prepare() + " + Carbonara (${price})"

    override fun price(): Double = pasta.price() + 15.40
}


fun main() {
    var pasta: Pasta = ServiceCharge()
    pasta = Spagetti(pasta)
    pasta = Lasagne(pasta)
    pasta = Carbonara(pasta)

    println(pasta.prepare())
    println("\nTotal price: £${pasta.price()}")
    
}