package utils;

import java.io.*;

public class Printer {
    private static BufferedReader fileReader;

    public static void printMenu() {
        try {
            fileReader = new BufferedReader(new FileReader("./src/res/banner.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            String line = fileReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = fileReader.readLine();
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void clearScreen() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
}
