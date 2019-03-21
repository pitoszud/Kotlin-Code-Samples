package FunctionalProgramming.DSLs

// <T> template type

fun <T : Any> T?.whenNotNull(f: (it: T) -> Unit) {
    when {
        this != null -> f(this)
    }
}



fun Any?.ifNotNull(f: ()-> Unit){
    when {
        this != null -> f()
    }
}



fun main(args: Array<String>) {
    val str: String? = null

    str.whenNotNull{
        println(it.length)
    }


    str.ifNotNull {
        println(str?.length)
    }


    str?.let{
        println(it.length)
    }


    str ?: println(str?.length) // prints: null


    str ?: run {
        println(str?.length) // prints: null
    }





}