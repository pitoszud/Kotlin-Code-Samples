package OOP

interface Vehicle{
    fun printSportCar()
    fun printCar()
}

open class Car{
    fun printCar(){
        println("Car")
    }
}

open class SportCar: Car(), Vehicle{
    override fun printSportCar(){
        println("SportCar")
    }

}

// How to access both printCar and printSportCar method in SportCar

// if the client already extends a class then use composition (preferred method)
class ClientA: Car(){

    private var sportCar = SportCar()

    fun printCars(){
        sportCar.printCar()
        sportCar.printSportCar()
    }


}


// if client does not extend anything then extend Superclass and add call the method from the super class

class ClientB: SportCar(){
    fun printCars(){
        val car: Car = SportCar()
        car.printCar()
        super.printSportCar()
    }
}


// if the client already extends another class then use interface to get an instance of the

class ClientC: Car(){
    private val vehicle: Vehicle = SportCar()

    fun printCars(){
        val car: Car = SportCar()
        car.printCar()

        vehicle.printCar()
        vehicle.printSportCar()

    }


}

fun main() {
    val cA = ClientA()
    val cB = ClientB()
    val cC = ClientC()

    cA.printCars()
    cB.printCars()
    cC.printCars()
}
