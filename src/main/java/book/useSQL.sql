drop table if exists article;
create table article(
	id bigint auto_increment ,
    title text ,
    content longtext ,
    constraint article_id_pk primary key( id )
);