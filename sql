create database damhwa;

-- 회원 정보
drop table if exists member;
create table member(
  member_id VARCHAR(20) NOT NULL PRIMARY KEY,
  member_pw VARCHAR(20) NOT NULL,
  member_name VARCHAR(10) NOT NULL,
  member_nickname VARCHAR(20) NULL,
  member_phone INT NOT NULL,
  member_age INT NOT NULL,
  member_height SMALLINT NOT NULL,
  member_weight INT NOT NULL,
  member_live VARCHAR(10) NOT NULL,
  member_birthday DATE NOT NULL,
  member_profile TEXT NULL,
  member_tag TEXT NULL,
  member_date  DATETIME
);

insert into member values ('aaa', 1111, 'test', 'test', 01012345678, 40, 170, 10, '부산', '2024-11-11', null, null, null);
insert into member values ('bbb', 1111, 'test', 'test', 01012345678, 50, 170, 10, '부산', '2024-11-11', null, null, null);

select * from member;

drop table if exists community;
create table community(
	community_id VARCHAR(20) NOT NULL,
	community_nikname VARCHAR(20) NOT NULL,
	community_content TEXT NOT NULL,
	community_comment INT NULL,
	community_like INT NULL,
	FOREIGN KEY (community_id) REFERENCES member(member_id),
	FOREIGN KEY (community_nikname) REFERENCES member(member_nickname)
); 

insert into community values ('aaa', 'test content', 0, 0);
insert into community values ('bbb', 'test content', 2, 10);
insert into community values ('ccc', 'test content', null, null);

drop table if exists comment;
create table comment(
	comment_count INT NULL ,
	comment_id VARCHAR(20) NOT NULL,
    comment_nikname VARCHAR(20) NOT NULL,
	comment_content TEXT NOT NULL,
	comment_comment INT NULL,
	comment_like INT NULL,
    FOREIGN KEY (comment_count) REFERENCES member(commnity_comment),
	FOREIGN KEY (comment_id) REFERENCES member(member_id),
	FOREIGN KEY (comment_nikname) REFERENCES member(member_nickname)
); 

select * from comment;

drop table if exists tag;
create table tag(
	tag_id VARCHAR(20) NOT NULL,
	tag_dog tinyint(1) DEFAULT '0',
	tag_cat tinyint(1) DEFAULT '0',
	tag_fox tinyint(1) DEFAULT '0',
	tag_pretty tinyint(1) DEFAULT '0',
	tag_cute tinyint(1) DEFAULT '0',
	tag_sexy tinyint(1) DEFAULT '0',
	tag_elders tinyint(1) DEFAULT '0',
	tag_younger tinyint(1) DEFAULT '0',
	tag_sameAge tinyint(1) DEFAULT '0',
	FOREIGN KEY (tag_id) REFERENCES member(member_id)
);

select * from tag;

drop table if exists friend;
create table friend(
	friend_id VARCHAR(20) NOT NULL,
	friend_friendId VARCHAR(20) NOT NULL,
	friend_list TEXT NOT NULL,
	FOREIGN KEY (friend_id) REFERENCES member(member_id),
    FOREIGN KEY (friend_friendId) REFERENCES member(member_id),
	FOREIGN KEY (friend_list) REFERENCES member(member_nickname)
); 

select * from friend;

drop table if exists report;
create table report(
	report_id VARCHAR(20) NOT NULL,
	report_content TEXT NOT NULL,
	report_number INT NOT NULL AUTO_INCREMENT,
	FOREIGN KEY (report_id) REFERENCES member(member_id)
); 

select * from friend;


