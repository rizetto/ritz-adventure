import utils.Dialog;
import utils.Printer;

public class Main {
    public static void main(String[] args) {
        Story story = new Story(new Dialog());
        story.showMenu();
    }
}
