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


insert into member values(member_seq.nextval,'deathEater2',1234,'cuppy','01022221111','pakistan',3,to_char(sysdate,'yyyy-mm-dd'));

update member set member_pw='1234', phone='01022221111' where member_no = 16;

insert into member values (member_seq.nextval,'aa','aa','a','a','a',3,to_char(sysdate,'yyyy-mm-dd'));

delete from member where member_id = 'bunny';

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

update member set member_level = 1 where member_no = 1;

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

select * from board;
select * from notice;
select * from member;
commit;


insert into board values(board_seq.nextval,'a','a','deathEater',0,to_char(sysdate,'yyyy-mm-dd'),'a','a');
insert into notice values(notice_seq.nextval,'a','a','deathEater',0,to_char(sysdate,'yyyy-mm-dd'),'a','a');