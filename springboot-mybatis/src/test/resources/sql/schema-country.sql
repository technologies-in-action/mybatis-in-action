drop table if exists countries;
create table countries
(
    id integer not null,
    name varchar(255) not null,
    primary key(id)
);