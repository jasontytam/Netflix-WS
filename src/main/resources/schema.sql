CREATE TABLE NETFLIX_USER
(
   USERID      BIGINT       NOT NULL GENERATED ALWAYS AS IDENTITY,
   LOGIN       VARCHAR(20)  NOT NULL,
   FIRSTNAME   VARCHAR(40)  NOT NULL,
   LASTNAME    VARCHAR(40)  NOT NULL,
   CONSTRAINT  PK_USER            PRIMARY KEY(USERID),
   CONSTRAINT  UK_USER            UNIQUE(LOGIN)
);

CREATE TABLE FAV_MOVIE
(
   FAVID       BIGINT       NOT NULL GENERATED ALWAYS AS IDENTITY,
   USERID      BIGINT       NOT NULL,
   NETFLIXID   VARCHAR(40)  NOT NULL,
   TITLENAME   VARCHAR(100) NOT NULL,
   CONSTRAINT  PK_FAV_MOVIE       PRIMARY KEY(FAVID),
   CONSTRAINT  FK_FAV_MOVIE_NETFLIX_USER  FOREIGN KEY(USERID) REFERENCES NETFLIX_USER(USERID),
   CONSTRAINT  UK_FAV_MOVIE_USERID_NETFLIXID  UNIQUE(USERID, NETFLIXID)
);