package rxjavaS.impl;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class RetryWhen {

    public static void retryWhenExample(){

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .retryWhen(attempt -> attempt
                        .zipWith(Observable.range(1, 3), (n, i) -> i)
                        .flatMap(i -> Observable.timer(5 * i, TimeUnit.SECONDS)))
                .subscribe(viewModel -> {
                    // handle updated request state
                });
    }

}
