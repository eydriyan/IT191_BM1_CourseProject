package com.it191.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.it191.model.CollectionModel;
import com.it191.model.SongModel;

public class CollectionRepository extends RepositoryBase {
public void createNewCollection(CollectionModel collectionModel) {
        try (Connection connection = getSqlConnection()) {
            String insertQuery = "INSERT INTO Collection (title) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(2, collectionModel.getTitle());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CollectionModel> getAllCollections() {
        ArrayList<CollectionModel> collections = new ArrayList<>();
        try (Connection connection = getSqlConnection()) {
            String selectQuery = "SELECT * FROM Collection";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CollectionModel collection = new CollectionModel();
                collection.setCollectionId(resultSet.getInt("collection_id"));
                collection.setTitle(resultSet.getString("title"));
                collections.add(collection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collections;
    }

    public void updateCollectionInfo(CollectionModel collectionModel) {
        try (Connection connection = getSqlConnection()) {
            String updateQuery = "UPDATE Collection SET title = ? WHERE collection_id = ?";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, collectionModel.getTitle());
            statement.setInt(2, collectionModel.getCollectionId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCollectionInfo(CollectionModel collectionModel) {
        try (Connection connection = getSqlConnection()) {
            String deleteQuery = "DELETE FROM Collection WHERE collection_id = ?";
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, collectionModel.getCollectionId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSongToCollection(SongModel songModel, CollectionModel collectionModel) {
        try (Connection connection = getSqlConnection()) {
            String insertQuery = "INSERT INTO CollectionSong (collection_id, song_id) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setInt(1, collectionModel.getCollectionId());
            statement.setInt(2, songModel.getSongId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeSongFromCollection(SongModel songModel, CollectionModel collectionModel) {
        try (Connection connection = getSqlConnection()) {
            String deleteQuery = "DELETE FROM CollectionSong WHERE collection_id = ? AND song_id = ?";
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, collectionModel.getCollectionId());
            statement.setInt(2, songModel.getSongId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
