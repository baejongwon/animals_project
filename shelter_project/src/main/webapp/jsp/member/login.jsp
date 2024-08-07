<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<link href="member.css" rel="stylesheet">

<div class="login_bar">
	<div class="login-container">
		<div class="login-box">
			<h2>LOG IN</h2>
			<form action="loginProc" method="post" id="f">
				<div class="input-group">
					<label for="id">아이디</label> <input type="text" id="id" name="id"
						required>
				</div>
				<div class="input-group">
					<label for="pw">비밀번호</label> <input type="password" id="pw"
						name="pw" required>
				</div>
				<div class="forgot-password">
					<a href="#">아이디를 잊으셨나요?</a><br> <a href="#">비밀번호를 잊으셨나요?</a><br>
					<a href="regist" class="signup-link">회원가입</a>
				</div>

				<button type="submit" class="login-button">로그인</button>
			</form>

			<!-- kakao button -->
			<div class="text-center">
			<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${kakaoApiKey}&redirect_uri=${redirectUri}">
				<img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" />
			</a>
			</div>
		</div>
	</div>
</div>

<script>
	<c:if test="${not empty msg}">
	alert("${msg}")
	</c:if>
</script>