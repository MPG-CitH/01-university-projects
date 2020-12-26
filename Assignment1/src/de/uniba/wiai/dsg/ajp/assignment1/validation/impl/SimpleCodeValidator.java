package de.uniba.wiai.dsg.ajp.assignment1.validation.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import de.uniba.wiai.dsg.ajp.assignment1.validation.CodeValidationException;
import de.uniba.wiai.dsg.ajp.assignment1.validation.CodeValidator;
import de.uniba.wiai.dsg.ajp.assignment1.validation.FileValidatorReturn;
import de.uniba.wiai.dsg.ajp.assignment1.validation.ValidationResult;
import de.uniba.wiai.dsg.ajp.assignment1.validation.ValidationTask;
import de.uniba.wiai.dsg.ajp.assignment1.validation.helper.DirectoryStreamRecursive;
import de.uniba.wiai.dsg.ajp.assignment1.validation.helper.ExecutorSafeShutdown;

public class SimpleCodeValidator implements CodeValidator {
	ExecutorService masterExecutor; // Execution Service for FileValidator
	CompletionService<FileValidatorReturn> completionService; // Completion Service for masterExecutor
	ValidationResult validationResult;

	public SimpleCodeValidator() {
		masterExecutor = Executors.newWorkStealingPool();
		completionService = new ExecutorCompletionService<>(masterExecutor);
		validationResult = new ValidationResult();
	}

	@Override
	public ValidationResult validate(final ValidationTask task) throws CodeValidationException {
		// Validate arguments
		validateValidationTask(task);

		// Collect .java files
		List<Path> paths = new ArrayList<>();
		try {
			Path rootDirectory = Paths.get(task.getRootFolder());
			paths = DirectoryStreamRecursive.walk(rootDirectory);
		} catch (IOException e) {
			throw new CodeValidationException("IOError while iterating over files");
		} catch (InvalidPathException e1) {

		}

		if (paths.size() == 0) {
			System.out.println("No files to validate");
		}

		// Start a path queue in a single runnable executor
		ExecutorService balancerExecutor = Executors.newSingleThreadExecutor();
		TaskMemoryBalancer taskMemoryBalancer = new TaskMemoryBalancer(this);
		
		// Add paths of files to validate to queue
		for (Path path : paths) {
			taskMemoryBalancer.put(path);
		}

		balancerExecutor.submit(taskMemoryBalancer);

		// Collect validation warnings
		int tasksDone = 0;
		while (tasksDone < paths.size()) {
			try {
				Future<FileValidatorReturn> taskDone = completionService.take(); // Take Task from Threadpool. Counter
																					// part to addTaskToPool function
				FileValidatorReturn taskResult = taskDone.get();
				taskResult.getGuidelineViolations().stream().forEach(validationResult::addWarning); // Append
																									// FileValidator
																									// results to
																									// ValidationResult
				long clearedMemory = taskResult.getSourceFile().toFile().length();
				taskMemoryBalancer.decreaseMemoryUsage(clearedMemory); // Tell memory balancer that the file is cleared
																		// from memory
				tasksDone++;
			} catch (InterruptedException e) {
				throw new CodeValidationException("A file validation task was interrupted");
			} catch (ExecutionException e) {
				throw new CodeValidationException(e.getCause());
			}

		}

		// End task blocking queue
		taskMemoryBalancer.setDone();
		// Write result to CSV
		System.out.println("Write result to CSV");
		ResultToCsv csvWriter = new ResultToCsv(Paths.get(task.getResultFile()), validationResult);
		csvWriter.writeToCsv();

		// Terminate thread pool
		ExecutorSafeShutdown.shutdownAndAwaitTermination(balancerExecutor);
		ExecutorSafeShutdown.shutdownAndAwaitTermination(masterExecutor);
		return validationResult;
	}

	// TaskMemoryBalancer calls this to trigger a new Thread for a File
	public void addTaskToPool(Path filePath) {
		FileValidator fileTask = new FileValidator(filePath);
		completionService.submit(fileTask);
	}

	private void validateValidationTask(final ValidationTask task) throws CodeValidationException {
		String rootFolder = task.getRootFolder();
		String resultFile = task.getResultFile();
		String exceptionMessage = "";
		boolean throwException = false;

		if (rootFolder == "" || rootFolder == null) {
			exceptionMessage = String.format("rootFolder attribute is empty.");
			throwException = true;
		} else {

			try {
				Path pathRootFolder = Paths.get(rootFolder);
				if (!Files.isDirectory(pathRootFolder)) {
					exceptionMessage = String.format("'%s' is not a directory.", rootFolder);
					throwException = true;
				}
			} catch (InvalidPathException e) {
				exceptionMessage = String.format("'%s' is not a valid path.", rootFolder);
				throwException = true;
			}
		}

		if (resultFile == "" || resultFile == null) {
			exceptionMessage = String.format("resultFile attribute is empty.");
			throwException = true;
		} else {
			try {
				Path pathResultFile = Paths.get(resultFile);
				File fileResultFile = new File(resultFile);

				try {
					if (!fileResultFile.createNewFile()) {
						if (!(Files.isRegularFile(pathResultFile) || Files.isWritable(pathResultFile))) {
							exceptionMessage = String.format("'%s' is not a file that can be processed.", resultFile);
							throwException = true;
						}
					}
				} catch (IOException | SecurityException e) {
					exceptionMessage = String.format(
							"Something went wrong while trying to create the non-existent file '%s' ", resultFile);
					throwException = true;
				}

			} catch (InvalidPathException e) {
				exceptionMessage = String.format("'%s' is not a valid path.", resultFile);
				throwException = true;
			}

		}
		if (throwException) {
			throw new CodeValidationException(exceptionMessage);
		}
	}

}
