<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/header" />
<link rel="stylesheet" href="infoBoard.css">
<script>
	
</script>

<div class="banner">
	<div>
		<p>반려동물 지식 나눔</p>
	</div>
</div>

<div class="sub-contents" id="contents" tabindex="0">
	<div class="container">
		<div class="board-view">
			<h4 class="blind">정보공개 내용</h4>
			<div class="head">
				<strong class="subject">2021년 반려동물 보호복지 실태조사</strong>
				<ul class="info">
					<li class="first"><strong>작성일</strong>2022-08-09</li>
					<li class="last"><strong>조회수</strong>12,225</li>
				</ul>
			</div>
			<div class="content">
				<p>
					2021년 반려동물 보호복지 실태조사 결과를 붙임과 같이 공표합니다<br>
				</p>
			</div>
			<div class="file">
				<div class="tit">첨부파일</div>
				<ul>
					<li class="first last"><span class="icon_hwp"></span> <a
						href="/front/attachment/download.do?forceDown=true&amp;seq=20131"
						title="2021년 반려동물 보호·복지 실태조사.hwp"> 2021년 반려동물 보호·복지 실태조사.hwp </a>
					</li>
				</ul>
			</div>
		</div>
		<div class="action-btn-group">
			<div class="center">
				<button type="button" class="btn btn-light btn-lg w-sm"
					onclick="location.href=''">목록</button>
				<button type="button" class="btn btn-light btn-lg w-sm"
					onclick="location.href=''">수정</button>
			</div>
		</div>

	</div>
</div>

<c:import url="/footer" />
