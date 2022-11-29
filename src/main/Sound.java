package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Sound
{
    Clip clip;
    URL[] soundURL = new URL[10];


    public Sound() {
        soundURL[0] = getClass().getResource("/res/sound/song.wav");
        soundURL[1] = getClass().getResource("/res/sound/pickKey.wav");
        soundURL[2] = getClass().getResource("/res/sound/doorOpen.wav");
        soundURL[3] = getClass().getResource("/res/sound/menuselect.wav");
        soundURL[4] = getClass().getResource("/res/sound/selectinv.wav");
        soundURL[5] = getClass().getResource("/res/sound/teleport.wav");


    }


    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch (Exception exception) {
        }
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