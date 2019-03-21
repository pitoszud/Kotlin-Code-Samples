package DesignPatterns.AdapterB

interface SocketAdapter {

    fun convertToUS(): Volt
    fun convertToUK(): Volt
}