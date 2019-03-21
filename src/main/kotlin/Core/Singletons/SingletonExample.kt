package Core.Singletons

class SingletonExample private constructor() {

    // 2
    init {
        INSTANCE = this
        println("init complete")
    }

    companion object {
        lateinit var INSTANCE: SingletonExample

        // 1
        init {
            SingletonExample()
        }
    }
}

// Java SingletonExample

//public final class SomeSingleton {
//    public static final SomeSingleton INSTANCE;
//
//    private SomeSingleton() {
//        INSTANCE = (SomeSingleton)this;
//        System.out.println("init complete");
//    }
//
//    static {
//        new SomeSingleton();
//    }
//}