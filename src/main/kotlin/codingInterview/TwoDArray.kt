package codingInterview

import kotlin.collections.*
import kotlin.io.*

// Complete the hourglassSum function below.
fun hourglassSum(arr: Array<Array<Int>>): Int {
    return 1

}

fun main(args: Array<String>) {

    sandpit()

}


fun sandpit() {
    val arr1 = arrayOf(1, 1, 1, 0, 0, 0)
    val arr2 = arrayOf(0, 1, 0, 0, 0, 0)
    val arr3 = arrayOf(1, 1, 1, 0, 0, 0)
    val arr4 = arrayOf(0, 0, 2, 4, 4, 0)
    val arr5 = arrayOf(0, 0, 0, 2, 0, 0)
    val arr6 = arrayOf(0, 0, 1, 2, 4, 0)

    val arr16: Array<Array<Int>> = arrayOf(arr1, arr2, arr3, arr4, arr5, arr6)


    val arrs1 = arrayOf(-9, -9, -9, 1, 1, 1)
    val arrs2 = arrayOf(0, -9, 0, 4, 3, 2)
    val arrs3 = arrayOf(-9, -9, -9, 1, 2, 3)
    val arrs4 = arrayOf(0, 0, 8, 6, 6, 0)
    val arrs5 = arrayOf(0, 0, 0, -2, 0, 0)
    val arrs6 = arrayOf(0, 0, 1, 2, 4, 0)

    val arrs16: Array<Array<Int>> = arrayOf(arrs1, arrs2, arrs3, arrs4, arrs5, arrs6)

    val arrw1 = arrayOf(-1, -1, 0, -9, -2, -2)
    val arrw2 = arrayOf(-2, -1, -6, -8, -2, -5)
    val arrw3 = arrayOf(-1, -1, -1, -2, -3, -4)
    val arrw4 = arrayOf(-1, -9, -2, -4, -4, -5)
    val arrw5 = arrayOf(-7, -3, -3, -2, -9, -9)
    val arrw6 = arrayOf(-1, -3, -1, -2, -4, -5)

    val arrw16: Array<Array<Int>> = arrayOf(arrw1, arrw2, arrw3, arrw4, arrw5, arrw6)

    println(calcMaxGlass(arrw16))

}


fun calcMaxGlass(arr16: Array<Array<Int>>): Int {

    var maxSize = -63

    for (s in 0..3) {
        for (d in 0..3) {
            val top = arr16[d].sliceArray(s..s + 2).sum()
            val middle = arr16[d + 1][s + 1]
            val bottom = arr16[d + 2].sliceArray(s..s + 2).sum()

            val total = top + middle + bottom

            // println(top + middle + bottom)

            if (maxSize < total) {
                maxSize = total
            }
        }
    }


    return maxSize
}



