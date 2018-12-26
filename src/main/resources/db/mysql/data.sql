INSERT INTO author VALUES (1, 'Lev Tolstoy');
INSERT INTO author VALUES (2, 'Alexander Pushkin');
INSERT INTO author VALUES (3, 'Alexander Griboedov');

INSERT INTO book_genre VALUES(1,'drama');
INSERT INTO book_genre VALUES(2,'poema');
INSERT INTO book_genre VALUES(3,'comedy');

INSERT INTO book VALUES(1,'Voina i mir','Moscow publish',1890,1006, TIMESTAMP '2018-12-24');
INSERT INTO book VALUES(2, 'Evgeniy Onegin','Moscow publish',1840, 300, TIMESTAMP '2018-12-24');
INSERT INTO book VALUES(3, 'Gore ot uma','Moscow publish',1850,100, TIMESTAMP '2018-12-24');

INSERT INTO jnd_author_book VALUES(1,1);
INSERT INTO jnd_author_book VALUES(2,2);
INSERT INTO jnd_author_book VALUES(3,3);

INSERT INTO jnd_genre_book VALUES (1,1);
INSERT INTO jnd_genre_book VALUES (2,2);
INSERT INTO jnd_genre_book VALUES (3,3);