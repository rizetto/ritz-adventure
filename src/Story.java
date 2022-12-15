import story.Dialogs;
import story.StoryMode;
import utils.Console;
import utils.Printer;

public class Story {
    private Printer printer;
    private StoryMode storyMode;

    public Story(Printer printer) {
        this.printer = printer;
    }

    public void showMenu() {
        printer.printFromFile(Dialogs.BANNER);
        printer.printCountdown(3, "\nStarting");
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
}
