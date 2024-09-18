package com.shelter_project.likes;

import java.time.LocalDateTime;

/*
오라클 DB
CREATE TABLE likes (
   like_no NUMBER PRIMARY KEY,
   member_id VARCHAR(20),
   post_no NUMBER,
   post_type VARCHAR(20),
   CONSTRAINT fk_likes_member FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE,
   CONSTRAINT fk_likes_personal FOREIGN KEY (post_no) REFERENCES personal(post_no) ON DELETE CASCADE,
   CONSTRAINT fk_likes_infoboard FOREIGN KEY (post_no) REFERENCES infoboard(post_no) ON DELETE CASCADE,
   CONSTRAINT fk_likes_center FOREIGN KEY (post_no) REFERENCES center(post_no) ON DELETE CASCADE
);

CREATE SEQUENCE likes_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;


마리아 DB
CREATE TABLE likes (
   like_no INT PRIMARY KEY AUTO_INCREMENT,
   member_id VARCHAR(20),
   post_no INT,
   post_type VARCHAR(20),
   CONSTRAINT fk_likes_member FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE,
   CONSTRAINT fk_likes_personal FOREIGN KEY (post_no) REFERENCES personal(post_no) ON DELETE CASCADE,
   CONSTRAINT fk_likes_infoboard FOREIGN KEY (post_no) REFERENCES infoboard(post_no) ON DELETE CASCADE,
   CONSTRAINT fk_likes_center FOREIGN KEY (post_no) REFERENCES center(post_no) ON DELETE CASCADE
);
*/
public class LikeDTO {
	private int like_no; // 좋아요 게시글 id
	private String member_id; // 회원 ID
	private int post_no; // 좋아요를 누른 게시글 번호
	private String post_type; // 게시글 유형
	public int getLike_no() {
		return like_no;
	}
	public void setLike_no(int like_no) {
		this.like_no = like_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getPost_no() {
		return post_no;
	}
	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}
	public String getPost_type() {
		return post_type;
	}
	public void setPost_type(String post_type) {
		this.post_type = post_type;
	}
	
	
	
}
