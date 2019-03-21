package Core.CompanionBobjects

fun getFacebookName(accountId: Int) = "fb:$accountId"

open class User private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String) =
                User(email.substringBefore('@'))

        fun newFacebookUser(accountId: Int) =
                User(getFacebookName(accountId))
    }
}

open class Person(val name : String){
    companion object Loader : Decorator<Person> {
        fun newSubscribingUser(email: String) =
                Person(email.substringBefore('@'))

        fun newFacebookUser(accountId: Int) =
                Person(getFacebookName(accountId))

        // inteface
        override fun loadUser(id: Int): Person = newFacebookUser(id)

    }
}

interface Decorator<out T>{
    fun loadUser(id : Int) : T
}




fun main(args: Array<String>) {
    val subscribingUser = User.newSubscribingUser("bob@gmail.com")
    val facebookUser = User.newFacebookUser(4)
    println(subscribingUser.nickname)
    println(facebookUser)

    val subscribingPerson = Person.Loader.newSubscribingUser("lolel@gmail.com")
    val facebookPerson = Person.Loader.newFacebookUser(5)
    println(subscribingPerson.name)
    println(facebookPerson)


}