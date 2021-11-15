package kr.or.member.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.or.member.model.service.MemberService;
import kr.or.member.model.vo.Member;

@Controller
public class MemberController {
	
	@Autowired //스프링이 서버 시작시 자동으로 생성한 객체 중 아래 변수와 일치하는 데이터 타입의 객체를 찾아서 변수에 저장
	//의존성 주입
	private MemberService service;
	
	public MemberController() {
		super();
		System.out.println("MemberController 객체 생성 완료");
		System.out.println(service);
	}
	
//	@RequestMapping(value="/test1.do")
//	public String test1() {
//		System.out.println("/test1.do 호출");
//		System.out.println(service);
//		return "test/test1";
//	}
//
//	@RequestMapping(value="/test2.do")
//	public String test2() {
//		System.out.println("/test2.do 호출");
//		System.out.println(service);
//		return "test/test2";
//	}
//	
	
	@RequestMapping(value="/login.do")
	public String login(Member member, HttpSession session, Model model) {
		System.out.println("로그인 비지니스 로직 수행 전(컨트룰러 시작)");
		
//	public String login(HttpServletRequest request) {
//		String memberId = request.getParameter("memberId");
//		String memberPw = request.getParameter("memberPw");
//		System.out.println("memberId : " +member.getMemberId());
//		System.out.println("memberPw : " +member.getMemberPw());

		
		//Model -> request 영역에 데이터를 등록하기위한 객체
		//request.setAttribute('key',value) -> model.addAttribute('key',value);
		
		Member m = service.selectOneMember(member);
		
		System.out.println(member);
		System.out.println(m);
				
		if( m != null) {
			session.setAttribute("m", m);
			model.addAttribute("msg","로그인 성공");
		}
		model.addAttribute("msg","로그인 실패");
		
		System.out.println("로그인 비지니스 로직 수행 후(컨트룰러 끝)");
		
		return "redirect:/";
//		return "common/msg";
	};
	
	@RequestMapping(value="/joinFrm.do")
	public String joinFrm() {
		return "member/joinFrm";
	};
	
	@RequestMapping(value="/join.do")
	public String join(Member member, Model model) {
		int result = service.insertMember(member);
		
		if(result > 0) {
			model.addAttribute("msg", "회원가입성공");
			model.addAttribute("loc", "/");
			
		}else {
			model.addAttribute("msg", "회원가입실패");
			model.addAttribute("loc", "/joinFrm.do");			
		}
		return "common/msg";
	};
	
	
	@RequestMapping(value="/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		//String return 시 redirect:컨트룰러매핑값 -> viewresolver가 앞뒤에 붙이는 기능 수행하지 않음
		return "redirect:/";
		
		// return "/";
		// "/WEB-INF/views//.jsp";
		
	};

	@RequestMapping(value="/mypage.do")
	public String mypage(String memberId, Model model) {
		Member m = service.mypage(memberId);
		
		model.addAttribute("m", m);
	
		return "member/mypage";
	}
	
	@RequestMapping(value="/updateMember.do")
	public String updateMember(Member m, Model model) {
		
		int result = service.updateMember(m);
		
		if(result > 0) {
			model.addAttribute("msg","정보 변경 성공");
		}else {
			model.addAttribute("msg","정보 변경 실패");
		}

		model.addAttribute("loc", "/mypage.do?memberId="+m.getMemberId());
		
		return "common/msg";
		
	}
	
	
	@RequestMapping(value="/allMember.do")
	public String allMember(Model model) {
		ArrayList<Member> list = service.selectAllMember();
		model.addAttribute("list",list);
		return "member/allMember";
		
	}
	
	@ResponseBody //페이지 이동이 아닌
	@RequestMapping(value="/idCheck.do")
	public String idCheck(String memberId) {
		Member m = service.selectOneMember(memberId);
		
		if(m == null) {
			return "1"; // /WEB-INF/views/1.jsp
		}else {
			return "0"; // /WEB-INF/views/0.jsp
		}
		
	}
	
	@RequestMapping(value="/allMemberAjax.do")
	public String allMemberAjax() {
		return "member/allMemberAjax";
	}
		
	@ResponseBody
	@RequestMapping(value="/ajaxAllMember.do",produces = "application/json;charset=utf-8")
	public String ajaxAllMember() {
		ArrayList<Member> list = service.selectAllMember();
		return new Gson().toJson(list);
	}
	
}
