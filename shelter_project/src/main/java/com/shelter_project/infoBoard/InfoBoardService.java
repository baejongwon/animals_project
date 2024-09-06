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

import com.shelter_project.PageDTO;
import com.shelter_project.center.CenterDTO;

import jakarta.servlet.http.HttpSession;

@Service
public class InfoBoardService {

	
	@Autowired InfoBoardMapper mapper;
	@Autowired HttpSession session; 
	
	int pageLimit = 10; // 한 페이지당 보여줄 글 갯수
	int blockLimit = 5; // 하단에 보여줄 페이지 번호 갯수
	
	public PageDTO pagingParam(int page, String searchColumn, String keyword) {

		int boardCount;
		// 전체 글 갯수 조회
		if(searchColumn != null && keyword != null && keyword != "") {
			boardCount = mapper.getSearchCount(searchColumn, keyword);
		}else {
			boardCount = mapper.boardCount();
		}
      
      // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
      int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));
      // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
      int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
      // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
      int endPage = startPage + blockLimit - 1;
      if (endPage > maxPage) {
          endPage = maxPage;
      }
      PageDTO pageDTO = new PageDTO();
      pageDTO.setPage(page);
      pageDTO.setMaxPage(maxPage);
      pageDTO.setStartPage(startPage);
      pageDTO.setEndPage(endPage);
      return pageDTO;
	} 
	
	public ArrayList<InfoBoardDTO> getInfoBoards(int page) {
		
		int pagingStart = (page-1) * pageLimit;
		Map<String, Integer> pagingParams = new HashMap<>();
		pagingParams.put("start", pagingStart);
		pagingParams.put("limit", pageLimit);
		return mapper.getInfoBoards(pagingParams);
		
	}

	public int getBoardCount() {
		int count = mapper.boardCount();
		return count;
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
		
		mapper.insertBoard(board);
		
	}

//	public String saveImage(MultipartFile image) {
//		 if (image.isEmpty()) {
//	            return null;
//	        }
//		 	String sessionID = (String)session.getAttribute("id");
//	        String fileName = image.getOriginalFilename();
//	        String fileUrl = "C:\\Users\\jongwon\\git\\animals_project\\shelter_project\\src\\main\\resources\\static\\img\\ITS\\" + sessionID + "\\" + fileName;
//
//	        try {
//	            File dest = new File(fileUrl);
//	            if (!dest.getParentFile().exists()) {
//	                dest.getParentFile().mkdirs(); // 폴더가 없을 경우 생성
//	            }
//	            image.transferTo(dest);
//	            
//	            System.out.println(fileUrl);
//	            return "/img/ITS/"+ sessionID + "/" + fileName;
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	            return null;
//	        }
//	}

	public String saveImage(MultipartFile image) {
		 if (image.isEmpty()) {
	            return null;
	        }
		 	String sessionID = (String)session.getAttribute("id");
	        String fileName = image.getOriginalFilename();
	        String fileUrl = "C:\\Users\\jongwon\\git\\animals_project\\shelter_project\\src\\main\\resources\\static\\img\\ITS\\" + sessionID + "\\" + fileName;

	        try {
	            File dest = new File(fileUrl);
	            if (!dest.getParentFile().exists()) {
	                dest.getParentFile().mkdirs(); // 폴더가 없을 경우 생성
	            }
	            image.transferTo(dest);
	            
	            System.out.println(fileUrl);
	            return sessionID + "/" + fileName;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	}

	public InfoBoardDTO getContent(int postNo) {
		InfoBoardDTO board = mapper.getContent(postNo);
		
		 mapper.ViewCount(postNo);
		
		return board;
	}

	public void infoBoardModifyProc(MultipartHttpServletRequest multi, int postNo) {
		System.out.println("수정 서비스 호출");
		
		String sessionID = (String)session.getAttribute("id");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String category= multi.getParameter("category");
		
		System.out.println(sessionID);
		System.out.println(title);
		System.out.println(content);
		System.out.println(category);
		
		MultipartFile file = multi.getFile("image");
		
		InfoBoardDTO board = mapper.getContent(postNo);
		LocalDate updatedDate = LocalDate.now();
		
		board.setTitle(title);
		board.setContent(content);
		board.setAuthor(sessionID);
		board.setCategory(category);
		board.setUpdatedDate(updatedDate);
		
		mapper.updateBoard(board);
		
	}

	public void deleteBoard(int postNo) {
		mapper.deleteBoard(postNo);
	}

	public List<InfoBoardDTO> infoSearch(String searchColumn, String keyword, int page) {
		int pagingStart = (page-1) * pageLimit;
		Map<String, Object> pagingParams = new HashMap<>();
		pagingParams.put("start", pagingStart);
		pagingParams.put("limit", pageLimit);
		pagingParams.put("searchColumn", searchColumn);
		pagingParams.put("keyword", keyword);
		
		return mapper.infoSearch(pagingParams);
	}

	public int getSearchCount(String searchColumn, String keyword) {
		int count = mapper.getSearchCount(searchColumn,keyword);
		return count;
	}

}
