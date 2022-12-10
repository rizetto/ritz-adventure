import story.Story;
import utils.Printer;

public class Main {
    public static void main(String[] args) {
        Story story = new Story();
        story.createDialogs();

        Printer.printMenu();
    }
}
