CREATE TABLE accident_type (
    id serial primary key,
    name varchar (2000)
);

insert into accident_type (name) values ('Две машины');
insert into accident_type (name) values ('Машина и человек');
insert into accident_type (name) values ('Машина и велосипед');

CREATE TABLE rule (
    id serial primary key,
    name varchar (2000)
);

insert into rule (name) values ('Статья 1');
insert into rule (name) values ('Статья 2');
insert into rule (name) values ('Статья 3');

CREATE TABLE accident (
    id serial primary key,
    name varchar (2000),
    text varchar (2000),
    address varchar (2000),
    accident_type_id int references accident_type(id)
);

CREATE TABLE accident_rule (
    accident_id int references accident(id),
    rule_id int references rule(id)
);