package Core.Singletons

open class SingletonHolder<out T, in A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator
    @Volatile private var instance: T? = null

    fun getInstance(arg: A): T {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}

class SingletonWithParameter private constructor(context: Context){
    init {
        SingletonWithParameter(context)
    }

    companion object: SingletonHolder<SingletonWithParameter, Context>(::SingletonWithParameter)

    fun fetchData(){

    }
}


class FragmentA{

    fun perform(context: Context){
        SingletonWithParameter.getInstance(context).fetchData()
    }
}




class Context