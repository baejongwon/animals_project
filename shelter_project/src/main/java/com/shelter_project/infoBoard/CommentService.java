package com.shelter_project.infoBoard;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.shelter_project.center.CenterDTO;

import jakarta.servlet.http.HttpSession;

@Service
public class CommentService {

	@Autowired CommentMapper mapper;
	@Autowired HttpSession session; 

	public void addComment(int postNo, String content, String sessionID, int parentNo, String type) {
		CommentDTO comment = new CommentDTO();
		int orderNumber;
		
		LocalDate createdDate = LocalDate.now();
		LocalDate updatedDate = null;
		
		if(parentNo != 0) {
			orderNumber = mapper.getOrderNumber(parentNo);
		}else {
			orderNumber = mapper.getMaxOrderNumber(postNo,type) + 1;
		}
		comment.setPostNo(postNo);
		comment.setContent(content);
		comment.setAuthor(sessionID);
		comment.setCreatedDate(createdDate);
		comment.setUpdatedDate(updatedDate);
		comment.setParentNo(parentNo);
		comment.setOrderNumber(orderNumber);
		
		mapper.addComment(comment);
	}

	public List<CommentDTO> getComments(int postNo) {
		return mapper.getComments(postNo);
	}

	public int getCount(int postNo, String type) {
		return mapper.getCount(postNo,type);
	}

	public void deleteComment(int commentNo) {
		mapper.deleteComment(commentNo);
		
	}

	public void updateComment(int commentNo, String content) {
		mapper.updateComment(commentNo,content);
	}

	public void addPerComment(Integer animal_no, String content, String sessionID, int parentNo, String type) {
		CommentDTO comment = new CommentDTO();
		int orderNumber;
		
		LocalDate createdDate = LocalDate.now();
		LocalDate updatedDate = null;
		
		if(parentNo != 0) {
			orderNumber = mapper.getOrderNumber(parentNo);
		}else {
			orderNumber = mapper.getMaxOrderNumber(animal_no,type) + 1;
		}
		comment.setAnimal_no(animal_no);
		comment.setContent(content);
		comment.setAuthor(sessionID);
		comment.setCreatedDate(createdDate);
		comment.setUpdatedDate(updatedDate);
		comment.setParentNo(parentNo);
		comment.setOrderNumber(orderNumber);
		
		mapper.addPerComment(comment);
		
	}

	public List<CommentDTO> getPerComments(int animal_no) {
		return mapper.getPerComments(animal_no);
	}

}
