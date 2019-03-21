package Core.ExtensionsFunct

open class Bird{
    open fun fly() = "bird is flying"
}

open class Duck: Bird(){
    override fun fly() = "Duck is flying"

    fun Duck.speak() = "quack, quack..."
}


fun Bird.speak(): String = "tweet, tweet..."
fun Duck.speak(): String = "quack, quack..."

fun expressYourself(bird: Bird){
    println(bird.fly())
    println(bird.speak())
}


fun main(args: Array<String>) {
    expressYourself(Bird())
    expressYourself(Duck())
}

//bird is flying
//tweet, tweet...
//Duck is flying
//tweet, tweet... <== Duck is tweeting !!!