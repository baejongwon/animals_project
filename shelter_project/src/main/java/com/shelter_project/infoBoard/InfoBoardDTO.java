package com.shelter_project.infoBoard;

import java.time.LocalDateTime;
import java.util.List;

public class InfoBoardDTO {

	private int postNo; // 게시물 ND (고유 식별자)
	private String title; // 게시물 제목
	private String content; // 게시물 내용
	private String author; // 작성자
	private String category; //카테고리
	private LocalDateTime createdDate; // 작성일자
	private LocalDateTime updatedDate; // 수정일자
	private int viewCount; // 조회수
	private List<CommentDTO> comments; // 댓글 목록
	private List<String> tags; // 태그 목록
	private String fileName;  // 원본 파일 이름
    private String fileUrl;  // 파일 저장 경로 또는 URL
    
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
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
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
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
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
