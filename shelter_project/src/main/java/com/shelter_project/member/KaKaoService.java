package com.shelter_project.member;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;

@Service
public class KaKaoService {
	
	@Value("${kakaoApiKey}")
	private String kakaoAPI;
	@Value("${redirectUri}")
	private String redirectUri;
	
	@Autowired
	MemberMapper memberMapper;
	
	private String accessToken;
	
	public void getAccessToken(String code) {
		String requrl = "https://kauth.kakao.com/oauth/token";
		String reqParam = "grant_type=authorization_code";
		reqParam += "&client_id="+kakaoAPI;
		reqParam += "&redirect_uri="+redirectUri;
		reqParam += "&code="+code;
		
		try {
			URL url = new URL(requrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			bw.write(reqParam);
			bw.flush();
			
			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			
			ObjectMapper om = new ObjectMapper();
			Map<String,String> map= om.readValue(isr, new TypeReference<Map<String,String>>() {});
			accessToken = map.get("access_token");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//사용자 정보 가져오기
	@Autowired private HttpSession session;
	public void getUserInfo() {
		String reqUrl = "https://kapi.kakao.com/v2/user/me";
		
		try {
			URL url = new URL(reqUrl); // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);//띄어쓰기 조심
			
			int responseCode = conn.getResponseCode();//결과 코드가 200이라면 성공
			
			ObjectMapper om = new ObjectMapper();
			
			JsonNode jsonNode = om.readTree(conn.getInputStream());
			
			 String nickname = jsonNode.path("kakao_account")
                     .path("profile")
                     .path("nickname")
                     .asText();
			 
			  String email = jsonNode.path("kakao_account")
                      .path("email")
                      .asText();
			  long userIdLong = jsonNode.get("id").asLong();
			  String userID = String.valueOf(userIdLong);
			  
			session.setAttribute("id",userID);
			String login_type = "kakao";
			
			MemberDTO checkID = memberMapper.checkId(userID);
			
			if(checkID==null) {
				MemberDTO member = new MemberDTO();
				member.setEmail(email);
				member.setId(userID);
				member.setName(nickname);
				member.setLogin_type(login_type);
				memberMapper.kakaoRegist(member);	
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//로그아웃
	public void unlink(String accessToken) {
		String reqUrl = "https://kapi.kakao.com/v1/user/unlink";
	
		try {
			URL url = new URL(reqUrl); 
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);
			
			int responseCode = conn.getResponseCode();
			
			ObjectMapper om = new ObjectMapper();
			JsonNode jsonNode = om.readTree(conn.getInputStream());
						
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
