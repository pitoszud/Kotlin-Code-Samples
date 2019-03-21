package Core.Other

/**
 * Created by upatryk on 11/10/2017.
 */
fun printNums(vararg nums: Int) {
    for(num in nums){
        println(num)
    }
}

fun main(args: Array<String>) {
    val numArr = intArrayOf(6,7,8,9)
    printNums(1, 2, 3, 4, 5, *numArr, 14, 15)
}