drop trigger if exists updateBookNumber;
DELIMITER $
CREATE TRIGGER updateBookNumber
BEFORE delete ON cart
FOR EACH ROW
BEGIN
if exists(select books.booknumber, cart.number from books, cart where books.ISBN = old.ISBN and books.booknumber > old.number) then
update books set books.booknumber = books.booknumber - old.number where books.ISBN = old.ISBN;
end if;
if exists(select books.booknumber, cart.number from books, cart where books.ISBN = old.ISBN and books.booknumber = old.number) then
update books set books.booknumber = 0 where books.ISBN = old.ISBN;
end if;
END
$
DELIMITER ;