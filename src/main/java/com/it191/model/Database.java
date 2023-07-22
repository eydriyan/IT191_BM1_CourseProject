package com.it191.model;

import java.util.ArrayList;

public class Database {
    
    // Users
    public boolean signUpUser() {
        return false;
    }

    public boolean logInUser() {
        return false;
    }

    

    // Artist
    public void createArtistAccount(ArtistModel artistModel) {
        //
    }

    public ArtistModel getArtistAccount() {
        return null;
    }

    public void updateSongInfo(SongModel songModel) {
        //
    }

    public void deleteSongInfo(SongModel songModel) {
        //
    }



    // Playlist
    public void createNewPlaylist(PlaylistModel playlistModel) {
        //
    }

    public ArrayList<PlaylistModel> getAllPlaylists() {
        return null;
    }

    public void updatePlaylistInfo(PlaylistModel playlistModel) {
        //
    }

    public void deletePlaylistInfo(PlaylistModel playlistModel) {
        //
    }

    public void addSongToPlaylist(SongModel songModel, PlaylistModel playlistModel) {
        //
    }

    public void removeSongFromPlaylist(SongModel songModel, PlaylistModel playlistModel) {
        //
    }
    


    // Albums
    public void createNewAlbum(AlbumModel albumModel) {
        //
    }

    public ArrayList<AlbumModel> getAllAlbums() {
        return null;
    }

    public void updateAlbumInfo(AlbumModel albumModel) {
        //
    }

    public void deleteAlbumInfo(AlbumModel albumModel) {
        //
    }

    public void addSongToAlbum(SongModel songModel, AlbumModel albumModel) {
        //
    }

    public void removeSongFromAlbum(SongModel songModel, AlbumModel albumModel) {
        //
    }



    // Songs
    public void addNewSong(SongModel songModel) {
        //
    }

    public ArrayList<SongModel> getAllSongsFromAlbum(AlbumModel albumModel) {
        return null;
    }

    public ArrayList<SongModel> getAllSongsFromPlaylist(PlaylistModel playlistModel) {
        return null;
    }

    public ArrayList<SongModel> getAllSongsFromArtist(ArtistModel artistModel) {
        return null;
    }
}