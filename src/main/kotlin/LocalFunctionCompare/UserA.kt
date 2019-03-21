package LocalFunctionCompare

class UserA(val id: Int, val name: String, val address: String)

fun saveUser(user: UserA) {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                    "Can't save userA ${user.id}: " +
                            "empty $fieldName")
        }
    }

    validate(user.name, "Name")
    validate(user.address, "Address")
    //validate(name, "Name") - DOES NOT COMPILE

    // Save userA to the database
}

fun main(args: Array<String>) {
    saveUser(UserA(1, "", ""))
}