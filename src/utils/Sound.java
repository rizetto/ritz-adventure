package utils;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public interface Sound {
    void play(String filePath) throws
            UnsupportedAudioFileException,
            IOException,
            LineUnavailableException;
}
