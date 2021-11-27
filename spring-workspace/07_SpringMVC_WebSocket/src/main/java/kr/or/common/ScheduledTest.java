package kr.or.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.or.board.model.service.BoardService;

@Component
public class ScheduledTest {
	
	@Autowired
//	private BoardService service;
	
	// 초 : 0~59 * - /
	// 분 : 0~59 * - /
	// 시 : 0~23 * - /
	// 일 : 1~31 * - / ?(사용하지않을때 ) L(매월 마지막날) LW(해당하는 달의 마지막 평일)
	// 월 : 1~12 * - / JAN-DEC 
	// 요알 : 1~7 * - / SUN-SAT
	// 년도 : 1970-2099 * - /
	// 초 분 시 일 월 요일
	// 0 0 0 * * *  -> 매일 0시 0분 0초
	// -> 쿠폰 service호출 -> 쿠폰 만료일이 오늘 날짜보다 작은 쿠폰 
	
//	@Scheduled(fixedDelay = 10000)
	//자동 출력이기 때문에 매개변수를 못가짐.
	public void test1() {
		System.out.println("5초마다 자동으로 출력!");
	}
	
	@Scheduled(cron="0/5 * * * * *")
	
	public void test2() {
		System.out.println("크론식으로 호출");
	}
}
