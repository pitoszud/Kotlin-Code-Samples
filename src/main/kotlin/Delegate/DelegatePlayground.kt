package Delegate

import kotlin.properties.Delegates.notNull
import kotlin.properties.Delegates.observable
import kotlin.properties.Delegates.vetoable

// use this delegate for log changes, cache old values etc
var counter: Int by observable(initialValue = 0){ property, oldValue, newValue ->
    println("${property.name} changed from $oldValue to $newValue")
}


// use this delegate to implement simple validation or constraint on the value(s)
var age: Int by vetoable(initialValue = 6){property, oldValue, newValue ->
    newValue > 18
}

// use this delegate to replace lateinit, where you cannot user non-primitive types.
var length: Int by notNull()
// lateinit var width: Int // DOES NOT COMPILE





fun main() {
    counter ++
    counter += 2
    counter --

    age += 6

    length++
}


class User