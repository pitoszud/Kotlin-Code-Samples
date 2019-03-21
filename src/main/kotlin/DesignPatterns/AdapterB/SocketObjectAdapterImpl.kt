package DesignPatterns.AdapterB

class SocketObjectAdapterImpl : SocketAdapter {
    private var usPlug: USPlug? = null
    private var ukPlug: UKPlug? = null

    constructor() {
        usPlug = USPlug()
        ukPlug = UKPlug()
    }

    constructor(ukPlug: UKPlug) {
        this.ukPlug = ukPlug
    }

    constructor(usPlug: USPlug) {
        this.usPlug = usPlug
    }

    override fun convertToUS(): Volt {
        return this.usPlug!!.volt
    }

    override fun convertToUK(): Volt {
        return this.ukPlug!!.volt
    }
}