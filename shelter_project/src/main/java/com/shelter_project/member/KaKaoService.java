package com.shelter_project.member;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

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
	
	//액세스 토큰을 저장할 변수
	private String accessToken;
	//액세스 토큰 가져오기
	public void getAccessToken(String code) {
		String requrl = "https://kauth.kakao.com/oauth/token";
		String reqParam = "grant_type=authorization_code";
		reqParam += "&client_id="+kakaoAPI;
		reqParam += "&redirect_uri="+redirectUri;
		reqParam += "&code="+code;
		
		/*서버에 데이터 전달*/
		
		try {
			URL url = new URL(requrl);//requrl 문자열을 URl객체로 변환 액세스 토큰을 요청하는 카카오 API의 엔드포인트를 가리킴
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//URL 객체를 사용해서 HTTP 연결을 연다
			
			
			conn.setRequestMethod("POST");//HTTP 연결 방식은 POST
			conn.setDoOutput(true);//이 연결에서 데이터를 전송할 수 있도록 출력 스트림을 열도록 설정 기본값은 false
			// 기본 outputStream을 통해 문자열로 처리할 수 있는
			// OutPutStreamWriter 변환 후 처리속도를 빠르게 하기위한
		    // BufferedWriter로 변환해서 사용한다.
		
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			/*
			 연결의 출력 스트림을 가져와 OutputStreamWriter로 감싼 뒤,
			 다시 BufferedWriter로 감싸서 데이터를 전송할 준비를 합니다.
			 이 설정은 데이터를 효율적으로 전송하기 위함입니다.
			 * */
			bw.write(reqParam);
			/*요청 파라미터(reqParam)를 BufferedWriter를 통해 전송합니다. reqParam에는 클라이언트 ID, 리다이렉트 URI, 인증 코드 등이 포함되어 있습니다.*/
			bw.flush();//데이터 보낸후 BufferedWriter초기화
			
			//요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			/*JSON 데이터를 파싱하기 위해 ObjectMapper 객체를 생성합니다.
			 Jackson 라이브러리의 ObjectMapper는 JSON 데이터를 Java 객체로 변환하는 데 사용됩니다.*/
			ObjectMapper om = new ObjectMapper();
			Map<String,String> map= om.readValue(isr, new TypeReference<Map<String,String>>() {});
			accessToken = map.get("access_token");
			
//			System.out.println("맵의 accessToken 값: "+map.get("access_token"));
//			System.out.println(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//사용자 정보 가져오기
	@Autowired private HttpSession session;
	public void getUserInfo() {
		String reqUrl = "https://kapi.kakao.com/v2/user/me";
		
	//	Authorization: Bearer ${ACCESS_TOKEN}
	
		try {
			URL url = new URL(reqUrl); // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);//띄어쓰기 조심
			
			int responseCode = conn.getResponseCode();//결과 코드가 200이라면 성공
//			System.out.println("responseCode:" + responseCode);
			
			ObjectMapper om = new ObjectMapper();
			
			JsonNode jsonNode = om.readTree(conn.getInputStream());
			
//			System.out.println(jsonNode.get("kakao_account"));
//			System.out.println(jsonNode.get("kakao_account").get("profile").get("nickname"));
			
			session.setAttribute("id",jsonNode.get("id"));
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//로그아웃
	public void unlink(String accessToken) {
		String reqUrl = "https://kapi.kakao.com/v1/user/unlink";
		
	//	Authorization: Bearer ${ACCESS_TOKEN}
	
		try {
			URL url = new URL(reqUrl); // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);//띄어쓰기 조심
			
			int responseCode = conn.getResponseCode();//결과 코드가 200이라면 성공
//			System.out.println("responseCode:" + responseCode);
			
			ObjectMapper om = new ObjectMapper();
			JsonNode jsonNode = om.readTree(conn.getInputStream());
//			System.out.println(jsonNode.get("id"));
						
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
