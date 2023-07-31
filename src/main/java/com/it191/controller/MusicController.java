package com.it191.controller;

import java.util.ArrayList;

import com.it191.model.CollectionModel;
import com.it191.model.SongModel;
import com.it191.view.listeners.IPlayerUpdateListener;
import com.it191.view.objects.SongEvent;

//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;

public class MusicController extends MusicPlayer {

    // MediaPlayer mp;
    // String mp_song_path = "file:/C:/Users/Dell/Downloads/tests/song_2.mp3";

    // Format:
    // songs
    //  |_ <song_id>
    //          |_ song.mp3
    //          |_ image.(jpg, png, etc...)

    // playedSongsHistory currentlySelectedSong loadedSongsToPlay
    // [ s1 s2 s3 s4 ] s5 [ s7 s8 s9 s10 ]

    private ArrayList<SongModel> playedSongsHistory;
    private ArrayList<SongModel> loadedSongsToPlay;
    private SongModel currentlySelectedSong;

    private IPlayerUpdateListener songUpdateListener;

    public MusicController() {
        super();
        playedSongsHistory = new ArrayList<>();
        loadedSongsToPlay = new ArrayList<>();
    }

    public void setSongUpdateListener(IPlayerUpdateListener songUpdateListener) {
        this.songUpdateListener = songUpdateListener;
    }

    public void songToPlay(SongEvent evt) {
        SongModel songModel = new SongModel();
        songModel.setTitle(evt.getTitle());
        songModel.setArtist(evt.getArtist());
        songModel.setDuration(evt.getDuration());
        songModel.setImgPath(evt.getImgPath());
        songModel.setLyrics(evt.getLyrics());
        songModel.setSongId(evt.getSongId());
        songModel.setSongPath(evt.getSongPath());

        if (this.currentlySelectedSong != null)
            if (this.currentlySelectedSong.equalsSong(songModel))
                return;

        this.loadedSongsToPlay.add(0, songModel);
        this.nextSong();
    }

    public void addSongFromCollection(CollectionModel collectionModel) {
        for (SongModel songModel : collectionModel.getSongs()) {
            this.loadedSongsToPlay.add(songModel);
        }

        // If currentlySelectedSong is None, load from loadedSongsToPlay
        if(this.currentlySelectedSong == null) {
            SongModel songToPlay = this.loadedSongsToPlay.remove(0);
            this.currentlySelectedSong = songToPlay;
            
            this.loadSongToPlayer();
        }
    }

    public boolean prevSong() {
        if (this.playedSongsHistory.isEmpty())
            return false;
        
        this.loadedSongsToPlay.add(0, this.currentlySelectedSong);
        SongModel prevSongToPlay = this.playedSongsHistory.remove(0);
        this.currentlySelectedSong = prevSongToPlay;

        this.loadSongToPlayer();
        
        return true;
    }

    public boolean nextSong() {
        if (this.loadedSongsToPlay.isEmpty())
            return false;
        
        this.playedSongsHistory.add(0, this.currentlySelectedSong);
        SongModel nextSongToPlay = this.loadedSongsToPlay.remove(0);
        this.currentlySelectedSong = nextSongToPlay;

        this.loadSongToPlayer();

        return true;
    }

    // ==============================================================================================================

    // ==============================================================================================================

    private void loadSongToPlayer() {
        // Get Currently Selected Song
        // Put to Music Player
        this.loadSong(this.currentlySelectedSong.getSongPath());

        // Update View(s)
        SongEvent evt = new SongEvent(
            new Object(),
            currentlySelectedSong.getTitle(),
            currentlySelectedSong.getArtist(),
            currentlySelectedSong.getImgPath(),
            currentlySelectedSong.getLyrics()
        );
        this.songUpdateListener.onPlayerUpdateSong(evt);
    }
}
