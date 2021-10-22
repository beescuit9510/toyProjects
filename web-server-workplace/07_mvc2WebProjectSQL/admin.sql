create user webserver identified by 1234;
grant resource, connect to webserver;

update member set c_name = '펀더변덕' where c_member_no = 3;
update member set c_enroll_date = to_char(sysdate,'yyyy-mm-dd') where c_member_no = 5;

insert into member values(member_seq.nextval,'펀더변덕3','123456789','010-8888-7868',to_char(sysdate,'yyyy-mm-dd'),'bunny@gmail.com',3);
insert into member values(member_seq.nextval,'일반변덕','1234','010-7777-8767',to_char(sysdate,'yyyy-mm-dd'),'byunduck@gmail.com',2);
select * from member;
insert into payment_info values(to_char(sysdate,'yyyymmdd')||999999, 2, '강남', to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS'),'변변덕','010-4444-4444',2,5);
select * from payment_info;
insert into business values(7,'법인명333','123456789012','담당자명333');
select * from business;
insert into project_basic_info values(project_seq.nextval,3,'프로젝트이름222','200',null,'2022-12-21','프로젝트이야기2222','반려동물',to_char(sysdate,'yyyy-dd-mm'));
select * from project_basic_info;
select * from funding_category;
insert into reward values(2,'200','리워드제목222','리워드내용222',to_char(SYSDATE + (INTERVAL '2' YEAR),'yyyy-dd-mm'),'취소취소취소22222','이메일이메일2222','010-7777-8767');
select * from reward;
insert into maker_info values(2,'은행은행2222',2345,'변변덕2222');
select * from maker_info;

commit;

-- 펀딩한 프로젝트 불러오기
select * 
from payment_info p
join project_basic_info pbi
on p.project_no = pbi.project_no
join reward r
on pbi.project_no = r.reward_no
join maker_info m
on pbi.project_no = m.maker_info_no
where p.c_member_no = 5;


commit;

select * from member;
select * from ;
select * from project_basic_info;
select * from funding_like;

select * from maker_board;
commit;
insert into maker_board values(6,'법인명333','1888-12-21','bunny@gmail.com','라이츄츄츄333','스킬이름333','스킬레벨3333','회사주소333','회사소개333','사진을넣어야하는데사진이없어3333','반려동물',maker_board_seq.nextval,to_char(sysdate, 'yyyy-mm-dd'));
select * from business;

-- 펀딩한 프로젝트 불러오기
select * 
from payment_info p
join project_basic_info pbi
on p.project_no = pbi.project_no
join reward r
on pbi.project_no = r.reward_no
join maker_info m
on pbi.project_no = m.maker_info_no
where p.c_member_no = 5
order by shipping_date desc;



-- 좋아요한 펀딩
select fl.like_no, pbi.*,r.*,mi.* 
from member m
join funding_like fl
on m.c_member_no = fl.c_member_no
join project_basic_info pbi
on fl.liked_project_no = pbi.project_no
join reward r
on fl.liked_project_no = r.reward_no
join maker_info mi
on fl.liked_project_no = mi.maker_info_no
where m.c_member_no = 5
order by fl.like_no desc;
;

-- 좋아요한 펀더
select fl.like_no, mb.*
from member m
join funder_like fl
on m.c_member_no = fl.c_member_no
join member fm
on fl.liked_business_no = fm.c_member_no
join maker_board mb
on fl.liked_business_no = mb.writer_no
where m.c_member_no = 5
order by fl.like_no desc;
;

select * from maker_board;
-- 진행중인 프로젝트 가져오기
select pbi.*, r.*, mi.*
from member m
join project_basic_info pbi
on m.c_member_no = pbi.business_no
join reward r
on pbi.project_no = r.reward_no
join maker_info mi
on pbi.project_no = mi.maker_info_no
where m.c_member_no = 3
order by pbi.project_no desc
;


-- 진행중인 ?몇째 프로젝트의 댓글
select fc.* 
from project_basic_info pbi
join funding_comment fc
on pbi.project_no = fc.project_ref_no
where pbi.project_no = 1
order by fc.comment_no desc;


-- 진행중인 ?몇째 프로젝트의 주문목록
select pi.*, m.*
from project_basic_info pbi
join payment_info pi
on pbi.project_no = pi.project_no
join member m
on pi.c_member_no = m.c_member_no
where pbi.project_no = 2
order by pi.order_date desc
;
delete from payment_info where order_date like '2021-10-22 04:06:53';
select * from member;
commit;
select * from payment_info;
select * from project_basic_info;
insert into funding_comment values(FUNDCOMM_SEQ.nextval,'일반댓글1111',to_char(sysdate,'yyyy-mm-dd'),1,1,null,7);
commit;

FUNDCOMM_SEQ;

-- 펀딩 좋아요 취소하기
delete from funding_like
where c_member_no = 5 and liked_project_no = 1;

commit;
insert into funding_like values(5, 1, like_seq.nextval);
insert into funder_like values(5, 3,like_seq.nextval);
select * from funder_like;
select * from funding_like;
select * from project_basic_info;
select * from maker_board;

-- 펀더 좋아요 취소하기
delete from funder_like
where c_member_no = 5 and liked_business_no = 6;


-- 좋아요한 펀딩 펀더
insert into funder_like values(5,6,like_seq.nextval);
insert into funding_like values(5,2,like_seq.nextval);
select * from maker_board;
select * from project_basic_info;
select * from funding_like;
delete from funder_like;



insert into funding_comment values(FUNDCOMM_SEQ.nextval,'일반댓글1111',to_char(sysdate,'yyyy-mm-dd'),1,1,null,7);

insert into funding_comment values(FUNDCOMM_SEQ.nextval,'대댓글2222',to_char(sysdate,'yyyy-mm-dd'),2,?,?,?);
select * from funding_comment;
select * from project_basic_info;

commit;





create sequence maker_board_seq;
alter table maker_board add maker_board_no number;
alter table maker_board modify maker_board_no number not null;
alter table maker_board modify maker_board_no number unique;

alter table maker_board add write_date char(10);
update maker_board set write_date = to_char(sysdate,'yyyy-mm-dd');
alter table maker_board modify write_date char(10) not null;
commit;

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


commit;

select * from maker_board;
