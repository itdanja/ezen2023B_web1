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


# 1. 게시물 카테고리
drop table if exists bcategory;
create table  bcategory(
	bcno int unsigned auto_increment ,
    bcname varchar( 30 ) not null unique,
	bcdate datetime default now() not null  ,
    constraint bcategory_bcno_pk primary key ( bcno )
);
insert into bcategory( bcname ) values ( '자유' ) , ( '노하우' );
select * from bcategory;

# 2. 게시물
drop table if exists board;
create table board(
	bno bigint unsigned auto_increment ,
    btitle varchar( 255 ) not null ,
    bcontent longtext ,
    bfile varchar( 255 ) ,
    bview int unsigned default 0 not null ,
    bdate datetime  default now() not null  ,
    mno  bigint ,
    bcno int unsigned,
    constraint board_bno_pk primary key( bno ) ,
    constraint board_mno_fk foreign key( mno) references member( no ) on update cascade on delete cascade ,
    constraint board_bcno_fk foreign key( bcno ) references bcategory( bcno ) on update cascade on delete cascade
);
select *from board;

# 3. 게시물 댓글
drop table if exists breply;
create table breply(
	brno bigint unsigned auto_increment ,
    brcontent varchar(255) not null,
    brdate datetime default now() not null,
    brindex bigint unsigned default 0 not null,
    mno bigint ,
    bno bigint unsigned,
    constraint breply_brno_fk primary key( brno ) ,
	constraint breply_mno_fk foreign key(mno) references member( no ) on update cascade on delete cascade ,
    constraint breply_bno_fk foreign key(bno) references board( bno ) on update cascade on delete cascade
);
select *from breply;

# 1. 제품
drop table if exists product;
create table product(
	pno int auto_increment	, 			-- 제품번호
	pname varchar(100) not null,		-- 제품 이름
    pprice int  default 0 ,        		-- 제품 가격
    pcontent varchar(255) ,        		-- 제품 설명
    pstate   tinyint default 0 ,      	-- 제품 상태
    pdate    datetime default now(),    -- 제품 등록일
    plat     varchar(30) not null,  	-- 제품 위치 위도
    plng     varchar(30) not null,  	-- 제품 위치 경도
    mno		bigint ,					-- 제품 등록 회원
    constraint product_pno_pk primary key(pno) , -- pk
    constraint product_mno_fk foreign key(mno) references member(no) on update cascade on delete cascade
);
# 2. 제품 이미지
drop table if exists productimg;
create table productimg(
	pimgno int auto_increment ,			-- 제품 이미지 식별번호
    pimg varchar(255) , 				-- 제품 이미지 파일명
    pno int ,
    constraint productimg_pimgno_pk primary key( pimgno ) , -- pk
    constraint productimg_pno_fk foreign key (pno) references product(pno) on update cascade on delete cascade
);

# 3. 좋아요 테이블
create table plike(
	mno bigint,
    pno int ,	-- pno 와 mno 가 일치했을때 삭제.
    constraint plike_mno_fk foreign key(mno) references member(no) on update cascade on delete cascade,
    constraint plike_pno_fk foreign key(pno) references product(pno) on update cascade on delete cascade
);


























