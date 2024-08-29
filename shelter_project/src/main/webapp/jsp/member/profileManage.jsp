<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/header" />

<link href="member.css" rel="stylesheet">

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
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
   
</script>
<div class="banner">
	<div>
		<h1>ProfileManage</h1>
		<p>프로필 관리</p>
	</div>
</div>
	 <div class="profile_bar">
        <div class="profile-container">
            <div class="profile-box">
                <form action="updateProc" method="post" id="f" onsubmit="return validateForm()">
                    <div class="input-group">
                        <label for="id">아이디</label>
                        <input type="text" id="id" name="id" value="${member.id }" readOnly>
                    </div>
                    <div class="input-group">
                        <label for="pw">새 비밀번호</label>
                        <input type="password" id="pw" name="pw" required>
                    </div>
                    <div class="input-group">
                        <label for="confirmPw">새 비밀번호 확인</label>
                        <input type="password" id="confirmPw" name="confirmPw" required>
                    </div>
                    <div class="input-group">
                        <label for="name">이름</label>
                        <input type="text" id="name" name="name" value="${member.name }"required>
                    </div>
                    <div class="input-group">
                        <label for="zipcode">우편번호</label>
                        <div class="zipcode-container">
                            <input type="text" id="sample6_postcode" name="sample6_postcode" value="${postcode }" required>
                            <input type="button" onclick="sample6_execDaumPostcode()" class="zipcode-button"  value="우편번호 찾기">
                        </div>
                    </div>
                    <div class="input-group">
                        <label for="address">주소</label>
                        <input type="text" id="sample6_address" name="sample6_address" value="${address }" required>
                    </div>
                    <div class="input-group">
                        <label for="detailAddress">상세주소</label>
                        <input type="text" id="sample6_detailAddress" name="sample6_detailAddress" value="${detailAddress }" required>
                    </div>
                    <div class="input-group">
                        <label for="email">이메일</label>
                        <input type="email" id="email" name="email" value="${member.email }" required>
                    </div>
                    <div class="input-group">
                        <label for="tel">전화번호</label>
                        <div class="tel-container">
                            <input type="text" id="tel" name="tel" value="${member.tel }"required>
                        </div>
                    </div>
                    <div class="button-group">
                        <button type="submit" class="update-button">정보 수정</button>
                        <button type="button" class="cancel-button" onclick="location.href='index'">취소</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

<c:import url="/footer" />
