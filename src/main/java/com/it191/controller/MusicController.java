package com.it191.controller;

import java.util.ArrayList;

import com.it191.model.CollectionModel;
import com.it191.model.SongModel;
import com.it191.view.listeners.ISongUpdateListener;
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
    String song_1 = "songs/Happy Together.mp3";
    String song_2 = "songs/song_2.mp3";
    String song_3 = "songs/song_3.mp3";
    String song_4 = "songs/song_4.mp3";
    String song_5 = "songs/song_5.mp3";
    String song_6 = "songs/song_6.mp3";
    String song_7 = "songs/song_7.mp3";

    // playedSongsHistory currentlySelectedSong loadedSongsToPlay
    // [ s1 s2 s3 s4 ] s5 [ s7 s8 s9 s10 ]

    private ArrayList<SongModel> playedSongsHistory;
    private ArrayList<SongModel> loadedSongsToPlay;
    private SongModel currentlySelectedSong;

    private ISongUpdateListener songUpdateListener;

    public MusicController() {
        super();
        playedSongsHistory = new ArrayList<>();
        loadedSongsToPlay = new ArrayList<>();
    }

    public void MockLoad() {
        SongModel s1 = new SongModel();
        s1.setTitle("s1");
        s1.setArtist("a1");
        s1.setSongPath(song_1);
        SongModel s2 = new SongModel();
        s2.setTitle("s2");
        s2.setArtist("a2");
        s2.setSongPath(song_2);
        SongModel s3 = new SongModel();
        s3.setTitle("s3");
        s3.setArtist("a3");
        s3.setSongPath(song_3);
        SongModel s4 = new SongModel();
        s4.setTitle("s4");
        s4.setArtist("a4");
        s4.setSongPath(song_4);
        SongModel s5 = new SongModel();
        s5.setTitle("s5");
        s5.setArtist("a5");
        s5.setSongPath(song_5);
        SongModel s6 = new SongModel();
        s6.setTitle("s6");
        s6.setArtist("a6");
        s6.setSongPath(song_6);
        SongModel s7 = new SongModel();
        s7.setTitle("s7");
        s7.setArtist("a7");
        s7.setSongPath(song_7);

        this.loadedSongsToPlay.add(s1);
        this.loadedSongsToPlay.add(s2);
        this.loadedSongsToPlay.add(s3);
        this.loadedSongsToPlay.add(s4);
        this.loadedSongsToPlay.add(s5);
        this.loadedSongsToPlay.add(s6);
        this.loadedSongsToPlay.add(s7);

        if(this.currentlySelectedSong == null) {
            SongModel songToPlay = this.loadedSongsToPlay.remove(0);
            this.currentlySelectedSong = songToPlay;
            
            this.loadSongToPlayer();
        }
    }

    public void setSongUpdateListener(ISongUpdateListener songUpdateListener) {
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
        this.songUpdateListener.onSongUpdated(evt);
    }
}
