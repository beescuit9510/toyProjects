package kr.or.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.or.member.model.vo.Member;

@Component
@Aspect
public class PasswordEncAdvice {
	@Autowired
	private SHA256Enc enc;
	
	@Pointcut(value="execution(* kr.or.member.model.service..*Service.*Member(kr.or.member.model.vo.Member))")
	public void encPointcut() {}
	@Pointcut(value="execution(* kr.or.member.model.service..*Service.*Pw(kr.or.member.model.vo.Member))")
	public void encPointcut2() {}
	
	@Before(value="encPointcut()")
	public void pwEnc(JoinPoint jp) throws Exception{
		String methodName = jp.getSignature().getName();
		
		Member m = (Member)jp.getArgs()[0];
		String pw = m.getMemberPw();
		String encodedPw = enc.encData(pw);
		m.setMemberPw(encodedPw);

		System.out.println("메서드 : "+methodName);
		System.out.println("암호화 전 비밀번호 :"+pw);
		System.out.println("암호화 후 비밀번호 :"+encodedPw);
		
		
	}
	
	@Before(value="encPointcut2()")
	public void pwEnc2(JoinPoint jp) throws Exception{
		String methodName = jp.getSignature().getName();
		
		Member m = (Member)jp.getArgs()[0];
		String pw = m.getMemberPw();
		String encodedPw = enc.encData(pw);
		m.setMemberPw(encodedPw);
		
		System.out.println("메서드 : "+methodName);
		System.out.println("암호화 전 비밀번호 :"+pw);
		System.out.println("암호화 후 비밀번호 :"+encodedPw);
		
		
	}
}
