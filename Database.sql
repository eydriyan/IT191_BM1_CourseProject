-- Select Database

-- Clear Tables
TRUNCATE TABLE AlbumSong;
TRUNCATE TABLE Album;
TRUNCATE TABLE PlaylistSong;
TRUNCATE TABLE Playlist;
TRUNCATE TABLE Song;
TRUNCATE TABLE Genre;
TRUNCATE TABLE Artist;
TRUNCATE TABLE User;

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
    FOREIGN KEY (user_id) REFERENCES User(user_id)
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
    FOREIGN KEY (artist_id) REFERENCES Artist(artist_id),
    FOREIGN KEY (genre_id) REFERENCES Genre(genre_id)
);

CREATE TABLE Playlist (
	playlist_id INT PRIMARY KEY auto_increment,
    user_id INT,
    title VARCHAR(100) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE PlaylistSong (
	playlist_id INT,
    song_id INT,
    FOREIGN KEY(playlist_id) REFERENCES Playlist(playlist_id),
    FOREIGN KEY(song_id) REFERENCES Song(song_id)
);

CREATE TABLE Album (
	album_id INT PRIMARY KEY auto_increment,
    artist_id INT,
    title VARCHAR(100),
    FOREIGN KEY (artist_id) REFERENCES Artist(artist_id)
);

CREATE TABLE AlbumSong (
	album_id INT,
    song_id INT,
    FOREIGN KEY (album_id) REFERENCES Album(album_id),
    FOREIGN KEY (song_id) REFERENCES Song(song_id)
);


-- Create Data
