package LocalFunctionCompare

class UserB(val id: Int, val name: String, val address: String)

fun UserB.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                    "Can't save user $id: empty $fieldName")
        }
    }

    validate(name, "Name")
    validate(address, "Address")
}

fun saveUser(userA: UserB) {
    userA.validateBeforeSave()

    // Save userA to the database
}

fun main(args: Array<String>) {
    saveUser(UserB(1, "", ""))
}
