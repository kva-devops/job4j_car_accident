CREATE TABLE accident_types (
    id serial primary key,
    name varchar (2000)
);

insert into accident_types (name) values ('Две машины');
insert into accident_types (name) values ('Машина и человек');
insert into accident_types (name) values ('Машина и велосипед');

CREATE TABLE rules (
    id serial primary key,
    name varchar (2000)
);

insert into rules (name) values ('Статья 1');
insert into rules (name) values ('Статья 2');
insert into rules (name) values ('Статья 3');

CREATE TABLE accidents (
    id serial primary key,
    name varchar (2000),
    text varchar (2000),
    address varchar (2000),
    accident_types_id int references accident_types(id)
);

CREATE TABLE accidents_rules (
    accident_id int not null ,
    rules_id int not null
);