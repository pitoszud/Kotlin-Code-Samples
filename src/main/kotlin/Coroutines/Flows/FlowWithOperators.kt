package Coroutines.Flows

import kotlinx.coroutines.flow.*

import kotlinx.coroutines.*
import kotlin.random.Random

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
fun main() {
    val fwo = FlowWithOperators()
    fwo.appClient()
}

class FlowWithOperators {

    suspend fun intTokenToStrToken(intToken: Int): String{
        delay(400)
        return "$intToken"
    }

    suspend fun applyId(strToken: String): Pair<Int, String>{
        delay(400)
        return Pair(Random.nextInt(100, 500), strToken)
    }

    suspend fun pairToString(pair: Pair<Int, String>): String{
        return "Id: ${pair.first}, Item: ${pair.second}"
    }


    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    fun appClient() = runBlocking<Unit> {
        val flow: Flow<Int> = (1..6).asFlow()

        flow
            .transform {
                emit(it * 2)
            }
            .take(3)
            .map { intTokenToStrToken(it) }
            .map { applyId(it) }
            .map{ pairToString(it) }
            .collect { println(it) }
    }

}