CREATE TABLE user (
    id int NOT NULL,
    name varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE authority (
    id int NOT NULL,
    authority varchar(255) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE user_authorities (
    id int NOT NULL,
    user_id int NOT NULL,
    authority_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (authority_id) REFERENCES authority(id)
);
