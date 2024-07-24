drop database interviewtestdb;
drop user interviewtest;
create user interviewtest with password 'password';
create database interviewtestdb with template=template0 owner=interviewtest;
\connect interviewtestdb;
alter default privileges grant all on tables to interviewtest;
alter default privileges grant all on sequences to interviewtest;

create table et_users(
user_id integer primary key not null,
first_name varchar(20) not null,
last_name varchar(20) not null,
email varchar(30) not null,
password text not null
);

CREATE TABLE et_employees (
    employee_id INTEGER PRIMARY KEY NOT NULL,
    user_id INTEGER NOT NULL,
    job_title VARCHAR(20) NOT NULL
);

ALTER TABLE et_employees ADD CONSTRAINT empl_users_fk
FOREIGN KEY (user_id) REFERENCES et_users(user_id);

CREATE SEQUENCE et_users_seq INCREMENT 1 START 1;
CREATE SEQUENCE et_employees_seq INCREMENT 1 START 1;
