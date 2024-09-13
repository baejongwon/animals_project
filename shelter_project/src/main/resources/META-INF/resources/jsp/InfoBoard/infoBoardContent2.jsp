<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:import url="/header" />
<link rel="stylesheet" href="infoBoard.css">


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
				<strong class="subject">${board.title}</strong>
				<ul class="info">
					<li class="first"><strong>작성일</strong>${board.createdDate }</li>
					<li class="last"><strong>조회수</strong>${board.viewCount }</li>
				</ul>
			</div>
			<div class="content" id="viewer">
				<p>
					<br>
				</p>
			</div>
			
		</div>
		<div class="action-btn-group">
			<div class="center">
				<button type="button" class="btn btn-light btn-lg w-sm"
					onclick="location.href='infoBoard'">목록</button>
				<button type="button" class="btn btn-light btn-lg w-sm"
					onclick="location.href=''">수정</button>
				<c:if test="${sessionScope.id eq board.author }">
				<button type="button" class="btn btn-light btn-lg w-sm"
					onclick="location.href=''">삭제</button>
				</c:if>
			</div>
		</div>

	</div>
</div>
    
<script>
    // 서버에서 JSP로 전달된 마크다운 콘텐츠
    const content = ${contentJson};

    // Toast UI Editor Viewer 초기화
     const viewer = new toastui.Editor.factory({
        el: document.querySelector('#viewer'),  // Viewer가 렌더링될 요소
        viewer: true,  // Viewer 모드로 설정
        initialValue: content  // 초기값으로 서버에서 전달된 콘텐츠 설정
    });
</script>
<c:import url="/footer" />
