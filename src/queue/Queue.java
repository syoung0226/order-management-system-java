package queue;

import queue.exception.QueueEmptyException;
import queue.exception.QueueFullException;

import java.util.List;

public interface Queue<T> {

	int getCurrentQueueSize();
	boolean isEmpty();
	boolean isFull();

	void enqueue(T queueData) throws QueueFullException;

	T dequeue() throws QueueEmptyException;

	List<T> dequeueAll();
}
