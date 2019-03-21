package FunctionalProgramming

// higher-order function



fun main(args: Array<String>) {

    // input - inside
    // lambda operation - outside
    fun getVal(avrExp3: (Double, Double) -> Double): Double{
        return avrExp3(1.3, 0.9)
    }


    // input - outside
    // operation - inside
    fun getVal2(d1: Double): Double {
        return (d1 * 1.3)* 0.9
    }


    // input - outside
    // operation - inside
    val avrExp1 = { d1: Double -> d1*1.3 }


    // input - outside
    // lambda operation - inside
    val avrExp2: (Double, Double) -> Double = {x, y -> ((x+y)/2)}


    // the operation is defined here
    val gv1 = getVal { x, y -> (x+y)/2}
    val gv2 = getVal {x, y -> (x*y)*(x*y)}
    val gv3 = getVal {x, y -> (x*y)*2}
    val gv4 = getVal {x, y -> (x-y)*(x+y)}

    // operation is defined in the function
    val gv5 = getVal2(12.75)

    val gv6 = avrExp1(14.24)
    val gv7 = avrExp2(8.28, 4.94)

    print("$gv1, \n$gv2, \n$gv3, \n$gv4, \n$gv5, \n$gv6, \n$gv7")




}






val avrNull: (Double, Double) -> Double? = {x, y -> null}
val avrExplNull: ((Double, Double) -> Double)? = null

val report = {println(1982)}
val reportExpl: () -> Unit = {println(1982)}

// calling higher-order function

