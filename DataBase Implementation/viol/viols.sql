//a. An INSERT command creating a key violation:
INSERT INTO `books`(`ISBN`, `bookname`, `authors`, `topic`, `publishername`, `image`, `booknumber`, `price`) VALUES ('978-1590282410','aaaa','a','cs','aa','aa',1,19.99)

//b. An UPDATE command creating a key violation
UPDATE `books` SET `ISBN`='978-0470181607',`bookname`='Python Programming',`authors`='Cay Horsimann',`topic`='cs',`publishername`='Wiley',`image`='book image',`booknumber`=6,`price`=50.00

//c. An INSERT command creating a referential integrity violation
UPDATE `books` SET `ISBN`='123',`bookname`='aaaa',`authors`='aa',`topic`='cs',`publishername`='newPublisher',`image`='a',`booknumber`=3,`price`=10.00

//d. A DELETE command creating a referential integrity violation
DELETE FROM `books` WHERE books.ISBN = '978-0132569033'

//e. An UPDATE command creating a referential integrity violation
UPDATE `books` SET `ISBN`='123',`bookname`='Java Concepts for AP Computer Science',`authors`='Cay S. Horstmann',`topic`='cs',`publishername`='newPublisher',`image`='book image',`booknumber`=6,`price`=99.98