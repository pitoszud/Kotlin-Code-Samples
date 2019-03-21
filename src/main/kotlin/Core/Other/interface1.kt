package Core.Other

interface Vehicle {
    val type : String
    fun ride()
    fun stop() = println("breaking the vehicle")
}

interface Plain {
    fun fly()
    fun stop() = println("landing the plain")
}

open class JetCar(override val type : String) : Vehicle, Plain {
    override fun ride() = println("driving a car")
    override fun fly() = println("flying a car")

    // both stop() methods overridden
    override fun stop() {
        super<Vehicle>.stop()
        super<Plain>.stop()
    }



    // only one stop() method overridden
    //override fun stop(vehType: String) = super<Vehicle>.stop(vehType)
}

// JetCar is final by default. "open" makes it accessible to subclasses
open class Motorcycle : JetCar("Motocycle") {
    final override fun stop() = println("breaking the motorcycle")
}

class Bike(val size : String, override val type: String) : Vehicle {
    override fun ride() {
    }
}

fun main(args: Array<String>) {
    JetCar("car").ride()
    JetCar("car and plain").stop()

    Motorcycle().stop()
}