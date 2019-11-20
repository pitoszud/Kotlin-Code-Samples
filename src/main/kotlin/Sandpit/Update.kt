package Sandpit

fun main() {

}


class RegWindowLineItemUI(
    val productGuid: String = "",
    val parentProductGuid: String = "",
    val productName: String = ""
)




fun regList(): MutableList<RegWindowLineItemUI>{
    val p1 = RegWindowLineItemUI(
        "10",
        "000",
        "Tree"
    )
    val ch1_p1 = RegWindowLineItemUI(
        "11",
        "10",
        "Pear"
    )
    val ch2_p1 = RegWindowLineItemUI(
        "12",
        "10",
        "Apple"
    )
    val ch3_p1 = RegWindowLineItemUI(
        "13",
        "10",
        "Plum"
    )
    val p3 = RegWindowLineItemUI(
        "20",
        "000",
        "Vehicle"
    )
    val p4 = RegWindowLineItemUI(
        "30",
        "000",
        "Computer"
    )
    val p5 = RegWindowLineItemUI(
        "40",
        "000",
        "Road"
    )
    val p6 = RegWindowLineItemUI(
        "50",
        "000",
        "Weather"
    )
    val ch1_p6 = RegWindowLineItemUI(
        "51",
        "50",
        "Sunny"
    )
    val ch2_p6 = RegWindowLineItemUI(
        "52",
        "50",
        "Rain"
    )
    val ch3_p6 = RegWindowLineItemUI(
        "53",
        "50",
        "Tree"
    )
    val ch4_p6 = RegWindowLineItemUI(
        "54",
        "50",
        "Tree"
    )

    return mutableListOf(p1, ch1_p1, ch2_p1, p3, p4, p5, p6, ch1_p1, ch2_p6, ch3_p6, ch4_p6)
}
