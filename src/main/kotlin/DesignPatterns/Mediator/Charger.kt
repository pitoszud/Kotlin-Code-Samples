package DesignPatterns.Mediator

class Charger(private val system: System) {
    var enabled: Boolean = false
    var chargeLevel: Int = 0

    init {
        system.charger = this
    }

    fun chargeToLevel(chargeLevel: Int){
        if (enabled){
            this.chargeLevel = chargeLevel
        }
    }


}