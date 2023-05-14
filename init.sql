BEGIN;

DROP TABLE IF EXISTS prepod_info CASCADE;
CREATE TABLE prepod_info (id bigserial PRIMARY KEY, email VARCHAR(255), phoneNumber VARCHAR(255));
INSERT INTO prepod_info (email, phoneNumber) VALUES
('john@gmail.com', '+71111111111'),
('peter@gmail.com', '+72222222222'),
('ted@gmail.com', '+73333333333');

DROP TABLE IF EXISTS departments CASCADE;
CREATE TABLE departments (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO departments (name) VALUES
('ikt'),
('khtp');

DROP TABLE IF EXISTS groups CASCADE;
CREATE TABLE groups (id bigserial PRIMARY KEY, number bigint);
INSERT INTO groups (number) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8);

DROP TABLE IF EXISTS prepods CASCADE;
CREATE TABLE prepods (id bigserial PRIMARY KEY, name VARCHAR(255), salary bigint, info_id bigint, department_id bigint, FOREIGN KEY (info_id) REFERENCES prepod_info(id), FOREIGN KEY (department_id) REFERENCES departments(id));
INSERT INTO prepods (name, salary, info_id, department_id) VALUES
('John', 60000, 1, 1),
('Peter', 50000, 2, 2),
('Ted', 100000, 3, 1);

DROP TABLE IF EXISTS groups_prepods CASCADE;
CREATE TABLE groups_prepods (group_id bigint, prepod_id bigint, FOREIGN KEY (group_id) REFERENCES groups (id), FOREIGN KEY (prepod_id) REFERENCES prepods (id));
INSERT INTO groups_prepods (group_id, prepod_id) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 2),
(3, 2),
(7, 2),
(8, 3),
(1, 3),
(2, 3);

COMMIT;