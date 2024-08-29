<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/header" />
<link rel="stylesheet" href="center.css">
<script>

</script>

<div class="banner">
	<div>
		<h1>Adoptable Animals</h1>
		<p>입양대기동물 현황</p>
	</div>
</div>

<div class="content_box_1">

	<div class="top_m_wrap">
			<button class="button button1" onclick="location.href='adoption?type=all'">전체</button>
			<button class="button button1" onclick="location.href='adoption?type=DOG'">강아지</button>
			<button class="button button1" onclick="location.href='adoption?type=CAT'">고양이</button>
	</div>

    <div class="board-container">
        <c:forEach var="board" items="${boards}" varStatus="status">
            <div class="board-item">
                <div class="board-image">
                    <a href="adoptionDetail?no=${board.animal_no}">
                        <img src="https://${firstImagesMap[board.animal_no] }" alt="Animal Image" loading="lazy">
                    </a>
                </div>
                <div class="board-info">
                    <div class="animal-no-nm">
                        <div class="animal-no">${board.animal_no}</div>
                        <a href="adoptionDetail?no=${board.animal_no}" class="nm">${board.nm}</a>
                    </div>
                    <div class="entrance-date">${board.entrnc_date}</div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

	<div class="pagination">
    <c:choose>
        <c:when test="${paging.page<=1}">
            <span class="prev disabled">&lt;</span>
        </c:when>
        <c:otherwise>
            <a href="adoption?page=${paging.page-1}&type=${param.type}" class="prev">&lt;</a>
        </c:otherwise>
    </c:choose>

    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
        <c:choose>
            <c:when test="${i eq paging.page}">
                <span class="current">${i}</span>
            </c:when>
            <c:otherwise>
                <a href="adoption?page=${i}&type=${param.type}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:choose>
        <c:when test="${paging.page>=paging.maxPage}">
            <span class="next disabled">&gt;</span>
        </c:when>
        <c:otherwise>
            <a href="adoption?page=${paging.page+1}&type=${param.type}" class="next">&gt;</a>
        </c:otherwise>
    </c:choose>
</div>


<c:import url="/footer" />
