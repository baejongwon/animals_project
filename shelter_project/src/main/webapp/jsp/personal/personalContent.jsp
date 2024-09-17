<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/header" />
<!-- Swiper CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<link rel="stylesheet" href="center.css">
<link rel="stylesheet" href="infoBoard.css">
<script src="infoBoard.js"></script>

<div class="detail-container">
    <div class="top-section">
        <div class="image-container">
            <!-- 슬라이드 이미지 컨테이너 -->
            <div style="--swiper-navigation-color: #fff; --swiper-pagination-color: #fff" class="swiper mySwiper2">
                <div class="swiper-wrapper">
                    <c:forEach var="image" items="${ImagesMap[board.animal_no]}">
                        <div class="swiper-slide">
                            <img src="${image}" alt="Animal Image">
                        </div>
                    </c:forEach>
                </div>
                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
            </div>
            <div thumbsSlider="" class="swiper mySwiper">
                <div class="swiper-wrapper">
                    <c:forEach var="image" items="${ImagesMap[board.animal_no]}">
                        <div class="swiper-slide">
                            <img src="${image}" alt="Animal Image Thumbnail">
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="info-container">
            <h1>${board.nm}
           	 <img id="likeImg" src="img/Like_button.png" alt="like button" style="width:20px;margin-top: 5px;cursor: pointer;">
            </h1>
            <p>성별 : ${board.sexdstn}</p>
            <p>품종 : ${board.breeds}</p>
            <p>나이 : ${board.age}세</p>
            <p>체중 : ${board.bdwgh}kg</p>            
            <p>등록일 : ${board.time }</p>
            
        </div>
    </div>
    
    <div class="content" id="viewer">
		<c:out value="${board.content}" escapeXml="false" />
	</div>
	
	
	<!-- 댓글 작성 폼 -->
		<form action="addComment" method="POST">
			<input type="hidden" name="animal_no" value="${board.animal_no}" /> 
			<input type="hidden" name="parentNo" value="0" />
			<input type="hidden" name="type" value="per" />
			<div class="form-group" style="margin-top: 20px;">
				<textarea name="content" class="form-control"
					placeholder="댓글을 입력하세요" required onclick="showButton()"
					style="height:100px;"></textarea>
			</div>
			<button type="button" class="btn-n" id="cancelButton"
				style="display: none; background: #fff; border: none; color: #000"
				onclick="hideButton()">취소</button>
			<button type="submit" class="btn-n" id="submitButton"
				style="display: none;">작성</button>
		</form>
		<div class="comment-section">
			<h4>댓글 ${commentCount } 개</h4>

			<!-- 기존 댓글 목록 -->
			<div class="comments-list">
				<c:forEach var="comment" items="${comments}">
					<c:if test="${comment.parentNo == 0}">
						<!-- 부모 댓글 -->
						<div class="comment-item" style="margin-top: 12px;">
							<p style="margin-bottom: 3px;">
								<strong>${comment.author}</strong> <small>${comment.createdDate}</small>
								<button type="button" class="btn-s"
									onclick="showReplyForm(${comment.commentNo})">답글</button>
								<c:if test="${sessionScope.id eq comment.author }">
									<button type="button" class="btn-s " id="modifyButton-${comment.commentNo}"
										onclick="modifyComment(${comment.commentNo},'${comment.content}','${type}')">수정</button>
									<button type="button" class="btn-s "
										onclick="deleteComment(${comment.commentNo},${board.animal_no},'${type}')">삭제</button>
								</c:if>
							</p>
							<p id="commentText-${comment.commentNo}" class="light-text">${comment.content}</p>
						</div>
						<!-- 대댓글 목록 -->
						<c:forEach var="reply" items="${comments}">
							<c:if test="${reply.parentNo == comment.commentNo}">
								<div class="comment-item reply-item"
									style="margin-left: 20px; margin-top: 10px;">
									<p style="margin-bottom: 3px;">
										<strong>${reply.author}</strong> <small>${reply.createdDate}</small>
										<c:if test="${sessionScope.id eq reply.author }">
											<button type="button" class="btn-s" id="modifyButton-${reply.commentNo}"
												onclick="modifyComment(${reply.commentNo},'${reply.content}','${type}')">수정</button>
											<button type="button" class="btn-s"
												onclick="deleteComment(${reply.commentNo},${board.animal_no},'${type}')">삭제</button>
										</c:if>
									</p>
									<p id="commentText-${reply.commentNo}" class="light-text">${reply.content}</p>
								</div>
							</c:if>
						</c:forEach>
						<!-- 대댓글 작성 폼 -->
						<div id="replyForm-${comment.commentNo}" class="reply-form"
							style="display: none; margin-left: 20px; margin-top: 20px;">
							<form action="addComment" method="POST">
								<input type="hidden" name="animal_no" value="${comment.animal_no}" />
								<input type="hidden" name="parentNo" value="${comment.commentNo}" />
								<input type="hidden" name="type" value="per" />
								<div class="form-group">
									<textarea name="content" class="form-control"
										placeholder="답글을 입력하세요" required></textarea>
								</div>
								<button type="submit" class="btn-n">작성</button>
							</form>
						</div>
					</c:if>
				</c:forEach>
			</div>

		</div>
		<!-- 댓글 작성 끝 -->
		
	<div class="action-btn-group" style="clear: both;">
			<input type="hidden" id="animal_no" value="${board.animal_no}" />
			<div class="center">
				<button type="button" class="btn btn-light btn-lg w-sm"
					onclick="location.href='personalBoards?page=${paging}'">목록</button>
				<c:if test="${sessionScope.id eq board.author }">
					<button type="button" class="btn btn-light btn-lg w-sm"
						onclick="location.href='personalModify?animal_no=${board.animal_no}'">수정</button>
					<button type="button" class="btn btn-light btn-lg w-sm"
						onclick="deleteCheck()">삭제</button>
				</c:if>
			</div>
		</div>
