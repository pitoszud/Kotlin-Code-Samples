package Core.collections;

import java.util.Collection;

public class testColJava {
    public static void main(String[] args) {

    }

    //https://kotlinlang.org/docs/reference/generics.html

    // Collection<in T>
    void demo1(Collection<String> c) {
        Collection<? extends Object> objects = c; // c must extend Object
    }

    // Collection<out T>
    void demo2(Collection<Object> c) {
        Collection<? super String> objects = c; // c must be a supertype of String
    }



}
