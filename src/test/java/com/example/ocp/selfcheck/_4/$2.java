package com.example.ocp.selfcheck._4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class $2 {

    // ================================================================
    // 1. Creating / supplying streams
    // ================================================================

    @Test
    void createStreamsVariousWays() {
        Stream<String> s1 = Stream.of("a", "b", "c");
        Assertions.assertEquals(List.of("a", "b", "c"), s1.collect(Collectors.toList()));

        Stream<String> s2 = List.of("x", "y").stream();
        Assertions.assertEquals(2, s2.count());

        Stream<String> s3 = Arrays.stream(new String[]{"p", "q"});
        Assertions.assertEquals(List.of("p", "q"), s3.collect(Collectors.toList()));

        Assertions.assertEquals(0, Stream.empty().count());
    }

    @Test
    void iterateInfiniteWithLimit() {
        List<Integer> powersOfTwo = Stream.iterate(1, n -> n * 2)
                .limit(5)
                .collect(Collectors.toList());

        Assertions.assertEquals(List.of(1, 2, 4, 8, 16), powersOfTwo);
    }

    @Test
    void boundedIterateJava9() {
        List<Integer> powersOfTwo = Stream.iterate(1, n -> n < 20, n -> n * 2)
                .collect(Collectors.toList());

        Assertions.assertEquals(List.of(1, 2, 4, 8, 16), powersOfTwo);
    }

    @Test
    void generateWithSupplier() {
        List<String> xs = Stream.generate(() -> "x")
                .limit(3)
                .collect(Collectors.toList());

        Assertions.assertEquals(List.of("x", "x", "x"), xs);
    }

    @Test
    void primitiveStreamCreation() {
        int[] arr = IntStream.range(1, 5).toArray(); // exclusive end
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4}, arr);

        int[] arr2 = IntStream.rangeClosed(1, 5).toArray(); // inclusive end
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr2);
    }

    // ================================================================
    // 2. Filtering - Predicate<T>
    // ================================================================

    @Test
    void filterWithPredicate() {
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> evens = nums.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        Assertions.assertEquals(List.of(2, 4, 6), evens);
    }

    @Test
    void predicateCombinators() {
        List<Integer> nums = List.of(-2, -1, 0, 1, 2, 3, 4);
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isPositive = n -> n > 0;

        List<Integer> evenAndPositive = nums.stream().filter(isEven.and(isPositive)).collect(Collectors.toList());
        Assertions.assertEquals(List.of(2, 4), evenAndPositive);

        List<Integer> evenOrPositive = nums.stream().filter(isEven.or(isPositive)).collect(Collectors.toList());
        Assertions.assertEquals(List.of(-2, 0, 1, 2, 3, 4), evenOrPositive);

        List<Integer> odds = nums.stream().filter(isEven.negate()).collect(Collectors.toList());
        Assertions.assertEquals(List.of(-1, 1, 3), odds);
    }

    // ================================================================
    // 3. Mapping - Function<T,R>
    // ================================================================

    @Test
    void mapWithFunction() {
        List<String> names = List.of("alice", "bob");
        List<Integer> lengths = names.stream()
                .map(String::length)
                .collect(Collectors.toList());

        Assertions.assertEquals(List.of(5, 3), lengths);
    }

    @Test
    void mapToIntAndBackToObj() {
        List<String> names = List.of("alice", "bob");

        int[] lengths = names.stream().mapToInt(String::length).toArray();
        Assertions.assertArrayEquals(new int[]{5, 3}, lengths);

        List<String> labeled = names.stream()
                .mapToInt(String::length)
                .mapToObj(i -> "len:" + i)
                .collect(Collectors.toList());
        Assertions.assertEquals(List.of("len:5", "len:3"), labeled);
    }

    @Test
    void flatMapFlattensNestedStreams() {
        List<List<Integer>> nested = List.of(List.of(1, 2), List.of(3, 4));
        List<Integer> flat = nested.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        Assertions.assertEquals(List.of(1, 2, 3, 4), flat);
    }

    // ================================================================
    // 4. Sorting
    // ================================================================

    @Test
    void sortedNaturalOrder() {
        List<String> names = List.of("banana", "apple", "cherry");
        List<String> sorted = names.stream().sorted().collect(Collectors.toList());

        Assertions.assertEquals(List.of("apple", "banana", "cherry"), sorted);
    }

    @Test
    void sortedWithComparator() {
        List<String> names = List.of("banana", "apple", "cherry");
        List<String> sorted = names.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        Assertions.assertEquals(List.of("cherry", "banana", "apple"), sorted);
    }

    @Test
    void sortedMultiLevelThenComparing() {
        record Person(String name, int age) {
        }
        List<Person> people = List.of(
                new Person("Bob", 30),
                new Person("Alice", 30),
                new Person("Charlie", 25)
        );

        List<String> sortedNames = people.stream()
                .sorted(Comparator.comparing(Person::age).thenComparing(Person::name))
                .map(Person::name)
                .collect(Collectors.toList());

        Assertions.assertEquals(List.of("Charlie", "Alice", "Bob"), sortedNames);
    }

    // ================================================================
    // 5. Consuming - Consumer<T>
    // ================================================================

    @Test
    void forEachConsumesElements() {
        List<String> collected = new ArrayList<>();
        List.of("a", "b", "c").forEach(collected::add);

        Assertions.assertEquals(List.of("a", "b", "c"), collected);
    }

    @Test
    void forEachOrderedPreservesOrderEvenInParallel() {
        List<Integer> collected = Collections.synchronizedList(new ArrayList<>());
        IntStream.rangeClosed(1, 100).boxed()
                .parallel()
                .forEachOrdered(collected::add);

        Assertions.assertEquals(100, collected.size());
        Assertions.assertEquals(1, collected.get(0));
        Assertions.assertEquals(100, collected.get(99));
    }

    // ================================================================
    // 6. Terminal operations - collect / reduce / match / find
    // ================================================================

    @Test
    void collectToListSetJoiningMap() {
        List<String> names = List.of("banana", "apple", "cherry");

        List<String> list = names.stream().collect(Collectors.toList());
        Set<String> set = names.stream().collect(Collectors.toSet());
        String joined = names.stream().sorted().collect(Collectors.joining(", "));
        Map<String, Integer> map = names.stream().collect(Collectors.toMap(n -> n, String::length));

        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals(3, set.size());
        Assertions.assertEquals("apple, banana, cherry", joined);
        Assertions.assertEquals(6, map.get("banana"));
    }

    @Test
    void groupingByFirstLetter() {
        List<String> words = List.of("apple", "banana", "avocado", "blueberry", "cherry");
        Map<Character, List<String>> grouped = words.stream()
                .collect(Collectors.groupingBy(w -> w.charAt(0)));

        Assertions.assertEquals(List.of("apple", "avocado"), grouped.get('a'));
        Assertions.assertEquals(List.of("banana", "blueberry"), grouped.get('b'));
        Assertions.assertEquals(List.of("cherry"), grouped.get('c'));
    }

    @Test
    void reduceWithAndWithoutIdentity() {
        Optional<Integer> sum = Stream.of(1, 2, 3, 4).reduce((a, b) -> a + b);
        Assertions.assertEquals(10, sum.get());

        int sum2 = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        Assertions.assertEquals(10, sum2);
    }

    @Test
    void matchingOperations() {
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6);

        Assertions.assertTrue(nums.stream().anyMatch(n -> n > 5));
        Assertions.assertTrue(nums.stream().allMatch(n -> n > 0));
        Assertions.assertTrue(nums.stream().noneMatch(n -> n < 0));
    }

    @Test
    void findFirstAndMaxMin() {
        List<Integer> nums = List.of(3, 1, 4, 1, 5, 9, 2, 6);

        Assertions.assertEquals(3, nums.stream().findFirst().get());
        Assertions.assertEquals(9, nums.stream().max(Comparator.naturalOrder()).get());
        Assertions.assertEquals(1, nums.stream().min(Comparator.naturalOrder()).get());
    }

    // ================================================================
    // 7. Primitive stream terminal operations
    // ================================================================

    @Test
    void primitiveStreamSumAverageStats() {
        int sum = IntStream.of(1, 2, 3, 4).sum();
        Assertions.assertEquals(10, sum);

        OptionalDouble avg = IntStream.of(1, 2, 3, 4).average();
        Assertions.assertEquals(2.5, avg.getAsDouble());

        IntSummaryStatistics stats = IntStream.of(1, 2, 3, 4).summaryStatistics();
        Assertions.assertEquals(4, stats.getMax());
        Assertions.assertEquals(1, stats.getMin());
        Assertions.assertEquals(10, stats.getSum());
        Assertions.assertEquals(4, stats.getCount());

        IntSummaryStatistics empty = IntStream.of().summaryStatistics();
        Assertions.assertEquals(-2147483648, empty.getMax());
        Assertions.assertEquals(2147483647, empty.getMin());
        Assertions.assertEquals(0, empty.getSum());
        Assertions.assertEquals(0, empty.getCount());
    }

    @Test
    void streamOfIntegerHasNoDirectSum_mustUseMapToInt() {
        List<Integer> nums = List.of(1, 2, 3, 4);
        // nums.stream().sum(); // would NOT compile - Stream<Integer> has no sum()

        int sum = nums.stream().mapToInt(Integer::intValue).sum();
        Assertions.assertEquals(10, sum);
    }

    // ================================================================
    // 8. Streams are single-use
    // ================================================================

    @Test
    void streamCannotBeReused() {
        Stream<String> stream = List.of("a", "b").stream();
        stream.forEach(s -> {
        }); // consumes the stream

        Assertions.assertThrows(IllegalStateException.class,
                () -> stream.forEach(s -> {
                })); // reusing throws
    }

    // ================================================================
    // 9. Laziness of intermediate operations
    // ================================================================

    @Test
    void intermediateOperationsAreLazy() {
        List<Integer> touched = new ArrayList<>();

        Stream<Integer> stream = Stream.of(1, 2, 3)
                .filter(n -> {
                    touched.add(n); // side effect to prove when filter actually runs
                    return n > 1;
                });

        Assertions.assertTrue(touched.isEmpty()); // nothing has run yet - lazy

        List<Integer> result = stream.collect(Collectors.toList()); // triggers execution now

        Assertions.assertEquals(List.of(1, 2, 3), touched); // filter ran for every element during the terminal op
        Assertions.assertEquals(List.of(2, 3), result);
    }

    // ================================================================
    // 10. distinct / limit / skip
    // ================================================================

    @Test
    void distinctLimitSkip() {
        List<Integer> distinct = Stream.of(1, 2, 2, 3, 3, 3).distinct().collect(Collectors.toList());
        Assertions.assertEquals(List.of(1, 2, 3), distinct);

        List<Integer> limited = Stream.of(1, 2, 3, 4, 5).limit(3).collect(Collectors.toList());
        Assertions.assertEquals(List.of(1, 2, 3), limited);

        List<Integer> skipped = Stream.of(1, 2, 3, 4, 5).skip(2).collect(Collectors.toList());
        Assertions.assertEquals(List.of(3, 4, 5), skipped);
    }

    // ================================================================
    // 11. peek() for debugging only, doesn't alter the stream's values
    // ================================================================

    @Test
    void peekDoesNotModifyStreamContents() {
        List<String> peeked = new ArrayList<>();

        List<String> result = Stream.of("alice", "bob", "charlie")
                .peek(peeked::add)
                .filter(n -> n.length() > 3)
                .collect(Collectors.toList());

        Assertions.assertEquals(List.of("alice", "bob", "charlie"), peeked); // peek saw ALL elements
        Assertions.assertEquals(List.of("alice", "charlie"), result);         // filter still applied after
    }

    // ================================================================
    // 12. Method references as lambda shorthand
    // ================================================================

    @Test
    void methodReferencesInStreams() {
        List<String> upper = Stream.of("a", "b").map(String::toUpperCase).collect(Collectors.toList());
        Assertions.assertEquals(List.of("A", "B"), upper);

        List<Integer> parsed = Stream.of("1", "2", "3").map(Integer::parseInt).collect(Collectors.toList());
        Assertions.assertEquals(List.of(1, 2, 3), parsed);

        List<ArrayList<Integer>> lists = Stream.generate(ArrayList<Integer>::new).limit(2).collect(Collectors.toList());
        Assertions.assertEquals(2, lists.size());
    }

}
