package com.it191.view;

import java.util.ArrayList;

import javax.swing.JPanel;

import com.it191.controller.FavoritesController;
import com.it191.model.SongModel;
import com.it191.view.listeners.ISongRequestListener;
import com.it191.view.listeners.ISongUpdateListener;
import com.it191.view.objects.SongEvent;

public class FavoritesPanel extends JPanel implements ISongUpdateListener {

    private FavoritesController favoritesController;
    private ISongRequestListener songRequestListener;

    public FavoritesPanel() {
        this.onUISetup();
        favoritesController = new FavoritesController();
    }

    public void setSongRequestListener(ISongRequestListener songRequestListener) {
        this.songRequestListener = songRequestListener;
    }

    private void onUISetup() {
        this.setBackground(new java.awt.Color(51, 51, 51));
        this.setLayout(new java.awt.GridLayout(10, 1));
    }

    public void onRefreshSongs() {
        this.removeAll();

        ArrayList<SongModel> songs = favoritesController.getFavoriteSongs();
        for (SongModel songModel : songs) {
            SongItem songItem = new SongItem(songModel);
            songItem.setSongRequestListener(songRequestListener);
            songItem.setSongUpdateListener(this);
            this.add(songItem);
        }

        this.repaint();
        this.revalidate();
        
    }

    @Override
    public void onSongUpdate(SongEvent evt) {
        if (evt.isInFavorites()) {
            favoritesController.addSongToFavorites(evt);
        } else {
            favoritesController.removeSongFromFavorites(evt);
        }

        this.onRefreshSongs();
    }
}
