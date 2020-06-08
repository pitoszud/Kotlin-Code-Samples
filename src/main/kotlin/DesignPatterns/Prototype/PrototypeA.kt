package DesignPatterns.Prototype

import java.util.Hashtable




// 1. abstract class implementing Cloneable interface
abstract class Knive : Cloneable {

    var id: String? = null
    var type: String? = null


    internal abstract fun cut()

    public override fun clone(): Any {
        var clone: Any? = null

        try {
            clone = super.clone()

        } catch (e: CloneNotSupportedException) {
            e.printStackTrace()
        }

        return clone!!
    }
}

// 2. concrete class extending Abstract Cloneable
class ChefKnive : Knive() {
    init {
        type = "chefKnive"
    }

    override fun cut() {
        println("cutting with $type")
    }
}

class BreadKnive : Knive() {
    init {
        type = "breadKnive"
    }

    override fun cut() {
        println("cutting with $type")
    }
}


// 3. get concrete classes from db and store them in a hashtable

object KniveCache {

    private val kniveMap = Hashtable<String, Knive>()

    //
    fun getType(kniveId: String): Knive {
        val cachedKnive: Knive? = kniveMap[kniveId]
        return cachedKnive!!.clone() as Knive
    }


    fun loadCache() {
        val chefKnive = ChefKnive()
        chefKnive.id = "chefKnive"
        kniveMap[chefKnive.id] = chefKnive

        val breadKnive = BreadKnive()
        breadKnive.id = "breadKnive"
        kniveMap[breadKnive.id] = breadKnive
    }
}


// 4. get clones from hashtable

fun main(args: Array<String>) {
    KniveCache.loadCache()

    val k1: Knive = KniveCache.getType("chefKnive")
    println(k1.type)

    val k2: Knive = KniveCache.getType("breadKnive")
    println(k2.type)
}
