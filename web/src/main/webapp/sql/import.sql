drop table if exists authorities;
drop table if exists persistent_logins;
drop table if exists contacts;
drop table if exists users;
drop ALIAS if exists sha256code;

create table contacts (
	id int not null IDENTITY,
	email varchar(64) not null,
	phone varchar(20));

create table users (
  username varchar(64) not null primary key,
  name varchar(128),
  contact_id int not null,
  password varchar(64) not null,
  enabled boolean not null,
  constraint fk_users_contacts foreign key(contact_id) references contacts(id));

create table authorities (
  username varchar(64) not null,
  authority varchar(50) not null,
  constraint fk_authorities_users foreign key(username) references users(username));
  create unique index ix_auth_username on authorities (username,authority);
      
create table persistent_logins (
  username varchar(64) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null);
  
CREATE ALIAS sha256code FOR "ru.wmtool.commons.utils.CodeUtils.generateSHA256";

 insert into contacts (email, phone) values('itdembel@gmail.com', '12345');
 insert into contacts (email, phone) values('user@mail.ru', '67890');
 insert into contacts (email, phone) values('admin@mail.ru', '102939');

insert into users (username, password, enabled, name, contact_id) values('reader', sha256code('reader'), true, 'Иванов Иван Иванович', 1);
insert into users (username, password, enabled, name, contact_id) values('user', sha256code('user'), true, 'Сидоров Федор Вячеславович', 2);
insert into users (username, password, enabled, name, contact_id) values('admin', sha256code('admin'), true, 'Федоров Максим Анатольевич', 3);

insert into authorities (username, authority) values ('reader', 'ROLE_READER');
insert into authorities (username, authority) values ('user', 'ROLE_USER');
insert into authorities (username, authority) values ('admin', 'ROLE_ADMIN');
