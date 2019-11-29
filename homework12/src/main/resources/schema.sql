drop table authors if exists;
drop table books if exists;
drop table comments if exists;
drop table books_genre if exists;
drop table genres if exists;
drop table users if exists;
create table users (id bigint generated by default as identity, username varchar(255) not null, password varchar(255) not null, role varchar(255) not null, primary key (id));
create table authors (id bigint generated by default as identity, author_name varchar(255) not null, primary key (id));
create table books (id bigint generated by default as identity, book_name varchar(255) not null, author_id bigint not null, primary key (id));
create table comments (id bigint generated by default as identity, comment varchar(255), book_id bigint not null, primary key (id));
create table genres (id bigint generated by default as identity, genre_name varchar(255) not null, primary key (id));
create table books_genre (book_id bigint not null, genre_id bigint not null, primary key(book_id,genre_id));
alter table books add constraint fk_book_author foreign key (author_id) references authors on delete cascade;
alter table books_genre add constraint fk_genre foreign key (genre_id) references genres;
alter table books_genre add constraint fk_book foreign key (book_id) references books on delete cascade;
alter table comments add constraint fk_comment_book foreign key (book_id) references books on delete cascade;