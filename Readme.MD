
**Results:**

In what order were the integers printed?
Ans: Sample output for producer with limit:10 and 2 consumers
Producer produced=1
Producer produced=2
Consumer Thread-1 consumed=1
Producer produced=3
Producer produced=4
Producer produced=5
Producer produced=6
Consumer Thread-2 consumed=2
Consumer Thread-2 consumed=3
Consumer Thread-2 consumed=4
Consumer Thread-2 consumed=5
Consumer Thread-1 consumed=6
Producer produced=7
Producer produced=8
Producer produced=9
Producer produced=10
Consumer Thread-2 consumed=7
Consumer Thread-2 consumed=8
Consumer Thread-2 consumed=9
Consumer Thread-1 consumed=10

The producer produces items in an incremental order starting at 1 till it reaches the limit. The Consumer threads also consume the items in the incremental order: picking items in the order in which they were added to the buffer. 

**Answer the following:**

(1)How many of each integer should you see printed?
Ans: Each integer is equivalent to an item, will be produced and consumed only ONCE.

Sample output for producer with limit:10 and 5 consumers
Example:Enter limit for producer to produce item
10
Enter number of consumers
5
Producer produced=1
Producer produced=2
Producer produced=3
Producer produced=4
Producer produced=5
Consumer Thread-1 consumed=1
Consumer Thread-5 consumed=2
Consumer Thread-2 consumed=3
Consumer Thread-3 consumed=4
Consumer Thread-4 consumed=5
Producer produced=6
Producer produced=7
Producer produced=8
Producer produced=9
Producer produced=10
Consumer Thread-1 consumed=6
Consumer Thread-5 consumed=7
Consumer Thread-2 consumed=8
Consumer Thread-3 consumed=9
Consumer Thread-4 consumed=10

(2)In what order should you expect to see them printed? Why?
Ans: Sample output with producer limit 10 items and 5 consumers:
Enter limit for producer to produce item
10
Enter number of consumers
5
Producer produced=1
Producer produced=2
Producer produced=3
Producer produced=4
Producer produced=5
Consumer Thread-5 consumed=1
Consumer Thread-1 consumed=2
Consumer Thread-2 consumed=3
Consumer Thread-3 consumed=4
Consumer Thread-4 consumed=5
Producer produced=6
Producer produced=7
Producer produced=8
Producer produced=9
Producer produced=10
Consumer Thread-5 consumed=6
Consumer Thread-1 consumed=7
Consumer Thread-2 consumed=8
Consumer Thread-3 consumed=9
Consumer Thread-4 consumed=10

(3)In this producer consumed in increasing order of integer,and was consumed in the same order in which the items were added to the buffer. 

Did your results differ from your answers in (1) and (2)?
Ans: 
The integer order in which items are produced/consumed and printed will go from 1 to the limit: order in which items are added to the buffer ie. First in First Out fashion.
However, we must note that Threads that consume each these items will vary in each run, as the thread that acquires lock on the buffer item due to the concurrent nature of operations.

Example: In the first output, Consumer Thread-1 consumed the first item produced, while Consumer Thread-5 consumed the second item produced. In the second output, it was the other way around - Consumer Thread-5 consumed the first item produced, while Consumer Thread-1 consumed the second item produced. This difference in order of consumption could be due to the random nature of thread scheduling and execution in a multi-threaded environment.

**Title: Producer-Consumer with Threads**

This program demonstrates the solution to the classic Producer-Consumer problem using a bounded buffer implemented with a circular buffer data structure.

**Dependencies:**

This program requires Java version 8 or higher.

**Usage:**
To run the program, execute the following command from the command line:
(1)Go to the directory which has all java and make files.
(2)Run command: make prodcon
(3)Next run command:java RunnerClass 
(4)provide input for 'Enter limit for producer to produce item'
(5)provide input for 'Enter number of consumers'

**Implementation Details:**

ProducerConsumer Class: contains the main logic of the program. It uses a CircularBuffer class to implement the bounded buffer, and uses Semaphore objects to synchronize access to the buffer between the producer and consumer threads.

The produce method is used by the producer thread to add items to the buffer. It first acquires the producerSemaphore and the empty semaphore, and then adds the item to the buffer. After releasing the mutex semaphore and acquiring the full semaphore, it releases the consumerSemaphore.

The consume method is used by the consumer threads to remove items from the buffer. It first acquires the consumerSemaphore and the full semaphore, and then removes the item from the buffer. After releasing the mutex semaphore and acquiring the empty semaphore, it releases the producerSemaphore.

Runner Class: creates circularBuffer of capacity 5,
input::limit for producer to produce item and number of consumers,
three threads:: creates one producer and two consumers, and starts them.

CircularBuffer Class: contains implementation for buffer
