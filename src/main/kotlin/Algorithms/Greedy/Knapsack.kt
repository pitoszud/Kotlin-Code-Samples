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

        var bestValForWeight = 0

        for (w in weights.indices){
            println("Can weight ${weights[w]} with value ${values[w]} be added to weight limit $wl ?: ${weights[w] <= wl}")

            if (weights[w] <= wl){
                val previousWeight = wl - weights[w]
                println("previous weight: $previousWeight")

                val bestPossibleValue = previousWeight + values[w]
                println("Best possible value is: $previousWeight + ${values[w]} = ${values[w]}")

                if (bestPossibleValue > bestValForWeight){
                    bestValForWeight = bestPossibleValue

                    println("bestPossibleValue $bestPossibleValue is bigger from the current best value $bestValForWeight")
                }else{
                    println("Current best value $bestValForWeight is still the biggest")
                }
            }

        }
    }






    return 0
}