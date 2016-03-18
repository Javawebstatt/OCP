package de.javawebstatt.umgebung;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class VereineUndSpielerPersist {

	private static final String FILE_OBJ_EXT = ".obj";
	private static final String FILE_TXT_EXT = ".txt";

	private static Path baseDirectory = Paths.get("C:\\", "Entwicklung", "GIT", "javawebstatt", "OCP", "TT Umgebung",
			"data");

	public static VereineUndSpieler laden(String fileName, String... directories) {
		VereineUndSpieler vus = null;
		Path directoryPath = Paths.get("", directories);

		Path completePath = baseDirectory.resolve(directoryPath);
		try {
			Files.createDirectories(completePath);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Path objFilePath = completePath.resolve(fileName.concat(FILE_OBJ_EXT));
		try (ObjectInputStream ois = new ObjectInputStream(
				Files.newInputStream(objFilePath, StandardOpenOption.READ))) {
			vus = (VereineUndSpieler) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return vus;
	}

	public static void speichern(VereineUndSpieler vus, String fileName, String... directories) {
		Path directoryPath = Paths.get("", directories);

		Path completePath = baseDirectory.resolve(directoryPath);
		try {
			Files.createDirectories(completePath);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Path objFilePath = completePath.resolve(fileName.concat(FILE_OBJ_EXT));
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(objFilePath.toFile()))) {
			oos.writeObject(vus);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Path txtFilePath = completePath.resolve(fileName.concat(FILE_TXT_EXT));
		try (BufferedWriter bufWrite = Files.newBufferedWriter(txtFilePath, StandardOpenOption.CREATE)) {
			bufWrite.write(vus.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
