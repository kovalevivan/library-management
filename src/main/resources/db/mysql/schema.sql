CREATE DATABASE IF NOT EXISTS library_management;

ALTER DATABASE library_management
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;

USE library_management;

CREATE TABLE IF NOT EXISTS author (
  id        BIGINT NOT NULL,
  full_name VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS book (
  id        BIGINT NOT NULL,
  added     DATE,
  pages     INTEGER,
  publisher VARCHAR(255),
  title     VARCHAR(255) NOT NULL ,
  year      INTEGER,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS genre (
  id   BIGINT NOT NULL,
  name VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS jnd_book_author (
  book_fk BIGINT NOT NULL,
  author_fk   BIGINT NOT NULL,
  FOREIGN KEY (book_fk) REFERENCES book(id),
  FOREIGN KEY (author_fk) REFERENCES author(id)
);

CREATE TABLE IF NOT EXISTS jnd_book_genre (
  book_fk  BIGINT NOT NULL,
  genre_fk BIGINT NOT NULL,
  FOREIGN KEY (book_fk) REFERENCES book(id),
  FOREIGN KEY (genre_fk) REFERENCES genre(id)
);

CREATE TABLE IF NOT EXISTS user (
  id       BIGINT NOT NULL,
  name     VARCHAR(255),
  password VARCHAR(255),
  role     VARCHAR(255),
  PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS hibernate_sequence (
  next_val BIGINT
);
INSERT INTO hibernate_sequence VALUES (100);
INSERT INTO hibernate_sequence VALUES (100);
INSERT INTO hibernate_sequence VALUES (100);
INSERT INTO hibernate_sequence VALUES (100);