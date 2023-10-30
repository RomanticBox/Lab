
import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

public class HeapTests {
    @Test
    @Score(1)
    public void emptyTest() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            Heap<Integer> heap = new Heap<>();
            assertTrue(heap.isEmpty());
            assertThat(heap.getSize(), is(0));
        });
    }

    @Test
    @Score(2)
    public void clearTest() {
        Heap<Integer> heap = new Heap<>();
        heap.insert(5);
        heap.insert(3);
        heap.insert(7);

        heap.clear();
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.getSize());
    }

    @Test
    @Score(4)
    public void oneEntryTest() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            Heap<Integer> heap = new Heap<>();
            heap.insert(1);
            assertThat(heap.getSize(), is(1));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(1));
            assertThat(heap.getSize(), is(1));
            assertFalse(heap.isEmpty());
            assertThat(heap.removeMin(), is(1));
            assertThat(heap.getSize(), is(0));
            assertTrue(heap.isEmpty());
            heap.insert(2);
            assertThat(heap.getSize(), is(1));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(2));
            assertThat(heap.getSize(), is(1));
            assertFalse(heap.isEmpty());
            assertThat(heap.removeMin(), is(2));
            assertThat(heap.getSize(), is(0));
            assertTrue(heap.isEmpty());
        });

    }

    @Test
    @Score(8)
    public void twoEntryTest() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            Heap<Integer> heap = new Heap<>();

            // 크기 순서대로 원소를 삽입
            heap.insert(1);
            heap.insert(2);
            assertThat(heap.getSize(), is(2));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(1));
            assertThat(heap.removeMin(), is(1));
            assertThat(heap.getSize(), is(1));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(2));
            assertThat(heap.removeMin(), is(2));
            assertTrue(heap.isEmpty());
            assertThat(heap.getSize(), is(0));

            // 크기 순서의 반대로 원서를 삽입
            heap.insert(2);
            heap.insert(1);
            assertThat(heap.getSize(), is(2));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(1));
            assertThat(heap.removeMin(), is(1));
            assertThat(heap.getSize(), is(1));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(2));
            assertThat(heap.removeMin(), is(2));
            assertTrue(heap.isEmpty());
            assertThat(heap.getSize(), is(0));

            // 동일한 원소의 삽입
            heap.insert(2);
            heap.insert(2);
            assertThat(heap.getSize(), is(2));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(2));
            assertThat(heap.removeMin(), is(2));
            assertThat(heap.getSize(), is(1));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(2));
            assertThat(heap.removeMin(), is(2));
            assertTrue(heap.isEmpty());
            assertThat(heap.getSize(), is(0));
        });
    }

    @Test
    @Score(16)
    public void threeEntryTest() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            Heap<Integer> heap = new Heap<>();

            // 크기 순서대로 원소를 삽입
            heap.insert(1);
            heap.insert(2);
            heap.insert(3);
            assertThat(heap.getSize(), is(3));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(1));
            assertThat(heap.removeMin(), is(1));
            assertThat(heap.getSize(), is(2));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(2));
            assertThat(heap.removeMin(), is(2));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(1));
            assertThat(heap.min(), is(3));
            assertThat(heap.removeMin(), is(3));
            assertTrue(heap.isEmpty());
            assertThat(heap.getSize(), is(0));

            // 크기 순서의 반대로 원서를 삽입
            heap.insert(3);
            heap.insert(2);
            heap.insert(1);
            assertThat(heap.getSize(), is(3));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(1));
            assertThat(heap.removeMin(), is(1));
            assertThat(heap.getSize(), is(2));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(2));
            assertThat(heap.removeMin(), is(2));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(1));
            assertThat(heap.min(), is(3));
            assertThat(heap.removeMin(), is(3));
            assertTrue(heap.isEmpty());
            assertThat(heap.getSize(), is(0));

            // 무작위 원소의 삽입
            heap.insert(2);
            heap.insert(3);
            heap.insert(1);
            assertThat(heap.getSize(), is(3));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(1));
            assertThat(heap.removeMin(), is(1));
            assertThat(heap.getSize(), is(2));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(2));
            assertThat(heap.removeMin(), is(2));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(1));
            assertThat(heap.min(), is(3));
            assertThat(heap.removeMin(), is(3));
            assertTrue(heap.isEmpty());
            assertThat(heap.getSize(), is(0));

            // 겹치는 원소의 삽입
            heap.insert(3);
            heap.insert(1);
            heap.insert(1);
            assertThat(heap.getSize(), is(3));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(1));
            assertThat(heap.removeMin(), is(1));
            assertThat(heap.getSize(), is(2));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(1));
            assertThat(heap.removeMin(), is(1));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(1));
            assertThat(heap.min(), is(3));
            assertThat(heap.removeMin(), is(3));
            assertTrue(heap.isEmpty());
            assertThat(heap.getSize(), is(0));

        });
    }

    @Test
    @Score(32)
    public void fourEntryTest() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            Heap<Integer> heap = new Heap<>();

            // 크기 순서대로 원소를 삽입
            heap.insert(1);
            heap.insert(2);
            heap.insert(3);
            heap.insert(4);
            assertThat(heap.getSize(), is(4));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(1));
            assertThat(heap.removeMin(), is(1));
            assertThat(heap.getSize(), is(3));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(2));
            assertThat(heap.removeMin(), is(2));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(2));
            assertThat(heap.min(), is(3));
            assertThat(heap.removeMin(), is(3));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(1));
            assertThat(heap.min(), is(4));
            assertThat(heap.removeMin(), is(4));
            assertTrue(heap.isEmpty());
            assertThat(heap.getSize(), is(0));

            // 크기 순서의 반대로 원서를 삽입
            heap.insert(4);
            heap.insert(3);
            heap.insert(2);
            heap.insert(1);
            assertThat(heap.getSize(), is(4));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(1));
            assertThat(heap.removeMin(), is(1));
            assertThat(heap.getSize(), is(3));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(2));
            assertThat(heap.removeMin(), is(2));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(2));
            assertThat(heap.min(), is(3));
            assertThat(heap.removeMin(), is(3));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(1));
            assertThat(heap.min(), is(4));
            assertThat(heap.removeMin(), is(4));
            assertTrue(heap.isEmpty());
            assertThat(heap.getSize(), is(0));

            // 무작위 원소의 삽입
            heap.insert(2);
            heap.insert(4);
            heap.insert(1);
            heap.insert(3);
            assertThat(heap.getSize(), is(4));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(1));
            assertThat(heap.removeMin(), is(1));
            assertThat(heap.getSize(), is(3));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(2));
            assertThat(heap.removeMin(), is(2));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(2));
            assertThat(heap.min(), is(3));
            assertThat(heap.removeMin(), is(3));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(1));
            assertThat(heap.min(), is(4));
            assertThat(heap.removeMin(), is(4));
            assertTrue(heap.isEmpty());
            assertThat(heap.getSize(), is(0));

            // 겹치는 원소의 삽입
            heap.insert(2);
            heap.insert(4);
            heap.insert(4);
            heap.insert(1);
            assertThat(heap.getSize(), is(4));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(1));
            assertThat(heap.removeMin(), is(1));
            assertThat(heap.getSize(), is(3));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(2));
            assertThat(heap.removeMin(), is(2));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(2));
            assertThat(heap.min(), is(4));
            assertThat(heap.removeMin(), is(4));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(1));
            assertThat(heap.min(), is(4));
            assertThat(heap.removeMin(), is(4));
            assertTrue(heap.isEmpty());
            assertThat(heap.getSize(), is(0));
        });
    }

    @Test
    @Score(64)
    public void fiveEntryTest() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            Heap<Integer> heap = new Heap<>();

            // 크기 순서대로 원소를 삽입
            heap.insert(1);
            heap.insert(2);
            heap.insert(3);
            heap.insert(4);
            heap.insert(5);
            assertThat(heap.getSize(), is(5));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(1));
            assertThat(heap.removeMin(), is(1));
            assertThat(heap.getSize(), is(4));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(2));
            assertThat(heap.removeMin(), is(2));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(3));
            assertThat(heap.min(), is(3));
            assertThat(heap.removeMin(), is(3));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(2));
            assertThat(heap.min(), is(4));
            assertThat(heap.removeMin(), is(4));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(1));
            assertThat(heap.min(), is(5));
            assertThat(heap.removeMin(), is(5));
            assertThat(heap.getSize(), is(0));
            assertTrue(heap.isEmpty());

            // 크기 순서의 반대로 원서를 삽입
            heap.insert(5);
            heap.insert(4);
            heap.insert(3);
            heap.insert(2);
            heap.insert(1);
            assertThat(heap.getSize(), is(5));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(1));
            assertThat(heap.removeMin(), is(1));
            assertThat(heap.getSize(), is(4));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(2));
            assertThat(heap.removeMin(), is(2));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(3));
            assertThat(heap.min(), is(3));
            assertThat(heap.removeMin(), is(3));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(2));
            assertThat(heap.min(), is(4));
            assertThat(heap.removeMin(), is(4));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(1));
            assertThat(heap.min(), is(5));
            assertThat(heap.removeMin(), is(5));
            assertThat(heap.getSize(), is(0));
            assertTrue(heap.isEmpty());

            // 무작위 원소의 삽입
            heap.insert(1);
            heap.insert(5);
            heap.insert(4);
            heap.insert(2);
            heap.insert(3);
            assertThat(heap.getSize(), is(5));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(1));
            assertThat(heap.removeMin(), is(1));
            assertThat(heap.getSize(), is(4));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(2));
            assertThat(heap.removeMin(), is(2));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(3));
            assertThat(heap.min(), is(3));
            assertThat(heap.removeMin(), is(3));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(2));
            assertThat(heap.min(), is(4));
            assertThat(heap.removeMin(), is(4));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(1));
            assertThat(heap.min(), is(5));
            assertThat(heap.removeMin(), is(5));
            assertThat(heap.getSize(), is(0));
            assertTrue(heap.isEmpty());

            // 겹치는 원소의 삽입
            heap.insert(3);
            heap.insert(2);
            heap.insert(2);
            heap.insert(5);
            heap.insert(1);
            assertThat(heap.getSize(), is(5));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(1));
            assertThat(heap.removeMin(), is(1));
            assertThat(heap.getSize(), is(4));
            assertFalse(heap.isEmpty());
            assertThat(heap.min(), is(2));
            assertThat(heap.removeMin(), is(2));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(3));
            assertThat(heap.min(), is(2));
            assertThat(heap.removeMin(), is(2));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(2));
            assertThat(heap.min(), is(3));
            assertThat(heap.removeMin(), is(3));
            assertFalse(heap.isEmpty());
            assertThat(heap.getSize(), is(1));
            assertThat(heap.min(), is(5));
            assertThat(heap.removeMin(), is(5));
            assertThat(heap.getSize(), is(0));
            assertTrue(heap.isEmpty());
        });
    }

    @Test
    @Score(128)
    public void emptyHeapMergeTest() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            // 2개의 빈 힙 간의 병합
            Heap<Integer> heap = new Heap<>();
            Heap<Integer> heap2 = new Heap<>();
            heap.merge(heap2);
            assertTrue(heap.isEmpty());
            assertThat(heap.getSize(), is(0));

            // 원소 1개가 있는 힙과 빈 힙 간의 병합
            Heap<Integer> heap3 = new Heap<>();
            heap3.insert(7);
            Heap<Integer> heap4 = new Heap<>();
            heap3.merge(heap4);
            assertThat(heap3.min(), is(7));
            assertThat(heap3.getSize(), is(1));
            assertFalse(heap3.isEmpty());

            // 원소가 없는 힙과 원소가 1개 있는 힙 간의 병합
            Heap<Integer> heap5 = new Heap<>();
            heap5.insert(7);
            Heap<Integer> heap6 = new Heap<>();
            heap6.merge(heap5);
            assertThat(heap6.min(), is(7));
            assertThat(heap6.getSize(), is(1));
            assertFalse(heap6.isEmpty());
        });
    }

    @Test
    @Score(256)
    public void oneEntryHeapMergeTest() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            // 1 + 1
            Heap<Integer> heap = new Heap<>();
            heap.insert(8);
            Heap<Integer> heap2 = new Heap<>();
            heap.insert(4);
            heap.merge(heap2);
            assertThat(heap.min(), is(4));
            assertThat(heap.removeMin(), is(4));
            assertThat(heap.removeMin(), is(8));
        });
    }

    @Test
    @Score(512)
    public void twoEntryHeapMergeTest() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            // 2 + 1
            Heap<Integer> heap = new Heap<>();
            heap.insert(8);
            heap.insert(-2);
            Heap<Integer> heap2 = new Heap<>();
            heap.insert(4);
            heap.merge(heap2);
            assertThat(heap.min(), is(-2));
            assertThat(heap.removeMin(), is(-2));
            assertThat(heap.removeMin(), is(4));
            assertThat(heap.removeMin(), is(8));

            // 2 + 2
            Heap<Integer> heap3 = new Heap<>();
            heap3.insert(8);
            heap3.insert(-2);
            Heap<Integer> heap4 = new Heap<>();
            heap4.insert(4);
            heap4.insert(10);
            heap4.merge(heap3);
            assertThat(heap4.getSize(), is(4));
            assertThat(heap4.removeMin(), is(-2));
            assertThat(heap4.removeMin(), is(4));
            assertThat(heap4.removeMin(), is(8));
            assertThat(heap4.removeMin(), is(10));
        });
    }

    @Test
    @Score(512)
    public void threeEntryHeapMergeTest() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            // 3 + 3
            Heap<Integer> heap1 = new Heap<>();
            heap1.insert(8);
            heap1.insert(1);
            heap1.insert(5);
            Heap<Integer> heap2 = new Heap<>();
            heap2.insert(3);
            heap2.insert(2);
            heap2.insert(7);
            heap1.merge(heap2);
            assertThat(heap1.removeMin(), is(1));
            assertThat(heap1.removeMin(), is(2));
            assertThat(heap1.removeMin(), is(3));
            assertThat(heap1.removeMin(), is(5));
            assertThat(heap1.removeMin(), is(7));
            assertThat(heap1.removeMin(), is(8));
        });
    }

    @Test
    @Score(1024)
    public void complexHeapMergeTest() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            // 7 + 5
            Heap<Integer> heap1 = new Heap<>();
            heap1.insert(8);
            heap1.insert(1);
            heap1.insert(5);
            heap1.insert(9);
            heap1.insert(2);
            heap1.insert(9);
            heap1.insert(7);
            assertThat(heap1.removeMin(), is(1));
            assertThat(heap1.removeMin(), is(2));
            assertThat(heap1.removeMin(), is(5));
            assertThat(heap1.removeMin(), is(7));
            assertThat(heap1.removeMin(), is(8));
            assertThat(heap1.removeMin(), is(9));
            assertThat(heap1.removeMin(), is(9));
            heap1.insert(8);
            heap1.insert(1);
            heap1.insert(5);
            heap1.insert(9);
            heap1.insert(2);
            heap1.insert(9);
            heap1.insert(7);
            Heap<Integer> heap2 = new Heap<>();
            heap2.insert(3);
            heap2.insert(2);
            heap2.insert(7);
            heap2.insert(8);
            heap2.insert(0);
            assertThat(heap2.removeMin(), is(0));
            assertThat(heap2.removeMin(), is(2));
            assertThat(heap2.removeMin(), is(3));
            assertThat(heap2.removeMin(), is(7));
            assertThat(heap2.removeMin(), is(8));
            heap2.insert(3);
            heap2.insert(2);
            heap2.insert(7);
            heap2.insert(8);
            heap2.insert(0);
            heap1.merge(heap2);
            assertThat(heap1.removeMin(), is(0));
            assertThat(heap1.removeMin(), is(1));
            assertThat(heap1.removeMin(), is(2));
            assertThat(heap1.removeMin(), is(2));
            assertThat(heap1.removeMin(), is(3));
            assertThat(heap1.removeMin(), is(5));
            assertThat(heap1.removeMin(), is(7));
            assertThat(heap1.removeMin(), is(7));
            assertThat(heap1.removeMin(), is(8));
            assertThat(heap1.removeMin(), is(8));
            assertThat(heap1.removeMin(), is(9));
            assertThat(heap1.removeMin(), is(9));
        });
    }
}