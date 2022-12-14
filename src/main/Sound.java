package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


public class Sound
{
    Clip clip;
    URL[] soundURL = new URL[10];
    FloatControl fc;
    int volumeScale = 1;
    float volume;

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
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void setFile(URL soundURL) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        }
        catch (Exception exception) {
            exception.printStackTrace();
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

    public void checkVolume() {
        switch (volumeScale) {
            case 0 -> volume = -80f;
            case 1 -> volume = -30f;
            case 2 -> volume = -15f;
            case 3 -> volume = -5f;
            case 4 -> volume = 1f;
            case 5 -> volume = 6f;
        }
        fc.setValue(volume);

    }
}