package Coroutines.Channels

import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    flowExampleA()
}

fun flowExampleA() = runBlocking{

    val posList = getPosList()

   val flow = flow {
       emit(posList[0])
       emit(posList[1])
   }


    // PRODUCER
    // ------------------------------------------
    // CONSUMER

    val consumer = runBlocking<Unit> {
        flow.collect{
            println(it)
        }
    }



}