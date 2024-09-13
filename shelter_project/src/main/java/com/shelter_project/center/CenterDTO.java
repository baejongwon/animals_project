package com.shelter_project.center;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/*
 오라클
 create table center(
 animal_no number not null,
 nm varchar2(100),
 entrnc_date varchar2(20),
 spcs varchar2(100),
 breeds varchar2(100),
 sexdstn varchar2(20),
 age varchar2(25),
 bdwgh varchar2(20),
 adp_sttus varchar2(20),
 tmpr_prtc_sttus varchar2(20),
 intrcn_mvp_url varchar2(200),
 intrcn_cn clob,
 tmpr_prtc_cn clob
 );
 
 ALTER TABLE center
 ADD CONSTRAINT pk_center_animal_no PRIMARY KEY (animal_no);
 
 마리아
 CREATE TABLE center (
  animal_no INT NOT NULL,
  nm VARCHAR(100),
  entrnc_date VARCHAR(20),
  spcs VARCHAR(100),
  breeds VARCHAR(100),
  sexdstn VARCHAR(20),
  age VARCHAR(25),
  bdwgh VARCHAR(20),
  adp_sttus VARCHAR(20),
  tmpr_prtc_sttus VARCHAR(20),
  intrcn_mvp_url VARCHAR(200),
  intrcn_cn LONGTEXT,
  tmpr_prtc_cn LONGTEXT,
  PRIMARY KEY (animal_no)
);

 */
public class CenterDTO {
	private int animal_no; // 동물번호
	private String nm; // 이름
	private String entrnc_date; // 입소날짜
	private String spcs; // 종
	private String breeds; // 품종
	private String sexdstn; // 성별
	private String age; // 나이
	private String bdwgh; // 체중
	private String adp_sttus; // 입양상태
	private String tmpr_prtc_sttus; // 임시보호상태
	private String intrcn_mvp_url; // 소개 동영상 url
	private String intrcn_cn; // 소개내용
	private String tmpr_prtc_cn; // 임시보호 내용

	public CenterDTO(int animal_no, String nm, String entrnc_date, String spcs, String breeds, String sexdstn,
			String age, String bdwgh, String adp_sttus, String tmpr_prtc_sttus, String intrcn_mvp_url, String intrcn_cn,
			String tmpr_prtc_cn) {
		this.animal_no = animal_no;
		this.nm = nm;
		this.entrnc_date = entrnc_date;
		this.spcs = spcs;
		this.breeds = breeds;
		this.sexdstn = sexdstn;
		this.age = age;
		this.bdwgh = bdwgh;
		this.adp_sttus = adp_sttus;
		this.tmpr_prtc_sttus = tmpr_prtc_sttus;
		this.intrcn_mvp_url = intrcn_mvp_url;
		this.intrcn_cn = intrcn_cn;
		this.tmpr_prtc_cn = tmpr_prtc_cn;
	}

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

	public String getEntrnc_date() {
		return entrnc_date;
	}

	public void setEntrnc_date(String entrnc_date) {
		this.entrnc_date = entrnc_date;
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

	public String getTmpr_prtc_sttus() {
		return tmpr_prtc_sttus;
	}

	public void setTmpr_prtc_sttus(String tmpr_prtc_sttus) {
		this.tmpr_prtc_sttus = tmpr_prtc_sttus;
	}

	public String getIntrcn_mvp_url() {
		return intrcn_mvp_url;
	}

	public void setIntrcn_mvp_url(String intrcn_mvp_url) {
		this.intrcn_mvp_url = intrcn_mvp_url;
	}

	public String getIntrcn_cn() {
		return intrcn_cn;
	}

	public void setIntrcn_cn(String intrcn_cn) {
		this.intrcn_cn = intrcn_cn;
	}

	public String getTmpr_prtc_cn() {
		return tmpr_prtc_cn;
	}

	public void setTmpr_prtc_cn(String tmpr_prtc_cn) {
		this.tmpr_prtc_cn = tmpr_prtc_cn;
	}

}
