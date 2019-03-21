package Core.Enums

/**
 * Created by upatryk on 11/10/2017.
 */
enum class EnumJ private constructor(private val r: Int, private val g: Int, private val b: Int) {
    RED(255, 0, 0),
    BLUE(0, 0, 255),
    YELLOW(255, 255, 0);

    fun rgb(): Int {
        return (this.r * 256 + this.g) * 256 + this.b
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            println(BLUE.rgb())
        }
    }
}