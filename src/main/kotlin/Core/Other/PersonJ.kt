package Core.Other

/**
 * Created by upatryk on 11/10/2017.
 */
class PersonJ(val name: String, var age: Int) {

    val isAuthor: Boolean
        get() = this.name == "Patryk"

    override fun toString(): String {
        return "Core.Other.PersonJ{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}'
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val person = PersonJ("Pitos", 35)
            println(person)
            person.age = 36

        }
    }
}