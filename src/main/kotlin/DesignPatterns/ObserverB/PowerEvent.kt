package DesignPatterns.ObserverB

import java.util.*


fun main() {
    val powerObserverA = PowerObserver("ObserverA")
    val powerObserverB = PowerObserver("ObserverB")

    val powerObservable = PowerObservable()
    powerObservable.registerObserver(powerObserverA)
    powerObservable.registerObserver(powerObserverB)

    powerObservable.increasePower(40)
    powerObservable.increasePower(40)
    powerObservable.increasePower(40)
    powerObservable.decreasePower(20)

    powerObservable.removeObserver(powerObserverA)
    powerObservable.removeObserver(powerObserverB)
}

class PowerEvent(source: Any, val power: Int) : EventObject(source) {

}

interface PowerListener: EventListener{
    fun powerChanged(event: PowerEvent)
    fun powerExceeded(event: PowerEvent)
}

class PowerObservable{
    var currentPower = 0
    private val listeners = mutableListOf<PowerListener>()

    fun increasePower(power: Int){
        if (currentPower + power > 100){
            powerAlert(power)
        }else{
            currentPower += power
            powerChanged()
        }
    }

    fun decreasePower(power: Int){
        if (currentPower - power > 0){
            currentPower - power
        }else{
            currentPower = 0
        }

        powerChanged()
    }

    fun registerObserver(powerListener: PowerListener){
        listeners.add(powerListener)
    }

    fun removeObserver(powerListener: PowerListener){
        listeners.remove(powerListener)
    }


    private fun powerChanged(){
        val powerEvent = PowerEvent(this, this.currentPower)
        listeners.forEach {
            it.powerChanged(powerEvent)
        }
    }

    private fun powerAlert(power: Int){
        val powerEvent = PowerEvent(this, this.currentPower + power)
        listeners.forEach {
            it.powerExceeded(powerEvent)
        }
    }
}


class PowerObserver(val name: String) : PowerListener{

    override fun powerChanged(event: PowerEvent) {
        println("$name: Current power: ${event.power}")
    }

    override fun powerExceeded(event: PowerEvent) {
        println("$name: power exceeded: ${event.power}")
    }
}


