<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:import url="/header" />
<link rel="stylesheet" href="infoBoard.css">
<script>
	function deleteCheck() {
		var result = confirm('진짜로 삭제하겠습니까?');
		if (result == true) {
			var postNo = document.getElementById("postNo").value;
			location.href = 'deleteBoard?postNo=' + postNo;
		}
	}
	
	function showReplyForm(commentNo) {
	    var form = document.getElementById("replyForm-" + commentNo);
	    if (form.style.display === "none") {
	        form.style.display = "block";
	    } else {
	        form.style.display = "none";
	    }
	}

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
				<strong class="subject">${board.title}</strong>
				<ul class="info">
					<li class="first"><strong>작성일</strong>${board.createdDate }</li>
					<li class="last"><strong>조회수</strong>${board.viewCount }</li>
				</ul>
			</div>
			<div class="content" id="viewer">
				<c:out value="${board.content}" escapeXml="false" />
			</div>

		</div>


		<div class="comment-section">
			<h4>댓글 ${commentCount } 개</h4>

			<!-- 기존 댓글 목록 -->
			<div class="comments-list">
				<c:forEach var="comment" items="${comments}">
					<c:if test="${comment.parentNo == 0}">
						<!-- 부모 댓글 -->
						<div class="comment-item" style="margin-top:12px;">
							<p>
								<strong>${comment.author}</strong> <small>${comment.createdDate}</small>
								<button type="button" class="btn-s"
									onclick="showReplyForm(${comment.commentNo})">답글</button>
								<c:if test="${sessionScope.id eq comment.author }">
									<button type="button" class="btn-s "
										onclick="editComment(${comment.commentNo})">수정</button>
									<button type="button" class="btn-s "
										onclick="deleteComment(${comment.commentNo})">삭제</button>
								</c:if>
							</p>
							<p>${comment.content}</p>
						</div>
						<!-- 대댓글 목록 -->
						<c:forEach var="reply" items="${comments}">
							<c:if test="${reply.parentNo == comment.commentNo}">
								<div class="comment-item reply-item" style="margin-left: 20px; margin-top:10px;">
									<p>
										<strong>${reply.author}</strong> <small>${reply.createdDate}</small>
										<c:if test="${sessionScope.id eq reply.author }">
											<button type="button" class="btn-s"
												onclick="editComment(${reply.commentNo})">수정</button>
											<button type="button" class="btn-s"
												onclick="deleteComment(${reply.commentNo})">삭제</button>
										</c:if>
									</p>
									<p>${reply.content}</p>
								</div>
							</c:if>
						</c:forEach>
						<!-- 대댓글 작성 폼 -->
						<div id="replyForm-${comment.commentNo}" class="reply-form"
							style="display: none; margin-left: 20px; margin-top: 20px;">
							<form action="addComment" method="POST">
								<input type="hidden" name="postNo" value="${comment.postNo}" />
								<input type="hidden" name="parentNo" value="${comment.commentNo}" />
								<div class="form-group">
									<textarea name="content" class="form-control"
										placeholder="답글을 입력하세요" required></textarea>
								</div>
								<button type="submit" class="btn btn-sm btn-primary">답글
									작성</button>
							</form>
						</div>
					</c:if>
				</c:forEach>
			</div>

			<!-- 댓글 작성 폼 -->
			<form action="addComment" method="POST">
				<input type="hidden" name="postNo" value="${board.postNo}" /> <input
					type="hidden" name="parentNo" value="0" />
				<div class="form-group" style="margin-top: 20px;">
					<textarea name="content" class="form-control"
						placeholder="댓글을 입력하세요" required></textarea>
				</div>
				<button type="submit" class="btn btn-primary">댓글 작성</button>
			</form>
		</div>
		<!-- 댓글 작성 끝 -->
		
		<div class="action-btn-group">
			<input type="hidden" id="postNo" value="${board.postNo}" />
			<div class="center">
				<button type="button" class="btn btn-light btn-lg w-sm"
					onclick="location.href='infoBoard?page=${paging}'">목록</button>
				<c:if test="${sessionScope.id eq board.author }">
					<button type="button" class="btn btn-light btn-lg w-sm"
						onclick="location.href='infoBoardModify?postNo=${board.postNo}'">수정</button>
					<button type="button" class="btn btn-light btn-lg w-sm"
						onclick="deleteCheck()">삭제</button>
				</c:if>
			</div>
		</div>

	</div>
</div>

<c:import url="/footer" />
