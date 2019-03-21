package rxjavaS.impl;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.ThreadLocalRandom;

public class Concurrency {
    public static void main(String[] args) {
        concEx();
    }


    public static void concEx(){
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(Concurrency::longCalculation)
                .subscribe(System.out::println);

        Observable.range(1,6)
                .subscribeOn(Schedulers.computation())
                .map(Concurrency::longCalculation)
                .subscribe(System.out::println);

        sleep(20000);
    }



    public static <T> T longCalculation(T value) {
        sleep(ThreadLocalRandom.current().nextInt(3000));
        return value;
    }
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
