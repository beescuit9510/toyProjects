package kr.or.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import kr.or.member.model.vo.Member;

@Component
//@Service
@Aspect
public class BeforeAdvice2 {
	
	//포인트컷 생성
	@Pointcut(value="execution(* kr.or.member.model.service..*Service.selectOneMember(kr.or.member.model.vo.Member))")
	public void testPointcut() {}
	
	@Pointcut(value="execution(* kr.or.member.model.service..*Service.login2(..))")
	public void loginPointcut() {}

	
	@Around(value="loginPointcut()")
	public Object aroundTest(ProceedingJoinPoint pjp) throws Throwable{
		Object[] args = pjp.getArgs();
		String str1 = (String)args[0];
		String str2 = (String)args[1];
		
		System.out.println("-----------");
		System.out.println(str1+"/"+str2);		
		System.out.println("-----------");
		
		System.out.println("-----------");
		str2 = "pw11111";
		System.out.println(str1+"/"+str2);		
		System.out.println("args 0 : "+(String)args[0]);
		System.out.println("args 1 : "+(String)args[1]);
		System.out.println("-----------");
		
		System.out.println("-----------");
		args[1] ="pw2222";
		System.out.println(str1+"/"+str2);		
		System.out.println("args 0 : "+(String)args[0]);
		System.out.println("args 1 : "+(String)args[1]);
		System.out.println("-----------");
		
		Object obj = pjp.proceed(args);
//		Object obj = pjp.proceed();
		

		
		return obj;
	}
	
	
	@Before(value="testPointcut()")
	public void printLog(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		System.out.println("호출 매소드 이름 : "+methodName);
		
	}
	
	@After(value="testPointcut()")
	public void test1(JoinPoint jp){
		System.out.println("[AFTER]");
		
	}
	
	@AfterReturning(value="loginPointcut()", returning = "returnObj")
	public void afterReturn (JoinPoint jp, Object returnObj) {
		if(returnObj!=null) {
			Member m = (Member)returnObj;
			System.out.println("["+m.getMemberName()+"]님이 로그인 하셨습니다.");
		}
	}

}
