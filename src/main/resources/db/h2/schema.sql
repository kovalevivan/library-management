DROP TABLE IF EXISTS author ;
DROP TABLE IF EXISTS book ;
DROP TABLE IF EXISTS genre ;
DROP TABLE IF EXISTS jnd_book_author ;
DROP TABLE IF EXISTS jnd_book_genre ;
DROP TABLE IF EXISTS user ;
DROP SEQUENCE IF EXISTS hibernate_sequence;

CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE author (
  id        BIGINT NOT NULL,
  full_name VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE book (
  id        BIGINT NOT NULL,
  added     DATE,
  pages     INTEGER,
  publisher VARCHAR(255),
  title     VARCHAR(255) NOT NULL ,
  year      INTEGER,
  PRIMARY KEY (id)
);

CREATE TABLE genre (
  id   BIGINT NOT NULL ,
  name VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE jnd_book_author (
  book_fk   BIGINT NOT NULL,
  author_fk BIGINT NOT NULL,
  FOREIGN KEY (book_fk) REFERENCES book(id),
  FOREIGN KEY (author_fk) REFERENCES author(id)

);

create table jnd_book_genre (
  book_fk  BIGINT NOT NULL,
  genre_fk BIGINT NOT NULL,
  FOREIGN KEY (book_fk) REFERENCES book(id),
  FOREIGN KEY (genre_fk) REFERENCES genre(id)
);

CREATE TABLE user (
  id       BIGINT NOT NULL,
  name     VARCHAR(255),
  password VARCHAR(255),
  role     VARCHAR(255),
  PRIMARY KEY (id)
);
