package rxjavaS.impl;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class Timers {
    public static void main(String[] args) {

    }


    public static void timeElapsed(){
        Observable<Long> timeObservable = Observable
                .timer(3, TimeUnit.SECONDS);

        timeObservable.subscribe(new Observer<Long>() {

            long time = 0;

            @Override
            public void onSubscribe(Disposable d) {
                time = System.currentTimeMillis() / 1000;
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println((System.currentTimeMillis()/1000)-time + " seconds");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
