package Coroutines.Flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

@InternalCoroutinesApi
fun main() {
    val fce = FlowColdExample()
    fce.flowProducer()
    fce.appClient()
}

class FlowColdExample {

    /*
    * Flow does not block the main thread so it can be run concurrently
    * */
    fun flowProducer(): Flow<Int> = flow { // flow builder
        for (i in 1..3) {
            delay(400) // pretend we are doing something useful here
            emit(i) // emit next value
        }
    }

    @InternalCoroutinesApi
    fun appClient() = runBlocking<Unit> {
        val flow: Flow<Int> = flowProducer()

        // subscribe 1
        flow.collect{
            println("subscriber A: $it")
        }

        delay(1000)

        // subscribe 2
        flow.collect{
            println("subscriber B: $it")
        }

        // subscribe 3 - with cancellation
        withTimeoutOrNull(450){
            flow.collect{
                println("subscriber B: $it")
            }
        }
        println("--- done ---")

    }




}