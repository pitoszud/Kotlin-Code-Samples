package Algorithms.Binary

// every recursion decreases the list by half so it is not possible to get an index of the target
fun binary1(list: List<Int>, target: Int): Boolean {
    if (list.isEmpty()){
        return false
    } else{
        var midIndex = list.size / 2
        var midVal = list[midIndex]
        var minVal = list.first()
        var maxVal = list.last()
        if (list[midIndex] == target){
            return true
        } else{
            if (target < list[midIndex]){
                return binary1(list.subList(0, midIndex),target)
            } else{
                return binary1(list.subList(midIndex+1,list.size),target)
            }
        }
    }
}

// list is not effected by each recursion, so we can get an index of the target
fun binary2(list: List<Int>, target: Int, start: Int, end: Int): Int {
    if (list.isEmpty()){
        return -1
    }
    if (end-start+1 <= 0){
        return -1
    } else{
        //0 + (18 - 0)/2 = 9
        //10 + (18 - 10)/2 = 14
        //15 + (18 - 15)/2 = 17
        var midIndex = start + (end - start) / 2
        var midVal = list[midIndex]
        var minVal = list.first()
        var maxVal = list.last()
        if (list[midIndex] == target){
            return midIndex
        } else{
            if (target < list[midIndex]){
                return binary2(list, target, start, midIndex-1)
            } else{
                return binary2(list, target, midIndex+1, end)
            }
        }
    }
}


fun main(args: Array<String>) {
    val list: List<Int> = arrayListOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18)

    val i = binary2(list, 18, 0, list.size)
    print(i)
}