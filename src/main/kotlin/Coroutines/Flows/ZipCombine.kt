package Coroutines.Flows

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


@ExperimentalCoroutinesApi
@InternalCoroutinesApi
suspend fun main() {
    val zc = ZipCombine()
    zc.zipExample()
    //zc.combineExample()
}

class ZipCombine {

    @ExperimentalCoroutinesApi
    suspend fun zipExample(){
        // A |---|---|---|---|
        // B |----|----|----|----|
        //AB |    |    |    |    |

        val numsA = (1..5).asFlow().onEach{ delay(300)}
        val numB = flowOf(4, 2, 5, 1, 7).onEach { delay(400) }

        numsA.zip(numB){ a, b -> a*b }
            .collect{
                println(it)
            }
    }


    @ExperimentalCoroutinesApi
    suspend fun combineExample(){
        // A |---|---|---|---|
        // B |----|----|----|----|
        //AB |   ||  | | |  ||   |

        val numsA = (1..5).asFlow().onEach{ delay(300)}
        val numsB = flowOf(4, 2, 5, 1, 7).onEach { delay(400) }

        numsA.zip(numsB){ a, b -> a*b }
            .collect{
                println(it)
            }

        println("----------------")

        numsA.combine(numsB){a, b -> a*b}
            .collect{
                println(it)
            }
    }



}