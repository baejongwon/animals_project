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
public class InfoBoardService {

	
	@Autowired InfoBoardMapper mapper;
	@Autowired HttpSession session; 
	
	int pageLimit = 12; // 한 페이지당 보여줄 글 갯수
	int blockLimit = 5; // 하단에 보여줄 페이지 번호 갯수
	
	public ArrayList<InfoBoardDTO> getInfoBoard(int page) {
		
		int pagingStart = (page-1) * pageLimit;
		Map<String, Integer> pagingParams = new HashMap<>();
		pagingParams.put("start", pagingStart);
		pagingParams.put("limit", pageLimit);
		return mapper.getInfoBoard(pagingParams);
		
	}

	public int getBoardCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void infoBoardWrite(MultipartHttpServletRequest multi) {
		
		String sessionID = (String)session.getAttribute("id");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String category= multi.getParameter("category");
		
		MultipartFile file = multi.getFile("image");
		
		
		
		String fileName = null;
		String fileUrl = null;
		
		InfoBoardDTO board = new InfoBoardDTO();
		LocalDate createdDate = LocalDate.now();
		
		board.setTitle(title);
		board.setContent(content);
		board.setAuthor(sessionID);
		board.setCategory(category);
		board.setCreatedDate(createdDate);
		board.setUpdatedDate(createdDate);
		
		mapper.insertBoard(board);
		
	}

	public String saveImage(MultipartFile image) {
		 if (image.isEmpty()) {
	            return null;
	        }
		 	
	        String fileName = image.getOriginalFilename();
	        String fileUrl = "C:\\Users\\hyo\\git\\animals_project\\shelter_project\\src\\main\\resources\\static\\img\\" + fileName;

	        try {
	            File dest = new File(fileUrl);
	            if (!dest.getParentFile().exists()) {
	                dest.getParentFile().mkdirs(); // 폴더가 없을 경우 생성
	            }
	            image.transferTo(dest);
	            
	            System.out.println(fileUrl);
	            return "/img/" + fileName;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	}

}
