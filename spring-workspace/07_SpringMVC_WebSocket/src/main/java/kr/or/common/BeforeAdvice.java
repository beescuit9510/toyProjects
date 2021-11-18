package kr.or.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import kr.or.member.model.vo.Member;

public class BeforeAdvice {
	public void beforeTest(JoinPoint jp) {
		//AOP가 동작하는 메소드의 정보
		Signature sig = jp.getSignature();
		System.out.println(sig.getName());
		System.out.println(sig.toLongString());

		//AOP가 동작하는 메소드의 매개뱐수
		Object[] args= jp.getArgs();
//		System.out.println("매개변수 정보: "+args[0].toString());
//   	args[0]가 멤버가 아닌 경우는 ClassCastException 오류가 남.
//		Member m = (Member)args[0]; <--- 정확히 이 자리!
//		m.setMemberPw("11111");
	}
}
