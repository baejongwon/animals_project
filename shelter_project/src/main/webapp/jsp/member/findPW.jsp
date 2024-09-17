<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<link href="member.css" rel="stylesheet">

<div class="login_bar">
	<div class="login-container">
		<div class="login-box">
			<h2>비밀번호 찾기</h2>
			<form action="findPWProc" method="post" id="f">
				<div class="input-group">
					<label for="name">이름</label> <input type="text" id="name" name="name"
						required>
				</div>
				<div class="input-group">
					<label for="id">아이디</label> <input type="text" id="id" name="id"
						required>
				</div>
				 <div class="input-group">
                        <label for="tel">전화번호</label>
                        <div class="tel-container">
                            <input type="text" id="tel" name="tel" required>
                            <input type="button" onclick="sendSms()" class="tel-button" value="인증번호 받기"
                            style="width: 30%">
                        </div>
                    </div>
                     <div class="input-group">                        
                        <div class="tel-container">
                            <input type="text" id="confirmNum" name="confirmNum" required>
                            <input type="button" onclick="smsCheck()" class="tel-button" value="인증번호 확인"
                            style="width: 30%">
                        </div>
                    </div>
					<div class="button-group">
                        <button type="submit" class="login-button">찾기</button>
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
	
	function sendSms() {
        var tel = document.getElementById("tel").value;
        fetch('sendSms', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
            	tel: tel
            })
        }).then(response => response.text())
        .then(data => {
            alert(data);
        });
    }
    
    function smsCheck() {
        var tel = document.getElementById("tel").value;
        var confirmNum = document.getElementById("confirmNum").value;
        fetch('smsCheck', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
            	tel: tel,
            	confirmNum: confirmNum
            })
        }).then(response => response.text())
        .then(data => {
            alert(data);
            if(data === "인증 성공"){
            	document.getElementById("confirmNum").setAttribute("readOnly", true);
            }
        });
    }
    
</script>