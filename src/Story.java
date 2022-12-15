import models.Character;
import story.Dialogs;
import story.StoryMode;
import utils.Console;
import utils.Database;
import utils.Printer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.spi.AbstractResourceBundleProvider;

public class Story {
    private Printer printer;
    private Database<Character> database;
    private StoryMode storyMode;

    public Story(Printer printer, Database<Character> database) {
        this.printer = printer;
        this.database = database;
    }

    public void startGame() {
        showLoadingScreen();

        while (true) {
            showMenu();
            switch (storyMode) {
                case ADVENTURE_MODE -> renderAdventureMode();
                case ZEN_MODE -> renderZenMode();
                case CHARACTER_MODE -> renderCharacterMode();
                default -> System.exit(0);
            }
        }
    }

    private void showLoadingScreen() {
        printer.printFromFile(Dialogs.BANNER);
        printer.printCountdown(3, "\nStarting");
    }

    private void showMenu() {
        Console.clearScreen();
        printer.printFromFile(Dialogs.MENU);

        int mode = Console.getIntegerInput("Enter mode: ");
        switch (mode) {
            case 1 -> storyMode = StoryMode.ADVENTURE_MODE;
            case 2 -> storyMode = StoryMode.ZEN_MODE;
            case 3 -> storyMode = StoryMode.CHARACTER_MODE;
            case 4 -> {
                System.out.println("Bye!");
                System.exit(0);
            }
            default -> {
                System.out.println("Invalid input!");
                System.exit(0);
            }
        }

        Console.clearScreen();
    }

    private void renderAdventureMode() {

    }

    private void renderZenMode() {
        printer.printCountdown(3, "Starting Zen Mode");
        Console.clearScreen();
        System.out.println("Press E to read through the story");
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("./src/res/story.txt"));
            String line = fileReader.readLine();
            while (line != null) {
                System.out.println(line);
                printer.printSeparator();
                if (Console.getStringInput("").equalsIgnoreCase("E")) {
                    line = fileReader.readLine();
                    Console.clearScreen();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void renderCharacterMode() {
        Console.clearScreen();
        while (true) {
            printCharacters();
            int characterCode = Console.getIntegerInput("Choose a character to view information: ");
            if (characterCode > database.getAll().size()) {
                break;
            }

            printCharacterInformation(characterCode);
        }
    }

    private void printCharacters() {
        List<Character> characters = database.getAll();

        System.out.println("Characters");
        printer.printSeparator();
        for (Character character : characters) {
            int characterIndex = characters.indexOf(character) + 1;
            String characterName = character.getName();
            System.out.printf("%d. %s\n", characterIndex, characterName);
        }
        printer.printSeparator();
    }

    private void printCharacterInformation(int characterCode) {
        List<Character> characters = database.getAll();

        printer.printSeparator();
        for (int i = 0; i < characters.size(); i++ ){
            if (i == (characterCode - 1)) {
                Character character = characters.get(i);
                System.out.printf(
                        "Name: %s\nAge: %s\nDescription: %s\n",
                        character.getName(),
                        character.getAge(),
                        character.getDescription()
                );
            }
        }
    }
}
