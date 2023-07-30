package com.it191.view;

import java.util.ArrayList;

import javax.swing.JPanel;

import com.it191.controller.FavoritesController;
import com.it191.model.SongModel;
import com.it191.view.listeners.ISongRequestListener;

public class FavoritesPanel extends JPanel {

    private FavoritesController favoritesController;
    private ISongRequestListener songRequestListener;

    public FavoritesPanel() {
        this.onUISetup();
        favoritesController = new FavoritesController();

        this.onRefreshSongs();
    }

    private void onUISetup() {
        this.setBackground(new java.awt.Color(51, 51, 51));
        this.setLayout(new java.awt.GridLayout(10, 1));
    }

    private void onRefreshSongs() {
        ArrayList<SongModel> songs = favoritesController.getFavoriteSongs();

        this.removeAll();
        for (SongModel songModel : songs) {
            SongItem songItem = new SongItem(songModel);
            songItem.setSongRequestListener(songRequestListener);
            this.add(songItem);
        }

        this.revalidate();
    }

}
