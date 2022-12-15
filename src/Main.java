import models.CharacterDatabase;
import utils.Dialog;
import utils.Printer;

public class Main {
    public static void main(String[] args) {
        Story story = new Story(new Dialog(), new CharacterDatabase());
        story.startGame();
    }
}
