package story;

import utils.Composer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Story implements Composer {
    private Map<String, String> dialogs;


    public Story() {
        dialogs = new HashMap<>();
    }

    public void printDialogs() {
        for (Map.Entry<String, String> e : dialogs.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }

    @Override
    public void createDialogs() {
        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new FileReader("./src/res/story.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            String line = fileReader.readLine();
            StringBuilder dialog = new StringBuilder();
            while (line != null) {
                if (line.contains("#")) {
                    dialogs.put(line.replace("#", ""), dialog.toString());
                    dialog.delete(0, dialog.length() - 1);
                } else {
                    dialog.append("\n").append(line);
                }

                line = fileReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
