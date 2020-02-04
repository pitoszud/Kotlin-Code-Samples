package Coroutines.Flows

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/*
* concurrently collect all the incoming flows
* and merge their values into a single flow so that values are emitted as soon as possible.
* */

fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i: First")
    delay(500) // wait 500 ms
    emit("$i: Second")
}

@FlowPreview
fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis() // remember the start time
    (1..3).asFlow().onEach { delay(100) } // a number every 100 ms
        .flatMapMerge { requestFlow(it) }
        .collect { value -> // collect and print
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}


// ---- Concurrent -----------

//1: First at 141 ms from start
//2: First at 250 ms from start
//3: First at 359 ms from start
//1: Second at 656 ms from start
//2: Second at 765 ms from start
//3: Second at 875 ms from start