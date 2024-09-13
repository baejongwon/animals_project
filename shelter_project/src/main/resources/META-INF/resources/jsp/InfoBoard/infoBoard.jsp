<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/header" />
<link rel="stylesheet" href="infoBoard.css">
<script>
	
</script>

<div class="banner">
	<div>
		<h1>Information</h1>
		<p>반려동물 지식 나눔</p>
	</div>
</div>

<div class="sub-contents" id="contents">
	<div class="container">
		<div class="table-utility">
			<div class="left">
				<div class="txt count">
					전체 <span> ${boardCount } </span>건
				</div>
			</div>

			<div class="right">
				<form action="infoSearch" id="searchList" class="post-search" method="post">
					<fieldset>
						<legend>게시물검색</legend>
						<label for="searchColumn" class="blind">검색구분 선택</label> 
						<select	class="form-control" id="searchColumn"  name="searchColumn" title="검색구분 선택">
							<option value="title" selected="selected">제목</option>
							<option value="author">작성자</option>
						</select>
						<div class="wrap">
							<label class="blind" for="keyword">검색어 입력</label>
							<input name="keyword" id="keyword" name="keyword" class="form-control w-lg" maxlength="50" title="검색어 입력" placeholder="검색어를 입력하세요">
							<button class="btn" id="searchButton">
								<span id="search_button">검색</span>
							</button>
						</div>
					</fieldset>
				</form>
			</div>
		</div>

		<table class="table board-list">

			<colgroup>
				<col class="num">
				<col class="subject">
				<col class="date">
				<col class="counter">
			</colgroup>
			<thead>
				<tr>
					<th class="num first" scope="col">번호</th>
					<th class="subject" scope="col">제목</th>
					<th class="author" scope="col">작성자</th>
					<th class="date" scope="col">작성일</th>
					<th class="counter last" scope="col">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boards}">
					<tr>
						<td scope="col" class="num first">${board.postNo}</td>
						<td scope="col" class="subject"><a href="infoBoardContent?postNo=${board.postNo}&page=${paging.page}">
								<c:if test="${board.category eq 'ALL' }"><span style="color:#7bc043; margin-right:10px;">[공통]</span></c:if>
								<c:if test="${board.category eq 'DOG' }"><span style="color:#7bc043; margin-right:10px;">[강아지]</span></c:if>
								<c:if test="${board.category eq 'CAT' }"><span style="color:#7bc043; margin-right:10px;">[고양이]</span></c:if>
								${board.title} </a></td>
						<td scope="col">${board.author}</td>
						<td scope="col">${board.createdDate}</td>
						<td scope="col" class="last">${board.viewCount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="action-btn-group">
			<div class="right">
				<button type="button" class="btn btn-light btn-lg"
					onclick="location.href='infoBoardWrite'">게시글 작성</button>
			</div>
		</div>
	</div>
</div>
<!-- 페이징 시작 -->
<div class="pagination">
	<c:choose>
		<c:when test="${paging.page<=1}">
			<span class="prev disabled">&lt;</span>
		</c:when>
		<c:otherwise>
			<a href="infoBoard?page=${paging.page-1}"
				class="prev">&lt;</a>
		</c:otherwise>
	</c:choose>

	<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i"
		step="1">
		<c:choose>
			<c:when test="${i eq paging.page}">
				<span class="current">${i}</span>
			</c:when>
			<c:otherwise>
				<a href="infoBoard?page=${i}">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<c:choose>
		<c:when test="${paging.page>=paging.maxPage}">
			<span class="next disabled">&gt;</span>
		</c:when>
		<c:otherwise>
			<a href="infoBoard?page=${paging.page+1}"
				class="next">&gt;</a>
		</c:otherwise>
	</c:choose>
</div>


<c:import url="/footer" />
