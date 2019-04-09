package rxjavaS.impl;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.ThreadLocalRandom;

public class Concurrency {
    public static void main(String[] args) {
        concEx();
    }


    public static void concEx(){

        // the size of the Computation Scheduler's pool is fixed to the number of cores available in the system.
        // if there are more jobs to run than the count of processors, they will have to wait until a worker becomes available again.
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(Concurrency::longCalculation)
                .subscribe(System.out::println);

        // Concurrency can be achieved by creating n-number of Observables running on its own thread
        Observable.range(1,6)
                .flatMap(i -> Observable.just(i)
                        .subscribeOn(Schedulers.io())
                        .map(Concurrency::longCalculation)
                )
                .map(Object::toString)
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
