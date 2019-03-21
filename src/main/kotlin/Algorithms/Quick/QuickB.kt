package Algorithms.Quick


internal class QuickB {

    private fun partition(arr: IntArray, low: Int, high: Int): Int {
        val pivot = arr[high]
        var i = low - 1 // index of smaller element
        for (j in low until high) {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot) {
                i++

                // swap arr[i] and arr[j]
                val temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        val temp = arr[i + 1]
        arr[i + 1] = arr[high]
        arr[high] = temp

        return i + 1
    }


    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    fun sort(arr: IntArray, low: Int, high: Int) {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            val pi = partition(arr, low, high)

            // Recursively sort elements before
            // partition and after partition
            sort(arr, low, pi - 1)
            sort(arr, pi + 1, high)
        }
    }

    companion object {

        private fun printArray(arr: IntArray) {
            val n = arr.size
            for (i in 0 until n)
                print(arr[i].toString() + " ")
            println()
        }


        @JvmStatic
        fun main(args: Array<String>) {
            val arr = intArrayOf(10, 7, 8, 9, 1, 5)

            val ob = QuickB()
            ob.sort(arr, 0, arr.size - 1)

            println("sorted array")
            printArray(arr)
        }
    }
}