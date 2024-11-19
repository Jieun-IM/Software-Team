create database damhwa;

-- 회원 정보
drop table if exists member;
create table member(
  id VARCHAR(20) NOT NULL PRIMARY KEY,
  pw VARCHAR(20) NOT NULL,
  name VARCHAR(10) NOT NULL,
  nickname VARCHAR(20) NULL,
  phone INT NOT NULL,
  age INT NOT NULL,
  height SMALLINT NOT NULL,
  weight INT NOT NULL,
  live VARCHAR(10) NOT NULL,
  birthday DATE NOT NULL,
  profile TEXT NULL,
  tag TEXT NULL,
  date  DATETIME
);
select * from member;
insert into member values ('aaa', 1111, 'test', 'test', 01012345678, 40, 170, 10, '부산', '2024-11-11', null, null, null);
insert into member values ('bbb', 1111, 'test', 'test', 01012345678, 50, 170, 10, '부산', '2024-11-11', null, null, null);
