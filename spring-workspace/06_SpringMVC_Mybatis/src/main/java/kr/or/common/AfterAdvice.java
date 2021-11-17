package kr.or.common;

import org.aspectj.lang.JoinPoint;

public class AfterAdvice {
	public void after(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		System.out.println("[after] :"+methodName);
		System.out.println("[after] : 동작");
	}
}
