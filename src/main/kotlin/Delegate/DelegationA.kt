package Delegate


interface A{
    fun doA(i: Int): String
}

class B(val j: Int): A {
    override fun doA(i: Int): String {
        return (i + j).toString()
    }
}

val b = B(12)
class C(val a: A): A by b


class D(private val impl: A): A by impl {
    override fun doA(i: Int): String {
        return impl.doA(i)
    }
}






class CounterList<T>(private val delegateB: MutableList<T> = mutableListOf()):
        MutableList<T> by delegateB {
    var addCount = 0

    override fun add(element: T): Boolean{
        addCount++
        return delegateB.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean{
        addCount += elements.size
        return delegateB.addAll(elements)
    }
}