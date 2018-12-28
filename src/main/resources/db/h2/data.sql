INSERT INTO PUBLIC."author"("id","full_name") VALUES
                                                     (1, 'Lev Tolstoy'),
                                                     (2, 'Alexander Pushkin'),
                                                     (3, 'Alexander Griboedov');

INSERT INTO PUBLIC."book_genre"("id","name") VALUES
                                                    (1,'drama'),
                                                    (2,'poema'),
                                                    (3,'comedy');

INSERT INTO PUBLIC."book"("id","added","pages","publisher","title","year") VALUES
                                               (-2,TIMESTAMP '2018-11-24',1006,'Moscow publish','Voina i mir', 1890),
                                               (-1,TIMESTAMP '2018-11-24',300, 'Moscow publish','Evgeniy Onegin',1840),
                                               (0,TIMESTAMP '2018-11-24',100,'Moscow publish', 'Gore ot uma',1830);

INSERT INTO PUBLIC."jnd_author_book"("author_fk","book_fk") VALUES
                                                                   (1,0),
                                                                   (2,-1),
                                                                   (3,-2);

INSERT INTO PUBLIC."jnd_genre_book"("book_fk","genre_fk") VALUES
                                                                  (1,0),
                                                                  (2,-1),
                                                                  (3,-2);




