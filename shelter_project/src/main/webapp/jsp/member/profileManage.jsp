<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/header" />

<link href="member.css" rel="stylesheet">

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/profileManage.js"></script>

<div class="banner" style="background-image: url('img/close-up-couple-with-dog-indoors.jpg'); background-position: 0 27%;">
	<div>
		<h1>ProfileManage</h1>
		<p>프로필 관리</p>
	</div>
</div>
	 <div class="profile_bar">
        <div class="profile-container">
            <div class="profile-box">
                <form action="updateProc" method="post" id="f" onsubmit="return validateForm()">
                <input type="hidden" id ="login_type" name="login_type" value="${member.login_type}">
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
    
<script>
	var loginType = '${member.login_type}';
	console.log(loginType);
	loginTypeCheck(loginType);
</script>

<c:import url="/footer" />
