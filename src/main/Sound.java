package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Sound
{
    Clip clip;
    URL[] soundURL = new URL[30];


    public Sound() {
        soundURL[0] = getClass().getResource("/res/sound/song.wav");

    }


    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch (Exception exception) {}
    }


    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(-1);
    }

    public void stop() {
        clip.stop();
    }
}