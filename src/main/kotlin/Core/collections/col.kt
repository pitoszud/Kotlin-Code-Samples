package Core.collections

import java.util.Random
import kotlin.collections.MutableCollection

fun main(args: Array<String>){
    var c2: List<Int?> = colection1()


}


// List<Int?> - individual values are nullable
// List<Int>? - entire list is nullable
fun colection1(): List<Int?>{
    val res = ArrayList<Int?>()
    val rndm = Random()
    for (i in 1..10){
        res.add(rndm.nextInt(1-10))
    }
    return res
}

fun <T> copyElements(source: Collection<T>, target: MutableCollection<T>){
    for (i in source){
        target.add(i)
    }

}

