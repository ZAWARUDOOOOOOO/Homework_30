--liquibase formatted sql

--changeset Sava:init_role
insert into roles(id,role)
values (1,'ROLE_ADMIN');
insert into roles(id,role)
values (2,'ROLE_USER');
insert into roles(id,role)
values (3,'temp');

