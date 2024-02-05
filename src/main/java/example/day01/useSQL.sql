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
