package coding_challenges

fun main() {
    val nums = arrayListOf(2, 3, 4, 1, 5)
    minSwaps(nums)
}


fun minSwaps(nums: ArrayList<Int>): Int{
    val counter = 0
    var length = nums.size
    val arrDict = mutableMapOf<Int, Int>()

    for (i in 0 until nums.size){
        arrDict[nums[i]] = i
        println("add to dict ${nums[i]} : $i")
    }

    val checked = arrayListOf(false)
    nums.forEach { _ ->
        checked.add(false)
    }

    // TODO - complete the algorithm

    return counter
}