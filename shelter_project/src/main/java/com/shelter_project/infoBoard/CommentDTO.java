package com.shelter_project.infoBoard;

import java.time.LocalDateTime;
import java.util.List;

public class CommentDTO {

	private int commentNo; // 댓글 ID
	private int postNo; // 댓글이 달린 게시물의 ID
	private String content; // 댓글 내용
	private String author; // 작성자
	private LocalDateTime createdDate; // 작성일자
	private LocalDateTime updatedDate; // 수정일자
	private int parentNo; // 부모 댓글 ID (대댓글 기능을 위한 필드, optional)
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
	public int getParentNo() {
		return parentNo;
	}
	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}
	
}
