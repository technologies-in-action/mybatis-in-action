drop table if exists countries;
create table countries
(
    id integer not null,
    name varchar(255) not null,
    primary key(id)
);
drop table if exists cities;
create table cities
(
    id integer not null,
    name varchar(255) not null,
    primary key(id)
);