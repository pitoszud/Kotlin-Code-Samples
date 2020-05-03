package DesignPatterns.Mediator

class PowerSaving(private val system: System){

    var enabled: Boolean = false
    var applied: Boolean = false

    init {
        system.powerSaving = this
    }


    fun enable(){
        enabled = true
        system.powerSavingEnabled()
    }

}