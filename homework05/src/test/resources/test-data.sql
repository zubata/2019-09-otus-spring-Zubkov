INSERT INTO AUTHORS (ID, `AUTHORNAME`) VALUES (1,'Толстой');
INSERT INTO AUTHORS (ID, `AUTHORNAME`) VALUES (2,'Есенин');

insert into books (id,`bookname`,`authorname`) values (1, 'Война и Мир','Толстой');
insert into books (id,`bookname`,`authorname`) values (2,'Не стихов златая пена','Есенин');

insert into genres (id,`genrename`,`bookname`) values (1, 'Роман','Война и Мир');
insert into genres (id,`genrename`,`bookname`) values (2, 'Стихи','Не стихов златая пена');
