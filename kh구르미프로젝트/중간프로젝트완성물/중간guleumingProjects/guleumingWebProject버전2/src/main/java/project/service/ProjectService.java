package project.service;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import common.JDBCTemplate;
import project.dao.ProjectDao;
import table.model.vo.MakerInfo;
import table.model.vo.ProjectBasicInfo;
import table.model.vo.Reward;

public class ProjectService {



	public int insertProject(ProjectBasicInfo pjInfo, Reward re, MakerInfo mInfo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ProjectDao().insertProjInfo(conn,pjInfo);
		int projNo=0; 
		if(result>0) {
			projNo = new ProjectDao().selectProjNo(conn,pjInfo);
			if(projNo>0) {
				 result = new ProjectDao().insertReward(conn,re,projNo);
				 if(result>0) {
					 result = new ProjectDao().insertMakerInfo(conn,mInfo,projNo);
					 if(result>0) {
						 JDBCTemplate.commit(conn);
					 }else {
						 JDBCTemplate.rollback(conn);
					 }
				 }else {
					 JDBCTemplate.rollback(conn);
				 }
			}else {
				JDBCTemplate.rollback(conn);
			}
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public String selectPayDate(String endDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate now = LocalDate.now();
		String endDate2 = endDate;
	    LocalDate date = LocalDate.parse(endDate2, DateTimeFormatter.ISO_DATE);
	    LocalDate payDate = date.plusDays(1);
	    String payDateS =payDate.format(formatter);
		return payDateS;
	}

	public long selectPeriod(String endDate) {
		 
		  LocalDate now = LocalDate.now();
		  LocalDate date = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
		  long period = ChronoUnit.DAYS.between(now, date);
		return period;
	}


}
