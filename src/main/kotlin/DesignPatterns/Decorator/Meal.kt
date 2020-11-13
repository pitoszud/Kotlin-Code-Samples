package DesignPatterns.Decorator

interface Meal {
    fun prepare()
    fun price(): Double
}

class Pizza: Meal {
    override fun prepare() {
        print("(Pizza)")
    }

    override fun price(): Double = 0.0
}


abstract class PizzaDecorator(val meal: Meal): Meal {
    override fun prepare() {
        meal.prepare()
    }
}


class Vegi(meal: Meal): PizzaDecorator(meal) {
    override fun prepare() {
        super.prepare()
        print(" + Vegi")
    }

    override fun price(): Double = meal.price() + 13.50
}

class Meaty(meal: Meal): PizzaDecorator(meal) {
    override fun prepare() {
        super.prepare()
        print(" + Meaty")
    }

    override fun price(): Double = meal.price() + 15.90
}


class Spicy(meal: Meal): PizzaDecorator(meal) {
    override fun prepare() {
        super.prepare()
        print(" + Spicy")
    }

    override fun price(): Double = meal.price() + 15.40
}


fun main() {
    val order = Spicy(Meaty(Vegi(Pizza())))
    order.prepare()

    println("\nTotal price: £${order.price()}")
}
