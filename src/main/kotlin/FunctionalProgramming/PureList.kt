package FunctionalProgramming

import FunctionalProgramming.PureList.*

sealed class PureList<out T>{
    object Nil: PureList<Nothing>()
    data class Cons<out T>(val head: T, val tail: PureList<T>): PureList<T>()

    fun forEach(f: (T) -> Unit){
        tailrec fun go(list: PureList<T>, f: (T) -> Unit){
            when(list){
                is Cons -> {
                    f(list.head)
                    go(list.tail, f)
                }
                is Nil -> Unit
            }
        }
        go(this, f)
    }
}

fun main(args: Array<String>) {
    val nums1 = Cons(1, Cons(2, Cons(3, Nil)))
    val nums2 = intListOf(5,7,1,3,8,7)
}

// varargs - infinite number of parameters
fun intListOf(vararg nums: Int): PureList<Int> = if (nums.isEmpty()){
    Nil
} else{
    Cons(nums.first(), intListOf(*nums.drop(1).toTypedArray().toIntArray()))
}