DROP TABLE IF EXISTS AUTHORS;
DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS GENRES;
CREATE TABLE AUTHORS(ID BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY, AUTHORNAME VARCHAR(255));
CREATE TABLE GENRES(ID BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY, GENRENAME VARCHAR(255));

CREATE TABLE BOOKS(
ID BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
BOOKNAME VARCHAR(255),
AUTHOR_ID BIGINT REFERENCES AUTHORS(ID),
GENRE_ID BIGINT REFERENCES GENRES(ID)
);