package Coroutines.Flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis



@ExperimentalCoroutinesApi
@InternalCoroutinesApi
fun main() {
    val sc = SwitchContext()
    //sc.flowProducer()
    sc.collector()
}

class SwitchContext {

    fun flowProducer(): Flow<String> = flow { // flow builder
        for (i in 1..5) {
            //delay(100) // pretend we are doing something useful here
            emit("$i") // emit next value
        }
    }


    /*
    * Whenever you call flowOn(), you're applying the context switch only on the preceding operators.
    * Consumption of events can happen only on the original context.
     */

    @ExperimentalCoroutinesApi
    fun collector() {
        GlobalScope.launch {
            flowProducer()
                .map { it.toUpperCase()}
                .map { it.first()}
                .flowOn(Dispatchers.IO)
                .onEach { delay(1000) }
                .flowOn(Dispatchers.Default)
                .collect { value ->
                    println(value)
                }
        }
    }
}