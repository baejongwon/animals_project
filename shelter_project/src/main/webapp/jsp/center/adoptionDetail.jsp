<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/header" />
<!-- Swiper CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<link rel="stylesheet" href="center.css">

<div class="detail-container">
    <div class="top-section">
        <div class="image-container">
            <!-- 슬라이드 이미지 컨테이너 -->
            <div style="--swiper-navigation-color: #fff; --swiper-pagination-color: #fff" class="swiper mySwiper2">
                <div class="swiper-wrapper">
                    <c:forEach var="image" items="${ImagesMap[board.animal_no]}">
                        <div class="swiper-slide">
                            <img src="https://${image}" alt="Animal Image">
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
                            <img src="https://${image}" alt="Animal Image Thumbnail">
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
            <p>나이 : ${board.age}</p>
            <p>체중 : ${board.bdwgh}kg</p>
            <p>입소 날짜 : ${board.entrnc_date}</p>
            <p>임시보호 상태 : ${board.adp_sttus}</p>
             ${board.tmpr_prtc_cn}
            
        </div>
    </div>
    <div class="description">
    <c:if test="${youtubeID !=null}">
    <iframe width="720" height="576" src="https://www.youtube.com/embed/${youtubeID }" title="마포센터) 산초1 입양문의02-2124-2839#고양이#고양이일상#유기동물입양#유기묘#서울동물복지지원센터#아기고양이#책임#반려묘#서울시#코리안숏헤어 #아기고양이" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
    </c:if>
        <br>
        <p>${board.intrcn_cn }</p>
        <br>
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
    		            post_type : 'cen'
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
 		           post_type : 'cen'
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
