package DesignPatterns.Mediator

class Charger(private val mediator: System) {
    var enabled: Boolean = false
    var chargeLevel: Int = 0

    init {
        mediator.charger = this
    }

    fun chargeToLevel(chargeLevel: Int){
        if (enabled){
            this.chargeLevel = chargeLevel
        }
    }


}