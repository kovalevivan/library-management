INSERT INTO PUBLIC."user"("id","name","password","role") VALUES (0,'admin','$2a$10$8AAKm6KE0URtcgclZvsX4.HtXZQgn6fZtnXF4djF2zR75B9SYdpym', 'ADMIN');

INSERT INTO PUBLIC."author"("id","full_name") VALUES
                                                     (-16, 'Author16'),
                                                     (-15, 'Author15'),
                                                     (-14, 'Author14'),
                                                     (-13, 'Author13'),
                                                     (-12, 'Author12'),
                                                     (-11, 'Author11'),
                                                     (-10, 'Author10'),
                                                     (-9, 'Author9'),
                                                     (-8, 'Author8'),
                                                     (-7, 'Author7'),
                                                     (-6, 'Author6'),
                                                     (-5, 'Author5'),
                                                     (-4, 'Author4'),
                                                     (-3, 'Author3'),
                                                     (-2, 'Author2'),
                                                     (-1, 'Author1');


INSERT INTO PUBLIC."book"("id","added","pages","publisher","title","year") VALUES
                                               (-16,TIMESTAMP '2018-12-15',1600,'Publisher16','Book16', 2003),
                                               (-15,TIMESTAMP '2018-12-16',1500,'Publisher15','Book15', 2004),
                                               (-14,TIMESTAMP '2018-12-17',1400, 'Publisher14','Book14',2005),
                                               (-13,TIMESTAMP '2018-12-18',1300,'Publisher13', 'Book13',2006),
                                               (-12,TIMESTAMP '2018-12-19',1200,'Publisher12', 'Book12',2007),
                                               (-11,TIMESTAMP '2018-12-20',1100,'Publisher11', 'Book11',2008),
                                               (-10,TIMESTAMP '2018-12-21',1000,'Publisher10','Book10', 2009),
                                               (-9,TIMESTAMP '2018-12-22',900, 'Publisher9','Book9',2010),
                                               (-8,TIMESTAMP '2018-12-23',800,'Publisher8', 'Book8',2011),
                                               (-7,TIMESTAMP '2018-12-24',700,'Publisher7', 'Book7',2012),
                                               (-6,TIMESTAMP '2018-12-25',600,'Publisher6', 'Book6',2013),
                                               (-5,TIMESTAMP '2018-12-26',500,'Publisher5', 'Book5',2014),
                                               (-4,TIMESTAMP '2018-12-27',400,'Publisher4', 'Book4',2015),
                                               (-3,TIMESTAMP '2018-12-28',300,'Publisher3', 'Book3',2016),
                                               (-2,TIMESTAMP '2018-12-29',200,'Publisher2', 'Book2',2017),
                                               (-1,TIMESTAMP '2018-12-30',100,'Publisher1', 'Book1',2018);

INSERT INTO PUBLIC."jnd_author_book"("author_fk","book_fk") VALUES
                                                                   (-16,-16),
                                                                   (-15,-15),
                                                                   (-14,-14),
                                                                   (-13,-13),
                                                                   (-12,-12),
                                                                   (-11,-11),
                                                                   (-10,-10),
                                                                   (-9,-9),
                                                                   (-8,-8),
                                                                   (-7,-7),
                                                                   (-6,-6),
                                                                   (-5,-5),
                                                                   (-4,-4),
                                                                   (-3,-3),
                                                                   (-2,-2),
                                                                   (-1,-1),
                                                                   (-16, -3),
                                                                   (-5,-6),
                                                                   (-3,-1),
                                                                   (-3,-15),
                                                                   (-14, -8),
                                                                   (-13,-8),
                                                                   (-4,-5);

INSERT INTO PUBLIC."book_genre"("id","name") VALUES
(-16, 'Genre16'),
(-15, 'Genre15'),
(-14, 'Genre14'),
(-13, 'Genre13'),
(-12, 'Genre12'),
(-11, 'Genre11'),
(-10,'Genre10'),
(-9,'Genre9'),
(-8,'Genre8'),
(-7, 'Genre7'),
(-6, 'Genre6'),
(-5, 'Genre5'),
(-4, 'Genre4'),
(-3, 'Genre3'),
(-2, 'Genre2'),
(-1, 'Genre1');

INSERT INTO PUBLIC."jnd_genre_book"("book_fk","genre_fk") VALUES
(-16,-16),
(-15,-15),
(-14,-14),
(-13,-13),
(-12,-12),
(-11,-11),
(-10,-10),
(-9,-9),
(-8,-8),
(-7,-7),
(-6,-6),
(-5,-5),
(-4,-4),
(-3,-3),
(-2,-2),
(-1,-1),
(-4,-1),
(-10, -16),
(-15, -4),
(-3,-2),
(-8,-9);




