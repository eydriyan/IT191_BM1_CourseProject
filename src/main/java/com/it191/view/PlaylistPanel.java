package com.it191.view;

import java.util.ArrayList;

import javax.swing.JPanel;

import com.it191.controller.PlaylistsController;

public class PlaylistPanel extends JPanel {

    private PlaylistsController playlistsController;
    private ArrayList<PlaylistItem> playlistItems;

    public PlaylistPanel(){
        this.onUISetup();
        playlistsController = new PlaylistsController();
        playlistItems = new ArrayList<>();

        this.onRefreshPlaylists();
    }

    private void onUISetup() {
        this.setBackground(new java.awt.Color(51, 51, 51));
        this.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
    }

    private void onRefreshPlaylists() {
        playlistItems.add(new PlaylistItem("Playlist 1"));
        playlistItems.add(new PlaylistItem("Playlist 2"));
        playlistItems.add(new PlaylistItem("Playlist 3"));
        playlistItems.add(new PlaylistItem("Playlist 4"));
        playlistItems.add(new PlaylistItem("Playlist 5"));

        for (PlaylistItem playlistItem : playlistItems) {
            this.add(playlistItem);
        }

        this.revalidate();
    }
}
