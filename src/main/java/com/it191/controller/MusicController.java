package com.it191.controller;

import java.util.ArrayList;

import com.it191.model.CollectionModel;
import com.it191.model.SongModel;

//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;

public class MusicController {

    // MediaPlayer mp;
    // String mp_song_path = "file:/C:/Users/Dell/Downloads/tests/song_2.mp3";

    String song_1 = "C://Users//Dell//Downloads//tests//Happy Together.mp3"; // yung kay mam
    String song_2 = "C:/Users/Dell/Downloads/tests/song_2.mp3";
    String song_3 = "C:/Users/Dell/Downloads/tests/song_3.mp3";
    String song_4 = "C:/Users/Dell/Downloads/tests/song_4.mp3";
    String song_5 = "C:/Users/Dell/Downloads/tests/song_5.mp3";
    String song_6 = "C:/Users/Dell/Downloads/tests/song_6.mp3";
    String song_7 = "C:/Users/Dell/Downloads/tests/song_7.mp3";

    // playedSongsHistory currentlySelectedSong loadedSongsToPlay
    // [ s1 s2 s3 s4 ] s5 [ s7 s8 s9 s10 ]

    private MusicPlayer musicPlayer;
    private ArrayList<SongModel> playedSongsHistory;
    private ArrayList<SongModel> loadedSongsToPlay;
    private SongModel currentlySelectedSong;

    public MusicController() {
        musicPlayer = new MusicPlayer();
        playedSongsHistory = new ArrayList<>();
        loadedSongsToPlay = new ArrayList<>();
    }

    public void addSongFromCollection(CollectionModel collectionModel) {
        for (SongModel songModel : collectionModel.songs) {
            this.loadedSongsToPlay.add(songModel);
        }
    }

    public boolean prevSong() {
        if (this.playedSongsHistory.isEmpty())
            return false;
        
        this.loadedSongsToPlay.add(0, this.currentlySelectedSong);
        SongModel prevSongToPlay = this.playedSongsHistory.remove(0);
        this.currentlySelectedSong = prevSongToPlay;
        
        return true;
    }

    public boolean nextSong() {
        if (this.loadedSongsToPlay.isEmpty())
            return false;
        
        this.playedSongsHistory.add(0, this.currentlySelectedSong);
        SongModel nextSongToPlay = this.loadedSongsToPlay.remove(0);
        this.currentlySelectedSong = nextSongToPlay;

        return true;
    }

    public boolean playSong() {
        return musicPlayer.Play();
    }

    public void pauseSong() {
        musicPlayer.Pause();
    }

    public void stopSong() {
        musicPlayer.Stop();
    }

    public void seekPlaySong(double positionPercent) {
        musicPlayer.seekPlay(positionPercent);
    }

    public double getCurrentSongPositionPercent() {
        return musicPlayer.getCurrentPositionPercent();
    }

    public void setSongVolume(float volumePercent) {
        musicPlayer.setVolume(volumePercent);
    }
}
