package Algorithms.Insertion


// p - pointer
// cv - current value

fun insertionSortA(numList: MutableList<Int>){
    // [5,8,1,3,9,6]
    for (i in 1 until numList.size){
        var cv = numList[i] // 1
        var p = i // 2

        while ((p >0) && (numList[p-1] > cv)){ // while 8 > 1
            numList[p] = numList[p-1] // [5,8,8,3,9,6]
            p -= 1 // 2 - 1
        }
        numList[p] = cv // [5,1,8,3,9,6]
    }
    println(numList)

}

fun main(args: Array<String>) {
    val numList: MutableList<Int> = mutableListOf(5,8,1,3,9,6)
    insertionSortA(numList)
}