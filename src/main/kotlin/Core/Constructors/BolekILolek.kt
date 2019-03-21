package Core.Constructors

// in signature
class Lolek(val name : String)

// assigning from signature
class Ola(_name : String){
    val name = _name
}

// assigning from signature by init
class Bolek constructor(name : String){
    val name : String = name
}

// assigning in the constructor
class Atomek constructor(val name: String)


// assigning in with constructor
class Tomek{
    var name: String? = null
    var surname: String? = null

    constructor(name: String){
        this.name = name
    }

    constructor(surname: String, name: String){
        this.name = name
        this.surname = surname
    }
}

// creating a singleton
object Friend{
    //val friendList = arrayListOf<String>()
}


// using init and companionObject
class Tola {
    private val name: StringBuilder
    private val age: Int

    // 1, 3
    init {
        name = StringBuilder("name")
        age = 18
        println("class init ${name.javaClass.name} and $name")
    }

    companion object {
        internal val NUM = 1
        private val CODE: String

        // A singleton
        var tolaInstance: Tola? = null
            private set

        // 2
        init{
            CODE = "Tola"
            tolaInstance = Tola()
            println("companion object init $CODE and ${tolaInstance.toString()}")
        }
    }
}


class Romek internal constructor() {
    private var name: StringBuilder? = null
    private var age: Int? = null


    // 1, 3
    init {
        name = StringBuilder("name")
        age = 18
        println("class init ${name.toString()} and $age")
    }

    // 2
    companion object {
        internal val NUM = 1
        private val CODE: String

        var romekInstance: Romek? = null
            private set


        init{
            CODE = "Romek"
            romekInstance = Romek()
            println("companion object init in class constructor ${romekInstance.toString()} and $CODE")
        }
    }

}



class Bolo{
    private var name: StringBuilder? = null
    private var age: Int? = null

    internal constructor(){
        name = StringBuilder("name")
        age = 18
        println("class internal constructor init ${name.toString()} and $age")
    }

    companion object {
        internal val NUM = 1
        private val CODE: String

        var boloInstance: Bolo? = null
            private set

        init{
            CODE = "Bolo"
            boloInstance = Bolo()
            println("companion object init ${boloInstance.toString()} and $CODE")
        }
    }
}






fun main(args: Array<String>) {
    //val lolek = Lolek("Lolek")
    val tola = Tola()
    println("---------------")
    val romek = Romek()
    println("---------------")
    val bolo = Bolo()

}