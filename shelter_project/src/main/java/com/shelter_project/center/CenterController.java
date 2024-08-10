package com.shelter_project.center;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;

@Controller
public class CenterController {
	
	@Value("${dataApiKey}")
	private String dataApiKey;
	@Value("${dataApiUrl}")
	private String dataApiUrl;
	
	
	@GetMapping("adoption")
	public String adoption(Model model) throws IOException {
		
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
		urlBuilder.append("/" +  URLEncoder.encode(dataApiKey,"UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
		urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
		urlBuilder.append("/" + URLEncoder.encode("TbAdpWaitAnimalView","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
		urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
		urlBuilder.append("/" + URLEncoder.encode("10","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
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
		
		ArrayList<CenterDTO> boards = new ArrayList<>();
		
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

			    System.out.println(animal_no);
			    System.out.println(nm);
			    System.out.println(entrnc_date);
			    
			    CenterDTO centerDTO  = new CenterDTO(animal_no, nm, entrnc_date, spcs, breeds, sexdstn, age, bdwgh, adp_sttus, tmpr_prtc_sttus, intrcn_mvp_url, intrcn_cn, tmpr_prtc_cn);			    
			    boards.add(centerDTO);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		model.addAttribute("boards",boards);
		
		return "center/adoption";
	}
}

	