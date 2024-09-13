package com.shelter_project.personal;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/*
create table personal_images(
image_no number PRIMARY key,
animal_no number,
image_path VARCHAR2(1000),
FOREIGN key (animal_no) REFERENCES personal(animal_no) on delete CASCADE);
CREATE SEQUENCE image_no_seq START WITH 1 INCREMENT BY 1 NOCACHE;

마리아db
CREATE TABLE personal_images (
  image_no INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  animal_no INT,
  image_path VARCHAR(1000),
  FOREIGN KEY (animal_no) REFERENCES personal(animal_no) ON DELETE CASCADE
);


 */
public class PersonalImagesDTO {
	private int image_no;
	private int animal_no; // 외래키로 personal 테이블의 animal_no와 연결
	private String image_path;
	
	public int getImage_no() {
		return image_no;
	}
	public void setImage_no(int image_no) {
		this.image_no = image_no;
	}
	public int getAnimal_no() {
		return animal_no;
	}
	public void setAnimal_no(int animal_no) {
		this.animal_no = animal_no;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	
	
}
