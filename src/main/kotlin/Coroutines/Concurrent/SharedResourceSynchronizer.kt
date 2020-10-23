package Coroutines.Concurrent

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

fun main() {

}


data class Item(
    val itemId: String = "",
    val name: String = ""
)

class SharedResourceSynchronizer(
    private val timer: ReceiveChannel<Unit> = ticker(
        delayMillis = TimeUnit.SECONDS.toMillis(1),
        initialDelayMillis = 0
    )
): CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO
    private val sharedResouce = mutableListOf<Item>()

    private val mutex = Mutex()

    suspend fun addItems(items: List<Item>){
        mutex.withLock {
            sharedResouce.addAll(items)
        }
    }

    /*
    * network / clear
    * */
    fun startSync(){
        launch {
            for (event in timer){
                delay(300) // Network request
                mutex.withLock {
                    sharedResouce.clear()
                }
            }
        }
    }

    /*
    * copy / clear / return / network
    * */
    fun startSyncOptimised(){
        launch {
            for (event in timer){
                val queueCopy: List<Item> = mutex.withLock {
                    val sharedResourcesCopy: List<Item> = sharedResouce.map{
                        it.copy()
                    }
                    sharedResouce.clear()
                    sharedResourcesCopy
                }
                delay(300) // Network request
            }
        }
    }


}