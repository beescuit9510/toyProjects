-- 펀딩 카테고리 테이블
CREATE TABLE funding_category (
	funding_category	varchar2(50) PRIMARY KEY
);

-- 펀더 카테고리 테이블
CREATE TABLE funder_category (
	funder_category     varchar2(50) PRIMARY KEY
);

-- 멤버테이블
CREATE TABLE member (
	c_member_no	    number		        PRIMARY KEY,
	c_name		    varchar2(20)		NOT NULL,
	c_password	    varchar2(50)		NOT NULL,
	c_phone		    char(13)			NOT NULL,
	c_enroll_date	char(10)			NOT NULL,
	c_email		    varchar2(100)		NOT NULL UNIQUE,
	c_level		    number				
);

-- 멤버 시퀀스
CREATE SEQUENCE MEMBER_SEQ;


-- 비즈니스 테이블
CREATE TABLE business (
	business_no	    number		    PRIMARY KEY  REFERENCES MEMBER(c_member_no),
	business_name	varchar2(50)	NOT NULL UNIQUE,
	business_code	char(12)		NOT NULL,
	manager_name	varchar2(20)	NOT NULL
);

-- 프로젝트 만들기 - 기본정보 테이블
CREATE TABLE project_basic_info (
	project_no		    number		        PRIMARY KEY,
    business_no 		number 		        NOT NULL REFERENCES business(business_no) on delete cascade,    -- 프로젝트 펀더no
	project_title		varchar2(120)		NOT NULL,
	target_price		number				NOT NULL,
	filepath			varchar2(1500),
	end_date			char(10)			NOT NULL,
	project_story		varchar2(4000)		NOT NULL,
	funding_category	varchar2(50)		NOT NULL  REFERENCES funding_category(funding_category)
);

-- 프로젝트 만들기 - 기본정보 시퀀스
CREATE SEQUENCE PROJECT_SEQ;


-- 리워드 테이블
CREATE TABLE reward (
	reward_no			number			PRIMARY KEY  REFERENCES project_basic_info(project_no),
	reward_price		number			NOT NULL,
	reward_title		varchar2(500)	NOT NULL,
	reward_content	    varchar2(4000)	NOT NULL,
	shipping_date		char(10)		NOT NULL,
	cancel_policy		varchar2(3000)	NOT NULL,
	q_email			    varchar2(50)	NOT NULL,
	q_phone			    char(13)		NOT NULL
);

-- 부가정보 테이블
CREATE TABLE maker_info (
	maker_info_no	number		    PRIMARY KEY REFERENCES project_basic_info(project_no),
	trade_bank	    varchar2(50)	NOT NULL,
	account_number	number		    NOT NULL,
	deposit_name	varchar2(20)	NOT NULL
);

-- 펀더게시판 테이블
CREATE TABLE maker_board (
	writer_no	        number		   PRIMARY KEY     REFERENCES business(business_no) ON DELETE CASCADE,
    business_name	    varchar2(50)   REFERENCES business(business_name),
	open_date	        char(10),
	board_email	        varchar2(50),
	search_tag	        varchar2(500),
	skill_name	        varchar2(500),
	skill_level	        varchar2(500),
	company_addr	    varchar2(500),
	company_intro	    varchar2(4000)  NOT NULL,
	profile_filepath	varchar2(1500)  NOT NULL,
	funder_category	    varchar2(50)    REFERENCES funder_category(funder_category)
	
);

-- 펀딩댓글 테이블
CREATE TABLE funding_comment (
	comment_no	    number		    PRIMARY KEY,
	comment_content	varchar2(1500)	NOT NULL,
	write_date	    char(10),
	comment_level	number		    NOT NULL,
	project_ref_no	number		    NOT NULL REFERENCES project_basic_info(project_no) ON DELETE CASCADE,
	comment_ref_no	number		    NOT NULL REFERENCES funding_comment(comment_no) ON DELETE CASCADE,
	comment_writer	number      	NOT NULL REFERENCES MEMBER(c_member_no) ON DELETE CASCADE
);

-- 펀딩댓글 시퀀스
CREATE SEQUENCE FUNDCOMM_SEQ;

-- 공지사항 테이블
CREATE TABLE notice (
	notice_no	    number		        PRIMARY KEY,
	notice_title	varchar2(500)		NOT NULL,
	notice_content	varchar2(2000)		NOT NULL,
	write_date	    char(10)		    NOT NULL,
	filepath	    varchar2(1500),
	notice_writer	number		        NOT NULL  REFERENCES MEMBER(c_member_no) ON DELETE CASCADE
);

-- 공지사항 시퀀스
CREATE SEQUENCE NOTICE_SEQ;

-- 이벤트 테이블
CREATE TABLE event (
	event_no	    number		        PRIMARY KEY,
	event_title	    varchar2(500)		NOT NULL,
	event_content	varchar2(4000)		NOT NULL,
	write_date	    char(10)		    NOT NULL,
	filepath	    varchar2(1500),
	event_writer    number              NOT NULL  REFERENCES MEMBER(c_member_no) ON DELETE CASCADE
);

-- 이벤트 시퀀스
CREATE SEQUENCE EVENT_SEQ;

-- 펀딩좋아요 테이블
CREATE TABLE funding_like (
	c_member_no	        number	   NOT NULL    REFERENCES MEMBER(c_member_no),
	liked_project_no	number	   NOT NULL    REFERENCES project_basic_info(project_no),
    CONSTRAINT unique_constraint_funding_like UNIQUE(c_member_no, liked_project_no)
);

-- 펀더 좋아요 테이블
CREATE TABLE funder_like (
	c_member_no	        number		NOT NULL  REFERENCES MEMBER(c_member_no),
	liked_business_no	number		NOT NULL  REFERENCES BUSINESS(business_no),
    CONSTRAINT unique_constraint_funder_like UNIQUE(c_member_no, liked_business_no)
);

-- 주문목록 테이블
CREATE TABLE payment_info (
	payment_no	    number		        PRIMARY KEY,
	quantity	    number		        NOT NULL,
	receive_addr	varchar2(1500)		NOT NULL,
	order_date	    char(10)		    NOT NULL,
	receive_name	varchar2(50)		NOT NULL,
	receive_phone	char(13)		    NOT NULL,
	project_no	    number		        NOT NULL  REFERENCES project_basic_info(project_no),
	c_member_no	    number		        NOT NULL  REFERENCES MEMBER(c_member_no)
);


alter table maker_board add maker_board_no number;
alter table maker_board modify maker_board_no number not null;
alter table maker_board modify maker_board_no number unique;

alter table maker_board add write_date char(10);
update maker_board set write_date = to_char(sysdate,'yyyy-mm-dd');
alter table maker_board modify write_date char(10) not null;

alter table payment_info modify order_date char(19);
alter table payment_info modify order_date char(19) not null;
--to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') 형식으로

alter table project_basic_info add start_date char(10);
alter table project_basic_info modify start_date char(10) not null;

alter table funder_like add like_no number;
alter table funding_like add like_no number;
alter table funder_like modify like_no number unique not null;
alter table funding_like modify like_no number unique not null;

create sequence like_seq;
create sequence maker_board_seq;

