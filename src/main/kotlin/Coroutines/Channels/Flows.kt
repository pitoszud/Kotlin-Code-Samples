package Coroutines.Channels

import LambdaStreams.Patterns.Points
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.*


@ExperimentalCoroutinesApi
fun main() = runBlocking {
    flowExampleA()
}

@ExperimentalCoroutinesApi
fun flowExampleA() = runBlocking{

    val posList: List<ProductPOS> = getPosList()

   val flow: Flow<ProductPOS> = flowOf(posList[0], posList[1]).flowOn(Dispatchers.IO)


    // PRODUCER
    // ------------------------------------------
    // CONSUMER

    val consumer = runBlocking<Unit> {
        flow.collect{
            println(it.name)
        }
    }



    fun Flow<ProductPOS>.getNames(): Flow<String>{
        return flow{
            collect{
                emit(it.toString())
            }
        }
    }



}