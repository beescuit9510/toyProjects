create table member(
      MEMBER_NO NUMBER PRIMARY KEY,
      MEMBER_ID VARCHAR2(20) UNIQUE NOT NULL,
      MEMBER_PW VARCHAR2(20) NOT NULL,
      MEMBER_NAME VARCHAR2(20) NOT NULL,
      PHONE CHAR(13) NOT NULL,
      ADDRESS VARCHAR2(500) NOT NULL,
      MEMBER_LEVEL NUMBER NOT NULL,
      ENROLL_DATE CHAR(10)
);

CREATE SEQUENCE MEMBER_SEQ;


create table notice(
    notice_no number primary key,
    notice_title varchar(300) not null,
    notice_content varchar(1000) not null,
    notice_writer varchar(20) references member(member_id) on delete cascade,
    read_count number,
    reg_date char(10),
    filename varchar2(100), --게시글 작성시 원래 파일이름을 저장하는 컬럼(다운로드할때 올린파일명 그대로 다운로드)
    filepath varchar2(100) --업로드 시 파일이름이 기존에 존재하는 파일과 겹치는 경우 구분을 하기위해 파일명을 변경하고, 변경된 이름을 저장하는 컬럼345
);

create sequence notice_seq; --글번호 자동 채번을 위한 시퀸스;


create table board(
    board_no number primary key,
    board_title varchar(300) not null,
    board_content varchar(1000) not null,
    board_writer references member(member_id) on delete cascade,
    read_count number,
    reg_date char(10),
    filename varchar2(100),
    filepath varchar2(100)
);

create sequence board_seq;



create table notice_comment(
    nc_no number primary key,
    nc_level number, --댓글인지 대댓글인지 구분하는 컬럼(1:일반댓글,2:댓글의 댓글)
    nc_writer varchar2(20) references member(member_id) on delete cascade,
    nc_content varchar2(500) not null,
    nc_date char(10),
    notice_ref number references notice(notice_no) on delete cascade, --어떤 공지사항의 댓글인지 기록
    nc_ref number references notice_comment(nc_no) on delete cascade --어떤 댓글의 댓글인지
);

create sequence nc_seq;


select * from board;
select * from notice where notice_title like '%cuppy%';
select * from member;
select * from notice_comment;
commit;
