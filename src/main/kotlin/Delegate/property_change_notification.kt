package Delegate

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.reflect.KProperty

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

 // ------------------------------------------------------------------------------------------------


class Person1(val name: String, age: Int, salary: Int): PropertyChangeManager(){

    // fire property change in Person class

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


 // ----------------------------------------------------------------------------------------------------

class ObservableProperty2(val name: String, var pValue: Int, val change: PropertyChangeSupport){

    fun getValue(): Int = pValue

    fun setValue(newValue: Int){
        val oldValue = pValue
        pValue = newValue
        change.firePropertyChange(name, oldValue, newValue)
    }
}


class Person2(val name: String, age: Int, salary: Int): PropertyChangeManager(){

    // delegate fire property change to ObservableProperty

    val ageObservable = ObservableProperty2("age", age, changeSupport)

    var age: Int
        get() = ageObservable.getValue()
        set(newValue){
            ageObservable.setValue(newValue)
        }


    val salaryObservable = ObservableProperty2("salary", salary, changeSupport)

    var salary: Int
        get() = salaryObservable.getValue()
        set(newValue){
            salaryObservable.setValue(newValue)
        }
}


 // ------------------------------------------------------------------------------------------------

interface Person

class ObservableProperty3(var pValue: Int, val change: PropertyChangeSupport){

    operator fun getValue(p: Person, prop: KProperty<*>): Int = pValue

    operator fun setValue(p: Person, prop: KProperty<*>, newValue: Int){
        val oldValue = pValue
        pValue = newValue
        change.firePropertyChange(prop.name, oldValue, newValue)
    }
}


class Person3(val name: String, age: Int, salary: Int): PropertyChangeManager(), Person {

    // using Kotlin standard Library

    var age: Int by ObservableProperty3(age, changeSupport)
    var salary: Int by ObservableProperty3(salary, changeSupport)

    private val observer = {
            prop: KProperty<*>, oldValue: Int, newValue: Int ->
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}



class Person4(val name: String, age: Int, salary: Int): PropertyChangeManager(), Person {

    // using Kotlin standard Library

    var age: Int by ObservableProperty3(age, changeSupport)
    var salary: Int by ObservableProperty3(salary, changeSupport)

    private val observer = {
            prop: KProperty<*>, oldValue: Int, newValue: Int ->
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}





// --------------------------------------------------------------------------------------------------


fun main() {
    val p1 = Person1("Pitos", 37, 2000)

    p1.addPropertyChangeListener(
        PropertyChangeListener {event ->
            println("Property ${event.propertyName} changed from ${event.oldValue} to ${event.newValue}")
        }
    )

    p1.age = 37
    p1.salary = 2500


    val p3 = Person3("Pitos", 37, 2000)


}