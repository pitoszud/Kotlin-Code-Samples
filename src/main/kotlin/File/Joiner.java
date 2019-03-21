package File;

import java.util.stream.Stream;

public class Joiner {
    void exampleConcatTwo() {
        Stream<String> a = Stream.of("one", "two", "three");
        Stream<String> b = Stream.of("three", "four");
        Stream<String> out = Stream.concat(a, b);
        out.forEach(System.out::println);
    }

    void exampleReduce() {
        Stream<String> a = Stream.of("one", "two", "three");
        Stream<String> b = Stream.of("three", "four");
        Stream<String> c = Stream.of("five", "six");
        Stream<String> out = Stream.of(a, b, c)
                .reduce(Stream::concat)
                .orElseGet(Stream::empty);
        out.forEach(System.out::println);
    }


    void exampleFlatMap() {
        Stream<String> a = Stream.of("one", "two", "three");
        Stream<String> b = Stream.of("three", "four");
        Stream<String> c = Stream.of("five", "six");
        Stream<String> out = Stream.of(a, b, c).flatMap(s -> s);
        out.forEach(System.out::println);
    }

    public static void main(String[] args) {
        Joiner j = new Joiner();
        j.exampleConcatTwo();
        j.exampleReduce();
        j.exampleFlatMap();
    }
}
