package com.shelter_project.infoBoard;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


/*
  CREATE TABLE comments (
    commentNo NUMBER PRIMARY KEY, 
    postNo NUMBER, 
    content CLOB NOT NULL,
    author VARCHAR2(100) NOT NULL, 
    createdDate date NOT NULL,
    updatedDate date, 
    parentNo NUMBER,
    animal_no number,
    CONSTRAINT FK_POST_INFO FOREIGN KEY (postNo) REFERENCES infoBoard(postNo),
    CONSTRAINT FK_POST_per FOREIGN KEY (animal_no) REFERENCES personal(animal_no)
);
	CREATE SEQUENCE comments_seq START WITH 1 INCREMENT BY 1 NOCACHE;

 */
public class CommentDTO {

	private int commentNo; // 댓글 no
	private int postNo; // 댓글이 달린 게시물의 no
	private String content; // 댓글 내용
	private String author; // 작성자
	private LocalDate createdDate; // 작성일자
	private LocalDate updatedDate; // 수정일자
	private int parentNo; // 부모 댓글 ID (대댓글 기능을 위한 필드, optional)
	private int orderNumber; //댓글 순서
	private int animal_no; // 동물 게시글 no
	
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDate getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}
	public int getParentNo() {
		return parentNo;
	}
	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getAnimal_no() {
		return animal_no;
	}
	public void setAnimal_no(int animal_no) {
		this.animal_no = animal_no;
	}
	
}
