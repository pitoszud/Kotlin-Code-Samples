package FunctionalProgramming

fun main(args: Array<String>) {
    fun applyOp(x: Int, y: Int, f: (Int, Int) -> Int): Int = f(x, y)

    val sum1: (Int, Int) -> Int = { x, y -> x + y }
    println(applyOp(2, 3, sum1))

    fun sum2(a: Int, b: Int) = a + b
    println(applyOp(2, 3, ::sum2))



    fun <T, R> Collection<T>.fold(
            init: R,
            func: (acc: R, nextEl: T) -> R
    ): R {
        var acc: R = init
        for (el: T in this) {
            acc = func(acc, el)
        }
        return acc
    }


    val items = listOf(1, 2, 3, 4, 5)
    items.fold(0, {
        acc: Int, i: Int ->
        print("acc = $acc, i = $i, ")
        val result = acc + i
        println("result = $result")
        result
    })

    // Parameter types in a lambda are optional if they can be inferred:
    val joinedToString = items.fold("Elements:", { acc, i -> acc + " " + i })

    // Function references can also be used for higher-order function calls:
    val product = items.fold(1, Int::times)

    println("joinedToString = $joinedToString")
    println("product = $product")
}

