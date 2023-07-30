package com.it191.controller;

import java.util.ArrayList;

import com.it191.model.SongModel;
import com.it191.repository.SongRepository;

public class SongsController {
    public SongRepository songRepository;

    public SongsController() {
        songRepository = new SongRepository();
    }

    public ArrayList<SongModel> getSongs() {
        return songRepository.getAllSongs();
    }
}
