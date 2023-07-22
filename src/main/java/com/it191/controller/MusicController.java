package com.it191.controller;

public class MusicController {

    private MusicPlayer musicPlayer;

    String song_2 = "C://Users//Dell//Downloads//tests//song_1.mp3";
    String song_1 = "C://Users//Dell//Downloads//tests//AD1//src//resources//Happy Together.mp3";

    public MusicController() {
        // Initialize Variables
        musicPlayer = new MusicPlayer();
        musicPlayer.LoadSong(song_1);
    }

    public MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }

    public void PrevSong() {
        //
    }

    public void NextSong() {
        //
    }
}
