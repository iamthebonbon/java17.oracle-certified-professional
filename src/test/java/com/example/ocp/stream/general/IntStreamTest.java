package com.example.ocp.stream.general;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

public class IntStreamTest {

    @Test
    public void test() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            OptionalDouble average = IntStream.of().average();
            average.getAsDouble();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            OptionalInt min = IntStream.of().min();
            min.getAsInt();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            OptionalInt max = IntStream.of().max();
            max.getAsInt();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            IntBinaryOperator intBinaryOperator = Integer::sum;
            IntUnaryOperator intUnaryOperator = x -> x + 1;
            IntPredicate predicate = x -> x > 0;
            OptionalInt max = IntStream.of()
                    .map(intUnaryOperator)
                    .filter(predicate)
                    .reduce(intBinaryOperator);
            max.getAsInt();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            IntBinaryOperator intBinaryOperator = Integer::sum;
            IntUnaryOperator intUnaryOperator = x -> x + 1;
            IntPredicate predicate = x -> x > 0;
            Integer i = 1;
            ToIntFunction<Integer> f = i::compareTo;
            OptionalInt max = IntStream.of()
                    .map(intUnaryOperator)
                    .filter(predicate)
                    .max();
            max.getAsInt();
        });
//        Assertions.assertThrows(NoSuchElementException.class, () -> {
        Integer i = 1;
        UnaryOperator<Integer> f = i::compareTo;
        Optional<Integer> max = IntStream.of(1, 2, 3)
                .boxed()
                .map(f)
                .reduce((i1, i2) -> i1 + i2);
        Assertions.assertTrue(max.get() == -2);
//        });
    }
}
