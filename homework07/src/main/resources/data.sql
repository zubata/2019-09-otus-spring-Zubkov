insert into authors (`author_name`) values ('Толстой');
insert into authors (`author_name`) values ('Есенин');

insert into genres (`genre_name`) values ('Роман');
insert into genres (`genre_name`) values ('Стихи');

insert into books (`book_name`,`author_id`,`genre_id`) values ('Война и Мир', 1,1);
insert into books (`book_name`,`author_id`,`genre_id`) values ('Не стихов златая пена',2,2);

insert into comments (`book`,`comment`) values (1,'Good');
insert into comments (`book`,`comment`) values (2,'Well');
