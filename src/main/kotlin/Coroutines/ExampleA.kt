package Coroutines

import kotlinx.coroutines.*


fun main() {
    //blockingMainA()
    //blockingBackgroundA()
    //blockingGlobal()
    blockingLocal()
    blockingLocalDispatch()
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
        println("one - ${Thread.currentThread().name}") // 1. one - DefaultDispatcher-worker-1
        printDelayed("two - ${Thread.currentThread().name}") // 2. two - DefaultDispatcher-worker-1
    }
    println("three - ${Thread.currentThread().name}") // 3. three - main

}


fun blockingGlobal() = runBlocking{
    println("one - ${Thread.currentThread().name}") // 1. one - main

    val job = GlobalScope.launch {
        printDelayed("two - ${Thread.currentThread().name}") // 3. two - DefaultDispatcher-worker-1
    }

    println("three - ${Thread.currentThread().name}") // 2. three - main
    //delay(3000)
    job.join() // the runBlocking can finish only after job has finished.
}


fun blockingLocal() = runBlocking{
    println("one - ${Thread.currentThread().name}") // 1. one - main

    launch {
        printDelayed("two - ${Thread.currentThread().name}") // 3. two - main
    }

    println("three - ${Thread.currentThread().name}") // 2. three - main
}


fun blockingLocalDispatch() = runBlocking{
    println("one - ${Thread.currentThread().name}") // 1. one - main

    launch(Dispatchers.IO) {
        printDelayed("two - ${Thread.currentThread().name}") // 3. two - main
    }

    println("three - ${Thread.currentThread().name}") // 2. three - DefaultDispatcher-worker-1
}










suspend fun printDelayed(message: String){
    delay(1000)
    println(message)
}
