package Core.LambdaStreams

data class User(var name : String, val age : Int, val sex : String, val siblings : List<String>)

fun main(args: Array<String>) {
    val users = listOf(
            User("Pitos", 36, "male", listOf("Kamil", "Eryk")),
            User("Iwona", 35, "female", listOf("Gosia")),
            User("Oliwia", 2, "female",listOf("None")),
            User("Asia",34,"female",listOf("Adam", "Staszek")),
            User("Marysia",6,"female",listOf("None")))

    println(users.groupBy { it.sex })
    println(users.groupBy(User::sex))
    val name_l : Set<String> = users.flatMap { it.siblings }.toSet()
    println(name_l)



    // Lazy collection

    // using temporary object
    val ldy_list1 = users
            .map(User::name)
            .filter { it.endsWith("a") }
            .toList()
    println(ldy_list1)

    // using sequence
    val ldy_list2 = users
            .asSequence()
            .map(User::name)
            .filter { it.endsWith("a") }
            .toList()
    println(ldy_list2)
}


