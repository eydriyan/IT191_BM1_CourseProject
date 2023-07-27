package com.it191.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.it191.model.CollectionModel;
import com.it191.model.SongModel;
import com.it191.model.UserModel;

public class CollectionRepository extends RepositoryBase {
public void createNewCollection(UserModel userModel, CollectionModel collectionModel) {
        try (Connection connection = getSqlConnection()) {
            String insertQuery = "INSERT INTO Collection (user_id, title) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setInt(1, userModel.getUserId());
            statement.setString(2, collectionModel.getTitle());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CollectionModel> getAllCollections(UserModel userModel) {
        ArrayList<CollectionModel> collections = new ArrayList<>();
        try (Connection connection = getSqlConnection()) {
            String selectQuery = "SELECT * FROM Collection WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setInt(1, userModel.getUserId());
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

    public void updateCollectionInfo(UserModel userModel, CollectionModel collectionModel) {
        try (Connection connection = getSqlConnection()) {
            String updateQuery = "UPDATE Collection SET title = ? WHERE collection_id = ? AND user_id = ?";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, collectionModel.getTitle());
            statement.setInt(2, collectionModel.getCollectionId());
            statement.setInt(3, userModel.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCollectionInfo(UserModel userModel, CollectionModel collectionModel) {
        try (Connection connection = getSqlConnection()) {
            String deleteQuery = "DELETE FROM Collection WHERE collection_id = ? AND user_id = ?";
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, collectionModel.getCollectionId());
            statement.setInt(2, userModel.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSongToCollection(UserModel userModel, SongModel songModel, CollectionModel collectionModel) {
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

    public void removeSongFromCollection(UserModel userModel, SongModel songModel, CollectionModel collectionModel) {
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
