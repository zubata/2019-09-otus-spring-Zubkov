insert into persons (`username`,`password`,`role`) values ('admin','admin','ADMIN');
insert into persons (`username`,`password`,`role`) values ('user','user','USER');

insert into authors (`author_name`) values ('Толстой');
insert into authors (`author_name`) values ('Есенин');

insert into genres (`genre_name`) values ('Роман');
insert into genres (`genre_name`) values ('Стихи');

insert into books (`book_name`,`author_id`) values ('Война и Мир', 1);
insert into books (`book_name`,`author_id`) values ('Не стихов златая пена',2);

insert into books_genre (`book_id`,`genre_id`) values (1, 1);
insert into books_genre (`book_id`,`genre_id`) values (2, 2);

insert into comments (`book_id`,`comment`) values (1,'Good');
insert into comments (`book_id`,`comment`) values (2,'Well');
