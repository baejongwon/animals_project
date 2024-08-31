package com.shelter_project.personal;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/*
create table personal(
animal_no number not null,
 nm varchar2(100),
 spcs varchar2(100),
 breeds varchar2(100),
 sexdstn varchar2(20),
 age varchar2(20),
 bdwgh varchar2(20),
 adp_sttus varchar2(20),
 content clob,
 image varchar2(100),
 time varchar2(20)
)
 );
 */
public class PersonalDTO {
	private int animal_no; // 동물번호
	private String nm; // 이름
	private String spcs; // 종
	private String breeds; // 품종
	private String sexdstn; // 성별
	private String age; // 나이
	private String bdwgh; // 체중
	private String adp_sttus; // 입양상태
	private String content; // 소개내용
	private String image; // 이미지
	private String time; // 등록일
	
	public int getAnimal_no() {
		return animal_no;
	}
	public void setAnimal_no(int animal_no) {
		this.animal_no = animal_no;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getSpcs() {
		return spcs;
	}
	public void setSpcs(String spcs) {
		this.spcs = spcs;
	}
	public String getBreeds() {
		return breeds;
	}
	public void setBreeds(String breeds) {
		this.breeds = breeds;
	}
	public String getSexdstn() {
		return sexdstn;
	}
	public void setSexdstn(String sexdstn) {
		this.sexdstn = sexdstn;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getBdwgh() {
		return bdwgh;
	}
	public void setBdwgh(String bdwgh) {
		this.bdwgh = bdwgh;
	}
	public String getAdp_sttus() {
		return adp_sttus;
	}
	public void setAdp_sttus(String adp_sttus) {
		this.adp_sttus = adp_sttus;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

}
