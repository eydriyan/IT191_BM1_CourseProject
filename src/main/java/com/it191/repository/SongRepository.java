package com.it191.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.it191.model.CollectionModel;
import com.it191.model.SongModel;

public class SongRepository extends RepositoryBase {

    public ArrayList<SongModel> getAllSongs() {
        ArrayList<SongModel> songs = new ArrayList<>();
        try (Connection connection = getSqlConnection()) {
            String selectQuery = "SELECT s.*, CASE WHEN cs.song_id IS NOT NULL THEN 'true' ELSE 'false' END AS in_favorites " +
                "FROM Song s " +
                "LEFT JOIN CollectionSong cs " +
                "ON s.song_id = cs.song_id " +
                "LEFT JOIN Collection c " +
                "ON cs.collection_id = c.collection_id AND c.title = \'Favorites\'";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
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

    private ArrayList<SongModel> getAllSongsFromCollection(Connection connection, CollectionModel collectionModel) {
        ArrayList<SongModel> songs = new ArrayList<>();
        try {
            String selectQuery = "SELECT s.*, CASE WHEN cs.song_id IS NOT NULL THEN 'true' ELSE 'false' END AS in_favorites " +
                "FROM Song s " +
                "LEFT JOIN CollectionSong cs " +
                "ON s.song_id = cs.song_id " +
                "LEFT JOIN Collection c " +
                "ON cs.collection_id = c.collection_id AND c.title = \'Favorites\' " +
                "WHERE cs.collection_id = ?";
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

    public ArrayList<SongModel> getAllSongsFromCollection(String collectionTitle) {
        ArrayList<SongModel> songs = new ArrayList<>();
        try (Connection connection = getSqlConnection()) {
            String selectQuery = "SELECT collection_id FROM Collection WHERE Collection.title = ?";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setString(1, collectionTitle);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("collection_id");

                CollectionModel collectionModel = new CollectionModel();
                collectionModel.setCollectionId(id);
                songs = getAllSongsFromCollection(connection, collectionModel);
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
        song.setLyrics(resultSet.getString("lyrics"));
        song.setDuration(resultSet.getInt("duration"));
        song.setSongPath(resultSet.getString("song_path"));
        song.setImgPath(resultSet.getString("img_path"));
        song.setInFavorites(resultSet.getBoolean("in_favorites"));
        return song;
    }

}
