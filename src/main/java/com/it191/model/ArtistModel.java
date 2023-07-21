package com.it191.model;

import java.util.ArrayList;

public class ArtistModel {
    public int artistId;
    public UserModel user;
    public ArrayList<AlbumModel> albums;
    public ArrayList<SongModel> songs;

    
    public int getArtistId() {
        return artistId;
    }
    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }
    public UserModel getUser() {
        return user;
    }
    public void setUser(UserModel user) {
        this.user = user;
    }
    public ArrayList<AlbumModel> getAlbums() {
        return albums;
    }
    public void setAlbums(ArrayList<AlbumModel> albums) {
        this.albums = albums;
    }
    public ArrayList<SongModel> getSongs() {
        return songs;
    }
    public void setSongs(ArrayList<SongModel> songs) {
        this.songs = songs;
    }
}
