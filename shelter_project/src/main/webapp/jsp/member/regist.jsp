<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="member.css" rel="stylesheet">

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	<c:if test="${not empty msg}">
	alert("${msg}")
	</c:if>

    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
    
    function validateForm() {
    	var pw = document.getElementById("pw").value;
    	var confirmPw = document.getElementById("confirmPw").value;
    	
    	if(pw !== confirmPw){
    		alert("비밀번호가 일치하지 않습니다.");
    		return false;
    	}
    	
    	return true;
    }
    
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

    <div class="register_bar">
        <div class="register-container">
            <div class="register-box">
                <h2>회원가입</h2>
                <form action="registerProc" method="post" id="f" onsubmit="return validateForm()">
                    <div class="input-group">
                        <label for="id">아이디</label>
                        <input type="text" id="id" name="id" required>
                    </div>
                    <div class="input-group">
                        <label for="pw">비밀번호</label>
                        <input type="password" id="pw" name="pw" required>
                    </div>
                    <div class="input-group">
                        <label for="confirmPw">비밀번호 확인</label>
                        <input type="password" id="confirmPw" name="confirmPw" required>
                    </div>
                    <div class="input-group">
                        <label for="name">이름</label>
                        <input type="text" id="name" name="name" required>
                    </div>
                    <div class="input-group">
                        <label for="zipcode">우편번호</label>
                        <div class="zipcode-container">
                            <input type="text" id="sample6_postcode" name="sample6_postcode" required>
                            <input type="button" onclick="sample6_execDaumPostcode()" class="zipcode-button" value="우편번호 찾기">
                        </div>
                    </div>
                    <div class="input-group">
                        <label for="address">주소</label>
                        <input type="text" id="sample6_address" name="sample6_address" required>
                    </div>
                    <div class="input-group">
                        <label for="detailAddress">상세주소</label>
                        <input type="text" id="sample6_detailAddress" name="sample6_detailAddress" required>
                    </div>
                    <div class="input-group">
                        <label for="email">이메일</label>
                        <input type="email" id="email" name="email" required>
                    </div>
                    <div class="input-group">
                        <label for="tel">전화번호</label>
                        <div class="tel-container">
                            <input type="text" id="tel" name="tel" required>
                            <input type="button" onclick="sendSms()" class="tel-button" value="인증번호 받기">
                        </div>
                    </div>
                     <div class="input-group">                        
                        <div class="tel-container">
                            <input type="text" id="confirmNum" name="confirmNum" required>
                            <input type="button" onclick="smsCheck()" class="tel-button" value="인증번호 확인">
                        </div>
                    </div>
                    <div class="button-group">
                        <button type="submit" class="register-button">회원가입</button>
                        <button type="button" class="cancel-button" onclick="location.href='index'">취소</button>
                    </div>
                </form>
            </div>
        </div>
    </div>



