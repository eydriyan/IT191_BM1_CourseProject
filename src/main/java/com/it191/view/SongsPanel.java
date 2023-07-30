package com.it191.view;

import java.util.ArrayList;

import javax.swing.JPanel;

import com.it191.controller.SongsController;
import com.it191.model.SongModel;
import com.it191.view.listeners.ISongRequestListener;

public class SongsPanel extends JPanel {

    private SongsController songsController;
    private ISongRequestListener songRequestListener;

    public SongsPanel() {
        this.onUISetup();
        songsController = new SongsController();
    }

    public void setSongRequestListener(ISongRequestListener songRequestListener) {
        this.songRequestListener = songRequestListener;
    }

    private void onUISetup() {
        this.setBackground(new java.awt.Color(51, 51, 51));
        this.setLayout(new java.awt.GridLayout(10, 1));
    }

    public void onRefreshSongs() {
        ArrayList<SongModel> songs = songsController.getSongs();

        this.removeAll();
        for (SongModel songModel : songs) {
            SongItem songItem = new SongItem(songModel);
            songItem.setSongRequestListener(songRequestListener);
            this.add(songItem);
        }

        this.revalidate();
    }
}
