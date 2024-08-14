package com.shelter_project.center;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.shelter_project.PageDTO;

@Service
public class CenterService {

	@Autowired CenterMapper mapper;
	
	@Value("${dataApiKey}")
	private String dataApiKey;
	
	int pageLimit = 12; // 한 페이지당 보여줄 글 갯수
	int blockLimit = 5; // 하단에 보여줄 페이지 번호 갯수
	
	public List<CenterDTO> getAdoptionData(int page) throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
		urlBuilder.append("/" +  URLEncoder.encode(dataApiKey,"UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
		urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
		urlBuilder.append("/" + URLEncoder.encode("TbAdpWaitAnimalView","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
		urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
		urlBuilder.append("/" + URLEncoder.encode("1000","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
		// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
		
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
		BufferedReader rd;

		// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder(); //응답데이터를 하나의 문자열로 결합하기위해 선언
		String line;
		while ((line = rd.readLine()) != null) {
				sb.append(line);
		}
		rd.close();
		conn.disconnect();
				
		String jsonResponse = sb.toString();
		
		
		/*
//		 * 1페이지 
//		 * 1 => 1~12
//		 * 2 => 13~24
//		 * 3 => 25~36
//		 * 4 => 37~48
//		 * */

		int pagingStart = (page-1) * pageLimit;
		Map<String, Integer> pagingParams = new HashMap<>();
		pagingParams.put("start", pagingStart);
		pagingParams.put("limit", pageLimit);
		ArrayList<CenterDTO> boards = mapper.pagingList(pagingParams);
		
		
		// JSON 파싱
		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse);
			JSONObject tbAdpWaitAnimalView = (JSONObject) jsonObject.get("TbAdpWaitAnimalView");
			JSONArray animalArray = (JSONArray) tbAdpWaitAnimalView.get("row");

			for (Object obj : animalArray) {
			    JSONObject animal = (JSONObject) obj;
			    int animal_no = Integer.parseInt((String) animal.get("ANIMAL_NO")); // 동물번호
			    String nm = (String) animal.get("NM"); // 이름
			    String entrnc_date = (String) animal.get("ENTRNC_DATE"); // 입소날짜
			    String spcs = (String) animal.get("SPCS"); // 종
			    String breeds = (String) animal.get("BREEDS"); // 품종
			    String sexdstn = (String) animal.get("SEXDSTN"); // 성별
			    String age = (String) animal.get("AGE"); // 나이
			    String bdwgh = animal.get("BDWGH").toString(); // 체중
			    String adp_sttus = (String) animal.get("ADP_STTUS"); // 입양상태
			    String tmpr_prtc_sttus = (String) animal.get("TMPR_PRTC_STTUS"); // 임시보호상태
			    String intrcn_mvp_url = (String) animal.get("INTRCN_MVP_URL"); // 동영상URL
			    String intrcn_cn = (String) animal.get("INTRCN_CN"); // 소개내용
			    String tmpr_prtc_cn = (String) animal.get("TMPR_PRTC_CN"); // 임시보호내용
		    
			    CenterDTO centerDTO  = new CenterDTO(animal_no, nm, entrnc_date, spcs, breeds, sexdstn, age, bdwgh, adp_sttus, tmpr_prtc_sttus, intrcn_mvp_url, intrcn_cn, tmpr_prtc_cn);
			    
			    //데이터 베이스 존재확인
			    CenterDTO check = mapper.checkData(animal_no);
			    if(check == null) {
			    	mapper.insertData(centerDTO);	
			    }else {
			    	mapper.updateData(centerDTO);
			    }
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return boards;
	}


	public CenterDTO adoptionDetail(int no) {
		CenterDTO board = mapper.checkData(no);
		return board;
	}

	public void getAdoptionImgData() throws Exception {
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
		urlBuilder.append("/" +  URLEncoder.encode(dataApiKey,"UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
		urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
		urlBuilder.append("/" + URLEncoder.encode("TbAdpWaitAnimalPhotoView","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
		urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
		urlBuilder.append("/" + URLEncoder.encode("1000","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
		// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
		
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
		BufferedReader rd;

		// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder(); //응답데이터를 하나의 문자열로 결합하기위해 선언
		String line;
		while ((line = rd.readLine()) != null) {
				sb.append(line);
		}
		rd.close();
		conn.disconnect();
				
		String jsonResponse = sb.toString();
		
		ArrayList<CenterImgDTO> boards = new ArrayList<>();
		
		
		// JSON 파싱
		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse);
			JSONObject TbAdpWaitAnimalPhotoView = (JSONObject) jsonObject.get("TbAdpWaitAnimalPhotoView");
			JSONArray animalArray = (JSONArray) TbAdpWaitAnimalPhotoView.get("row");

			for (Object obj : animalArray) {
			    JSONObject animal = (JSONObject) obj;
			    int animal_no = Integer.parseInt((String) animal.get("ANIMAL_NO")); // 동물번호
			    String photoKnd = (String) animal.get("PHOTO_KND"); // 사진 종류
			    int photoNo = ((Number) animal.get("PHOTO_NO")).intValue();// 사진 번호
			    String photoUrl = (String) animal.get("PHOTO_URL"); // 사진 URL
			    
			    CenterImgDTO centerImgDTO  = new CenterImgDTO(animal_no, photoKnd, photoNo, photoUrl);
			    
			    //데이터 베이스 존재확인
			    CenterImgDTO check = mapper.checkImgData(animal_no,photoNo);
			    if(check == null) {
			    	mapper.insertImgData(centerImgDTO);	
			    }else {
			    	mapper.updateImgData(centerImgDTO);
			    }
			    boards.add(centerImgDTO);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> animalImg(int animal_no) {
		return mapper.getImg(animal_no);
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

}
