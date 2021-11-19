package kr.or.dm.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.dm.model.dao.DmDao;
import kr.or.dm.model.vo.DirectMessage;
import kr.or.member.model.dao.MemberDao;
import kr.or.member.model.vo.Member;

@Service
public class DmService {
	@Autowired
	private DmDao dao;
	private MemberDao mdao;

	public ArrayList<DirectMessage> selectDmList(String memberId) {
		return dao.selectDmList(memberId);
	}

	public int sendDm(DirectMessage dm) {
		return dao.insertDm(dm);
//		Member m = mdao.selecetOneMember(dm.getReceiver());
//		if(m != null) {
//		}
//		return -1;
	}

	public int dmCount(String memberId) {
		return dao.dmCount(memberId);
	}
}
