package org.kh.member.model.vo;

public class MemberService {
	
	public MemberVO insertMember(int userNo) {
		SqlSessionTemplate tem = new SqlSessionTemplate();
		return tem.insertMember(userNo);
	}
}
