package DesignPatterns.AdapterB

class UKPlug {

    val volt: Volt
        get() = Volt(220)
}