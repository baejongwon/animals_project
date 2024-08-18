package com.shelter_project.infoBoard;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/*
 create table infoBoard(
	postNo number PRIMARY KEY,
	title VARCHAR2(255) not null,
	content clob not null,
	author VARCHAR2(100) NOT NULL,  
    category VARCHAR2(100) NOT NULL, 
    createdDate TIMESTAMP NOT NULL,  
    updatedDate TIMESTAMP,  
    likeCount NUMBER DEFAULT 0, 
    viewCount NUMBER DEFAULT 0,
    fileName VARCHAR2(255),
    fileUrl VARCHAR2(1000),
    comments VARCHAR2(255)
    )
    CREATE SEQUENCE postNo_seq START WITH 1 INCREMENT BY 1 NOCACHE;
 * */

public class InfoBoardDTO {

	private int postNo; // 게시물 번호
	private String title; // 게시물 제목
	private String content; // 게시물 내용
	private String author; // 작성자
	private String category; // 카테고리
	private LocalDate createdDate; // 작성일자
	private LocalDate updatedDate; // 수정일자
	private int likeCount; // 좋아요 수
	private int viewCount; // 조회수
	private List<CommentDTO> comments; // 댓글 목록
	private String fileName; // 원본 파일 이름
	private String fileUrl; // 파일 저장 경로 또는 URL
	
	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public List<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}


}
