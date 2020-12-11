package Coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.produce
import java.lang.System.currentTimeMillis
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext


fun main() = runBlocking {
    //blockingMainA()
    //blockingBackgroundA()
    //blockingGlobal()
    //blockingGlobal2()
    //blockingLocal()
    //blockingLocalDispatch()
    //blockingLocalCustomDispatch()
    //asyncAwaitExampleA()
    //blockingAsyncAwait()
    //concurrentScope()
    //blockingWithCancelAndException()
    //awaitAllExample()
    //yieldIterator()
    //invokeSuspendingFunctions_concurrently()
    //invokeSuspendingFunctions_non_concurrently()
    //invokeSuspendingFunctions_concurrently_lazy()
    //awaitAllExample1()
    awaitAllExample2()

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

    job.join() // the runBlocking can finish only after job has finished, so adding delay() is not necessary
    //job.start() // main thread will continue running without waiting for the job to finish.

    if(job.isCompleted) println("job completed")

}




/**
 * invoke a suspending function from the main thread
 * and run each function in the background thread
 * */
// Android:
// CoroutineScope(Dispatchers.Main).launch{}
fun invokeSuspendingFunctions_concurrently() = runBlocking{
    launch(Dispatchers.Default) {
        val task1: Deferred<String> = async(Dispatchers.IO) {
            printDelayedInMain("task1") // 1 - concurrent
        }
        val task2: Deferred<String> = async(Dispatchers.IO) {
            printDelayedInMain("task2") // 1 - concurrent
        }

        val resultStr = task1.await() +  task2.await()
        println(resultStr)
    }
}


// Android:
// CoroutineScope(Dispatchers.IO).launch{}
fun invokeSuspendingFunctions_concurrently_lazy() = runBlocking{
    launch(Dispatchers.IO) {
        val task1: Deferred<String> = async(start = CoroutineStart.LAZY) {
            printDelayedInMain("task1") // 1 - concurrent
        }
        val task2: Deferred<String> = async(start = CoroutineStart.LAZY) {
            printDelayedInMain("task2") // 1 - concurrent
        }

        val resultStr = task1.await() +  task2.await()
        println(resultStr)
    }
}

// Android:
// CoroutineScope(Dispatchers.Main).launch{}
fun invokeSuspendingFunctions_non_concurrently()= runBlocking{
    launch(Dispatchers.Default) {
        val task1: String = printDelayedInMain("task1") // 1
        val task2: String = printDelayedInMain("task2") // 2

        val resultStr = task1 +  task2 // 3
        println(resultStr)
    }
}




// Android:
// CoroutineScope(Dispatchers.Main).launch{}
fun invokeSuspendingFunctions_with_timeout()= runBlocking{
    launch(Dispatchers.Default) {
        withTimeout(3000){
            val task1: String = printDelayedInMain("task1") // 1
            val task2: String = printDelayedInMain("task2") // 2

            val resultStr = task1 +  task2 // 3
            println(resultStr)
        }

    }
}


// It is not recomended to use GlobalScope.launch

fun blockingWithCancelAndException() = runBlocking{

    val exceptionHandler = CoroutineExceptionHandler { context: CoroutineContext, throwable: Throwable ->
        println("Job cancelled due to ${throwable.message}")
    }

    println("one - ${Thread.currentThread().name}") // 1. one - main

    val job = GlobalScope.launch(exceptionHandler) {
        printDelayed("two - ${Thread.currentThread().name}")
        printDelayed("three - ${Thread.currentThread().name}")
        printDelayed("four - ${Thread.currentThread().name}")
    }

    println("five - ${Thread.currentThread().name}") // 2. five - main

    //job.join()

    // if the job is not finished after 1 second, it will be cancelled
    job.cancel(cause = CancellationException("Timeout"))
    if (job.isCancelled) print("Job has cancelled")
    if (job.isCompleted) print("Job has completed")


    job.join()

    // retrieving cancelation information
    //val cancellation = job.getCancellationException().cause // still experimental
}

/**
 * Unconfined dispatcher runs coroutine in the current thread,
 * but resumes in whatever thread is used in the suspending lambda
 * passed as an argument to coroutine builder.
 *
 * */
