  create table user_table(
      userName varchar_ignorecase(50) not null primary key,
      password varchar_ignorecase(50) not null,
      enabled boolean not null,
      address varchar,
      location varchar);

  create table authorities_table (
      userName varchar_ignorecase(50) not null,
      authority varchar_ignorecase(50) not null,
      constraint fk_authorities_users foreign key(userName) references user_table(userName));

  create unique index ix_auth_username on authorities_table (userName,authority);