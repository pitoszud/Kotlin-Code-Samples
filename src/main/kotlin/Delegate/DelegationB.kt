package Delegate

interface Vehicle {
    fun go(): String
}

class CarImpl(private val where: String): Vehicle {
    override fun go() = "is going to $where"
}

class AirplaneImpl(private val where: String): Vehicle {
    override fun go() = "is flying to $where"
}


class CarOrAirplaneClassicDelegation(val model: String, val impl: Vehicle): Vehicle {

    override fun go(): String {
        return impl.go()
    }

    fun tellMeYourTrip() {
        println("$model ${go()}")
    }
}

class CarOrAirplane(private val model: String, impl: Vehicle): Vehicle by impl {
    private var _impl: Vehicle? = null

    fun tellMeYourTrip() {
        println("$model ${go()}")
    }
}




fun main(args: Array<String>) {
    val myAirbus330 = CarOrAirplane("Lamborghini", CarImpl("Seoul"))
    val myBoeing337 = CarOrAirplane("Boeing 337", AirplaneImpl("Seoul"))

    myAirbus330.tellMeYourTrip()
    myBoeing337.tellMeYourTrip()
}