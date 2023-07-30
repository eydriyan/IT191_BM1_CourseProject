package com.it191.controller;

import java.util.ArrayList;

import com.it191.model.SongModel;
import com.it191.repository.SongRepository;

public class FavoritesController {
    private SongRepository songRepository;

    public FavoritesController() {
        songRepository = new SongRepository();
    }

    public ArrayList<SongModel> getFavoriteSongs() {
        return songRepository.getAllSongsFromCollection("Favorites");
    }
}
