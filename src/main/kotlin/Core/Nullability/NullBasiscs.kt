package Core.Nullability

fun main(args: Array<String>) {
    // i can be a null object
    val x: Int? = null

    // checked (nothing returned) or...
    val z = 5.5
    if (z != null){
        val y = z.toDouble()
    }

    // check with ?. (nothing returned)
    val m = x?.toDouble()



    // checked (Double returned) or...
    val t = if (x != null) {
        x.toDouble()
    } else {
        0.0
    }

    // check with ?. (Double returned)
    val p = x?.toDouble() ?: 0.0


    // convert null to non-null
    val d = x!! * x
}