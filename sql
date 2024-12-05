CREATE DATABASE IF NOT EXISTS damhwa;
USE damhwa;

-- 회원 정보
DROP TABLE IF EXISTS member;
CREATE TABLE member (
  member_id VARCHAR(20) NOT NULL PRIMARY KEY,
  member_pw VARCHAR(20) NOT NULL,
  member_name VARCHAR(10) NOT NULL,
  member_nickname VARCHAR(20) DEFAULT NULL,
  member_phone VARCHAR(15) NOT NULL,
  member_age INT NOT NULL,
  member_height SMALLINT NOT NULL,
  member_weight INT NOT NULL,
  member_live VARCHAR(10) NOT NULL,
  member_birthday DATE NOT NULL,
  member_gender CHAR(1) NOT NULL,
  member_profile TEXT DEFAULT NULL,
  member_tag TEXT DEFAULT NULL,
  member_date DATETIME DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO member VALUES 
('aaa', '1111', 'test', 'test_nick', '01012345678', 40, 170, 60, '부산', '1984-11-11', NULL, NULL, CURRENT_TIMESTAMP),
('bbb', '1111', 'test', 'test_nick2', '01087654321', 50, 170, 70, '서울', '1974-10-10', NULL, NULL, CURRENT_TIMESTAMP);

SELECT * FROM member;

-- 커뮤니티
DROP TABLE IF EXISTS community;
CREATE TABLE community (
  community_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  member_id VARCHAR(20) NOT NULL,
  community_content TEXT NOT NULL,
  community_comment_count INT DEFAULT 0,
  community_like_count INT DEFAULT 0,
  FOREIGN KEY (member_id) REFERENCES member(member_id)
);

INSERT INTO community (member_id, community_content, community_comment_count, community_like_count) VALUES
('aaa', 'test content', 0, 0),
('bbb', 'test content', 2, 10);

SELECT * FROM community;

-- 댓글
DROP TABLE IF EXISTS comment;
CREATE TABLE comment (
  comment_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  community_id INT NOT NULL,
  member_id VARCHAR(20) NOT NULL,
  comment_content TEXT NOT NULL,
  comment_like_count INT DEFAULT 0,
  FOREIGN KEY (community_id) REFERENCES community(community_id),
  FOREIGN KEY (member_id) REFERENCES member(member_id)
);

SELECT * FROM comment;

-- 태그
DROP TABLE IF EXISTS tag;
CREATE TABLE tag (
  member_id VARCHAR(20) NOT NULL PRIMARY KEY,
  tag_dog TINYINT(1) DEFAULT 0,
  tag_cat TINYINT(1) DEFAULT 0,
  tag_fox TINYINT(1) DEFAULT 0,
  tag_pretty TINYINT(1) DEFAULT 0,
  tag_cute TINYINT(1) DEFAULT 0,
  tag_sexy TINYINT(1) DEFAULT 0,
  tag_elders TINYINT(1) DEFAULT 0,
  tag_younger TINYINT(1) DEFAULT 0,
  tag_sameAge TINYINT(1) DEFAULT 0,
  FOREIGN KEY (member_id) REFERENCES member(member_id)
);

SELECT * FROM tag;

-- 친구 목록
DROP TABLE IF EXISTS friend;
CREATE TABLE friend (
  friend_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  member_id VARCHAR(20) NOT NULL,
  friend_member_id VARCHAR(20) NOT NULL,
  FOREIGN KEY (member_id) REFERENCES member(member_id),
  FOREIGN KEY (friend_member_id) REFERENCES member(member_id)
);

SELECT * FROM friend;

-- 신고 테이블
DROP TABLE IF EXISTS report;
CREATE TABLE report (
  report_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  member_id VARCHAR(20) NOT NULL,
  report_content TEXT NOT NULL,
  FOREIGN KEY (member_id) REFERENCES member(member_id)
);

SELECT * FROM report;



