<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/header" />

<style>

</style>

<div class="banner" style="background-image: url('img/close-up-couple-with-dog-indoors.jpg'); background-position: 0 27%;">
	<div>
		<h1>MY Page</h1>
		<p>마이페이지</p>
	</div>
</div>

<div class="mypage-container">
	<div class="mypage-content">
		<div class="section" onclick="location.href='profileManage'">
			<img src="img/profile-icon.png" alt="Profile Icon">
			<h3>프로필 관리</h3>
			<p>회원님의 개인 정보를 수정/변경/관리하는 공간입니다.</p>
		</div>
		<div class="section" onclick="location.href='postManage'">
			<img src="img/post-icon.png" alt="Post Icon">
			<h3>게시글 관리</h3>
			<p>작성하신 게시글을 관리하는 공간입니다.</p>
		</div>
		
		<div class="section" onclick="location.href='likeManage'">
			<img src="img/like_icon.png" alt="Post Icon">
			<h3>좋아요 관리</h3>
			<p>좋아요한 글을 관리하는 공간입니다.</p>
		</div>
	</div>
</div>

<c:import url="/footer" />
