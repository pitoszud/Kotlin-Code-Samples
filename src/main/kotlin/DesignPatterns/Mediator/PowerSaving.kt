package DesignPatterns.Mediator

class PowerSaving(private val mediator: System, var enabled: Boolean = false){

    var applied: Boolean = false

    init {
        mediator.powerSaving = this
    }


    fun enable(chargeLevel: Int){
        if (chargeLevel > 50){
            enabled = true
            mediator.powerSavingEnabled()
        }
    }


    fun disable(chargeLevel: Int){
        if (chargeLevel < 50){
            enabled = false
            mediator.powerSavingDisabled()
        }
    }

}