package bit_manipulation

fun Int.to32bitString(): String =
    Integer.toBinaryString(this).padStart(Int.SIZE_BITS, '0')