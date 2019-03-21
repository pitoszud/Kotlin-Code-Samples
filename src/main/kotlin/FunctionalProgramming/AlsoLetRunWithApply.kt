package FunctionalProgramming

import java.lang.StringBuilder

fun let1(user: ServiceUser){
    val res: Int? = user.let {
        println(it)
        it.userAge?.minus(18)
    }

    val res2: Boolean = user.let {
        println(it.userName)
        true
    }

    println("res: $res")
}


fun run1(user: ServiceUser){
    val res: Int? = user.run {
        println(this)
        userAge?.minus(18)
    }

    val res2: Boolean = user.run {
        println(userName)
        true
    }

    println(res)
}

// User if:
// your receiver is non-nullable
// you want to perform some operations on an the receiver
// you want to return some OTHER object (optional)

fun with1(user: ServiceUser){
    val res: StringBuilder = with(user) {
        println(userName + this.userAge) // get
        userName = userName?.reversed() // modify object
        StringBuilder(userName) // return different object
    }

    val res2: Boolean = with(user){
        login()
        logout()
        true
    }

    println(res.toString())
}

// Use if:
// the block does not access its receiver parameter
// it does not mutate its receiver parameter

fun also1(user: ServiceUser){
    val res: ServiceUser = user.also {
        println(it.userAge)
    }

    val res2: ServiceUser = user.also {
        it.userAge = 0
    }

    println(res)
    println(user)
}



// Use if:
// you are not accessing any functions of the receiver within the block
// you want to modify the receiver
// you want to return THE SAME receiver

fun apply1(){
    val res = ServiceUser().apply {
        println(this.userName) // get
        this.userName = userName?.reversed() // modify object
    }
    println(res)
}



fun main() {

}



class ServiceUser {
    var userAge: Int? = null
    var userName: String? = null

    fun login(){

    }

    fun logout(){

    }
}
