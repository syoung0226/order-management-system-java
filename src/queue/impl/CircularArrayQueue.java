package queue.impl;

import queue.Queue;
import queue.exception.QueueEmptyException;
import queue.exception.QueueFullException;

import java.util.ArrayList;
import java.util.List;

public class CircularArrayQueue<T> implements Queue<T> {

	private int queueSize;
	private int currentQueueSize;
	private T[] queueDataArray;
	private int front = 0;
	private int rear = 0;

	public CircularArrayQueue(int capacity) {
		currentQueueSize = 0;
		queueSize = capacity;
		queueDataArray = (T[]) new Object[queueSize];
	}

	@Override public int getCurrentQueueSize() {
		return currentQueueSize;
	}

	@Override public boolean isEmpty() {
		return getCurrentQueueSize() == 0;
	}

	@Override public boolean isFull() {
		return getCurrentQueueSize() == queueSize;
	}

	@Override public void enqueue(T queueData) throws QueueFullException {

		if (isFull()) {
			resizeQueue(getCurrentQueueSize() * 2);
		}

		queueDataArray[rear] = queueData;
		rear = (rear + 1) % queueSize;
		currentQueueSize++;
	}

	@Override public T dequeue() throws QueueEmptyException {

		T result;
		if (isEmpty()) {
			throw new QueueEmptyException("Queue is Empty");
		} else {
			result = queueDataArray[front];
			queueDataArray[front] = null;
			front = (front + 1) % queueSize;
			currentQueueSize--;
			if (getCurrentQueueSize() < queueSize / 2) {
				resizeQueue(queueSize / 2);
			}
		}
		return result;
	}

	@Override public List<T> dequeueAll() {

		List<T> result = new ArrayList<>();

		while (!isEmpty()) {
			result.add(dequeue());
		}

		return result;
	}

	private void resizeQueue(int resizeQueueSize) {
		T[] resizeQueueDataArray = (T[]) new Object[resizeQueueSize];
		for (int i = 0; i < getCurrentQueueSize(); i++) {
			resizeQueueDataArray[i] = queueDataArray[(front + i) % queueDataArray.length];
		}
		queueDataArray = resizeQueueDataArray;
		front = 0;
		rear = getCurrentQueueSize();
		queueSize = resizeQueueSize;
	}
}
