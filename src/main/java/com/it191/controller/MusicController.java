package com.it191.controller;

//import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicController {

    private MusicPlayer musicPlayer;
    MediaPlayer mp;

    //String mp_song_path = "file:/C:/Users/Dell/Downloads/tests/song_2.mp3";

    String song_1 = "C://Users//Dell//Downloads//tests//Happy Together.mp3"; // yung kay mam
    String song_2 = "C:/Users/Dell/Downloads/tests/song_2.mp3";
    String song_3 = "C:/Users/Dell/Downloads/tests/song_3.mp3";
    String song_4 = "C:/Users/Dell/Downloads/tests/song_4.mp3";
    String song_5 = "C:/Users/Dell/Downloads/tests/song_5.mp3";
    String song_6 = "C:/Users/Dell/Downloads/tests/song_6.mp3";
    String song_7 = "C:/Users/Dell/Downloads/tests/song_7.mp3";

    public MusicController() {
        // Initialize Variables

        //this.mp = new MediaPlayer(new Media(song_2));
        
        musicPlayer = new MusicPlayer();
        musicPlayer.LoadSong(song_7);
    }

    public MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }

    public void TestPlay() {
        //mp.play();
    }

    public void TestPause() {
        //mp.pause();
        //mp.setVolume(0);
    }

    public void PrevSong() {
        //
    }

    public void NextSong() {
        //
    }
}
