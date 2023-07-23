-- Select Database
USE sql6633785;

CREATE DATABASE it191_course_project;
USE it191_course_project;

-- Safe Update
SET SQL_SAFE_UPDATES = 0;

-- Clear Tables
DELETE FROM AlbumSong;
DELETE FROM Album;
DELETE FROM PlaylistSong;
DELETE FROM Playlist;
DELETE FROM Song;
DELETE FROM Genre;
DELETE FROM Artist;
DELETE FROM User;

-- Drop Tables
DROP TABLE AlbumSong;
DROP TABLE Album;
DROP TABLE PlaylistSong;
DROP TABLE Playlist;
DROP TABLE Song;
DROP TABLE Genre;
DROP TABLE Artist;
DROP TABLE User;


-- Create Tables
CREATE TABLE User (
	user_id INT PRIMARY KEY auto_increment,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    dob DATE NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE Artist (
	artist_id INT PRIMARY KEY auto_increment,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
);

CREATE TABLE Genre (
	genre_id INT PRIMARY KEY auto_increment,
    name VARCHAR(100)
);

CREATE TABLE Song (
	song_id INT PRIMARY KEY auto_increment,
    artist_id INT,
    title VARCHAR(100),
    genre_id INT,
    lyrics TEXT,
    duration INT,
    song_path VARCHAR(100),
    img_path VARCHAR(100),
    FOREIGN KEY (artist_id) REFERENCES Artist(artist_id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES Genre(genre_id) ON DELETE CASCADE
);

CREATE TABLE Playlist (
	playlist_id INT PRIMARY KEY auto_increment,
    user_id INT,
    title VARCHAR(100) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
);

CREATE TABLE PlaylistSong (
	playlist_id INT,
    song_id INT,
    FOREIGN KEY(playlist_id) REFERENCES Playlist(playlist_id) ON DELETE CASCADE,
    FOREIGN KEY(song_id) REFERENCES Song(song_id) ON DELETE CASCADE
);

CREATE TABLE Album (
	album_id INT PRIMARY KEY auto_increment,
    artist_id INT,
    title VARCHAR(100),
    FOREIGN KEY (artist_id) REFERENCES Artist(artist_id) ON DELETE CASCADE
);

CREATE TABLE AlbumSong (
	album_id INT,
    song_id INT,
    FOREIGN KEY (album_id) REFERENCES Album(album_id) ON DELETE CASCADE,
    FOREIGN KEY (song_id) REFERENCES Song(song_id) ON DELETE CASCADE
);


-- Create Data

