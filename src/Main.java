import models.CharacterDatabase;
import story.BackgroundMusic;
import utils.Dialog;

public class Main {
    public static void main(String[] args) {
        Story story = new Story(
                new Dialog(),
                new CharacterDatabase(),
                new BackgroundMusic()
        );
        story.startGame();
    }
}
