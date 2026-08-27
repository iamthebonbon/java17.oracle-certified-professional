package com.example.ocp.selfcheck._4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class $1 {
    // ================================================================
    // 1. ARRAYS
    // ================================================================

    @Test
    void arrayCreate() {
        int[] arr1 = new int[5];
        int[] arr2 = {1, 2, 3};
        int[] arr3 = new int[]{1, 2, 3};

        Assertions.assertArrayEquals(new int[]{0, 0, 0, 0, 0}, arr1);
        Assertions.assertArrayEquals(arr2, arr3);
    }

    @Test
    void arrayUpdateAndRetrieve() {
        int[] arr = {1, 2, 3};
        arr[0] = 99; // update
        Assertions.assertEquals(99, arr[0]); // retrieve
    }

    @Test
    void arraySimulatedGrowthViaCopy() {
        int[] arr = {1, 2, 3};
        int[] bigger = Arrays.copyOf(arr, arr.length + 1);
        bigger[bigger.length - 1] = 100;

        Assertions.assertArrayEquals(new int[]{1, 2, 3, 100}, bigger);
        Assertions.assertEquals(3, arr.length); // original untouched
    }

    @Test
    void arraySortPrimitive() {
        int[] arr = {5, 3, 1, 4, 2};
        Arrays.sort(arr);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void arraySortWithComparator_referenceTypeOnly() {
        Integer[] boxed = {5, 3, 1, 4, 2};
        Arrays.sort(boxed, Comparator.reverseOrder());
        Assertions.assertArrayEquals(new Integer[]{5, 4, 3, 2, 1}, boxed);
    }

    // ================================================================
    // 2. LIST
    // ================================================================

    @Test
    void listCreate() {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = List.of("a", "b", "c");
        List<String> list3 = new ArrayList<>(list2);

        Assertions.assertTrue(list1.isEmpty());
        Assertions.assertEquals(3, list2.size());
        Assertions.assertEquals(list2, list3);
    }

    @Test
    void listAdd() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add(0, "z");
        list.addAll(List.of("x", "y"));

        Assertions.assertEquals(List.of("z", "a", "x", "y"), list);
    }

    @Test
    void listRemove_indexVsObject() {
        List<Integer> nums = new ArrayList<>(List.of(10, 20, 30));

        nums.remove(1); // removes by INDEX -> removes 20
        Assertions.assertEquals(List.of(10, 30), nums);

        nums.remove(Integer.valueOf(10)); // removes by OBJECT/VALUE -> removes 10
        Assertions.assertEquals(List.of(30), nums);
    }

    @Test
    void listRemoveIfAndClear() {
        List<String> list = new ArrayList<>(List.of("a", "", "b", ""));
        list.removeIf(String::isEmpty);
        Assertions.assertEquals(List.of("a", "b"), list);

        list.clear();
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    void listUpdate() {
        List<String> list = new ArrayList<>(List.of("a", "b", "c"));
        list.set(0, "new value");
        Assertions.assertEquals(List.of("new value", "b", "c"), list);

        list.replaceAll(String::toUpperCase);
        Assertions.assertEquals(List.of("NEW VALUE", "B", "C"), list);
    }

    @Test
    void listRetrieve() {
        List<String> list = new ArrayList<>(List.of("a", "b", "c"));
        Assertions.assertEquals("b", list.get(1));
        Assertions.assertEquals(1, list.indexOf("b"));
        Assertions.assertTrue(list.contains("a"));
        Assertions.assertEquals(3, list.size());
    }

    @Test
    void listSubListIsALiveView() {
        List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        List<Integer> sub = nums.subList(1, 3); // [2, 3]

        sub.set(0, 99);
        Assertions.assertEquals(List.of(1, 99, 3, 4, 5), nums); // original changed too
    }

    @Test
    void listSortNaturalOrder() {
        List<Integer> list = new ArrayList<>(List.of(5, 3, 1, 4, 2));
        Collections.sort(list);
        Assertions.assertEquals(List.of(1, 2, 3, 4, 5), list);
    }

    @Test
    void listSortCustomComparator() {
        List<Integer> list = new ArrayList<>(List.of(5, 3, 1, 4, 2));
        list.sort(Comparator.reverseOrder());
        Assertions.assertEquals(List.of(5, 4, 3, 2, 1), list);
    }

    @Test
    void arraysAsListIsFixedSize() {
        List<Integer> fixed = Arrays.asList(1, 2, 3);
        fixed.set(0, 99); // allowed
        Assertions.assertEquals(99, fixed.get(0));

        Assertions.assertThrows(UnsupportedOperationException.class, () -> fixed.add(4));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> fixed.remove(0));
    }

    // ================================================================
    // 3. SET
    // ================================================================

    @Test
    void setCreate() {
        Set<String> hashSet = new HashSet<>();
        Set<String> linkedHashSet = new LinkedHashSet<>();
        Set<String> treeSet = new TreeSet<>();
        Set<String> immutable = Set.of("a", "b");

        Assertions.assertTrue(hashSet.isEmpty());
        Assertions.assertTrue(linkedHashSet.isEmpty());
        Assertions.assertTrue(treeSet.isEmpty());
        Assertions.assertEquals(2, immutable.size());
    }

    @Test
    void setAddIgnoresDuplicates() {
        Set<String> set = new HashSet<>();
        boolean first = set.add("a");
        boolean second = set.add("a"); // duplicate

        Assertions.assertTrue(first);
        Assertions.assertFalse(second); // silently rejected, returns false
        Assertions.assertEquals(1, set.size());
    }

    @Test
    void setRemoveOperations() {
        Set<Integer> set = new HashSet<>(Set.of(1, 2, 3, 4, 5));
        set.remove(1);
        Assertions.assertFalse(set.contains(1));

        set.removeIf(n -> n > 3);
        Assertions.assertEquals(Set.of(2, 3), set);
    }

    @Test
    void setRetainAllIsIntersection() {
        Set<Integer> a = new HashSet<>(Set.of(1, 2, 3));
        Set<Integer> b = new HashSet<>(Set.of(2, 3, 4));

        a.retainAll(b);
        Assertions.assertEquals(Set.of(2, 3), a);
    }

    @Test
    void setUpdateViaRemoveThenAdd() {
        Set<String> set = new HashSet<>(Set.of("old"));
        if (set.remove("old")) {
            set.add("new");
        }
        Assertions.assertEquals(Set.of("new"), set);
    }

    @Test
    void treeSetMaintainsSortedOrder() {
        TreeSet<Integer> ts = new TreeSet<>(Set.of(5, 1, 3, 9, 7));

        Assertions.assertEquals(1, ts.first());
        Assertions.assertEquals(9, ts.last());
        Assertions.assertEquals(7, ts.higher(5));
        Assertions.assertEquals(3, ts.lower(5));
    }

    @Test
    void setSortedViaStreamOrTreeSetConstruction() {
        Set<Integer> unordered = new HashSet<>(Set.of(5, 3, 1, 4, 2));

        List<Integer> sortedList = unordered.stream().sorted().collect(Collectors.toList());
        Assertions.assertEquals(List.of(1, 2, 3, 4, 5), sortedList);

        TreeSet<Integer> sortedSet = new TreeSet<>(unordered);
        Assertions.assertEquals(List.of(1, 2, 3, 4, 5), new ArrayList<>(sortedSet));
    }

    // ================================================================
    // 4. MAP
    // ================================================================

    @Test
    void mapCreate() {
        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> immutable = Map.of("a", 1, "b", 2);

        Assertions.assertTrue(hashMap.isEmpty());
        Assertions.assertEquals(2, immutable.size());
    }

    @Test
    void mapPutAddsOrUpdates() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);      // add
        Assertions.assertEquals(1, map.get("a"));

        map.put("a", 2);      // update - same method
        Assertions.assertEquals(2, map.get("a"));
    }

    @Test
    void mapPutIfAbsentDoesNotOverwrite() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.putIfAbsent("a", 99); // "a" already present, ignored
        map.putIfAbsent("b", 2);   // "b" absent, added

        Assertions.assertEquals(1, map.get("a"));
        Assertions.assertEquals(2, map.get("b"));
    }

    @Test
    void mapMergeCombinesOrInserts() {
        Map<String, Integer> counts = new HashMap<>();
        counts.merge("apple", 1, Integer::sum); // absent -> inserts 1
        counts.merge("apple", 1, Integer::sum); // present -> sums to 2

        Assertions.assertEquals(2, counts.get("apple"));
    }

    @Test
    void mapComputeIfAbsentAndPresent() {
        Map<String, Integer> map = new HashMap<>();
        map.computeIfAbsent("a", k -> 10);
        Assertions.assertEquals(10, map.get("a"));

        map.computeIfPresent("a", (k, v) -> v + 5);
        Assertions.assertEquals(15, map.get("a"));
    }

    @Test
    void mapRemove() {
        Map<String, Integer> map = new HashMap<>(Map.of("a", 1, "b", 2));

        map.remove("a");
        Assertions.assertFalse(map.containsKey("a"));

        boolean removed = map.remove("b", 99); // conditional removal, value doesn't match
        Assertions.assertFalse(removed);
        Assertions.assertTrue(map.containsKey("b"));

        map.remove("b", 2); // value matches this time
        Assertions.assertFalse(map.containsKey("b"));
    }

    @Test
    void mapRetrieve() {
        Map<String, Integer> map = new HashMap<>(Map.of("a", 1));

        Assertions.assertEquals(1, map.get("a"));
        Assertions.assertEquals(0, map.getOrDefault("z", 0));
        Assertions.assertTrue(map.containsKey("a"));
        Assertions.assertTrue(map.containsValue(1));
    }

    @Test
    void mapSortedByKeyViaTreeMap() {
        Map<String, Integer> map = new HashMap<>(Map.of("c", 3, "a", 1, "b", 2));
        TreeMap<String, Integer> sorted = new TreeMap<>(map);

        Assertions.assertEquals(List.of("a", "b", "c"), new ArrayList<>(sorted.keySet()));
    }

    @Test
    void mapSortedByValueViaEntryList() {
        Map<String, Integer> map = new HashMap<>(Map.of("a", 3, "b", 1, "c", 2));

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort(Map.Entry.comparingByValue());

        List<String> keysInValueOrder = entries.stream().map(Map.Entry::getKey).collect(Collectors.toList());
        Assertions.assertEquals(List.of("b", "c", "a"), keysInValueOrder);
    }

    // ================================================================
    // 5. DEQUE
    // ================================================================

    @Test
    void dequeCreate() {
        Deque<Integer> d1 = new ArrayDeque<>();
        Deque<Integer> d2 = new LinkedList<>();

        Assertions.assertTrue(d1.isEmpty());
        Assertions.assertTrue(d2.isEmpty());
    }

    @Test
    void dequeAddFirstLast() {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.offerFirst(0);
        deque.offerLast(3);

        Assertions.assertEquals(List.of(0, 1, 2, 3), new ArrayList<>(deque));
    }

    @Test
    void dequeUsedAsStack_LIFO() {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        Assertions.assertEquals(3, stack.pop());
        Assertions.assertEquals(2, stack.pop());
        Assertions.assertEquals(1, stack.pop());
    }

    @Test
    void dequeUsedAsQueue_FIFO() {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(1); // adds to tail
        queue.offer(2);
        queue.offer(3);

        Assertions.assertEquals(1, queue.poll()); // removes from head
        Assertions.assertEquals(2, queue.poll());
        Assertions.assertEquals(3, queue.poll());
    }

    @Test
    void dequeRemove() {
        Deque<Integer> deque = new ArrayDeque<>(List.of(1, 2, 3, 4));

        Assertions.assertEquals(1, deque.removeFirst());
        Assertions.assertEquals(4, deque.removeLast());
        Assertions.assertEquals(List.of(2, 3), new ArrayList<>(deque));
    }

    @Test
    void dequeSafeVsThrowingMethods() {
        Deque<Integer> empty = new ArrayDeque<>();

        Assertions.assertNull(empty.peekFirst());     // safe - returns null
        Assertions.assertNull(empty.pollFirst());       // safe - returns null

        Assertions.assertThrows(NoSuchElementException.class, empty::getFirst);   // throws
        Assertions.assertThrows(NoSuchElementException.class, empty::removeFirst); // throws
    }

    @Test
    void dequeRetrieve() {
        Deque<Integer> deque = new ArrayDeque<>(List.of(1, 2, 3));

        Assertions.assertEquals(1, deque.getFirst());
        Assertions.assertEquals(3, deque.getLast());
        Assertions.assertEquals(1, deque.peekFirst());
        Assertions.assertEquals(3, deque.peekLast());
        Assertions.assertEquals(3, deque.size());
        Assertions.assertTrue(deque.contains(2));
    }

    @Test
    void dequeSortViaConversionToList() {
        Deque<Integer> deque = new ArrayDeque<>(List.of(5, 3, 1, 4, 2));

        List<Integer> asList = new ArrayList<>(deque);
        Collections.sort(asList);
        Deque<Integer> sorted = new ArrayDeque<>(asList);

        Assertions.assertEquals(List.of(1, 2, 3, 4, 5), new ArrayList<>(sorted));
    }

    // ================================================================
    // 6. Cross-cutting trap: Map is NOT a Collection
    // ================================================================

    @Test
    void mapIsNotACollectionButHasCollectionViews() {
        Map<String, Integer> map = new HashMap<>(Map.of("a", 1, "b", 2));

        // map itself cannot be assigned to a Collection<...> reference,
        // but its views (keySet, values, entrySet) ARE Collections:
        Collection<String> keys = map.keySet();
        Collection<Integer> values = map.values();

        Assertions.assertEquals(2, keys.size());
        Assertions.assertEquals(2, values.size());
    }
}
