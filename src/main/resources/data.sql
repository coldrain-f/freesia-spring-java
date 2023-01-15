INSERT INTO users (user_id, username, password, nickname, activated, created_at, modified_at) VALUES (1000, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 1, SYSDATE, SYSDATE);

INSERT INTO users (user_id, username, password, nickname, activated, created_at, modified_at) VALUES (1001, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 1, SYSDATE, SYSDATE);

INSERT INTO authority (authority_name) VALUES ('ROLE_USER');
INSERT INTO authority (authority_name) VALUES ('ROLE_ADMIN');

INSERT INTO user_authority (user_id, authority_name) VALUES (1000, 'ROLE_USER');
INSERT INTO user_authority (user_id, authority_name) VALUES (1000, 'ROLE_ADMIN');
INSERT INTO user_authority (user_id, authority_name) VALUES (1001, 'ROLE_USER');