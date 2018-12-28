INSERT INTO author VALUES (1, 'Lev Tolstoy');
INSERT INTO author VALUES (2, 'Alexander Pushkin');
INSERT INTO author VALUES (3, 'Alexander Griboedov');

INSERT INTO book_genre VALUES(1,'drama');
INSERT INTO book_genre VALUES(2,'poema');
INSERT INTO book_genre VALUES(3,'comedy');

INSERT INTO book VALUES(-2,'2018-11-24',1006,'Moscow publish','Voina i mir', 1890);
INSERT INTO book VALUES(-1,'2018-11-24',300, 'Moscow publish','Evgeniy Onegin',1840);
INSERT INTO book VALUES(0,'2018-11-24',100,'Moscow publish', 'Gore ot uma',1830);

INSERT INTO jnd_author_book VALUES(1,0);
INSERT INTO jnd_author_book VALUES(2,-1);
INSERT INTO jnd_author_book VALUES(3,-2);

INSERT INTO jnd_genre_book VALUES (1,0);
INSERT INTO jnd_genre_book VALUES (2,-1);
INSERT INTO jnd_genre_book VALUES (3,-2);