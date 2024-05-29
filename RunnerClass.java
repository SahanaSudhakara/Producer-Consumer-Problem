import java.util.Scanner;

/*This Class starts producer-consumer threads
 */
public class RunnerClass {
    public static void main(String[] args) {

        //Create buffer of fixed size of 5
        CircularBuffer cb = new CircularBuffer(5);
        final ProducerConsumer producerConsumer = new ProducerConsumer(cb);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter limit for producer to produce item");
        int max = sc.nextInt();

        //Create Runnable Objects for Thread Class
        Runnable producerRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    producerConsumer.produce(max);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        //Create Producer thread
        Thread producer = new Thread(producerRunnable);

        Runnable consumerRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    producerConsumer.consume(max);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        int numOfConsumer = 2;//Default consumers
        System.out.println("Enter number of consumers");
        numOfConsumer = sc.nextInt();
        //Create consumer thread based on input
        Thread[] consumers = new Thread[numOfConsumer];
        //Start producer thread
        producer.start();
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Thread(consumerRunnable);
            //Start consumer threads
            consumers[i].start();
        }

    }
}