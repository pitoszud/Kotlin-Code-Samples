package Core.Constructors

fun getFacebookName(accountId: Int) = "fb:$accountId"

class UserA {
    val nickname: String
    constructor(email: String) {
        nickname = email.substringBefore('@')
    }
    constructor(facebookAccountId: Int) {
        nickname = getFacebookName(facebookAccountId)
    }
}


class UserB private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String) =
                UserB(email.substringBefore('@'))
        fun newFacebookUser(accountId: Int) =
                UserB(getFacebookName(accountId))
    }
}

fun main(args: Array<String>) {

}