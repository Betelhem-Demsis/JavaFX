package com.example.filemover;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.stream.Stream;



public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        String sourcePath = "C:\\Users\\asus\\Desktop\\starter";
        String destinationPath = "C:\\Users\\asus\\Desktop\\destination";

        try (Stream<Path> filePathStream=Files.walk(Paths.get(sourcePath))) {
            Path lastDir = filePathStream
                    .filter(Files::isDirectory)
                    .max(Comparator.comparing(Path::toString))
                    .orElseThrow(() -> new IOException("No subdirectories found"));

            try (Stream<Path> fileStream = Files.list(lastDir)) {
                Path lastFile = fileStream
                        .filter(Files::isRegularFile)
                        .max(Comparator.comparing(Path::toString))
                        .orElseThrow(() -> new IOException("No files found in the last directory"));

                Files.move(lastFile, Paths.get(destinationPath, lastFile.getFileName().toString()));
                System.out.println("File moved successfully");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}