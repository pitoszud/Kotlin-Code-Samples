package Recursion

fun main(args: Array<String>) {
    val powerRisedToN: Double = toNpower(1.3, 4)
    println(powerRisedToN)

    val ptio: Double = toNpowerIfOdd(1.3, 4)
    println(ptio)

    val ptioTemp: Double = toNpowerTemp(1.3, 4)
    println(ptioTemp)
}


// compute value of a positive real value a raised to a power n, such that n is no negative
// 1 * 1.3 * 1.3 * 1.3 * 1.3 = 2.8561
fun toNpower(a: Double, n: Int): Double{
    return when {
        n == 0 -> 1.0
        n > 0 -> a * toNpower(a,n-1)
        else -> toNpower(a, n-1)
    }
}

// 1 * 1.3 * 1.3 * 1.3 * 1.3 = 2.8561
fun toNpowerIfOdd(a: Double, n: Int): Double{
    return when {
        n == 0 -> 1.0
        n%2 == 1 -> {
            a * toNpowerIfOdd(a,n/2) * toNpowerIfOdd(a,n/2)
        }
        else -> {
            println(a)
            toNpowerIfOdd(a,n/2) * toNpowerIfOdd(a,n/2)
        }
    }
}


// The most efficient
// invokes only one recuresive subproblem by storing the result in a temp variable to by multiplied by itself (squared)

// 1.3 * 1.0 * 1.0
// 1.3 * 1.3
// 1.69 * 1.69
// 2.85
fun toNpowerTemp(a: Double, n: Int): Double{
    val temp: Double
    return when (n) {
        0 -> 1.0
        else -> {
            temp = toNpowerTemp(a, n/2)
            //println("temp: $temp")
            return if (n%2 == 1){
                //println("$a * $temp * $temp")
                a * temp * temp
            }else{
                //println("$temp * $temp")
                temp * temp
            }
        }
    }
}
