package DesignPatterns.State

interface PadSetupState{
    fun previousPosition()
    fun nextPosition()
    fun selectPosition()

    fun getInstructions(): String
    fun getSelectedPosition(): Int
}
