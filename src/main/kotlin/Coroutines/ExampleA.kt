package Coroutines

import kotlinx.coroutines.*

fun main() {
    //blockingMainA()
    //blockingBackgroundA()
    //blockingGlobal()
    blockingLocal()
}


fun blockingMainA() = runBlocking{
    println("one")
    printDelayed("two")
    println("three")
}


fun blockingMainB(){
    println("one")
    runBlocking {
        printDelayed("two")
    }
    println("three")
}


fun blockingBackgroundA(){
    runBlocking(Dispatchers.Default) {
        println("one - ${Thread.currentThread().name}")
        printDelayed("two - ${Thread.currentThread().name}")
    }
    println("three - ${Thread.currentThread().name}")
}


fun blockingGlobal() = runBlocking{
    println("one - ${Thread.currentThread().name}")

    val job = GlobalScope.launch {
        printDelayed("two - ${Thread.currentThread().name}")
    }
    println("three - ${Thread.currentThread().name}")
    //delay(3000)
    job.join() // the program can finish only after job has finished.
}


fun blockingLocal() = runBlocking{
    println("one - ${Thread.currentThread().name}")

    launch {
        printDelayed("two - ${Thread.currentThread().name}")
    }
    println("three - ${Thread.currentThread().name}")
}








suspend fun printDelayed(message: String){
    delay(1000)
    println(message)
}
