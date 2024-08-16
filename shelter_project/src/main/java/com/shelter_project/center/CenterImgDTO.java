package com.shelter_project.center;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/*
CREATE TABLE center_img (
    animal_no NUMBER NOT NULL,          
    photo_knd VARCHAR2(50) NOT NULL,    
    photo_no NUMBER NOT NULL,           
    photo_url VARCHAR2(500),            
    CONSTRAINT pk_center_img PRIMARY KEY (animal_no, photo_no), 
    CONSTRAINT fk_center_animal_no FOREIGN KEY (animal_no) REFERENCES center(animal_no)
);
 */
public class CenterImgDTO {
	private int animal_no; // 동물번호
	private String photoKnd; // 사진종류
	private int photoNo; // 사진번호
	private String photoUrl; // 사진URL
	
	
	public CenterImgDTO(int animal_no, String photoKnd, int photoNo, String photoUrl) {
		this.animal_no = animal_no;
		this.photoKnd = photoKnd;
		this.photoNo = photoNo;
		this.photoUrl = photoUrl;
	}
	public int getAnimalNo() {
		return animal_no;
	}
	public void setAnimalNo(int animalNo) {
		this.animal_no = animalNo;
	}
	public String getPhotoKnd() {
		return photoKnd;
	}
	public void setPhotoKnd(String photoKnd) {
		this.photoKnd = photoKnd;
	}
	public int getPhotoNo() {
		return photoNo;
	}
	public void setPhotoNo(int photoNo) {
		this.photoNo = photoNo;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	
}
