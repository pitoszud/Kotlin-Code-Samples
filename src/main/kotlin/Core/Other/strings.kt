package Core.Other

/**
 * Created by upatryk on 11/10/2017.
 */
fun main(args: Array<String>) {
    val s1 = "12.345-6.A\\486.CG/57"
    println(s1.split("\\.|-|\\\\|\\/".toRegex()))
    println(s1.split(".", "\\","/","-"))
}