fun blockingGlobal2() = runBlocking{
    println("one - ${Thread.currentThread().name}") // 1. one - main

    val job: Job = GlobalScope.launch(Dispatchers.Unconfined) {
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

    val customDispatcher1: ExecutorCoroutineDispatcher = Executors.newFixedThreadPool(4).asCoroutineDispatcher()
    //val customDispather2 = newFixedThreadPoolContext(2, "IO") // experimental
    //val customDispatcher3 = newSingleThreadContext("Single") // experimental

    launch(customDispatcher1) {
        printDelayed("two - ${Thread.currentThread().name}") // 3. two - DefaultDispatcher-worker-1
    }

    println("four - ${Thread.currentThread().name}") // 2. three - main

    (customDispatcher1.executor as ExecutorService).shutdown()
}



/**
 * async is used when result is expected.
 * It will capture any exception in the coroutine and put it in its result.
 *
 * */
fun concurrentAsyncAwait() = runBlocking {
    val userId1: String = async { fetchUser("pitos007@gmail.com") }.await() // blocking

    println("$userId1 returned")

    val startTime = System.currentTimeMillis()
    val deferredValue1: Deferred<String> = async { fetchUserData(userId1) } // concurrent
    val deferredValue2: Deferred<String> = async { fetchWeather(1.12, 0.15) } // concurrent
    val deferredValue3: Deferred<String> = async { fetchUserExtraData(userId1) } // concurrent

    val result = "${deferredValue1.await()} ${deferredValue2.await()} ${deferredValue3.await()}"
    println(result)

    val endTime = System.currentTimeMillis()
    println("Run time: ${endTime - startTime}")
}


fun asyncWithDispatcher() = runBlocking {
    val customDispatcher: ExecutorCoroutineDispatcher = Executors.newFixedThreadPool(4).asCoroutineDispatcher()


    val userId1 = withContext(customDispatcher) {
        fetchUser("name@gmail.com")
    }

    // async returns Deffered<T> so it has to be called with await()
    val userId = async(customDispatcher){
        fetchUser("name@gmail.com")
    }.await()

}




fun awaitAllExample() = runBlocking {
    val userId = async { fetchUser("username@gmail.com") }.await() // blocking

    val userId2 = withContext(Dispatchers.Default) { fetchUser("username@gmail.com") } // blocking

    // all running concurrently
    val defferedList: List<Deferred<String>> = listOf(
        async { fetchUserData(userId) },
        async { fetchWeather(1.12, 0.15) },
        async { fetchUserExtraData(userId) }
    )



    defferedList.awaitAll().map {
        println(it)
    }

    println("-----------------------------")

    defferedList.flatMap {
        listOf(it.await()).map {r->
            println(r)
        }
    }

    println("-----------------------------")

    defferedList.forEach {
        println(it.await())

    }

    println("-----------------------------")

    defferedList.map {
        println(it.await())
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



/**
 * coroutineScope() is an alias of withContext(this.coroutineContext)
 * */
suspend fun awaitAllExample1() = coroutineScope {
    var fullStock = 0
    awaitAll(
        async {
            fullStock += getStock1(1000L) // 5500
        },
        async {
            fullStock += getStock2(4000L) //3500
        }
    )

    print("awaitAllExample2 $fullStock \n")
}

/**
 *  withContext(this.coroutineContext) is an alias of coroutineScope()
 * */
suspend fun awaitAllExample2() = coroutineScope {

    withContext(Dispatchers.Default){
        val stockDef1 = async{ getStock1(1000L)}.await() // concurrent
        val stockDef2 = async { getStock2(4000L) }.await() // concurrent

        val fullStock = stockDef1 + stockDef2

        print("awaitAllExample2 $fullStock \n") // 5500 + 3500
    }
}

/**
*
 * withContext is the same as async(Dispatcher)
* */
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
 * Producers can suspend during execution
 * */

@ExperimentalCoroutinesApi
fun CoroutineScope.produceNames() = produce<String> {
    send("Bolek")
    send("Lokek")
    send("Tomek")
    send("Atomek")
}





/**
* Both iterators and sequences cannot suspend during execution
* */
fun yieldIterator(){
    val names = iterator {
        yield("Bolek")
        yield("Lokek")
        yield("Tomek")
        yield("Atomek")
    }

    println(names.next())
    println("Lolek, Tomek and Atomek are suspended")
    println(names.next())
    println("Tomek and Atomek are suspended")
    println(names.next())
    println("Atomek is suspended")
    println(names.next())


    // --------------------------------------------

    names.forEach { n ->
        if(names.hasNext()){
            println(n)
            println(names.next())
        }
    }

    val multiUser: Iterator<Any> = iterator {
        yield("Lolek")
        yield(25)
        yield(42.54)
    }
}


fun yieldSequence(){
    val nameSequence = sequence {
        yield("Bolek")
        yield("Lokek")
        yield("Tomek")
        yield("Atomek")
    }

    println(nameSequence.last())
    println(nameSequence.take(3)) // takes first three
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

suspend fun printDelayedInMain(message: String): String{
    withContext(Dispatchers.Default){
        delay(1000)
        println(message)
    }
    return "$message done"
}

suspend fun fetchWeather(lat: Double, lon: Double): String{
    delay(1000)
    println("feaching weather data...")
    delay(1000)
    return "Weather at location: $lat, $lon returned ;"
}

suspend fun fetchUser(userEmail: String): String{
    delay(1000)
    println("feaching user $userEmail...")
    delay(1000)
    return "12345 "
}

suspend fun fetchUserData(userId: String): String{
    delay(1000)
    println("feaching data for user $userId...")
    delay(1000)
    return "data for user: $userId returned ;"
}


suspend fun fetchUserExtraData(userId: String): String{
    delay(1000)
    println("feaching extra data for user $userId...")
    delay(1000)
    return "Extra data for user: $userId returned ;"
}


private suspend fun getStock1(delay: Long) : Int {
    delay(delay)
    return 5500
}




private suspend fun getStock2(delay: Long) : Int {
    delay(delay)
    return 3500
}



