import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * Test cases:
 * <ul>
 *     <li>A queue is empty on construction</li>
 *     <li>A queue has size 0 on construction</li>
 *     <li>After n enqueues to an empty queue, n > 0, the queue is not empty and its size is n</li>
 *     <li>If one enqueues x then dequeues, the value dequeued is x.</li>
 *     <li>If one enqueues x then peeks, the value returned is x, but the size stays the same</li>
 *     <li>If the size is n, then after n dequeues, the stack is empty and has a size 0</li>
 *     <li>If one enqueues the values 1 through CAPACITY, in order, into an empty queue, then dequeues CAPACITY items, the values dequeued are 1 through CAPACITY and no Exceptions are thrown.</li>
 *     <li>Dequeueing from an empty queue does throw a NoSuchElementException</li>
 *     <li>Peeking into an empty queue does throw a NoSuchElementException</li>
 *     <li>Enqueueing onto a full queue does throw an IllegalStateException</li>
 * </ul>
 *
 * Created by ankush on 10/21/14.
 */
public class BoundedQueueTest {
    /**
     * The queue to use in all the test
     */
    private BoundedQueue q;
    private static int CAPACITY = 60;

    @Before
    public void makeBoundedQueue() {
        q = new BoundedQueue(CAPACITY);
    }

    /**
     * Tests that a queue is empty and has size 0 on construction
     */
    @Test
    public void testNewQueueIsEmpty() {
        assertTrue(q.isEmpty());
        assertEquals(q.size(), 0);
    }

    /**
     * After n enqueues to an empty queue where n > 0, the queue is not empty and its size is n
     */
    @Test
    public void testInsertsToEmptyQueue() {
        int numberOfInserts = 6;

        for (int i = 0; i < numberOfInserts; i++) {
            q.enqueue(i);
        }

        assertTrue(!q.isEmpty());
        assertEquals(q.size(), numberOfInserts);
    }

    /**
     * If one enqueues x then dequeues, the value dequeued is x.
     */
    @Test
    public void testEnqueueThenDequeue() {
        int queueVal = 100;

        q.enqueue(queueVal);
        assertEquals(q.dequeue(), queueVal);
    }

    /**
     * If one enqueues x then peeks, the value returned is x, but the size stays the same
     */
    @Test
    public void testEnqueueThenPeek() {
        int queueVal = Integer.MIN_VALUE;

        q.enqueue(queueVal);

        int size = q.size();
        assertEquals(q.peek(), queueVal);
        assertEquals(q.size(), size);
    }

    /**
     * If one enqueues the values 1 through CAPACITY, in order, into an empty queue, then dequeues CAPACITY items, the values dequeued are 1 through CAPACITY and no Exceptions are thrown.
     */
    @Test
    public void testCapacityInThenCapacityOut() {
        for (int i = 1; i <= CAPACITY; i++) {
            q.enqueue(i);
        }
        for (int i = 1; i <= CAPACITY; i++) {
            assertEquals(((Integer)q.dequeue()).intValue(), i);
        }
    }

    @Test
    public void testRemovingDownToEmpty() {
        int numberOfRemoves = (int)(Math.random() * 20 + 1);

        for (int i = 0; i < numberOfRemoves; i++) {
            q.enqueue(i);
        }
        for (int i = 0; i < numberOfRemoves; i++) {
            q.dequeue();
        }

        assertTrue(q.isEmpty());
        assertEquals(q.size(), 0);
    }

    /**
     * Dequeueing from an empty queue does throw a NoSuchElementException
     */
    @Test(expected=NoSuchElementException.class)
    public void testDequeueOnEmptyQueue() {
        assertTrue(q.isEmpty());
        q.dequeue();
    }

    /**
     * Peeking into an empty queue does throw a NoSuchElementException
     */
    @Test(expected=NoSuchElementException.class)
    public void testPeekIntoEmptyQueue() {
        assertTrue(q.isEmpty());
        q.dequeue();
    }

    /**
     * Enqueueing onto a full queue does throw an IllegalStateException
     */
    @Test(expected=IllegalStateException.class)
    public void testEnqueueToFullQueue() {
        for (int i = 0; i < CAPACITY; i++) {
            q.enqueue(i);
        }

        q.enqueue(10);
    }
}