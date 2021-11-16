package kr.or.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class AroundAdvice {
	
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		String methodName = pjp.getSignature().getName();			
		StopWatch stopWatch = new StopWatch();
		
		System.out.println("around 시작");
		stopWatch.start();
		Object obj = pjp.proceed();//포인트컷 메소드르 실행하고 그 결과를 리턴,
		stopWatch.stop();
		System.out.println(methodName+"() 메소드 수행시간 : "+stopWatch.getTotalTimeMillis()+" ms");
		System.out.println("around 끝");
		
		
		return obj;
	}
}
