package Coroutines.Flows

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking



@FlowPreview
fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis() // remember the start time
    (1..3).asFlow()
        .onEach { delay(100) } // a number every 100 ms
        .flatMapConcat { getFlowForConcat(it) }
        .collect { value -> // collect and print
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}



fun getFlowForConcat(i: Int): Flow<String> = flow {
    emit("$i: First")
    delay(500) // wait 500 ms
    emit("$i: Second")
}

// ---- Sequential -----------

//1: First at 141 ms from start
//1: Second at 663 ms from start
//2: First at 772 ms from start
//2: Second at 1288 ms from start
//3: First at 1397 ms from start
//3: Second at 1913 ms from start