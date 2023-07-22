package com.it191.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javazoom.jl.player.Player;

public class MusicController {

    private Thread musicThread;
    private FileInputStream inputStream;
    private Player player;
    private AtomicBoolean isPlaying;
    private boolean isFirstRun;

    private int currentTotalLength;
    private int lengthBeforePause;

    String song_2 = "C://Users//Dell//Downloads//tests//song_1.mp3";
    String song_1 = "C://Users//Dell//Downloads//tests//AD1//src//resources//Happy Together.mp3";

    public MusicController() {
        isFirstRun = true;
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
    }

    public void playMusic() {
        try {
            if(isPlaying.get()) return;
            
            inputStream = new FileInputStream(new File(song_1));
            
            if (!isFirstRun) inputStream.skip(currentTotalLength - lengthBeforePause);

            player = new Player(inputStream);
            this.isPlaying.set(true);

            if(isFirstRun) {
                currentTotalLength = inputStream.available();
                lengthBeforePause = currentTotalLength;
                isFirstRun = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pauseMusic() {
        try {
            if(!isPlaying.get()) return;

            isFirstRun = false;
            this.isPlaying.set(false);
            lengthBeforePause = inputStream.available();
            player.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        try {
            if(!isPlaying.get()) return;

            isFirstRun = true;
            this.isPlaying.set(false);
            player.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void seekPlay(double percent) {
        try {
            lengthBeforePause = currentTotalLength - (int) (currentTotalLength * percent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getCurrentPositionPercent() {
        try {
            double res = 1 - ((double)inputStream.available() / (double)currentTotalLength);
            return (res > 0) ? res : 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getDuration() {
        int dur = 0;

        try {
            Mp3File mp3file = new Mp3File(song_1);
            dur = (int) mp3file.getLengthInMilliseconds();
        } catch (UnsupportedTagException | InvalidDataException | IOException e) {
            e.printStackTrace();
        }

        return dur;
    }

    public void setVolume(float gain) {
    }
}
