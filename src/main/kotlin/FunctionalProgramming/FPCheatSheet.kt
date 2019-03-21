package FunctionalProgramming

import Core.collections.iterExample

// operation IN (method)
//fun inOpA(x: Double): Double = x * 1.3
fun inOpA(x: Double): Double {
    return x * 1.3
}

// operation IN (method object)
object Op{
    fun calc(x: Double): Double{
        return x*1.3
    }
}

// operation IN (lambda operation)
val opInA: (Double) -> Double = {x: Double -> (x*1.3)}

// or shorter version
val opInB = {x: Double -> x*1.3}


// operation IN with returned different type lambda
val opInC: (Int) -> Int = { it ->
    var n = it
    var rev = 0
    while (n > 0){
        val d = n % 10
        rev *= 10 * d
        n /= 10
    }
    rev
}





// operation OUT (lambda function)
//fun outOpA(fn: (Double) -> Double): Double = fn(1.3)
fun outOpA(fn: (Double) -> Double): Double {
    return fn(1.3)
}


// operation OUT (parameter + lambda function)

fun outOpB(x: Double, fn: (Double) -> Double): Double{
    return fn(x)
}


// operation IN (parameter + lambda function)
fun inOpB(x: Double, fn: (Double) -> Double): Double{
    return fn(x*1.3)
}

// operation IN (parameter) with returned function(parameter)
fun inOpC(x: Double): (Double)-> Unit{
    return {
        (x * 1.3) + it
    }
}

//---------------------------------------------------------------------------

fun unless(cond: Boolean, inOp: () -> Unit){
    if(!cond) inOp()
}


fun main(args: Array<String>) {
    val d1 = outOpA { x -> x*20.0 }

    val d2a= outOpB(20.0, opInA) // passing reference to lambda operation
    val d2b= outOpB(20.0, opInB) // passing reference to lambda operation

    val d3a0=outOpB(20.0, { x -> x*1.3}) // passing lambda operation as a parameter
    val d3a1=outOpB(20.0){ x -> x*1.3} // passing lambda operation outside


    // f(T) -> T * 1.3
    val d5=inOpA(15.0) // 15 * 1.3

    // val d4ar = outOpB(20.0, ::d5) // references to variables are not supported in Kotlin yet, so method must be used instead
    val d4a = outOpB(20.0, ::inOpA) // passing method,
    val d4b = outOpB(20.0, Op::calc) // passing object method
    val d4c = outOpB(20.0) { inOpA(it) } // lambda outside
    val d4d = outOpB(20.0) {i -> i * 3.1} // passing lambda operation outside

    println("$d1, $d2a, $d2b $d3a1, $d4a, $d4b, $d5, $d4c, $d4d")


    //------------------------------------------------------------------------

    val b = true
    unless(b){
        outOpA {x -> x*20.0}
        inOpB(20.0) { x -> x}
        opInC(54983)
        inOpC(20.0)(4.5)
    }




}