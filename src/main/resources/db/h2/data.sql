INSERT INTO PUBLIC."author"("id","full_name") VALUES
                                                     (1, 'Lev Tolstoy'),
                                                     (2, 'Alexander Pushkin'),
                                                     (3, 'Alexander Griboedov');

INSERT INTO PUBLIC."book_genre"("id","name") VALUES
                                                    (1,'drama'),
                                                    (2,'poema'),
                                                    (3,'comedy');

INSERT INTO PUBLIC."book"("id","title") VALUES
                                               (1, 'Voina i mir'),
                                               (2, 'Evgeniy Onegin'),
                                               (3, 'Gore ot uma');

INSERT INTO PUBLIC."jnd_author_book"("author_fk","book_fk") VALUES
                                                                   (1,1),
                                                                   (2,2),
                                                                   (3,3);

INSERT INTO PUBLIC."jnd_genre_book"("book_fk","genre_fk") VALUES
                                                                  (1,1),
                                                                  (2,2),
                                                                  (3,3);




