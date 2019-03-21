package FunctionalProgramming

val vals: List<Int> = listOf(1,2,3,4,5,6,7,8,9)

fun main(args: Array<String>) {
    val sumFromInt = vals.fold(5){acc, i-> acc + i}
    val sumAll = vals.reduce {acc, i -> acc + i}
    println("sumFromInt: $sumFromInt, sumAll: $sumAll")
}




