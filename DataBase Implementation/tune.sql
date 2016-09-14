
//this index makes searching with book name faster.
create index bookName on books(bookname);

//this index makes searching with book’s category faster.
create index topic on books(topic);

//this index makes searching with price faster.
create index price on books(price);

//this index makes searching with both book name and book’s category faster.
create index searchBookTopic on books(bookname, topic);

//this index makes searching with book name, book’s category and book’s price faster.
create index searchBookTopicPrice on books(bookname, topic, price);




