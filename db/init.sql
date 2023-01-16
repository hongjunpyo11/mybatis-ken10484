# DB 생성
DROP DATABASE IF EXISTS mybatis;
CREATE DATABASE mybatis;
USE mybatis;

    # 게시물 테이블 생성
CREATE TABLE article (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(id),
    createDate DATETIME NOT NULL,
    modifyDate DATETIME NOT NULL,
    `subject` CHAR(100) NOT NULL,
    content LONGTEXT NOT NULL
);

# 게시물 데이터
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

# 회원 테이블 생성
CREATE TABLE `member` (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(id),
    createDate DATETIME NOT NULL,
    modifyDate DATETIME NOT NULL,
    username CHAR(100) NOT NULL UNIQUE,
    `password` CHAR(100) NOT NULL,
    `name` CHAR(100) NOT NULL,
    email CHAR(100) NOT NULL
);

# 회원 데이터
INSERT INTO `member`
SET createDate = NOW(),
modifyDate = NOW(),
username = 'user1',
`password` = '{noop}1234',
`name` = '유저1',
email = 'user1@test.com';

INSERT INTO `member`
SET createDate = NOW(),
modifyDate = NOW(),
username = 'user2',
`password` = '{noop}1234',
`name` = '유저2',
email = 'user2@test.com';

# 게시물에 작성자 정보 추가
ALTER TABLE article
ADD COLUMN member_id BIGINT UNSIGNED NOT NULL AFTER modifyDate;

# 기존 게시물의 작성자를 user2 로 지정
UPDATE article
SET member_id = 2;

ALTER TABLE article
CHANGE member_id memberId BIGINT UNSIGNED NOT NULL;