package FunctionalProgramming.Inline

inline class Inch(val inchLength: Double){
    fun toCent() = inchLength / 1.6
}

inline class Cent(val centLenght: Double){
    fun toInch() = centLenght * 1.6
}


fun main(args: Array<String>) {
    val lengthVal = Cent(36.00) // double lengthVal = 36.00
    lengthVal.toInch() // public static final double toInch(double this){return this * 1.6}
}


// Examples of Valid and non-valid inline classes

// inline class Inch()              // DOES NOT COMPILE - needs to accept a value!
// inline class Inch(value: Double)    // DOES NOT COMPILE - value needs to be a property
// inline class Inch(var value: Double)  // DOES NOT COMPILE - property needs to be read-only
// inline class Inch(val value1: Double, val value2: Double)  // DOES NOT COMPILE - must have only one property
// inline class Inch private constructor(val value: Double) // DOES NOT COMPILE - constructor must be public
// inline class Inch(val value: Double): Number()  // DOES NOT COMPILES - class inheritance not allowed


// inline class Inch(val value: Double)           // COMPILES
// inline class Inch(private val value: Double)   // COMPILES
// inline class Inch(val value: Double): Number   // COMPILES - extending einterface allowed
