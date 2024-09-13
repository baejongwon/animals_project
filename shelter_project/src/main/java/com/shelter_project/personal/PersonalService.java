package com.shelter_project.personal;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.shelter_project.infoBoard.InfoBoardDTO;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class PersonalService {

	@Autowired PersonalMapper mapper;
	@Autowired HttpSession session; 
	
	//s3에 이미지 추가
	private String bucketName = "dogcatgohome"; // S3 버킷 이름
	private String s3FilePath = "static/img/"; // S3에 업로드할 경로

	@Autowired
	private S3Client s3Client; // AWS S3 클라이언트 주입
	
	String basePath = "dogcatgohome/";

	int pageLimit = 12; // 한 페이지당 보여줄 글 갯수
	int blockLimit = 5; // 하단에 보여줄 페이지 번호 갯수
	
	public List<PersonalDTO> getBoards(int page, String type) {
		int pagingStart = (page-1) * pageLimit;
		Map<String, Object> pagingParams = new HashMap<>();
		pagingParams.put("start", pagingStart);
		pagingParams.put("limit", pageLimit);
		pagingParams.put("type",type);
		
		ArrayList<PersonalDTO> boards = mapper.PagingList(pagingParams);
		
		return boards;
	}
	
	public PageDTO pagingParam(int page, String type, String searchColumn, String keyword) {
		 // 전체 글 갯수 조회
        int boardCount;
        
        if(searchColumn != null && keyword != null && keyword != "") {
			boardCount = mapper.getSearchCount(searchColumn, keyword);
		}else {
			boardCount = mapper.boardCount(type);
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
	            String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
				String s3key = s3FilePath + sessionID + "/" + uniqueFileName;
				String contentType = image.getContentType(); 
				
		        try (InputStream fileInputStream = image.getInputStream()) {
		            // S3에 업로드할 파일의 크기
		            long fileSize = image.getSize();	           
		            
		            // S3에 업로드할 객체 생성
		            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
		                    .bucket(bucketName)
		                    .key(s3key)
		                    .contentType(contentType) 
		                    .contentDisposition("inline")
		                    .build();

		            // S3에 파일 업로드
		            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(fileInputStream, fileSize));
		            
		            String imageUrl = "https://" + bucketName + ".s3.amazonaws.com/" + s3key;

		            PersonalImagesDTO imagesDTO = new PersonalImagesDTO();
		            System.out.println("animalNo는??= "+ animalNo);
		            imagesDTO.setAnimal_no(animalNo);
		            imagesDTO.setImage_path(imageUrl);
		            
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
		List<MultipartFile> newImages = multi.getFiles("images");
		// 기존 이미지와 새로운 이미지 비교
		if(!newImages.isEmpty() && newImages.get(0).getSize()>0) { //이미지 있을 때만 실행
			
			List<String> newImageURls = new ArrayList<>();
			for(MultipartFile newImage : newImages) {
				if(!newImage.isEmpty()) {
					String fileName = newImage.getOriginalFilename();
	                String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
					String s3key = s3FilePath + sessionID + "/" + uniqueFileName;
					String contentType = newImage.getContentType(); 
					
					
					//s3에서 파일 존재 확인
					boolean fileExistsInS3 = S3ImgExist(bucketName, s3key);
					
					if(fileExistsInS3) {
						for(String getImg : getImgs) {
							mapper.deleteImage(getImg,animal_no);
						}
						deleteS3Object(bucketName, s3key);
					}
				
					try(InputStream fileInputStream = newImage.getInputStream()){
						long fileSize = newImage.getSize();	           
						 PutObjectRequest putObjectRequest = PutObjectRequest.builder()
		                            .bucket(bucketName)
		                            .key(s3key)
		                            .contentType(contentType)
		                            .contentDisposition("inline")
		                            .build();
						 s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(fileInputStream, fileSize));
						 String newimageUrl = "https://" + bucketName + ".s3.amazonaws.com/" + s3key;
						 newImageURls.add(newimageUrl);
						 
						 PersonalImagesDTO imagesDTO = new PersonalImagesDTO();
				         imagesDTO.setAnimal_no(animal_no);
				         imagesDTO.setImage_path(newimageUrl);
				         mapper.insertPersonalImages(imagesDTO);
				          
					}catch (IOException e) {
	                    e.printStackTrace();
	                }
				}
			}
			//새 이미지 목록에 없는 기존 이미지는 삭제 처리
			for(String getImg : getImgs) {
				if(!newImageURls.contains(getImg)) {
					mapper.deleteImage(getImg,animal_no);
					String s3Delete = getImg.replace("https://" + bucketName + ".s3.amazonaws.com/", "");
					deleteS3Object(bucketName, s3Delete); // S3에서 이미지 삭제
				}
			}
		}	
	}

	// 이미지 존재 여부 확인 
	private boolean S3ImgExist(String bucketName, String s3key) {
	    try {
	        s3Client.headObject(HeadObjectRequest.builder().bucket(bucketName).key(s3key).build());
	        return true; // 객체가 존재함
	    } catch (NoSuchKeyException e) {
	        return false; // 객체가 존재하지 않음
	    }
	}
	// 이미지 삭제
	private void deleteS3Object(String bucketName, String s3key) {
	    s3Client.deleteObject(DeleteObjectRequest.builder().bucket(bucketName).key(s3key).build());
	}
	
	public List<Integer> getImageNo(int animal_no) {
		return mapper.getImageNo(animal_no);
	}

	public void personalDelete(int animal_no) {
		List<String> getImgs = mapper.getImg(animal_no);
		for(String getImg : getImgs) {
				String s3Delete = getImg.replace("https://" + bucketName + ".s3.amazonaws.com/", "");
				deleteS3Object(bucketName, s3Delete); // S3에서 이미지 삭제	
		}
		mapper.personalDelete(animal_no);
	}

	public List<PersonalDTO> getMainContent() {
		return mapper.getMainContent();
	}

	public List<PersonalDTO> perSearch(String searchColumn, String keyword, int page) {
		int pagingStart = (page-1) * pageLimit;
		Map<String, Object> pagingParams = new HashMap<>();
		pagingParams.put("start", pagingStart);
		pagingParams.put("limit", pageLimit);
		pagingParams.put("searchColumn", searchColumn);
		pagingParams.put("keyword", keyword);
		
		return mapper.perSearch(pagingParams);
	}

	public int getSearchCount(String searchColumn, String keyword) {
		int count = mapper.getSearchCount(searchColumn,keyword);
		return count;
	}

	public String saveImage(MultipartFile image) {
		 if (image.isEmpty()) {
	            return null;
	        }
		 	String sessionID = (String)session.getAttribute("id");
		 	String fileName = image.getOriginalFilename();
            String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
			String s3key = s3FilePath + sessionID + "/" + uniqueFileName;
			String contentType = image.getContentType(); 
			
		    try (InputStream fileInputStream = image.getInputStream()) {
	            // S3에 업로드할 파일의 크기
	            long fileSize = image.getSize();	           
	            
	            // S3에 업로드할 객체 생성
	            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
	                    .bucket(bucketName)
	                    .key(s3key)
	                    .contentType(contentType) 
	                    .contentDisposition("inline")
	                    .build();

	            // S3에 파일 업로드
	            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(fileInputStream, fileSize));
	            
	            String imageUrl = "https://" + bucketName + ".s3.amazonaws.com/" + s3key;

	            return imageUrl;
	            
	        }catch (IOException e) {
	            e.printStackTrace();
	        }
			return null;
		
	}
}
