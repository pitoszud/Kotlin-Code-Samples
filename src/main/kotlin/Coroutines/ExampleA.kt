package Coroutines

import kotlinx.coroutines.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.coroutines.Continuation
import kotlin.coroutines.suspendCoroutine


fun main() {
    //blockingMainA()
    //blockingBackgroundA()
    //blockingGlobal()
    //blockingLocal()
    //blockingLocalDispatch()
    //blockingLocalCustomDispatch()
    //asyncAwaitExampleA()
    asyncAwaitExampleContext()
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


fun blockingGlobal2() = runBlocking{
    println("one - ${Thread.currentThread().name}") // 1. one - main

    val job = GlobalScope.launch(Dispatchers.Unconfined) {
        suspendCoroutine<Unit> {

        }

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
        printDelayed("two - ${Thread.currentThread().name}") // 3. two - DefaultDispatcher-worker-1
    }

    println("three - ${Thread.currentThread().name}") // 2. three - main
}



fun blockingLocalCustomDispatch() = runBlocking{
    println("one - ${Thread.currentThread().name}") // 1. one - main

    val customDispatcher = Executors.newFixedThreadPool(4).asCoroutineDispatcher()

    launch(customDispatcher) {
        printDelayed("two - ${Thread.currentThread().name}") // 3. two - pool-1-thread-1
    }

    println("three - ${Thread.currentThread().name}") // 2. three - main

    (customDispatcher.executor as ExecutorService).shutdown()
}



fun asyncAwaitExampleA() = runBlocking {
    val userId = async { fetchUser("pitos007@gmail.com") }.await() // blocking

    println("$userId returned")

    val startTime = System.currentTimeMillis()
    val rValue1: Deferred<String> = async { fetchUserData(userId) } // concurrent
    val rValue2: Deferred<String> = async { fetchWeather(1.12, 0.15) } // concurrent
    val rValue3: Deferred<String> = async { fetchUserExtraData(userId) } // concurrent

    val result = "${rValue1.await()} ${rValue2.await()} ${rValue3.await()}"
    println(result)

    val endTime = System.currentTimeMillis()
    println("Run time: ${endTime - startTime}")
}


fun asyncAwaitExampleContext() = runBlocking {
    val userId = async { fetchUser("pitos007@gmail.com") }.await() // blocking

    println("$userId returned")

    val startTime = System.currentTimeMillis()

    val resultContext1: String = withContext(Dispatchers.IO) { fetchUserData(userId) } // blocking (IO - network)
    val resultContext2: String = withContext(Dispatchers.IO) { fetchWeather(1.12, 0.15) } // blocking (IO - network)
    val resultContext3: String = withContext(Dispatchers.Default) { fetchUserExtraData(userId) } // blocking (Main / Default - local)

    val resultContext = "$resultContext1 $resultContext2 $resultContext3" // concurrent
    println(resultContext)


//    val resultAsync1 = async { fetchUserData(userId) }.await() // blocking
//    val resultAsync2 = async { fetchUserData(userId) }.await() // blocking
//    val resultAsync3 = async { fetchUserData(userId) }.await() // blocking
//
//    val resultAsync = "$resultAsync1 $resultAsync2 $resultAsync3"
//    print(resultAsync)


    val endTime = System.currentTimeMillis()
    println("Run time: ${endTime - startTime}")
}














suspend fun printDelayed(message: String){
    delay(1000)
    println(message)
}

suspend fun fetchWeather(lat: Double, lon: Double): String{
    delay(1000)
    return "Weather at location: $lat, $lon returned ;"
}

suspend fun fetchUser(userEmail: String): String{
    delay(1000)
    return "12345 "
}

suspend fun fetchUserData(userId: String): String{
    delay(1000)
    return "data for user: $userId returned ;"
}


suspend fun fetchUserExtraData(userId: String): String{
    delay(1000)
    return "Extra data for user: $userId returned ;"
}


