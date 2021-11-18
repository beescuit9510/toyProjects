package kr.or.common;

import org.aspectj.lang.JoinPoint;

import kr.or.member.model.vo.Member;

public class AfterReturningAdvice {
	
	public void afterReturningAdvice(JoinPoint jp, Object returnObj) {
		String methodName = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		Member m = (Member)args[0];
		
		System.out.println("매개변수 :"+args[0].toString());
		System.out.println("로그인 시도 아이디: "+m.getMemberId());
		System.out.println("메소드명 :"+methodName);
		
		
		if(returnObj==null) {
			System.out.println("로그인 결과 : 실패");
			
		}else {
			System.out.println("로그인 결과 : 성공");
			Member m2 = (Member)returnObj;
			System.out.println(m2.getMemberName()+"님이 로그인하셨습니다.");
			
		}
		//System.out.println("리턴데이터 : "+returnObj.toString());
		
	}

}
