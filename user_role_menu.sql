create table users(
id bigint primary key auto_increment,
username varchar(20) unique not null,
password varchar(100)
);

-- 密码11
insert into users values(1,'tom','$2a$10$Q6/lq3dsRkgNOSs45U6Okuowz8QS8TJh6LH5NKs4aZqfg0DY8Cpr6');
-- 密码22
insert into users values(2,'tim','$2a$10$EKfHbbD30qpDa9QbuNNxBOuPndfwR/VnrqaaGH7c/xd9Ivl1UbBbW');

create table role(
id bigint primary key auto_increment,
name varchar(20)
);

insert into role values(1,'管理员');
insert into role values(2,'普通用户');

create table role_user(
uid bigint,
rid bigint
);

insert into role_user values(1,1);
insert into role_user values(2,2);

create table menu(
id bigint primary key auto_increment,
name varchar(20),
url varchar(100),
parentid bigint,
permission varchar(20)
);

insert into menu values(1,'系统管理','',0,'menu:system');
insert into menu values(2,'用户管理','',0,'menu:user');

create table role_menu(
mid bigint,
rid bigint
);

insert into role_menu values(1,1);

insert into role_menu values(2,1);
insert into role_menu values(2,2);