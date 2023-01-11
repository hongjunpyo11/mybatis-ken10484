DROP DATABASE IF EXISTS app_2022_09_23;
CREATE DATABASE app_2022_09_23;
USE app_2022_09_23;

CREATE TABLE article (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(id),
    createDate DATETIME NOT NULL,
    modifyDate DATETIME NOT NULL,
    `subject` CHAR(100) NOT NULL,
    content LONGTEXT NOT NULL
);

INSERT INTO article
SET createDate = NOW(),
modifyDate = NOW(),
`subject` = '제목1',
content = '내용1';

INSERT INTO article
SET createDate = NOW(),
modifyDate = NOW(),
`subject` = '제목2',
content = '내용2';