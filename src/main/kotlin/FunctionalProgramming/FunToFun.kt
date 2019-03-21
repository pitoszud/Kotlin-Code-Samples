package FunctionalProgramming

enum class Bike {TOUR, ROAD}

class Trip(val distnace: Int)

fun getDistanceCalculator(bike: Bike): (Trip) -> Double {
    if(bike == Bike.TOUR){
        return {trip -> 1.25 * trip.distnace}
    }
    return {trip -> 1.5 * trip.distnace}
}

fun main(args: Array<String>) {
    val tripCalc = getDistanceCalculator(Bike.ROAD)
    print("$tripCalc")
}