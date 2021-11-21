INSERT INTO tb_user (name, email, password) VALUES ('Jose', 'jose@gmail.com', '$2a$10$ueun2nZZY0M/w9PxQN.3XO7j4KoWuC8CqhNOATfi.ODw4XV38WAla');
INSERT INTO tb_user (name, email, password) VALUES ('Lucas', 'lucas@gmail.com', '$2a$10$3oNQ3LPgKP1eklIVLrirGeBPSa.xMDKx/HpXYBXHWYykjgjK8ZMbS');
INSERT INTO tb_user (name, email, password) VALUES ('Cristina', 'cristina@gmail.com', '$2a$10$VmqW5CogX/YBTlI0mSIR5O1dMAEaKVkb8VQHjDcCt7DV6hnxLDJMW');
INSERT INTO tb_user (name, email, password) VALUES ('Alexandre', 'alexandre@gmail.com', '$2a$10$JcrZ/FuhNf6aLgYVIZIheOa2uKNtphhWo6qKDjrbt8qNPAScx8.ti');

INSERT INTO tb_role (role_name) VALUES ('ROLE_CLIENTE');
INSERT INTO tb_role (role_name) VALUES ('ROLE_COLABORADOR');
INSERT INTO tb_role (role_name) VALUES ('ROLE_FORNECEDOR');
INSERT INTO tb_role (role_name) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 3);
INSERT INTO tb_user_role (user_id, role_id) VALUES (4, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (4, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (4, 3);
INSERT INTO tb_user_role (user_id, role_id) VALUES (4, 4);