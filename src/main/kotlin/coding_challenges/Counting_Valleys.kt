package coding_challenges

import kotlin.collections.*
import kotlin.io.*
import kotlin.text.*


// Complete the countingValleys function below.


open class CountingVallyes{
    fun countingValleys(n: Int, s: String): String {

        val steps = IntArray(n)
        var arrIndex = 0

        // convert e.g U, D, D, D, U, D, U, U to 1, -1, -1, -1, 1, -1, 1, 1

        s.forEach {
            when (it){
                "U".single() -> steps[arrIndex] = 1
                else -> steps[arrIndex] = -1
            }
            arrIndex ++
        }

        return getValleyCount(steps)
    }


    private fun getValleyCount(steps: IntArray): String{
        var valleyCount = 0
        var currentElevation = 0 // cs > 0 uphill, cs < 0 downhill

        // 0, 1, 0, -1, -2, -1, -2, -1, 0
        steps.forEachIndexed { i, e ->
            currentElevation += e

            // check whether sea level has been reached
            if (currentElevation == 0){
                val currentStep = steps[i]
                val prevElev = currentElevation - currentStep

                // count valley if the previous step was below the sea level
                if (prevElev < 0){
                    valleyCount ++
                }
            }
        }
        return valleyCount.toString()

    }


    fun matchConstraints(n: Int, s: String): Boolean{
        var match = false
        val regex = "^(D|U)*(D|U)$".toRegex()

        val chk1: Boolean = n >= 2
        val chk2: Boolean = n <= 1000000
        val chk3: Boolean = regex.matches(s)

        if(chk1 and chk2){
            if(chk3){
                match = true
            }
        }

        return match
    }

}




fun main(args: Array<String>) {

    val cv = CountingVallyes()
    val n = 8
    val s = "UDDDUDUU"
    if (cv.matchConstraints(n,s)){
        val result = cv.countingValleys(n,s)
        println(result)
    }



}



