package Coroutines.Flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

/**
 *  The code inside a flow builder does not run until the flow is collected.
 * */

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
fun main() {
    val ca = ConfilationCollectLatest()
    ca.flowProducer()
    //ca.conflationCollector()
    ca.collectLatestCollector()
}

class ConfilationCollectLatest {

    fun flowProducer(): Flow<Int> = flow { // flow builder
        for (i in 1..5) {
            delay(100) // pretend we are doing something useful here
            emit(i) // emit next value
        }
    }


    /*
    * If an emitted value was produced during the consumer process, it will be ignored by the consumer
    * This is useful if producer of consumer are slow
    * */

    // -- 1 -- 2 -- 3 -- 4 -- 5
    //    1 ------- 3 ------- 5
    @ExperimentalCoroutinesApi
    fun conflationCollector() = runBlocking {
        val values = measureTimeMillis {
            flowProducer()
                .conflate()
                .collect{value ->
                    delay(300)
                    println("processing: $value")
                }
        }
        println("collected in $values ms")
    }


    /*
    * collectLatest restarts every time a new value is emitted.
    * */

    // -- 1 -- 2 -- 3 -- 4 -- 5
    //    1 ------- 3 ------- 5
    @ExperimentalCoroutinesApi
    fun collectLatestCollector() = runBlocking {
        val values = measureTimeMillis {
            flowProducer()
                .collectLatest{value ->
                    println("collecting $value")
                    delay(300)
                    println("collected $value values")
                }
        }
        println("collected in $values ms")
    }

}