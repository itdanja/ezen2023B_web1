drop database if exists springweb;
create database springweb;
use springweb;

drop table if exists todo;
create table todo(
	id int auto_increment , 				-- 할일 식별번호 / 자동번호
    content varchar(30)	,					-- 할일 내용
    deadline date ,							-- 할일 마감일
    state boolean default false	,			-- 할일 상태 [ true:완료 / false:진행중 ]
    constraint todo_pk_id primary key(id) 	-- 식별키
);
select * from todo;

drop table if exists article;				-- 스프링부트 교재 사용하는 테이블
create table article(
	id bigint auto_increment ,
    title varchar(255) ,
    content varchar(255 ) ,
    constraint article_pk_id primary key (id)
);
select * from article;


drop tables if exists member;
create table member(
	no bigint auto_increment ,				-- 회원번호
    id varchar(30) not null unique ,		-- 회원 아이디
    pw varchar(30) not null ,				-- 회원 비밀번호
    name varchar(20) not null ,				-- 회원 이름
    email varchar(50) ,						-- 회원 이메일
    phone varchar(13) not null unique,		-- 회원 핸드폰 번호
    img text , 								-- 프로필 사진 경로( 사진데이터 아닌 사진이 저장된 서버 경로 )
    constraint member_no_pk primary key(no ) -- 회원 번호 pk
);
select * from member;



