package com.it191.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.it191.model.CollectionModel;
import com.it191.model.SongModel;

public class SongRepository extends RepositoryBase {

    public ArrayList<SongModel> getAllSongsFromArtist(String artist) {
        ArrayList<SongModel> songs = new ArrayList<>();
        try (Connection connection = getSqlConnection()) {
            String selectQuery = "SELECT Song.song_id, Song.artist, Song.title, Song.lyrics, Song.duration, Song.song_path, Song.img_path, Genre.name AS genre_name " +
                                 "FROM Song " +
                                 "INNER JOIN Genre ON Song.genre_id = Genre.genre_id " +
                                 "WHERE Song.artist = ?";

            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setString(1, artist);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                SongModel song = mapResultSetToSongModel(resultSet);
                songs.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
        return songs;
    }

    public ArrayList<SongModel> getAllSongsFromCollection(CollectionModel collectionModel) {
        ArrayList<SongModel> songs = new ArrayList<>();
        try (Connection connection = getSqlConnection()) {
            String selectQuery = "SELECT Song.song_id, Song.artist, Song.title, Song.lyrics, Song.duration, Song.song_path, Song.img_path, Genre.name AS genre_name " +
                                 "FROM Song " +
                                 "INNER JOIN Genre ON Song.genre_id = Genre.genre_id " +
                                 "INNER JOIN CollectionSong ON Song.song_id = CollectionSong.song_id " +
                                 "WHERE CollectionSong.collection_id = ?";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setInt(1, collectionModel.getCollectionId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                SongModel song = mapResultSetToSongModel(resultSet);
                songs.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

    private SongModel mapResultSetToSongModel(ResultSet resultSet) throws SQLException {
        SongModel song = new SongModel();
        song.setSongId(resultSet.getInt("song_id"));
        song.setArtist(resultSet.getString("artist"));
        song.setTitle(resultSet.getString("title"));
        song.setGenre(resultSet.getString("genre_name"));
        song.setLyrics(resultSet.getString("lyrics"));
        song.setDuration(resultSet.getInt("duration"));
        song.setSongPath(resultSet.getString("song_path"));
        song.setImgPath(resultSet.getString("img_path"));
        return song;
    }

}
