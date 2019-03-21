package Core.Loops

/**
 * Created by upatryk on 11/10/2017.
 */
fun main(args: Array<String>){
    var i = 0
    val toys = listOf("ball", "blocks", "lego", "dolly")

    for (i in toys){
        println("item $i is at index " + toys.indexOf(i)) // item ball is at index 0
    }

    for (i in toys.indices){
        println("item at $i is ${toys[i]}") // item at 0 is ball
    }

    while (i < toys.size){
        print("bam, ")
        i++
    }

    println()

    for (i in 1..10 step 2){
        print(i.toString() + ",")
    }

    println()

    for (i in 9 downTo 0 step 3){
        print(i.toString() + ",")
    }
}