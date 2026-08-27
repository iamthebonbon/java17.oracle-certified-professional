package com.example.ocp.selfcheck._4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class $3 {


    // ================================================================
    // 1. Decomposition - flatMap()
    // ================================================================

    @Test
    void decomposeSentencesIntoWords() {
        List<String> sentences = List.of("hello world", "foo bar baz");

        List<String> words = sentences.stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .collect(Collectors.toList());

        Assertions.assertEquals(List.of("hello", "world", "foo", "bar", "baz"), words);
    }

    @Test
    void decomposeNestedLists() {
        List<List<Integer>> nested = List.of(List.of(1, 2), List.of(3, 4, 5));
        List<Integer> flat = nested.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        Assertions.assertEquals(List.of(1, 2, 3, 4, 5), flat);
    }

    @Test
    void decomposeMapIntoKeysValuesPairs() {
        Map<String, Integer> map = new TreeMap<>(Map.of("a", 1, "b", 2, "c", 3));

        List<String> keys = map.keySet().stream().collect(Collectors.toList());
        List<Integer> values = map.values().stream().collect(Collectors.toList());
        List<String> pairs = map.entrySet().stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.toList());

        Assertions.assertEquals(List.of("a", "b", "c"), keys);
        Assertions.assertEquals(List.of(1, 2, 3), values);
        Assertions.assertEquals(List.of("a=1", "b=2", "c=3"), pairs);
    }

    // ================================================================
    // 2. Concatenation
    // ================================================================

    @Test
    void concatTwoStreams() {
        Stream<Integer> s1 = Stream.of(1, 2, 3);
        Stream<Integer> s2 = Stream.of(4, 5, 6);

        List<Integer> combined = Stream.concat(s1, s2).collect(Collectors.toList());
        Assertions.assertEquals(List.of(1, 2, 3, 4, 5, 6), combined);
    }

    @Test
    void concatFailsWithAlreadyConsumedStream() {
        Stream<Integer> s1 = Stream.of(1, 2);
        s1.count(); // consumes s1

        Assertions.assertThrows(IllegalStateException.class,
                () -> Stream.concat(s1, Stream.of(3, 4)).collect(Collectors.toList()));
    }

    @Test
    void joiningConcatenatesStringElements() {
        List<String> words = List.of("a", "b", "c");

        Assertions.assertEquals("abc", words.stream().collect(Collectors.joining()));
        Assertions.assertEquals("a, b, c", words.stream().collect(Collectors.joining(", ")));
        Assertions.assertEquals("[a, b, c]", words.stream().collect(Collectors.joining(", ", "[", "]")));
    }

    // ================================================================
    // 3. Reduction
    // ================================================================

    @Test
    void reduceWithIdentity() {
        List<Integer> nums = List.of(1, 2, 3, 4);

        int sum = nums.stream().reduce(0, Integer::sum);
        Assertions.assertEquals(10, sum);

        int product = nums.stream().reduce(1, (a, b) -> a * b);
        Assertions.assertEquals(24, product);
    }

    @Test
    void reduceWithoutIdentityReturnsOptional() {
        List<Integer> nums = List.of(1, 2, 3, 4);
        Optional<Integer> sum = nums.stream().reduce((a, b) -> a + b);

        Assertions.assertTrue(sum.isPresent());
        Assertions.assertEquals(10, sum.get());

        /**
         * WTF!!!
         */
        Optional<Integer> emptySum = Stream.<Integer>empty().reduce((a, b) -> a + b);
        Assertions.assertTrue(emptySum.isEmpty());
    }

    @Test
    void threeArgReduceWithCombiner() {
        List<String> words = List.of("a", "bb", "ccc");

        int totalLength = words.stream().reduce(
                0,
                (partial, str) -> partial + str.length(), // accumulator
                Integer::sum);                              // combiner (used only in parallel)

        Assertions.assertEquals(6, totalLength);
    }

    @Test
    void threeArgReduceCombinerActuallyInvokedInParallel() {
        List<String> words = List.of("a", "bb", "ccc", "dddd", "eeeee");

        int totalLength = words.parallelStream().reduce(
                0,
                (partial, str) -> partial + str.length(),
                Integer::sum);

        Assertions.assertEquals(15, totalLength); // 1+2+3+4+5
    }

    @Test
    void reductionViaCollectors() {
        List<Integer> nums = List.of(1, 2, 3, 4);

        long count = nums.stream().collect(Collectors.counting());
        int sum = nums.stream().collect(Collectors.summingInt(n -> n));
        double avg = nums.stream().collect(Collectors.averagingInt(n -> n));
        double avg2 = Stream.<Integer>of().collect(Collectors.averagingInt(n -> n));

        Assertions.assertEquals(4, count);
        Assertions.assertEquals(10, sum);
        Assertions.assertEquals(2.5, avg);
    }

    // ================================================================
    // 4. Grouping
    // ================================================================

    @Test
    void groupingByClassifier() {
        List<String> words = List.of("apple", "banana", "avocado", "blueberry", "cherry");

        Map<Character, List<String>> byFirstLetter = words.stream()
                .collect(Collectors.groupingBy(w -> w.charAt(0)));

        Assertions.assertEquals(List.of("apple", "avocado"), byFirstLetter.get('a'));
        Assertions.assertEquals(List.of("banana", "blueberry"), byFirstLetter.get('b'));
        Assertions.assertEquals(List.of("cherry"), byFirstLetter.get('c'));
    }

    @Test
    void groupingByOmitsKeysWithNoMatches() {
        List<String> words = List.of("apple", "avocado"); // no 'b' or 'c' words at all

        Map<Character, List<String>> grouped = words.stream()
                .collect(Collectors.groupingBy(w -> w.charAt(0)));

        Assertions.assertFalse(grouped.containsKey('b')); // absent entirely, unlike partitioningBy
        Assertions.assertEquals(1, grouped.size());
    }

    @Test
    void groupingByWithDownstreamCounting() {
        List<String> words = List.of("apple", "banana", "avocado", "blueberry", "cherry");

        Map<Character, Long> countByLetter = words.stream()
                .collect(Collectors.groupingBy(w -> w.charAt(0), Collectors.counting()));

        Assertions.assertEquals(2L, countByLetter.get('a'));
        Assertions.assertEquals(2L, countByLetter.get('b'));
        Assertions.assertEquals(1L, countByLetter.get('c'));
    }

    @Test
    void groupingByWithDownstreamMapping() {
        List<String> words = List.of("apple", "banana", "avocado", "cherry");

        Map<Character, List<Integer>> lengthsByLetter = words.stream()
                .collect(Collectors.groupingBy(w -> w.charAt(0),
                        Collectors.mapping(String::length, Collectors.toList())));

        Assertions.assertEquals(List.of(5, 7), lengthsByLetter.get('a'));
        Assertions.assertEquals(List.of(6), lengthsByLetter.get('b'));
        Assertions.assertEquals(List.of(6), lengthsByLetter.get('c'));
    }

    @Test
    void multiLevelGrouping() {
        record Employee(String department, String name, int salary) {
        }
        List<Employee> employees = List.of(
                new Employee("IT", "Alice", 5000),
                new Employee("IT", "Bob", 6000),
                new Employee("HR", "Carol", 4000)
        );

        Map<String, Map<Boolean, List<Employee>>> grouped = employees.stream()
                .collect(Collectors.groupingBy(Employee::department,
                        Collectors.groupingBy(e -> e.salary() > 4500)));

        Assertions.assertEquals(2, grouped.get("IT").get(true).size()); // Alice, Bob
        Assertions.assertFalse(grouped.get("HR").containsKey(true));     // Carol's salary not > 4500
        Assertions.assertEquals(1, grouped.get("HR").get(false).size());
    }

    // ================================================================
    // 5. Partitioning
    // ================================================================

    @Test
    void partitioningByEvenOdd() {
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        Map<Boolean, List<Integer>> partitioned = nums.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));

        Assertions.assertEquals(List.of(1, 3, 5, 7), partitioned.get(false));
        Assertions.assertEquals(List.of(2, 4, 6, 8), partitioned.get(true));
    }

    @Test
    void partitioningByAlwaysHasBothKeysEvenIfEmpty() {
        List<Integer> allEven = List.of(2, 4, 6);

        Map<Boolean, List<Integer>> partitioned = allEven.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));

        Assertions.assertTrue(partitioned.containsKey(false)); // present even though empty
        Assertions.assertTrue(partitioned.get(false).isEmpty());
        Assertions.assertEquals(List.of(2, 4, 6), partitioned.get(true));
    }

    @Test
    void partitioningByWithDownstreamCounting() {
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        Map<Boolean, Long> countPartitioned = nums.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0, Collectors.counting()));

        Assertions.assertEquals(4L, countPartitioned.get(false));
        Assertions.assertEquals(4L, countPartitioned.get(true));
    }

    // ================================================================
    // 6. Sequential vs Parallel - same results, associative ops required
    // ================================================================

    @Test
    void sequentialAndParallelGroupingProduceSameResult() {
        List<String> words = List.of("apple", "banana", "avocado", "blueberry", "cherry");

        Map<Character, List<String>> sequential = words.stream()
                .collect(Collectors.groupingBy(w -> w.charAt(0)));
        Map<Character, List<String>> parallel = words.parallelStream()
                .collect(Collectors.groupingBy(w -> w.charAt(0)));

        // sort each group's list before comparing since parallel execution order isn't guaranteed
        sequential.replaceAll((k, v) -> v.stream().sorted().collect(Collectors.toList()));
        parallel.replaceAll((k, v) -> v.stream().sorted().collect(Collectors.toList()));

        Assertions.assertEquals(sequential, parallel);
    }

    @Test
    void associativeReductionGivesCorrectResultInParallel() {
        List<Integer> nums = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toList());

        int sequentialSum = nums.stream().reduce(0, Integer::sum);
        int parallelSum = nums.parallelStream().reduce(0, Integer::sum);

        Assertions.assertEquals(sequentialSum, parallelSum);
        Assertions.assertEquals(500500, parallelSum);
    }

    @Test
    void groupingByConcurrentForParallelStreams() {
        List<String> words = List.of("apple", "banana", "avocado", "blueberry", "cherry");

        Map<Character, List<String>> grouped = words.parallelStream()
                .collect(Collectors.groupingByConcurrent(w -> w.charAt(0)));

        Assertions.assertEquals(2, grouped.get('a').size());
        Assertions.assertEquals(2, grouped.get('b').size());
        Assertions.assertEquals(1, grouped.get('c').size());
    }

    @Test
    void collectIsSafeForParallelUnlikeManualForEachMutation() {
        List<Integer> nums = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toList());

        // SAFE: collect() handles parallel merging internally
        List<Integer> result = nums.parallelStream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        Assertions.assertEquals(500, result.size());
    }

}
