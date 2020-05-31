package codingInterview

import kotlin.collections.*
import kotlin.io.*

// Complete the hourglassSum function below.
fun hourglassSum(arr: Array<Array<Int>>): Int {
return 1

}

fun main(args: Array<String>) {

    train()

//    val scan = Scanner(System.`in`)
//
//    val arr = Array<Array<Int>>(6) { Array<Int>(6) { 0 } }
//
//    for (i in 0 until 6) {
//        arr[i] = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()
//    }



    // val result = hourglassSum(arr)

    // println(result)
}


fun train(){
    val arr1 = arrayOf(1, 1, 1, 0, 0, 0)
    val arr2 = arrayOf(0, 1, 0, 0, 0, 0)
    val arr3 = arrayOf(1, 1, 1, 0, 0, 0)
    val arr4 = arrayOf(0, 0, 2, 4, 4, 0)
    val arr5 = arrayOf(0, 0, 0, 2, 0, 0)
    val arr6 = arrayOf(0, 0, 1, 2, 4, 0)

    val arr16: Array<Array<Int>> = arrayOf(arr1, arr2, arr3, arr4, arr5, arr6)

    val top: Array<Int> = arr16[0].sliceArray(0..2)
    val middle: Int = arr16[1][1]
    val bottom: Array<Int> = arr16[2].sliceArray(0..2)

    println("top")
    top.forEach {
        println(it.toString())
    }

    println("middle")
    println(middle)

    println("bottom")
    bottom.forEach {
        println(it)
    }

}



