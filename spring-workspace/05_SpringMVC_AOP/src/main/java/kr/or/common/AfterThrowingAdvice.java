package kr.or.common;

import org.aspectj.lang.JoinPoint;

public class AfterThrowingAdvice {
	
	public void afterThrowing(JoinPoint jp, Exception exceptObj) {
		String methodName = jp.getSignature().getName();
		System.out.println("예외발생 메소드 :"+methodName);
		System.out.println("예외메세지 : "+exceptObj);
//		System.out.println("예외메세지 : "+exceptObj.getMessage());
	}

}
