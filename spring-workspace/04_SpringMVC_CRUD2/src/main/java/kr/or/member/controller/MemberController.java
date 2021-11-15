package kr.or.member.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
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
	@Autowired
	private MemberService service;
	
	public MemberController() {
	}
	
	@RequestMapping(value="/login.do")
	public String login(Member member, HttpSession session) {
		Member m = service.selectOneMember(member);
		
		session.setAttribute("m", m);
		
		return "redirect:/";
	};
	
	@ResponseBody
	@RequestMapping(value="/ajaxAllMember.do", produces = "application/json;charset=utf-8")
	public String allMemberAjax() {
		ArrayList<Member> list = service.allMemberAjax();
		
		return new Gson().toJson(list);
	}
	
	@ResponseBody
	@RequestMapping(value="/idCheck.do")
	public String idCheck(String memberId) {
	
		Member m = service.idCheck(memberId);
		
		if(m != null) {
			return "1";			
		}else {
			return "0";
		}
		
		
	}
	
	@RequestMapping(value="/join.do")
	public String insertMember(Member member, Model model) {
		
		int result = service.insertMember(member);
		
		if(result > 0) {
			model.addAttribute("msg","가입 성공");
		}else {
			model.addAttribute("msg","가입 실패");			
		}
		
		model.addAttribute("loc","/");			
		
		return "common/msg";
	}
	
	@RequestMapping(value="/mypage.do")
	public String mypage(String memberId, HttpServletRequest request,Model model) {
		Member m = service.mypage(memberId);
		request.getSession().setAttribute("m", m);
		model.addAttribute("m", m);
		
		return "member/mypage";
	}
	@RequestMapping(value="allMemberAjax.do")
	public String allMemberAjax(String memberId, Model model) {
		return "member/allMemberAjax";
	}
	@RequestMapping(value="joinFrm.do")
	public String joinFrm(String memberId, Model model) {
		return "member/joinFrm";
	}
	@RequestMapping(value="/updateMember.do")
	public String updateMember(Member member, Model model) {
	
		int result = service.updateMember(member);
		
		if(result > 0) {
			model.addAttribute("msg","수정 성공");
		}else {
			model.addAttribute("msg","수정 실패");			
		}
		
		model.addAttribute("loc","/mypage.do");			
		
		return "common/msg";
	}
	
	
	@RequestMapping(value="/logout.do")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

	@RequestMapping(value="/deleteMember.do")
	public String deleteMember(String memberId, Model model, HttpServletRequest request) {
		System.out.println(memberId);
		
		int result = service.deleteMember(memberId);

		if(result > 0) {
			model.addAttribute("msg","탈퇴 성공");
			request.getSession().invalidate();
		}else {
			model.addAttribute("msg","탈퇴 실패");			
		}
		
		model.addAttribute("loc","/");			
		
		return "common/msg";
	}
	
	

}
