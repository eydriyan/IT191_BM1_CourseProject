package com.it191.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class MusicController {

    private Thread musicThread;
    private FileInputStream inputStream;
    private AdvancedPlayer player;
    private AtomicBoolean isPlaying;
    private int lengthBeforePause;

    String song_1 = "C://Users//Dell//Downloads//tests//song_2.mp3";
    String song_2 = "C://Users//Dell//Downloads//tests//AD1//src//resources//Happy Together.mp3";

    public MusicController() {
        isPlaying = new AtomicBoolean(false);

        musicThread = new Thread(() -> {
            while (true) {
                if (isPlaying.get()) {
                    try {
                        player.play();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        musicThread.setDaemon(true);
        musicThread.start();

        resetStartingPoint();
    }

    private void resetStartingPoint() {
        try (FileInputStream fis = new FileInputStream(new File(song_1))) {
            lengthBeforePause = fis.available();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void playMusic() {
        try {
            inputStream = new FileInputStream(new File(song_1));
            inputStream.skip(inputStream.available() - lengthBeforePause);
            player = new AdvancedPlayer(inputStream);
            this.isPlaying.set(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pauseMusic() {
        try {
            this.isPlaying.set(false);
            lengthBeforePause = inputStream.available();
            player.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        this.isPlaying.set(false);
        player.close();
        resetStartingPoint();
    }

    public void seekPlay() {
        //
    }
}
