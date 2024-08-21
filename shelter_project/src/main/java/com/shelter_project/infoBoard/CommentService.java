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

	public void addComment(int postNo, String content, String sessionID, int parentNo) {
		CommentDTO comment = new CommentDTO();
		int orderNumber;
		
		LocalDate createdDate = LocalDate.now();
		LocalDate updatedDate = null;
		
		if(parentNo != 0) {
			orderNumber = mapper.getOrderNumber(parentNo);
		}else {
			orderNumber = mapper.getMaxOrderNumber(postNo) + 1;
		}
		
		comment.setPostNo(postNo);
		comment.setContent(content);
		comment.setAuthor(sessionID);
		comment.setCreatedDate(createdDate);
		comment.setUpdatedDate(updatedDate);
		comment.setParentNo(parentNo);
		comment.setOrderNumber(orderNumber);
		
		System.out.println(parentNo);
		mapper.addComment(comment);
	}

	public List<CommentDTO> getComments(int postNo) {
		
		return mapper.getComments(postNo);
	}

	public int getCount(int postNo) {
		return mapper.getCount(postNo);
	}

}
