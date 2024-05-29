import static java.lang.System.exit;

/**This Class takes a buffer which is shared between producer-consumer
 */
public class ProducerConsumer {
    CircularBuffer buffer;
    int consumed;

    public ProducerConsumer(CircularBuffer cb) {
        this.buffer = cb;
        consumed = 0;
    }

    //This method is used by the producer thread to add items to the buffer
    public void produce(int limit) throws InterruptedException {
        int item = 1;
        int produced = 0;
        while (produced < limit) {
            //check for empty slots to produce-decrements the semaphore value
            buffer.empty.acquire();
            //acquire exclusive access for buffer
            buffer.mutex.acquire();
            System.out.println("Producer produced=" + item);
            buffer.add(item++);
            buffer.mutex.release();
            //notify a slot is filled-increments the semaphore value
            buffer.full.release();
            produced++;
        }
    }

    //This method is used by the consumer threads to remove items from the buffer
    public void consume(int limit) throws InterruptedException {
        while (consumed < limit) {
            //decrements the semaphore value
            buffer.full.acquire();
            buffer.mutex.acquire();
            consumed++;
            int item = buffer.get();
            System.out.println("Consumer " + Thread.currentThread().getName() + " consumed=" + item);
            Thread.sleep(100);
            buffer.mutex.release();
            //increments the semaphore value
            buffer.empty.release();
        }
        exit(0);
    }
}
