package Coroutines.ReposotoryExample

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class DataRepositoryImpl: DataRepository {

    override suspend fun asyncFetchByName(feedName: String): Feed {
        delay(1000)
        return Feed(1234, feedName, null)
    }

    override suspend fun asyncFetchById(id: Long): Feed {
        delay(1000)
        return Feed(id, "1244", null)
    }

}


fun main() = runBlocking {
    val repository: DataRepository = DataRepositoryImpl()
    val feed: Feed = repository.asyncFetchByName("FeedA")
    println(feed)
}




class Feed(
    val feedId: Long,
    val feedName: String,
    val feedUrl: String?
){
    override fun toString(): String {
        return "feedId: $feedId, feedName: $feedName"
    }
}



interface DataRepository {
    suspend fun asyncFetchByName(feedName: String): Feed
    suspend fun asyncFetchById(id: Long): Feed
}