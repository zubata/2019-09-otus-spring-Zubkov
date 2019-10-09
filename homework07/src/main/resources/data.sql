insert into authors (`authorname`) values ('Толстой');
insert into authors (`authorname`) values ('Есенин');

insert into genres (`genrename`) values ('Роман');
insert into genres (`genrename`) values ('Стихи');

insert into books (`bookname`,`author_id`,`genre_id`) values ('Война и Мир', 1,1);
insert into books (`bookname`,`author_id`,`genre_id`) values ('Не стихов златая пена',2,2);

insert into comments (`book`,`comment`) values (1,'Good');
insert into comments (`book`,`comment`) values (2,'Well');
