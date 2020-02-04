package Coroutines.Flows

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking


/*
* collection of the previous flow is cancelled as soon as new flow is emitted
* */

fun getFlowForLatest(i: Int): Flow<String> = flow {
    emit("$i: First")
    delay(500) // wait 500 ms
    emit("$i: Second")
}

@ExperimentalCoroutinesApi
fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis() // remember the start time
    (1..3).asFlow().onEach { delay(100) } // a number every 100 ms
        .flatMapLatest { getFlowForLatest(it) }
        .collect { value -> // collect and print
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}


//1: First at 156 ms from start
//2: First at 266 ms from start
//3: First at 375 ms from start
//3: Second at 891 ms from start