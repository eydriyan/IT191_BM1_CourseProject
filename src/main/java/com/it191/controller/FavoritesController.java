package com.it191.controller;

import java.util.ArrayList;

import com.it191.model.SongModel;
import com.it191.repository.CollectionRepository;
import com.it191.repository.SongRepository;
import com.it191.view.objects.SongEvent;

public class FavoritesController {
    private SongRepository songRepository;
    private CollectionRepository collectionRepository;

    public FavoritesController() {
        songRepository = new SongRepository();
        collectionRepository = new CollectionRepository();
    }

    public ArrayList<SongModel> getFavoriteSongs() {
        return songRepository.getAllSongsFromCollection("Favorites");
    }

    public void addSongToFavorites(SongEvent event) {
        SongModel songModel = new SongModel();
        songModel.setArtist(event.getArtist());
        songModel.setDuration(event.getDuration());
        songModel.setImgPath(event.getImgPath());
        songModel.setLyrics(event.getLyrics());
        songModel.setSongId(event.getSongId());
        songModel.setSongPath(event.getSongPath());
        songModel.setTitle(event.getTitle());
        songModel.setInFavorites(event.isInFavorites());

        collectionRepository.addSongToCollection(songModel, "Favorites");
    }

    public void removeSongFromFavorites(SongEvent event) {
        SongModel songModel = new SongModel();
        songModel.setArtist(event.getArtist());
        songModel.setDuration(event.getDuration());
        songModel.setImgPath(event.getImgPath());
        songModel.setLyrics(event.getLyrics());
        songModel.setSongId(event.getSongId());
        songModel.setSongPath(event.getSongPath());
        songModel.setTitle(event.getTitle());
        songModel.setInFavorites(event.isInFavorites());

        collectionRepository.removeSongFromCollection(songModel, "Favorites");
    }
}
