package DesignPatterns.FactoryMethod

import codingInterview.fac
import java.text.SimpleDateFormat
import java.util.*

// 0. abstract item
abstract class Notification{

    abstract fun getNotificationMessage(): String

    fun addTitle(title: String){
        println("Adding title $title")
    }

    fun encrypt(){
        println("Encrypting file")
    }

}


// 1.a concrete item
class SimpleNotification: Notification(){
    override fun getNotificationMessage(): String {
        return "This is simple notification"
    }
}

// 1.b concrete item
class AdvancedNotification: Notification(){
    override fun getNotificationMessage(): String {
        val todayDate = SimpleDateFormat("dd//MM/yy").format(Date(System.currentTimeMillis()))
        return "this is advanced notification. Today date is $todayDate"
    }
}


// 2. abstract Factory
abstract class NotificationFactory{

    fun getNotification(): Notification{
        println("Performing pre-processing...")

        val notification =  createNotification()

        println("performing post-processing...")

        return notification
    }

    // Factory method
    abstract fun createNotification(): Notification
}


// 3.a concrete Factory
class SimpleNotificationFactory: NotificationFactory(){
    override fun createNotification(): Notification {
        return SimpleNotification()
    }

}

// 3.b concrete Factory
class AdvancedNotificationFactory: NotificationFactory(){
    override fun createNotification(): Notification {
        return AdvancedNotification()
    }
}


// 4. Factory Controller
class FactoryController(private var factory: NotificationFactory){
    private var notification: Notification? = null

    fun initialize() {
        notification = factory.getNotification() // with pre/post processing
        // notification = factory.createNotification() // without pre/post processing
        println(notification?.getNotificationMessage())
    }

}

fun main() {
    // initialization in Controller
    val controller = FactoryController(AdvancedNotificationFactory())
    controller.initialize()

    // initialization in Client
    val simpleNotification = SimpleNotificationFactory().getNotification()
    println(simpleNotification.getNotificationMessage())
}



