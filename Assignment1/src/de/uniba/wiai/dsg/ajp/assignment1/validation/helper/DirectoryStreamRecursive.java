package de.uniba.wiai.dsg.ajp.assignment1.validation.helper;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DirectoryStreamRecursive {
	static List<Path> paths = new ArrayList<>();

	public static List<Path> walk(Path path) throws IOException {

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {

			for (Path entry : stream) {
				if (Files.isDirectory(entry)) {
					walk(entry);
				}
				if (Files.isRegularFile(entry)) {
					String name = entry.getFileName().toString();
					if (name.substring(name.lastIndexOf(".")).equals(".java")) {
						paths.add(entry);
					}
				}

			}
		}
		return paths;
	}
}
