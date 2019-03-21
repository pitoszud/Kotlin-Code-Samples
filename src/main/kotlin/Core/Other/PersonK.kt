package Core.Other

/**
 * Created by upatryk on 11/10/2017.
 */
class PersonK(val name: String, var age: Int) {

    companion object {

        @JvmStatic fun main(args: Array<String>) {
            val person = PersonK("Pitos", 35)
            println(person)
            person.age = 36
            println(person.age)
        }
    }
}

class UserK(val name: String, var age: Int)


