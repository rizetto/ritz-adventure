package utils;

import java.io.*;

public class Printer {
    private static BufferedReader fileReader;

    public static void printFromFile(String filePath) {
        try {
            fileReader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        try {
            String line = fileReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = fileReader.readLine();
            }

            fileReader.close();
        } catch (IOException e) {
            System.out.println("File not found!");
        }
    }

    public static void printCountdown(int duration, String message) {
        int timer = duration;

        System.out.println(message);
        while (timer > 0) {
            try {
                System.out.println(timer--);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void clearScreen() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
}
