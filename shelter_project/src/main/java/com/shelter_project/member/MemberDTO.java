package com.shelter_project.member;

import java.time.LocalDateTime;




/*
 오라클 DB
 create table member(
 no number not null,
 name varchar2(20) not null,
 id varchar2(20) not null primary key,
 pw varchar2(100) not null,
 email varchar2(50) not null,
 tel varchar2(20) not null,
 address varchar2(100) not null);
 
 CREATE SEQUENCE member_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

*/
public class MemberDTO {

	private int no; // no
	private String name; // 이름
	private String id; // 아이디
	private String pw; // 비밀번호
	private String confirmPw; //비밀번호 확인
	private String email; // 이메일
	private String tel; // 전화번호
	private String address; // 주소
	private LocalDateTime regitstDt;// 가입일시
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getConfirmPw() {
		return confirmPw;
	}
	public void setConfirmPw(String confirmPw) {
		this.confirmPw = confirmPw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocalDateTime getRegitstDt() {
		return regitstDt;
	}
	public void setRegitstDt(LocalDateTime regitstDt) {
		this.regitstDt = regitstDt;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
}
