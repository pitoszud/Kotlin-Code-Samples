package FunctionalProgramming

fun <T, R> convertOp(id: Int, vararg ts: T, f: (T) -> R): List<R> = ts.map(f)

fun <T> emitterOp(transmitter: T, vararg listeners: (T) -> Unit) = listeners.forEach { listener ->
    listener(transmitter)
}


// vararg with named parameters
fun paramOp(id: Int, vararg users: User, score: Double ){

}




fun <T> listenerOp(e: T){
    println(e)
}

fun main(args: Array<String>) {
    val valList: List<Double> = convertOp(2, 1, 4, 7) { d -> d * 1.3 }
    val intList: List<Int> = convertOp(8,"2", "1", "4", "7") { s -> s.toInt() }


    val vl = convertOp(8,"one", "two", "three") {s -> s.length}

    val res1 = emitterOp(5, ::listenerOp, { e -> listenerOp(e * 2)})
    val res2 = emitterOp(3, {x -> x*20.0})

    //println(valList)
    //println(intList)
    println(vl)
    println(res1)
    println(res2)
}


class User(val name: String, val age: Int)

