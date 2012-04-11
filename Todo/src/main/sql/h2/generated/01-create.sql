--
-- (c) Copyright 2005-2012 JAXIO, www.jaxio.com
-- Source code generated by Celerio, a Jaxio product
-- Want to use Celerio within your company? email us at info@jaxio.com
-- Follow us on twitter: @springfuse
-- Template pack-backend-sd:src/main/sql/h2/01-create.p.vm.sql
--
DROP ALL objects;

CREATE TABLE item (
	id INTEGER(10) not null
	,name VARCHAR(45) not null
	,tag VARCHAR(45) not null default 'default'
	,due BIT not null default '0'
	,frequency INTEGER(10) not null default 0
	,month_day INTEGER(10)
	,list_id INTEGER(10)
);
CREATE UNIQUE INDEX IF NOT EXISTS PRIMARY
	ON item (id);
CREATE INDEX IF NOT EXISTS List.ID
	ON item (list_id);
ALTER TABLE item ALTER COLUMN id IDENTITY;

CREATE TABLE lists (
	id INTEGER(10) not null
	,name VARCHAR(45)
);
CREATE UNIQUE INDEX IF NOT EXISTS PRIMARY
	ON lists (id);
ALTER TABLE lists ALTER COLUMN id IDENTITY;

CREATE TABLE users (
	id INTEGER(10) not null
	,email VARCHAR(45) not null
	,list_id INTEGER(10)
);
CREATE INDEX IF NOT EXISTS listid
	ON users (list_id);
CREATE UNIQUE INDEX IF NOT EXISTS PRIMARY
	ON users (id);
ALTER TABLE users ALTER COLUMN id IDENTITY;


ALTER TABLE item 
	ADD CONSTRAINT LIST.ID
		FOREIGN KEY (list_id)
			REFERENCES lists (id);
ALTER TABLE users 
	ADD CONSTRAINT LISTID
		FOREIGN KEY (list_id)
			REFERENCES lists (id);