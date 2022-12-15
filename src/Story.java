import models.Character;
import story.Audio;
import story.Dialogs;
import story.StoryMode;
import utils.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Story implements Playable {
    private final Printer printer;
    private final Database<Character> database;
    private final Sound sound;
    private StoryMode storyMode;

    public Story(Printer printer, Database<Character> database, Sound sound) {
        this.printer = printer;
        this.database = database;
        this.sound = sound;
    }

    @Override
    public void startGame() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        showLoadingScreen();
        try {
            sound.play(Audio.TITLE, true);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }

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
        System.out.println();
    }

    private void showMenu() {
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
        try {
            sound.stop();
            sound.play(Audio.SELECT, false);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    private void renderAdventureMode() {

    }

    private void renderZenMode() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        printer.printCountdown(3, "Starting Zen Mode");

        sound.stop();
        sound.play(Audio.ADVENTURE, true);

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
                Console.clearScreen();

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
