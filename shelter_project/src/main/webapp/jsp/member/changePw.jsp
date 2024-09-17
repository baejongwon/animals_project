<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<link href="member.css" rel="stylesheet">

<div class="login_bar">
	<div class="login-container">
		<div class="login-box">
			<h2>새 비밀번호 등록</h2>
			<form action="changePwProc" method="post" id="f">
				<input type="hidden" name="userID" value="${userID}"> 
				 <div class="input-group">
                        <label for="pw">새 비밀번호</label>
                        <input type="password" id="pw" name="pw" required>
                    </div>
                    <div class="input-group">
                        <label for="confirmPw">비밀번호 확인</label>
                        <input type="password" id="confirmPw" name="confirmPw" required>
                    </div>
                    
                    <div class="button-group">
                        <button type="submit" class="login-button">등록</button>
                        <button type="button" class="cancel-button" onclick="location.href='login'">취소</button>
                    </div>
			</form>
		</div>
	</div>
</div>

<script>
	<c:if test="${not empty msg}">
	alert("${msg}")
	</c:if>    
</script>