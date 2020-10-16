package Generics

interface CustomList<T>{
    fun get(i: Int): T
}

class NameList: CustomList<String>{
    override fun get(i: Int): String {
        return "name"
    }
}

fun main() {
    val nameList: CustomList<String> = NameList()
}

