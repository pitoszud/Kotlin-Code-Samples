package Core.Enums

/**
 * Created by upatryk on 11/10/2017.
 */
class EnumMgr {
    private val code: String? = null
    private val id: Int = 0
    var blue: BLUE? = null
        private set

    enum class BLUE {
        lightBlue, darkBlue, navy, turcouse
    }

    fun setColor(shade: String) {
        if (shade == "lb") {
            this.blue = BLUE.lightBlue
        }
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val ej = EnumJ.BLUE
            System.out.println(ej)
            System.out.println(EnumJ.RED)

            val b1 = BLUE.lightBlue

            val emgr = EnumMgr()

        }
    }
}