package Algorithms.Quick

fun main(args: Array<String>) {
    val unsortedIntList = listOf<Int>(28, 78, 5, 97, 25, 79, 17, 6, 11, 100, 46, 85, 44, 2, 19, 55)
    quicksort(unsortedIntList)
}

fun <T:Comparable<T>>quicksort(items:List<T>):List<T>{
    if (items.count() < 1) return items
    val pivot = items[items.count()/2]
    println("pivot: $pivot, items: $items")

    val equal = items.filter { it == pivot }
    println("equal: $equal")

    val less = items.filter { it < pivot }
    println("less: $less")

    val greater = items.filter { it > pivot }
    println("greater: $greater")

    return quicksort(less) + equal + quicksort(greater)
}