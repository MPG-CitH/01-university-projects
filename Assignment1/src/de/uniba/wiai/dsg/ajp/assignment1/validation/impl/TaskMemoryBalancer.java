package de.uniba.wiai.dsg.ajp.assignment1.validation.impl;

import java.nio.file.Path;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class TaskMemoryBalancer implements Runnable {

	private SimpleCodeValidator simpleCodeValidator;
	final long MAX_MEMORY = 512000; // in bytes
	private long memoryInUse = 0;
	BlockingQueue<Path> blockingQueue;
	private volatile boolean done = false;

	public TaskMemoryBalancer(SimpleCodeValidator simpleCodeValidator) {
		this.blockingQueue = new LinkedBlockingDeque<Path>();
		this.simpleCodeValidator = simpleCodeValidator;
	}

	@Override
	public void run() {
		Path currentPath;
		while (!done) { // Pop a path from the queue to trigger a new Thread for the validation
			if (this.memoryInUse <= MAX_MEMORY) {
				try {
					currentPath = this.blockingQueue.poll(100, TimeUnit.MILLISECONDS);
					long fileSize = currentPath.toFile().length();
					/*
					 * Only start a new thread if there is free memory. Memory will be cleared if the
					 * file is closed again Exception: Memory is empty (necessary for files sizes
					 * greater than the memory limit) This mechanism shall prevent the Validator from
					 * blocking the systems RAM while processing multiple files at once.
					 */
					if (this.memoryInUse + fileSize <= MAX_MEMORY || this.memoryInUse == 0) {
						this.increaseMemoryUsage(currentPath.toFile().length());
						simpleCodeValidator.addTaskToPool(currentPath);
					} else {
						this.put(currentPath); // If memory is over capacity return the task to the queue
					}
				} catch (InterruptedException e) {
					System.err.println("Interrupted while waiting for queue");
					Thread.currentThread().interrupt();
				}
			}
		}
	}

	public void put(Path filePath) {
		blockingQueue.add(filePath);
	}
	
	public void setDone() {
		this.done = true;
	}

	public synchronized void increaseMemoryUsage(long memory) {
		this.memoryInUse += memory;
	}

	public synchronized void decreaseMemoryUsage(long memory) {
		this.memoryInUse -= memory;
	}
}
