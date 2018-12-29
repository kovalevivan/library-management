INSERT INTO PUBLIC."author"("id","full_name") VALUES
                                                     (-10, 'Lev Tolstoy'),
                                                     (-9, 'Alexander Pushkin'),
                                                     (-8, 'Alexander Griboedov'),
                                                     (-7, 'Author'),
                                                     (-6, 'author'),
                                                     (-5, 'author'),
                                                     (-4, 'author'),
                                                     (-3, 'author'),
                                                     (-2, 'author'),
                                                     (-1, 'author'),
                                                     (0, 'author');

INSERT INTO PUBLIC."book_genre"("id","name") VALUES
                                                    (1,'drama'),
                                                    (2,'poema'),
                                                    (3,'comedy');

INSERT INTO PUBLIC."book"("id","added","pages","publisher","title","year") VALUES
                                               (-10,TIMESTAMP '2018-11-24',1006,'Moscow publish','Voina i mir', 1890),
                                               (-9,TIMESTAMP '2018-11-24',300, 'Moscow publish','Evgeniy Onegin',1840),
                                               (-8,TIMESTAMP '2018-11-24',100,'Moscow publish', 'Gore ot uma',1830),
                                               (-7,TIMESTAMP '2018-11-30',100,'Publisher', 'Book Title',2018),
                                               (-6,TIMESTAMP '2018-11-30',100,'Publisher', 'Book Title',2018),
                                               (-5,TIMESTAMP '2018-11-30',100,'Publisher', 'Book Title',2018),
                                               (-4,TIMESTAMP '2018-11-30',100,'Publisher', 'Book Title',2018),
                                               (-3,TIMESTAMP '2018-11-30',100,'Publisher', 'Book Title',2018),
                                               (-2,TIMESTAMP '2018-11-30',100,'Publisher', 'Book Title',2018),
                                               (-1,TIMESTAMP '2018-11-30',100,'Publisher', 'Book Title',2018);

INSERT INTO PUBLIC."jnd_author_book"("author_fk","book_fk") VALUES
                                                                   (-10,-10),
                                                                   (-9,-9),
                                                                   (-8,-8),
                                                                   (-7,-7),
                                                                   (-6,-6),
                                                                   (-5,-5),
                                                                   (-4,-4),
                                                                   (-3,-3),
                                                                   (-2,-2),
                                                                   (-1,-1);

INSERT INTO PUBLIC."jnd_genre_book"("book_fk","genre_fk") VALUES
                                                                  (1,-8),
                                                                  (2,-9),
                                                                  (3,-10);




