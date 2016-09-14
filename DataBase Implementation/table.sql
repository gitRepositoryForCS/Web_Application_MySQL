DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS publisher;
DROP TABLE IF EXISTS categories;

CREATE TABLE IF NOT EXISTS categories(
topic varchar(100) NOT NULL,
primary key(topic)
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS publisher(
publishername varchar(100) NOT NULL,
address varchar(100) NOT NULL,
city varchar(100) NOT NULL,
primary key(publishername)
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS users(
uid varchar(100) NOT NULL,
password varchar(30) NOT NULL,
fullname varchar(100) NOT NULL,
email varchar(50) NOT NULL,
primary key(uid)
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS books(
ISBN varchar(50) NOT NULL, 
bookname varchar(100) NOT NULL, 
authors varchar(100) NOT NULL, 
topic varchar(100) NOT NULL, 
publishername varchar(100) NOT NULL,
image varchar(100) NOT NULL,
booknumber int NOT NULL,
price double NOT NULL,
primary key (ISBN),
foreign key(topic) references categories(topic),
foreign key(publishername) references publisher(publishername)
)ENGINE = InnoDB;

create table IF NOT EXISTS cart(
uid varchar(100) NOT NULL,
ISBN varchar(50) NOT NULL,
number int NOT NULL,
primary key(uid, ISBN),
foreign key(uid) references users(uid),
foreign key(ISBN) references books(ISBN)
)ENGINE = InnoDB;