import java.util.NoSuchElementException;

/**
 * BoundedQueue was created to meet the following challenge:
 * <ul>
 *     <li>Using only primitive types, implement a bounded queue to store integers.</li>
 *     <li>The data structure should be optimized for algorithmic runtime, memory usage, and memory throughput.</li>
 * </ul>
 *
 * Created by ankush on 10/21/14.
 */
public class BoundedQueue {
    // Where to enqueue from -- incremented as items are enqueued and loops from 0 to size
    private int enqueueIndex;

    // Where to dequeue from -- incremented as items are dequeued and loops from 0 to size
    private int dequeueIndex;

    private int[] items;
    private int count;

    /**
     * Creates a new BoundedQueue from a given size
     *
     * @param size the maximum size of the queue. Must be non-negative.
     */
    public BoundedQueue(int size) {
        enqueueIndex = 0;
        dequeueIndex = 0;

        items = new int[size];
    }

    /**
     * Takes a given number and adds it to the end of the queue if possible
     *
     * @param newInt
     */
    public void enqueue(int newInt) {
        if (count >= items.length) {
            throw new IllegalStateException();
        }

        items[enqueueIndex] = newInt;

        enqueueIndex = ++enqueueIndex % items.length;
        count++;
    }

    /**
     * Get the value at the beginning of the queue, and remove it from the queue
     *
     * @return the int at the beginning of the queue
     */
    public int dequeue() {
        if (count <= 0) {
            throw new NoSuchElementException();
        }

        int item = items[dequeueIndex];

        items[dequeueIndex] = 0;

        dequeueIndex = ++dequeueIndex % items.length;
        count--;

        return item;
    }

    /**
     * Get the value at the beginning of the queue, without removing it from the queue
     *
     * @return the int at the beginning of the queue
     */
    public int peek() {
        if (count <= 0) {
            throw new NoSuchElementException();
        }

        return items[dequeueIndex];
    }

    /**
     * Check if the queue is empty or not
     *
     * @return <code>true</code> if the queue is empty, <code>false</code> if it is not
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Check the size of the queue
     *
     * @return the size of the queue
     */
    public int size() {
        return count;
    }

    @Override
    public String toString() {
        return java.util.Arrays.toString(items);
    }
}
