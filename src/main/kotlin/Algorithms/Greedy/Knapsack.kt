package Algorithms.Greedy

fun main() {
    val weights = arrayOf(2, 4, 5)
    val values = arrayOf(3, 6, 7)
    calcKnapsack(weights, values, 12)
}

fun calcKnapsack(weights: Array<Int>, values: Array<Int>, limit: Int): Int{

    // 1. Set up a list that will hold optimum values for weights from 1 to limit.
    val wValues = IntArray(limit)


    for (wl in 1..limit){
        println("value limit: $wl")

        val bestValForWeight = 0

        for (w in weights.indices){
            println("check if weight ${weights[w]} with value ${values[w]} can be added to weight limit $wl")

            if (weights[w] <= wl){
                var previousWeight = wl - weights[w]
                println("previous weight: $previousWeight")

                var bestPossibleValue = values[previousWeight] + values[w]

            }

        }
    }






    return 0
}