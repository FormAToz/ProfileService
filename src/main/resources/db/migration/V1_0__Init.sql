CREATE TABLE profiles (
id bigserial NOT NULL,
name varchar(255),
email varchar(255),
age integer,
created timestamp
);

CREATE TABLE errors (
id bigserial NOT NULL,
message varchar(255),
created timestamp
);