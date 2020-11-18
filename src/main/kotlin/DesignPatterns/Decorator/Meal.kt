package DesignPatterns.Decorator

interface Meal {
    fun prepare(): String
    fun price(): Double
}

class Pizza : Meal {
    var description = "Pizza"
    override fun prepare(): String = description

    override fun price(): Double = 0.0
}


abstract class PizzaDecorator(val meal: Meal) : Meal {
    override fun prepare(): String = meal.prepare()
}


class Vegi(meal: Meal) : PizzaDecorator(meal) {
    private val price = 13.50

    override fun prepare(): String {
        return super.prepare() + " + Vegi (${price})"
    }

    override fun price(): Double = meal.price() + price
}

class Meaty(meal: Meal) : PizzaDecorator(meal) {
    private val price = 15.90

    override fun prepare(): String {
        return super.prepare() + " + Meaty (${price})"
    }

    override fun price(): Double = meal.price() + price
}


class Spicy(meal: Meal) : PizzaDecorator(meal) {
    private val price = 15.40

    override fun prepare(): String {
        return super.prepare() + " + Spicy (${price})"
    }

    override fun price(): Double = meal.price() + price
}


fun main() {
    val order = Spicy(Meaty(Vegi(Pizza())))
    println(order.prepare())

    println("\nTotal price: £${order.price()}")

//    Pizza + Vegi (13.5) + Meaty (15.9) + Spicy (15.4)
//    Total price: £44.8
}
