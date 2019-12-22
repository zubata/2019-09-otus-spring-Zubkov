insert into persons (`username`,`password`,`role`) values ('admin','admin','ADMIN');
insert into persons (`username`,`password`,`role`) values ('user1','user','USERADULT');
insert into persons (`username`,`password`,`role`) values ('user2','user','USERNOADULT');

insert into authors (`author_name`) values ('Толстой');
insert into authors (`author_name`) values ('Есенин');
insert into authors (`author_name`) values ('Оруэл');
insert into authors (`author_name`) values ('Бредбери');

insert into genres (`genre_name`) values ('Роман');
insert into genres (`genre_name`) values ('Стихи');
insert into genres (`genre_name`) values ('Утопия');

insert into books (`book_name`,`author_id`) values ('Война и Мир', 1);
insert into books (`book_name`,`author_id`) values ('Не стихов златая пена',2);
insert into books (`book_name`,`author_id`) values ('1984',3);
insert into books (`book_name`,`author_id`) values ('451 по Фаренгейту',4);

insert into books_genre (`book_id`,`genre_id`) values (1, 1);
insert into books_genre (`book_id`,`genre_id`) values (2, 2);
insert into books_genre (`book_id`,`genre_id`) values (3, 3);
insert into books_genre (`book_id`,`genre_id`) values (3, 1);
insert into books_genre (`book_id`,`genre_id`) values (4, 3);

insert into comments (`book_id`,`comment`) values (1,'Good');
insert into comments (`book_id`,`comment`) values (2,'Well');
insert into comments (`book_id`,`comment`) values (3,'Not Bad');
insert into comments (`book_id`,`comment`) values (4,'Amazing');

INSERT INTO acl_sid (id, principal, sid) VALUES
(1, 1, 'admin'),
(2, 0, 'ROLE_USERADULT'),
(3, 0, 'ROLE_USERNOADULT');

INSERT INTO acl_class (id, class) VALUES
(1, 'ru.otus.spring.homework15.domain.Book');

INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
(1, 1, 1, NULL, 3, 0),
(2, 1, 2, NULL, 3, 0),
(3, 1, 3, NULL, 3, 0),
(4, 1, 4, NULL, 3, 0);

INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES
(1, 1, 1, 1, 1, 1, 1, 1),
(2, 1, 2, 1, 2, 1, 1, 1),
(3, 1, 3, 1, 8, 1, 1, 1),
(4, 1, 4, 2, 1, 1, 1, 1),
(5, 1, 5, 3, 1, 1, 1, 1),
(6, 2, 1, 1, 1, 1, 1, 1),
(7, 2, 2, 1, 2, 1, 1, 1),
(8, 2, 3, 1, 8, 1, 1, 1),
(9, 2, 4, 2, 1, 1, 1, 1),
(10, 2, 5, 3, 1, 1, 1, 1),
(11, 3, 1, 1, 1, 1, 1, 1),
(12, 3, 2, 1, 2, 1, 1, 1),
(13, 3, 3, 1, 8, 1, 1, 1),
(14, 3, 4, 2, 1, 1, 1, 1),
(15, 3, 5, 3, 1, 1, 1, 1),
(16, 4, 1, 1, 1, 1, 1, 1),
(17, 4, 2, 1, 2, 1, 1, 1),
(18, 4, 3, 1, 8, 1, 1, 1),
(19, 4, 4, 2, 1, 1, 1, 1);