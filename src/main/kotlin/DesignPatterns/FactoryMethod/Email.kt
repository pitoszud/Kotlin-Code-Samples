package DesignPatterns.FactoryMethod

import java.text.SimpleDateFormat
import java.util.*

// 0. abstract item
abstract class Notification{
    var notificationMessage: String = ""

    abstract fun getNotification(): String

    fun addTitle(title: String){
        notificationMessage += title
    }

    fun encrypt(){

    }

}


// 1.a concrete item
class SimpleNotification: Notification(){
    override fun getNotification(): String {
        return "This is simple notification"
    }
}

// 1.b concrete item
class AdvancedNotification: Notification(){
    override fun getNotification(): String {
        val todayDate = SimpleDateFormat("dd//MM/yy").format(Date(System.currentTimeMillis()))
        return "this is advanced notification. Today date is $todayDate"
    }
}


// 2. abstract creator
abstract class NotificationCreator{

    fun getNotification(): Notification{
        return createNotification().apply {
            addTitle("Default Item")
            encrypt()
        }
    }

    // Factory method
    abstract fun createNotification(): Notification
}


// 3.a concrete creator
class SimpleNotificationCreator: NotificationCreator(){
    override fun createNotification(): Notification {
        return SimpleNotification()
    }

}

// 3. concrete creator
class AdvancedNotificationCreator: NotificationCreator(){
    override fun createNotification(): Notification {
        return AdvancedNotification()
    }

}

fun main() {
    val simpleNotification = SimpleNotificationCreator().getNotification()
    println(simpleNotification.notificationMessage)
}



