create table author
(
    id     serial,
    name varchar,
    particularity  varchar,
    birth_date date,
    primary key (id)
);
alter sequence author_id_seq restart with 11;