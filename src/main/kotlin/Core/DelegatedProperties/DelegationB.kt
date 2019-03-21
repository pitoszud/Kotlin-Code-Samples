package Core.DelegatedProperties

interface Vehicle {
    fun go(): String
}

class CarImpl(private val where: String): Vehicle {
    override fun go() = "is going to $where"
}

class AirplaneImpl(private val where: String): Vehicle {
    override fun go() = "is flying to $where"
}

class CarOrAirplane(
        private val model: String,
        impl: Vehicle
): Vehicle by impl {
    fun tellMeYourTrip() {
        println("$model ${go()}")
    }
}

fun main(args: Array<String>) {
    val myAirbus330
            = CarOrAirplane("Lamborghini", CarImpl("Seoul"))
    val myBoeing337
            = CarOrAirplane("Boeing 337", AirplaneImpl("Seoul"))

    myAirbus330.tellMeYourTrip()
    myBoeing337.tellMeYourTrip()
}