package com.it191.view;

import java.util.ArrayList;

import javax.swing.JPanel;

import com.it191.controller.SongsController;

public class SongsPanel extends JPanel {

    private SongsController songsController;
    private ArrayList<SongItem> songItems;

    public SongsPanel() {
        this.onUISetup();
        songsController = new SongsController();
        songItems = new ArrayList<>();

        this.onRefreshSongs();
    }

    private void onUISetup() {
        this.setBackground(new java.awt.Color(51, 51, 51));
        this.setLayout(new java.awt.GridLayout(10, 1));
    }

    private void onRefreshSongs() {
        songItems.add(new SongItem("Song 1", "Artist 1", "", ""));
        songItems.add(new SongItem("Song 2", "Artist 2", "", ""));
        songItems.add(new SongItem("Song 3", "Artist 3", "", ""));
        songItems.add(new SongItem("Song 4", "Artist 4", "", ""));
        songItems.add(new SongItem("Song 5", "Artist 5", "", ""));

        for (SongItem songItem : songItems) {
            this.add(songItem);
        }

        this.revalidate();
    }
}
