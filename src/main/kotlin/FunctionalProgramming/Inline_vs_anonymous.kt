package FunctionalProgramming

fun containerA(){
    val numbers = 1..15
    numbers.forEachCust{ num ->
        if (num % 5 == 0){
            // outside return due to inline fun (copy - paste)
            //return

            // local return - forced by @forEachCust extension function
            return@forEachCust
        }
    }
    println("hop")
}

// same as forEach standard library
inline fun <T> Iterable<T>.forEachCust(action: (T) -> Unit): Unit {
    for (element in this) action(element)
}


fun main() {
    containerA()
}