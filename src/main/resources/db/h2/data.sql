INSERT INTO PUBLIC."user"("id","name","password","role") VALUES (0,'admin','$2a$10$8AAKm6KE0URtcgclZvsX4.HtXZQgn6fZtnXF4djF2zR75B9SYdpym', 'ADMIN');

INSERT INTO PUBLIC."author"("id","full_name") VALUES
                                                     (-16, 'Steve McConnell'),
                                                     (-15, 'Robert C. Martin'),
                                                     (-14, 'Erich Gamma'),
                                                     (-13, 'Richard Helm'),
                                                     (-12, 'Ralph Johnson'),
                                                     (-11, 'John Vlissides'),
                                                     (-10, 'Grady Booch'),
                                                     (-9, 'Craig Walls'),
                                                     (-8, 'Antonio Goncalves'),
                                                     (-7, 'Joshua Bloch'),
                                                     (-6, 'Cay S. Horstmann'),
                                                     (-5, 'Gary Cornell'),
                                                     (-4, 'Herbert Schildt'),
                                                     (-3, 'Bruce Eckel'),
                                                     (-2, 'Robert Lafore'),
                                                     (-1, 'Madhusudhan Konda');


INSERT INTO PUBLIC."book"("id","added","pages","publisher","title","year") VALUES
                                               (-16,TIMESTAMP '2019-01-02',960,'Microsoft Press','Code Complete', 2004),
                                               (-15,TIMESTAMP '2019-01-03',464,'Prentice Hall','Clean Code', 2008),
                                               (-14,TIMESTAMP '2019-01-04',395, 'Addison-Wesley Professional','Design Patterns',1994),
                                               (-13,TIMESTAMP '2019-01-05',520,'Manning Publications', 'Spring in Action 5th Edition',2018),
                                               (-12,TIMESTAMP '2019-01-06',608,'Apress', 'Beginning Java EE 7',2013),
                                               (-11,TIMESTAMP '2019-01-07',412,'Addison-Wesley Professional', 'Effective Java 3rd Edition',2018),
                                               (-10,TIMESTAMP '2019-01-08',624,'Manning Publications','Spring In Action 4th Edition', 2014),
                                               (-9,TIMESTAMP '2019-01-09',928, 'Prentice Hall','Core Java Volume I--Fundamentals 11th Edition',2018),
                                               (-8,TIMESTAMP '2018-12-23',1040,'Prentice Hall', 'Core Java Volume I--Fundamentals 10th Edition',2016),
                                               (-7,TIMESTAMP '2018-12-24',1008,'Prentice Hall', 'Core Java Volume I--Fundamentals 9th Edition',2012),
                                               (-6,TIMESTAMP '2018-12-25',864,'Prentice Hall', 'Core Java, Volume I--Fundamentals 8th Edition',2007),
                                               (-5,TIMESTAMP '2018-12-26',784,'Prentice Hall', 'Core Java 2, Volume I--Fundamentals 7th Edition',2004),
                                               (-4,TIMESTAMP '2018-12-27',1312,'McGraw-Hill Education', 'Java: The Complete Reference, 9th Edition',2014),
                                               (-3,TIMESTAMP '2018-12-28',1150,'Prentice Hall', 'Thinking in Java (4th Edition)',2006),
                                               (-2,TIMESTAMP '2018-12-29',800,'Sams Publishing', 'Data Structures and Algorithms in Java (2nd Edition)',2002),
                                               (-1,TIMESTAMP '2018-12-30',96,'O Reilly Media', 'Just Spring: A Lightweight Introduction to the Spring Framework',2011);

INSERT INTO PUBLIC."jnd_author_book"("book_fk","author_fk") VALUES
                                                                   (-16,-16),
                                                                   (-15,-15),
                                                                   (-14,-14),
                                                                   (-13,-14),
                                                                   (-12,-14),
                                                                   (-11,-14),
                                                                   (-10,-14),
                                                                   (-9,-13),
                                                                   (-9,-10),
                                                                   (-8,-12),
                                                                   (-7,-11),
                                                                   (-6,-9),
                                                                   (-6, -8),
                                                                   (-6,-7),
                                                                   (-6,-6),
                                                                   (-5,-6),
                                                                   (-6,-5),
                                                                   (-5,-5),
                                                                   (-4,-4),
                                                                   (-3,-3),
                                                                   (-2,-2),
                                                                   (-1,-1);

INSERT INTO PUBLIC."book_genre"("id","name") VALUES
(-16, 'Computer programming'),
(-15, 'Java'),
(-14, 'Spring Framework');

INSERT INTO PUBLIC."jnd_genre_book"("book_fk","genre_fk") VALUES
(-16,-16),
(-15,-16),
(-14,-16),
(-13,-14),
(-12,-15),
(-11,-15),
(-10,-14),
(-9,-15),
(-8,-15),
(-7,-15),
(-6,-15),
(-5,-15),
(-4,-15),
(-3,-15),
(-2,-15),
(-1,-14),
(-2,-16);




