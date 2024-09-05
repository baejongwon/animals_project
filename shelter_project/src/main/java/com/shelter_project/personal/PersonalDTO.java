package com.shelter_project.personal;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/*
create table personal(
animal_no number PRIMARY key,
 nm varchar2(100),
 spcs varchar2(100),
 breeds varchar2(100),
 sexdstn varchar2(20),
 age varchar2(20),
 bdwgh varchar2(20),
 content clob,
 time date,
 author varchar2(100)
)
 );
  CREATE SEQUENCE animal_no_seq START WITH 1 INCREMENT BY 1 NOCACHE;
 */
public class PersonalDTO {
	private int animal_no; // 동물번호
	private String nm; // 이름
	private String spcs; // 종
	private String breeds; // 품종
	private String sexdstn; // 성별
	private String age; // 나이
	private String bdwgh; // 체중
	private String content; // 소개내용
	private LocalDate time; // 등록일
	private String author; //작성자
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public LocalDate getTime() {
		return time;
	}
	public void setTime(LocalDate time) {
		this.time = time;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

}
