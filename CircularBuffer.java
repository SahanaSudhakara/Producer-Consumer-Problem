import java.util.concurrent.Semaphore;

/**
 * This class implements circular buffer
 */
class CircularBuffer {
    int[] buffer;

    // This semaphore represents the number of empty slots in the buffer
    Semaphore empty;
    //This semaphore represents the number of full slots in the buffer
    Semaphore full;
    //This semaphore is used to ensure that only one thread can access the buffer at a time
    Semaphore mutex;
    int readPointer;
    int size;
    int wrtPointer;

    CircularBuffer(int size) {
        buffer = new int[size];
        empty = new Semaphore(size);
        full = new Semaphore(0);
        mutex = new Semaphore(1);
        readPointer = 0;
        this.size = size;
        wrtPointer = 0;
    }

    /**Returns pointer value by wrapping around to
    the beginning of the buffer when it reaches the end.*/
    public int get() {
        int val = buffer[readPointer];
        this.readPointer = (readPointer + 1) % size;
        return val;
    }

    /**Adds item to buffer and updates pointer*/
    public void add(int val) {
        buffer[wrtPointer] = val;
        this.wrtPointer = (wrtPointer + 1) % size;
    }
}