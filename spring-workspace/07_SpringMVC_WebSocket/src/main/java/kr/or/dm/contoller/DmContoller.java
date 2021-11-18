package kr.or.dm.contoller;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.dm.model.service.DmService;
import kr.or.dm.model.vo.DirectMessage;
import kr.or.member.model.vo.Member;

@Controller
public class DmContoller {
	@Autowired
	private DmService service;
	
	@RequestMapping(value="/dmList.do")
	public String dmList(@SessionAttribute Member m, Model model) {
//		Member m = (Member)session.getAttribute("m");
		//보낸 쪽지/받은 쪽지 
		ArrayList<DirectMessage> list = service.selectDmList(m.getMemberId());
		model.addAttribute("list",list);
		Stream.of(list).forEach(System.out::println);
		System.out.println(list.size());
		return "dm/dmList";
	}

}
