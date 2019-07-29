package Coroutines

import kotlinx.coroutines.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.suspendCoroutine


fun main() {
    //blockingMainA()
    //blockingBackgroundA()
    //blockingGlobal()
    blockingGlobal2()
    //blockingLocal()
    //blockingLocalDispatch()
    //blockingLocalCustomDispatch()
    //asyncAwaitExampleA()
    //blockingAsyncAwait()
    //concurrentScope()
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

/**
 * runBlocking blocks the current thread until the execution of the coroutine is completed.
 *
 * */
fun blockingBackgroundA(){
    runBlocking(Dispatchers.Default) {
        println("one - ${Thread.currentThread().name}") // 1. one - DefaultDispatcher-worker-1
        printDelayed("two - ${Thread.currentThread().name}") // 2. two - DefaultDispatcher-worker-1
    }
    println("three - ${Thread.currentThread().name}") // 3. three - main

}

/**
 * launch is used for starting a computation that isn't expected to return a specific result.
 * Returned Job can be used to cancel its execution or the execution of its children.
 *
 * */
fun blockingGlobal() = runBlocking{
    println("one - ${Thread.currentThread().name}") // 1. one - main

    val job = GlobalScope.launch {
        printDelayed("two - ${Thread.currentThread().name}") // 3. two - DefaultDispatcher-worker-1
    }

    println("three - ${Thread.currentThread().name}") // 2. three - main
    //delay(3000)
    job.join() // the runBlocking can finish only after job has finished.
}

/**
 * Unconfined dispatcher runs coroutine in the current thread,
 * but resumes in whatever thread is used in the suspending lambda
 * passed as an argument to coroutine builder.
 *
 * */
fun blockingGlobal2() = runBlocking{
    println("one - ${Thread.currentThread().name}") // 1. one - main

    val job = GlobalScope.launch(Dispatchers.Unconfined) {
        printDelayed("two - ${Thread.currentThread().name}") // 3. two - main
        //suspendCoroutine<Unit> {}
        delay(1_000)
        printDelayed("three - ${Thread.currentThread().name}") // 4. three - kotlinx.coroutines.DefaultExecutor
    }

    println("four - ${Thread.currentThread().name}") // 2. four - main
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

    val customDispatcher: ExecutorCoroutineDispatcher = Executors.newFixedThreadPool(4).asCoroutineDispatcher()

    launch(customDispatcher) {
        printDelayed("two - ${Thread.currentThread().name}") // 3. two - pool-1-thread-1
    }

    println("three - ${Thread.currentThread().name}") // 2. three - main

    (customDispatcher.executor as ExecutorService).shutdown()
}



/**
 * async is used when result is expected.
 * It will capture any exception in the coroutine and put it in its result.
 *
 * */
fun concurrentAsyncAwait() = runBlocking {
    val userId1 = async { fetchUser("pitos007@gmail.com") }.await() // blocking

    println("$userId1 returned")

    val startTime = System.currentTimeMillis()
    val rValue1: Deferred<String> = async { fetchUserData(userId1) } // concurrent
    val rValue2: Deferred<String> = async { fetchWeather(1.12, 0.15) } // concurrent
    val rValue3: Deferred<String> = async { fetchUserExtraData(userId1) } // concurrent

    val result = "${rValue1.await()} ${rValue2.await()} ${rValue3.await()}"
    println(result)

    val endTime = System.currentTimeMillis()
    println("Run time: ${endTime - startTime}")
}


fun awaitAllExample() = runBlocking {
    val userId = async { fetchUser("pitos007@gmail.com") }.await() // blocking

    val defferedList: List<Deferred<String>> = listOf(
        async { fetchUserData(userId) },
        async { fetchWeather(1.12, 0.15) },
        async { fetchUserExtraData(userId) }
    )


    val res: List<String> = defferedList.awaitAll().map {
        it.plus(it)
    }
}


// all coroutines must complete before concurrentScope returns

fun concurrentScope() = runBlocking{

    coroutineScope {
        val userId: String = async { fetchUser("pitos007@gmail.com") }.await() // blocking

        val res0: Deferred<String> = async { fetchUser("pitos007@gmail.com")} // concurrent
        println(res0)

        launch { fetchWeather(1.12, 0.15) } // concurrent
        launch { fetchWeather(2.34, 4.61) } // concurrent

        async { fetchUserExtraData(userId) } // blocking

    }
}


fun blockingAsyncAwait() = runBlocking {
    val userId = async { fetchUser("pitos007@gmail.com") }.await() // blocking

    val startTime = System.currentTimeMillis()

    val resultContext1: String = withContext(Dispatchers.IO) { fetchUserData(userId) } // blocking (IO - network)
    val resultContext2: String = withContext(Dispatchers.IO) { fetchWeather(1.12, 0.15) } // blocking (IO - network)
    val resultContext3: String = withContext(Dispatchers.Default) { fetchUserExtraData(userId) } // blocking (Main / Default - local)

    val resultContext = "$resultContext1 $resultContext2 $resultContext3" // concurrent


//    val resultAsync1 = async { fetchUserData(userId) }.await() // blocking
//    val resultAsync2 = async { fetchUserData(userId) }.await() // blocking
//    val resultAsync3 = async { fetchUserData(userId) }.await() // blocking
//
//    val resultAsync = "$resultAsync1 $resultAsync2 $resultAsync3"
//    print(resultAsync)


    val endTime = System.currentTimeMillis()
    println("Run time: ${endTime - startTime}")
}






/**
 * Suspending function can suspend their execution without blocking the thread in which they reside.
 * Suspending function can only be invoked from other suspending function or from coroutines.
 *
 * */
suspend fun printDelayed(message: String){
    delay(1000)
    println(message)
}

suspend fun fetchWeather(lat: Double, lon: Double): String{
    delay(500)
    println("feaching weather data...")
    delay(500)
    return "Weather at location: $lat, $lon returned ;"
}

suspend fun fetchUser(userEmail: String): String{
    delay(500)
    println("feaching user $userEmail...")
    delay(500)
    return "12345 "
}

suspend fun fetchUserData(userId: String): String{
    delay(500)
    println("feaching data for user $userId...")
    delay(500)
    return "data for user: $userId returned ;"
}


suspend fun fetchUserExtraData(userId: String): String{
    delay(500)
    println("feaching extra data for user $userId...")
    delay(500)
    return "Extra data for user: $userId returned ;"
}



