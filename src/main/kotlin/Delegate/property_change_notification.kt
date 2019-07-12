package Delegate

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport

// manual

open class PropertyChangeManager{
    protected val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener){
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener){
        changeSupport.removePropertyChangeListener(listener)
    }

}

class Person(val name: String, age: Int, salary: Int): PropertyChangeManager(){

    var age: Int = age
    set(newValue){
        val oldValue = field // field is a copy of age
        field = newValue
        changeSupport.firePropertyChange("age", oldValue, newValue)
    }

    var salary: Int = salary
    set(newValue){
        val oldValue = field
        field = newValue
        changeSupport.firePropertyChange("salary", oldValue, newValue)
    }
}


fun main() {
    val p = Person("Pitos", 37, 2000)

    p.addPropertyChangeListener(
        PropertyChangeListener {event ->
            println("Property ${event.propertyName} changed from ${event.oldValue} to ${event.newValue}")
        }
    )

    p.age = 37
    p.salary = 2500

}