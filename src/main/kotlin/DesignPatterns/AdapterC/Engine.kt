package DesignPatterns.AdapterC

interface Engine{
    fun getSize(): Int
    fun isTurbo(): Boolean
}

abstract class AbstractEngine(val engineSize: Int, val turbo: Boolean): Engine{
    override fun getSize(): Int {
        return engineSize
    }

    override fun isTurbo(): Boolean {
        return turbo
    }
}


class GreenEngine(val engineSize: Int){
    // this class does not implement isTurbo()
}




// 1. extend a class you are adapting to (AbstractEngine)
// 2. inject an object you are adapting from (Employee)
// 3. pass the injected object to extended class with super

class GreenEngineAdapterA(greenEngine: GreenEngine): AbstractEngine(greenEngine.engineSize, false)




