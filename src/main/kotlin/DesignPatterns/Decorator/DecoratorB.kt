package DesignPatterns.Decorator

interface Meal {
    fun prepare()
}

class Pizza: Meal {
    override fun prepare() {
        print("(Pizza)")
    }
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
}

class Meaty(meal: Meal): PizzaDecorator(meal) {
    override fun prepare() {
        super.prepare()
        print(" + Meaty")
    }
}


class Spicy(meal: Meal): PizzaDecorator(meal) {
    override fun prepare() {
        super.prepare()
        print(" + Spicy")
    }
}


fun main() {
    val order = Spicy(Meaty(Vegi(Pizza())))
    order.prepare()
}
