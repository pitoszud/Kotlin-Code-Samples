package FunctionalProgramming

val months: List<String> = arrayListOf(
        "Styczen",
        "Lute",
        "Marzec",
        "Kwiecien",
        "Maj",
        "Czerwiec",
        "Lipiec",
        "Sierpien",
        "Wrzesien",
        "Pazdziernik",
        "Listopad",
        "Grudzien")

val nums: List<Int> = listOf(1,2,3,4,5,6,7,8,9)




fun List<String>.average(predicate: (String) -> Boolean) =
        filter(predicate)
                .map(String::length)
                .average()

fun main(args: Array<String>) {
    val mv1 = months.average { it.length > 5 }
    val mv2 = months.average { it.length < 5 }

    val mv0 = months
            .filter { it.length > 5 }
            .map(String::length)
            .average()

    println("$mv0, $mv1, $mv2")

}

