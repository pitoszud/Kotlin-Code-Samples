package FunctionalProgramming



fun opA(x: Double, fn: (Double) -> Double): Double{
    return fn(x)
}


fun inA(x: Double): Double{
    println("adding 1.0 to $x")
    return x + 1.0
}



fun main(args: Array<String>) {
    val inAv = inA(2.0) // 2 + 1

    val opRa = opA(5.0, ::inA) // 5 + 1
    val opRb = opA(5.0) { inA(it) }


    println("result: $opRa")
}