<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/header" />
<!-- Swiper CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<link rel="stylesheet" href="center.css">
<link rel="stylesheet" href="infoBoard.css">

<div class="detail-container">
    <div class="top-section">
        <div class="image-container">
            <!-- 슬라이드 이미지 컨테이너 -->
            <div style="--swiper-navigation-color: #fff; --swiper-pagination-color: #fff" class="swiper mySwiper2">
                <div class="swiper-wrapper">
                    <c:forEach var="image" items="${ImagesMap[board.animal_no]}">
                        <div class="swiper-slide">
                            <img src="img/ITS/${image}" alt="Animal Image">
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
                            <img src="img/ITS/${image}" alt="Animal Image Thumbnail">
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="info-container">
            <h1>${board.nm}</h1>
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
</script>



<c:import url="/footer" />
