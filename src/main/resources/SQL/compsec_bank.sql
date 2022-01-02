DROP DATABASE IF EXISTS compsec_bank;
CREATE DATABASE compsec_bank;
USE compsec_bank;
CREATE TABLE users (
    login    VARCHAR(128) NOT NULL,
    password VARCHAR(128) NOT NULL,
    email    VARCHAR(128) NOT NULL,
    answer   VARCHAR(128) NOT NULL,

    PRIMARY KEY (login)
);


CREATE TABLE transfers (
    transfer_id INT NOT NULL AUTO_INCREMENT,
    sender      VARCHAR(128) NOT NULL,
    receiver    VARCHAR(128) NOT NULL,
    amount      INT NOT NULL,

    PRIMARY KEY (transfer_id)
);
