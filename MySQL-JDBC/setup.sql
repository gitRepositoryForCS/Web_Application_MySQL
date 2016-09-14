create table plan(
pid int primary key,
name varchar(100) not null,
maxNo int not null,
fee int)
engine = InnoDB;

create table customer(
cid int primary key,
login varchar(100) not null,
password varchar(20) not null,
first_name varchar(100),
last_name varchar(100),
pid int not null)
engine = InnoDB;

create table rental(
cid int not null,
movie_id varchar(30) not null,
status varchar(30) not null,
time int not null,
primary key (cid,movie_id,status,time)
)

insert into customer values (1, 'first_person', 123, 'firstPersonFirstName', 'firstPersonLastName', 1);
insert into customer values (2, 'second_person', 234, 'secondPersonFirstName', 'secondPersonLastName', 2);
insert into customer values (3, 'third_person', 345, 'thirdPersonFirstName', 'thirdPersonLastName', 2);
insert into customer
values(4, 'fourth_person', 456, 'fourthPersonFirstName', 'fourthPersonLastName', '4');
insert into customer
values(5, 'fifth_person', 567, 'fifthPersonFirstName', 'fifthPersonLastName', '1');

insert into plan
values(1, 'basic', 1, 5);
insert into plan
values(2, 'rentalplus', 5, 10);
insert into plan
values(3, 'rentalplusplus', 10, 15);
insert into plan
values(4, 'supperaccess', 15, 20);

insert into rental
values(1, 'M_1008', 'open', 1);
insert into rental
values(2, 'M_1004', 'open', 1);
insert into rental
values(3, 'M_1001', 'open', 1);
insert into rental
values(2, 'M_1003', 'open', 2);
insert into rental
values(2, 'M_1012', 'open', 3);