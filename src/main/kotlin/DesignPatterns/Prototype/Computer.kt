package DesignPatterns.Prototype

import DesignPatterns.Prototype.Computer.OS



interface Computer: Cloneable{
    enum class OS {
        NO_INSTALLATION,
        WINDOWS,
        UBUNTU,
        IOS,
        ANDROID,
        CHROME
    }

    fun getCPU(): CPU?
    fun getOS(): Computer.OS
    fun install(os: Computer.OS)

    override fun clone(): Object
}

abstract class AbstractComputer: Computer{
    private var cpu: CPU? = null
    private var os: Computer.OS? = null

    constructor(cpu: CPU){
        this.cpu = cpu
        this.os = OS.NO_INSTALLATION
    }

    constructor(cpu: CPU, os: Computer.OS){
        this.cpu = cpu
        this.os = os
    }

    override fun getCPU(): CPU?{
        return this.cpu
    }

    //  tbc


}



class CPU{


}