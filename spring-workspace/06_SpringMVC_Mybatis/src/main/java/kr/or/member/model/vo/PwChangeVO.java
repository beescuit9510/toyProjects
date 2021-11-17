package kr.or.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PwChangeVO {
	private String memberId;
	private String oldPassword;
	private String newPassword;

}
