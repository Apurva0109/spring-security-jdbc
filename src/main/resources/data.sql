INSERT INTO user_table(userName, password, enabled)
VALUES ('user','user','true');

INSERT INTO user_table(userName, password, enabled)
VALUES ('admin','admin','true');

INSERT INTO authorities_table(userName,authority)
VALUES ('user','ROLE_USER');

INSERT INTO authorities_table(userName,authority)
VALUES ('admin','ROLE_ADMIN');

