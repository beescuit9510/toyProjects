package kr.or.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private String memberId;
	private String memberPw;
	private String memberName;
	private String address;
	private String enrollDate;

}
