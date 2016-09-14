-- Create tables for CIS560 SQL8

-- Note that MySQL automatically creates indices on primary key columns.
-- The existence of the index affects a few of the queries in the test query set.

CREATE TABLE  IF NOT EXISTS Patient (
	   pid int PRIMARY KEY,
	   fname varchar(30),
	   lname varchar(20),
	   age int,
	   street varchar(20), 
	   city varchar(10), 
	   zipcode varchar(5)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS Disease(
       pid int REFERENCES Patient(pid) ON DELETE CASCADE, 
       disease varchar(20),
       PRIMARY KEY(pid,disease)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS Doctor(
       did int PRIMARY KEY, 
       fname varchar(30), 
       lname varchar(20), 
       specialty varchar(20)
)ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS Sees(
       pid int REFERENCES Patient(pid) ON DELETE CASCADE,
       did int REFERENCES Doctor(did) ON DELETE NO ACTION,
       PRIMARY KEY(pid,did)
)ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS Product(
       eid int PRIMARY KEY, 
       description varchar(20)
)ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS Stock(
       eid int PRIMARY KEY REFERENCES Product(eid) ON DELETE CASCADE, 
       quantity int
)ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS Supplier(
       sid int PRIMARY KEY, 
       name varchar(20), 
       street varchar(20), 
       city varchar(10), 
       zipcode varchar(5)
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Supplies(
       eid int REFERENCES Product(eid) ON DELETE CASCADE,
       sid int REFERENCES Supplier(sid) ON DELETE SET NULL,
       UNIQUE (eid,sid)
)ENGINE = InnoDB;
