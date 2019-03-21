package Core.Other

typealias Operation<T> = (T) -> Unit

fun <T> perfOperation(t: T, operation: Operation<T>){
    operation(t)
}

class FastOperation<T>: Operation<T> {
    override fun invoke(x: T){
        println(x)
    }
}

fun main(args: Array<String>) {
    perfOperation(20.0, FastOperation())
    perfOperation(20.0, ::println)
    perfOperation(20.0) { o -> println(o) }
}