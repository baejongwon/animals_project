<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/header" />
<link rel="stylesheet" href="center.css">
<link rel="stylesheet" href="infoBoard.css">

<script>
	
</script>

<div class="banner" style="background-image: url('img/puppy-face-light-sit-660106-wallhere.com.jpg');background-position: 0 27%;" >
	<div>
		<h1>Personal Adoption</h1>
		<p>개인 분양 게시판</p>
	</div>
</div>

<div class="content_box_1">

	<div class="top_m_wrap">
			<button class="button button1" onclick="location.href='personalBoards?type=all'">전체</button>
			<button class="button button1" onclick="location.href='personalBoards?type=dog'">강아지</button>
			<button class="button button1" onclick="location.href='personalBoards?type=cat'">고양이</button>
			<button class="button button1" onclick="location.href='personalBoards?type=etc'">기타</button>
	</div>
<div class="table-utility">
	<div class="right">
				<form action="perSearch" id="searchList" class="post-search" method="post">
					<fieldset>
						<legend>게시물검색</legend>
						<label for="searchColumn" class="blind">검색구분 선택</label> 
						<select	class="form-control" id="searchColumn"  name="searchColumn" title="검색구분 선택">
							<option value="nm" selected="selected">이름</option>
							<option value="breeds">품종</option>
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
	<div class="board-container">
		<c:forEach var="board" items="${boards}" varStatus="status">
			<div class="board-item">
				<div class="board-image">
					<a href="personalContent?no=${board.animal_no}&page=${paging.page}"> <img src="${imagePathMap[board.animal_no] }"
						alt="Animal Image" loading="lazy">
					</a>
				</div>
				<div class="board-info">
					<div class="animal-no-nm">
						<div class="animal-no">${board.animal_no}</div>
						<a href="personalContent?no=${board.animal_no}&page=${paging.page}" class="nm">${board.nm}</a>
					</div>
					<div class="entrance-date">${board.author} | ${board.time}</div>
				</div>
			</div>
		</c:forEach>
	</div>

	<div class="action-btn-group">
		<div class="right">
			<button type="button" class="btn btn-light btn-lg"
				onclick="location.href='personalWrite'">게시글 작성</button>
		</div>
	</div>

</div>




<div class="pagination">
	<c:choose>
		<c:when test="${paging.page<=1}">
			<span class="prev disabled">&lt;</span>
		</c:when>
		<c:otherwise>
			<a href="personalBoards?page=${paging.page-1}&type=${param.type}" class="prev">&lt;</a>
		</c:otherwise>
	</c:choose>

	<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i"
		step="1">
		<c:choose>
			<c:when test="${i eq paging.page}">
				<span class="current">${i}</span>
			</c:when>
			<c:otherwise>
				<a href="personalBoards?page=${i}&type=${param.type}">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<c:choose>
		<c:when test="${paging.page>=paging.maxPage}">
			<span class="next disabled">&gt;</span>
		</c:when>
		<c:otherwise>
			<a href="personalBoards?page=${paging.page+1}&type=${param.type}" class="next">&gt;</a>
		</c:otherwise>
	</c:choose>
</div>


<c:import url="/footer" />
