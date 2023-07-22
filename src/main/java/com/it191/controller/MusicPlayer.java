package com.it191.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.JavaSoundAudioDevice;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class MusicPlayer {

    private Thread musicThread;
    private FileInputStream inputStream;
    private AdvancedPlayer player;
    private AtomicBoolean isPlaying;
    private boolean isFirstRun;
    private AudioDevice audioDevice;
    private FloatControl volumeControl;
    private int currentTotalLength;
    private int lengthBeforePause;
    private float currentVolumePercent;

    private String currentLoadedSongPath;

    public MusicPlayer() {
        // Initialize Variables
        this.isFirstRun = true;
        this.isPlaying = new AtomicBoolean(false);

        // Initialize and Run Music Thread
        this.musicThread = new Thread(() -> {
            while (true) {
                if (this.isPlaying.get()) {
                    try {
                        this.player.play();
                    } catch (Exception e) {
                        this.isPlaying.set(false);
                        e.printStackTrace();
                    }
                }
            }
        });

        musicThread.setDaemon(true);
        musicThread.start();
    }

    public void LoadSong(String songPath) {
        this.currentLoadedSongPath = songPath;
    }

    public void Play() {
        try {
            // Exit if currently playing already or if there are no loaded song path
            if (this.isPlaying.get() || this.currentLoadedSongPath == null || this.currentLoadedSongPath.equals(""))
                return;

            this.inputStream = new FileInputStream(new File(this.currentLoadedSongPath));

            // Only do skip calculations when it is not the "first run"
            if (!this.isFirstRun)
                this.inputStream.skip(this.currentTotalLength - this.lengthBeforePause);

            // Always reset AudioDevice, VolumeControl, and Player
            this.volumeControl = null;
            this.audioDevice = FactoryRegistry.systemRegistry().createAudioDevice();
            this.player = new AdvancedPlayer(inputStream, audioDevice);

            // Set Playback Listener for VolumeSync
            this.player.setPlayBackListener(this.playbackListener);

            // Start Playing Song
            this.isPlaying.set(true);

            // Calculate currentTotalLength if this is the first run
            if (this.isFirstRun) {
                this.currentTotalLength = this.inputStream.available();
                // this.lengthBeforePause = this.currentTotalLength;
                this.isFirstRun = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Pause() {
        try {
            // Exit if not playing currently
            if (!this.isPlaying.get())
                return;

            // Set variable values
            this.isFirstRun = false;
            this.isPlaying.set(false);
            this.lengthBeforePause = this.inputStream.available();

            // Close player (will also close AudioDevice) and close FileInputStream
            this.player.close();
            this.inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Stop() {
        try {
            // Exit if not playing currently
            if (!this.isPlaying.get())
                return;

            // Set variable values
            this.isFirstRun = true;
            this.isPlaying.set(false);

            // Close player (will also close AudioDevice) and close FileInputStream
            this.player.close();
            this.inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void seekPlay(double positionPercent) {
        try {
            // Only set lengthBeforePause property since
            // it will be used when played
            this.lengthBeforePause = this.currentTotalLength - (int) (this.currentTotalLength * positionPercent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getCurrentPositionPercent() {
        try {
            if (!this.isPlaying.get())
                return 0;

            // Calculate Percent of position
            // Then return result
            // Put Guard so min value is 0
            // and max value is 1
            double res = 1 - ((double) inputStream.available() / (double) currentTotalLength);
            return (res > 0) ? res : 0;
        } catch (IOException e) {
            this.isPlaying.set(false);
            e.printStackTrace();
        }
        return 0;
    }

    public void setVolume(float volumePercent) {
        try {
            // Set currentVolumePercent of MusicPlayer
            currentVolumePercent = volumePercent;

            // Exit if not playing currently
            if (!isPlaying.get())
                return;

            // Get VolumeControl if song is already playing
            // This is done via Reflection
            // Reference:
            // https://github.com/Gikkman/JavaZoom-Volume-Controll/blob/master/src/main/java/com/gikk/javazoom/JLayerTest.java
            if (volumeControl == null) {
                Field sourceField = JavaSoundAudioDevice.class.getDeclaredField("source");
                sourceField.setAccessible(true);
                SourceDataLine source = (SourceDataLine) sourceField.get(audioDevice);
                sourceField.setAccessible(false);

                if (source != null && source.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                    volumeControl = (FloatControl) source.getControl(FloatControl.Type.MASTER_GAIN);
                }
            }

            // If a VolumeControl instance had been already fetched, adjust the volume
            if (volumeControl != null) {
                float newVolume = (volumePercent * (volumeControl.getMaximum() - volumeControl.getMinimum()))
                        + volumeControl.getMinimum();
                volumeControl.setValue(newVolume);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PlaybackListener playbackListener = new PlaybackListener() {
        @Override
        public void playbackStarted(PlaybackEvent evt) {
            super.playbackStarted(evt);

            // Check if volumePercent in valid range
            if (currentVolumePercent >= 0 && currentVolumePercent <= 1) {

                // Create Thread to have delay on setting the volume
                // I guess that the volume is only set when there is a song loaded
                // so putting the right delay will set the volume when song is already loaded
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
    };
}
