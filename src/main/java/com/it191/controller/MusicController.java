package com.it191.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.JavaSoundAudioDevice;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class MusicController {

    private Thread musicThread;
    private FileInputStream inputStream;
    private AdvancedPlayer player;
    private AtomicBoolean isPlaying;
    private boolean isFirstRun;

    private AudioDevice audioDevice;
    private FloatControl volumeControl;

    private int currentTotalLength;
    private int lengthBeforePause;

    private float currentVolumePercent = -1;

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
            if (isPlaying.get())
                return;

            inputStream = new FileInputStream(new File(song_1));

            if (!isFirstRun)
                inputStream.skip(currentTotalLength - lengthBeforePause);

            audioDevice = FactoryRegistry.systemRegistry().createAudioDevice();
            volumeControl = null;
            player = new AdvancedPlayer(inputStream, audioDevice);
            player.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackStarted(PlaybackEvent evt) {
                    super.playbackStarted(evt);
                    if (currentVolumePercent >= 0 && currentVolumePercent <= 1) {
                        Thread volumeSyncThread = new Thread(() -> {
                            try {
                                Thread.sleep(8);
                                setVolume(currentVolumePercent);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        volumeSyncThread.setDaemon(true);
                        volumeSyncThread.start();
                    }
                }
            });
            this.isPlaying.set(true);

            if (isFirstRun) {
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
            if (!isPlaying.get())
                return;

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
            if (!isPlaying.get())
                return;

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
            double res = 1 - ((double) inputStream.available() / (double) currentTotalLength);
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

    public void setVolume(float volumePercent) {
        try {
            currentVolumePercent = volumePercent;

            if (!isPlaying.get())
                return;

            if (volumeControl == null) {
                Field sourceField = JavaSoundAudioDevice.class.getDeclaredField("source");
                sourceField.setAccessible(true);
                SourceDataLine source = (SourceDataLine) sourceField.get(audioDevice);
                sourceField.setAccessible(false);

                if (source != null && source.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                    volumeControl = (FloatControl) source.getControl(FloatControl.Type.MASTER_GAIN);
                }
            }

            if (volumeControl != null) {
                float newVolume = (volumePercent * (volumeControl.getMaximum() - volumeControl.getMinimum()))
                        + volumeControl.getMinimum();
                volumeControl.setValue(newVolume);
                System.out.println("Volume set");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
