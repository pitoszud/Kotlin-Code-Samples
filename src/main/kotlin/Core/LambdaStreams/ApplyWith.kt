package Core.LambdaStreams


// WITH

// use "with" to perform some operations on an object
// and return some OTHER object
fun makePancake(): Pancake{
    // an input object must be created or added as a function argument
    val recipe = Recipe()
    return with(recipe){
        // do something with the recipe and return Pancake
        this.name = "Blueberry"
        Pancake()
    }
}


// APPLY

// use "apply" when you need to do something with an object
// and return THE SAME object
fun modifyRecipe() : Recipe{
    return Recipe().apply{
        // modify the Recipe object and return it
        this.name = "blueberry meal"
    }
}



class Pancake{
    lateinit var name: String

    constructor()

    constructor(name: String){
        this.name = name
        println(this)
    }
}

class Recipe{
    var name: String? = null

    constructor()

    constructor(name: String){
        this.name = name
        println(this)
    }
}

fun main(args: Array<String>) {

    makePancake()
    modifyRecipe()


}
