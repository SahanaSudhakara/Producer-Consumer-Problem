# Define variables
JC = javac
JFLAGS = -g

# Define targets
all: prodcon

prodcon: RunnerClass.java CircularBuffer.java ProducerConsumer.java
	$(JC) $(JFLAGS) RunnerClass.java CircularBuffer.java ProducerConsumer.java

# Define clean-up task
clean:
	$(RM) *.class
