package DesignPatterns.AdapterB

class USPlug {

    val volt: Volt
        get() = Volt(120)
}