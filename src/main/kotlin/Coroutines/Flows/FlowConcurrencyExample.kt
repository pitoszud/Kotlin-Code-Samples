package Coroutines.Flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 *  The code inside a flow builder does not run until the flow is collected.
 * */

@InternalCoroutinesApi
fun main() {
    val fe = FlowExample()
    fe.flowProducer()
    fe.colllectiWithConcurrent()
}

class FlowExample {

    /*
    * Flow does not block the main thread so it can be run concurrently
    * */
    fun flowProducer(): Flow<Int> = flow { // flow builder
        for (i in 1..3) {
            delay(400) // pretend we are doing something useful here
            emit(i) // emit next value
        }
    }

    /*
    * Collection will block the main thread, so it cannot run concurrently
    * */
    fun collectionProducer(): Sequence<Int> = sequence{
        for (i in 1..3){
            Thread.sleep(400)
            yield(i)
        }
    }

    @InternalCoroutinesApi
    fun colllectiWithConcurrent() = runBlocking<Unit> {
        // Launch a concurrent coroutine to check if the main thread is blocked
        printCollection()

        // this will be run concurrently with printing collection
        flowProducer().collect {
            println("flow: $it")
            delay(400)
        }


        // this will wait until all collected without running concurrently
        collectionProducer().forEach {
            println("seq: $it")
            delay(400)
        }


    }

    private fun CoroutineScope.printCollection() {
        launch {
            for (k in 1..3) {
                println("coll $k")
                delay(400)
            }
        }
    }




}