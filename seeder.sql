CREATE DATABASE IF NOT EXISTS intellifridge_db;

CREATE USER intellifridge_user@localhost IDENTIFIED BY 'codeup';
GRANT ALL ON intellifridge_db.* TO intellifridge_user@localhost;