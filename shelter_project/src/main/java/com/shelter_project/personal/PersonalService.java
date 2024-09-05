package com.shelter_project.personal;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.shelter_project.PageDTO;
import com.shelter_project.center.CenterDTO;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Service
public class PersonalService {

	@Autowired PersonalMapper mapper;
	@Autowired HttpSession session; 

	int pageLimit = 12; // 한 페이지당 보여줄 글 갯수
	int blockLimit = 5; // 하단에 보여줄 페이지 번호 갯수
	String basePath = "C:\\Users\\jongwon\\git\\animals_project\\shelter_project\\src\\main\\resources\\static\\img\\ITS\\";
	public List<PersonalDTO> getBoards(int page) {
		int pagingStart = (page-1) * pageLimit;
		Map<String, Integer> pagingParams = new HashMap<>();
		pagingParams.put("start", pagingStart);
		pagingParams.put("limit", pageLimit);
		
		ArrayList<PersonalDTO> boards = mapper.PagingList(pagingParams);
		
		return boards;
	}
	
	public PageDTO pagingParam(int page) {
		 // 전체 글 갯수 조회
        int boardCount = mapper.boardCount();
        
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

	public void personalWriteProc(MultipartHttpServletRequest multi) {
		String sessionID = (String)session.getAttribute("id");
		String nm = multi.getParameter("nm");
		String spcs = multi.getParameter("spcs");
		String breeds = multi.getParameter("breeds");
		String sexdstn = multi.getParameter("sexdstn");
		String age = multi.getParameter("age");
		String bdwgh = multi.getParameter("bdwgh");
		LocalDate time = LocalDate.now();
		String content = multi.getParameter("content");
		
		PersonalDTO personalDTO = new PersonalDTO();
		
		personalDTO.setAuthor(sessionID);
		personalDTO.setNm(nm);
		personalDTO.setSpcs(spcs);
		personalDTO.setBreeds(breeds);
		personalDTO.setSexdstn(sexdstn);
		personalDTO.setAge(age);
		personalDTO.setBdwgh(bdwgh);
		personalDTO.setContent(content);
		personalDTO.setTime(time);
		
		mapper.personalWriteProc(personalDTO);
		
		int animalNo = personalDTO.getAnimal_no();
		List<MultipartFile> images = multi.getFiles("images");
		
		for(MultipartFile image : images) {
			if(!image.isEmpty()) {
				String fileName = image.getOriginalFilename();
				String path = basePath +sessionID + "\\" + fileName;
				
				
				try {
					File dest = new File(path);
					if(!dest.getParentFile().exists()) {
						dest.getParentFile().mkdir();
					}
					image.transferTo(dest); //이미지 폴더에 저장
					PersonalImagesDTO imagesDTO = new PersonalImagesDTO();
					imagesDTO.setAnimal_no(animalNo);
					imagesDTO.setImage_path(path);

					mapper.insertPersonalImages(imagesDTO);
					
				}catch (IOException e) {
	                e.printStackTrace();
	            }
			}
		}
	}

	public PersonalDTO personalContent(int no) {
		return mapper.personalContent(no);
	}

	public List<String> animalImg(int animal_no) {
		return mapper.getImg(animal_no);
	}

	public PersonalDTO personalModify(int animal_no) {
		return mapper.personalContent(animal_no);
	}

	public void personalModifyProc(MultipartHttpServletRequest multi, int animal_no) {
		
		String sessionID = (String)session.getAttribute("id");
		String nm = multi.getParameter("nm");
		String spcs = multi.getParameter("spcs");
		String breeds = multi.getParameter("breeds");
		String sexdstn = multi.getParameter("sexdstn");
		String age = multi.getParameter("age");
		String bdwgh = multi.getParameter("bdwgh");
		LocalDate time = LocalDate.now();
		String content = multi.getParameter("content");
		
		PersonalDTO personalDTO = new PersonalDTO();
		
		personalDTO.setAuthor(sessionID);
		personalDTO.setNm(nm);
		personalDTO.setSpcs(spcs);
		personalDTO.setBreeds(breeds);
		personalDTO.setSexdstn(sexdstn);
		personalDTO.setAge(age);
		personalDTO.setBdwgh(bdwgh);
		personalDTO.setContent(content);
		personalDTO.setTime(time);
		personalDTO.setAnimal_no(animal_no);
		// 새로운 정보로 업데이트
		mapper.personalModifyProc(personalDTO);
		
		// 기존 이미지 가져오기
		List<String> getImgs = mapper.getImg(animal_no);
		// 새로 업로드 된 이미지
		List<MultipartFile> images = multi.getFiles("images");
		// 기존 이미지와 새로운 이미지 비교
		if(!images.isEmpty() && images.get(0).getSize()>0) { //이미지 첨부 x 일떄 실행 x
			
			List<String> newImage = new ArrayList<>();
			for(MultipartFile image : images) {
				if(!image.isEmpty()) {
					String fileName = image.getOriginalFilename();
					String path = basePath +sessionID + "\\" + fileName;
					newImage.add(path);
				}
			}
			
			for(String getImg : getImgs) {
				if(!newImage.contains(getImg)) {
					mapper.deleteImage(getImg,animal_no);
					File fileDelete = new File(getImg);
						if(fileDelete.exists()) {
							fileDelete.delete();
						}
				}
			}
		}
		// 새로운 이미지 추가 및 저장
		for(MultipartFile image : images) {
			if(!image.isEmpty()) {
				String fileName = image.getOriginalFilename();
				String path = basePath +sessionID + "\\" + fileName;
				
				if(!getImgs.contains(path)) {
					try {
						File dest = new File(path);
						if(!dest.getParentFile().exists()) {
							dest.getParentFile().mkdir();
						}
						image.transferTo(dest); //이미지 폴더에 저장
						PersonalImagesDTO imagesDTO = new PersonalImagesDTO();
						imagesDTO.setAnimal_no(animal_no);
						imagesDTO.setImage_path(path);
						
						mapper.insertPersonalImages(imagesDTO);
						
					}catch (IOException e) {
		                e.printStackTrace();
		            }
				}
			}
		}	
	}

	public List<Integer> getImageNo(int animal_no) {
		return mapper.getImageNo(animal_no);
	}

	public void personalDelete(int animal_no) {
		mapper.personalDelete(animal_no);
	}
}
