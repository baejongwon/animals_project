package com.shelter_project.member;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

	MemberDTO checkId(String id);

	int registPro(MemberDTO member);

	int updatePro_local(MemberDTO member);

	void kakaoRegist(MemberDTO member);

	int updatePro_kakao(MemberDTO member);

	String findID(MemberDTO member);

	String findPw(MemberDTO member);

	int updatePw(String userID, String encodePw);

	MemberDTO checkTel(String tel);

}
