-- Select Database
DROP DATABASE it191_course_project;
CREATE DATABASE it191_course_project;
USE it191_course_project;

-- Safe Update
SET SQL_SAFE_UPDATES = 0;

-- Clear Tables
DELETE FROM CollectionSong;
DELETE FROM Colllection;
DELETE FROM Song;
DELETE FROM Genre;

-- Drop Tables
DROP TABLE CollectionSong;
DROP TABLE Collection;
DROP TABLE Song;
DROP TABLE Genre;

SELECT * FROM CollectionSong;
SELECT * FROM Collection;
SELECT * FROM Song;
SELECT * FROM Genre;


-- Create Tables

CREATE TABLE Genre (
	genre_id INT PRIMARY KEY auto_increment,
    name VARCHAR(100)
);

CREATE TABLE Song (
	song_id INT PRIMARY KEY auto_increment,
    artist VARCHAR(100),
    title VARCHAR(100),
    genre_id INT,
    lyrics TEXT,
    duration INT,
    song_path VARCHAR(100),
    img_path VARCHAR(100),
    FOREIGN KEY (genre_id) REFERENCES Genre(genre_id) ON DELETE CASCADE
);

CREATE TABLE Collection (
	collection_id INT PRIMARY KEY auto_increment,
    title VARCHAR(100) NOT NULL
);

CREATE TABLE CollectionSong (
	collection_id INT,
    song_id INT,
    FOREIGN KEY(collection_id) REFERENCES Collection(collection_id) ON DELETE CASCADE,
    FOREIGN KEY(song_id) REFERENCES Song(song_id) ON DELETE CASCADE
);

SHOW TABLES;