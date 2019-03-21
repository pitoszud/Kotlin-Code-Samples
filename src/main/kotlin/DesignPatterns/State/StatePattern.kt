package DesignPatterns.State

interface Fragment{
    fun onCreate()
    fun bindView(view: String)
}

abstract class BaseFragment: Fragment{
    var viewConcrete: String? = null
    var height = 0

    companion object {
        var viewAbstract = "none"
    }

    override fun onCreate() {
    }


    override fun bindView(view: String){
        this.viewConcrete = view
        viewAbstract = view
    }
}


class FragmentA: BaseFragment(){

    fun bind(view: String){
        bindView(view)
    }

    fun printView(): String? {
        return "$viewConcrete ,$viewAbstract"
    }
}


class FragmentB: BaseFragment(){
    fun bind(view: String){
        bindView(view)
    }

    fun printView(): String? {
        return "$viewConcrete , $viewAbstract"
    }
}

fun main(args: Array<String>) {
    val fra = FragmentA()
    val frb = FragmentB()

    fra.bind("FragmentA") // FragmentA ,FragmentA
    println(fra.printView())

    frb.bind("FragmentB") // FragmentB , FragmentB
    println(frb.printView())

    println("-------------------------")

    println(fra.printView()) // FragmentA ,FragmentB
    println(frb.printView()) // FragmentB , FragmentB

    println("-----------")

    frb.bind("FragmentA") // FragmentA ,FragmentA
    println(fra.printView())

}