</div>

<!-- Swiper JS -->
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

<!-- Initialize Swiper -->
<script>
    var swiper = new Swiper(".mySwiper", {
        loop: true,
        spaceBetween: 10,
        slidesPerView: 4,
        freeMode: true,
        watchSlidesProgress: true,
    });
    var swiper2 = new Swiper(".mySwiper2", {
        loop: true,
        spaceBetween: 10,
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
        },
        thumbs: {
            swiper: swiper,
        },
    });
    
    function deleteCheck() {
		var result = confirm('진짜로 삭제하겠습니까?');
		if (result == true) {
			var animal_no = document.getElementById("animal_no").value;
			location.href = 'personalDelete?animal_no=' + animal_no;
		}
	}

    document.addEventListener("DOMContentLoaded", function() {
    	const likeImg = document.getElementById("likeImg");
    	let isLike = false;
    	const animal_No = ${board.animal_no};
    	const like_check = ${like_check != null ? like_check : 0}; 
    	
    	if (like_check > 0) {
    	      likeImg.src = "img/Like_active.png";
    	      isLike = true; 
    	  } else {
    	      likeImg.src = "img/Like_button.png";
    	      isLike = false; 
    	  }
    	  
    	function likePoint() {
    		 $.ajax({
    		        url: "like/active",
    		        type: "POST",
    		        data: JSON.stringify({
    		            postNo: animal_No,
    		            post_type : 'per'
    		        }),
    		        dataType: "json",
    		        contentType: "application/json",
    		        success: function(data) {
    		        	console.log("data : ?", data);	
    		        	if (data.status === "로그인이 필요합니다.") {
    		                alert(data.status);  
    		                window.location.href = 'login';
    		            } else{
    		        		console.log("좋아요 활성화 성공:", data);	
    		        	}
    		        },
    		        error: function(errorThrown) {
    		            console.error("좋아요 활성화 실패:", errorThrown);
    		        }
    		    });
    	}

    	function unlikePoint() {
    		 $.ajax({
 		        url: "like/Inactive",
 		        type: "POST",
 		        data: JSON.stringify({
 		            postNo: animal_No,
 		           post_type : 'per'
 		        }),
 		        dataType: "json",
 		        contentType: "application/json",
 		        success: function(data) {
 		        	if (data === "로그인이 필요합니다.") {
 		                alert(data);  
 		                window.location.href = 'login';
 		            } else{
 		        		console.log("좋아요 활성화 성공:", data);	
 		        	}
 		        },
 		        error: function(errorThrown) {
 		            console.error("좋아요 활성화 실패:", errorThrown);
 		        }
 		    });
    		console.log("좋아요 비활성화");
    	}

    	likeImg.addEventListener("click", function() {
    		if (!isLike) {
    			likeImg.src = "img/Like_active.png";
    			likePoint();
    		} else {
    			likeImg.src = "img/Like_button.png";
    			unlikePoint();
    		}
    		isLike = !isLike;
    	});
    });

</script>



<c:import url="/footer" />
