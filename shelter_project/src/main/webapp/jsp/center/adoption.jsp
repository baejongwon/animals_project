<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/header" />
<link rel="stylesheet" href="center.css">

<style>
.pagination {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
}

.pagination a, .pagination span {
    display: inline-block;
    margin: 0 5px;
    padding: 8px 12px;
    text-decoration: none;
    color: #333;
    border: 1px solid #ccc;
    border-radius: 4px;
    transition: background-color 0.3s ease, color 0.3s ease;
}

.pagination a:hover {
    background-color: #f0f0f0;
    color: #000;
}

.pagination .current {
    font-weight: bold;
    background-color: #000;
    color: white;
}

.pagination .prev, .pagination .next {
    font-weight: bold;
    font-size: 16px;
    color: #333;
}

.pagination .prev.disabled, .pagination .next.disabled {
    color: #ccc;
    pointer-events: none;
}


</style>
<div class="banner">
	<div>
		<p>입양대기동물 현황</p>
	</div>
</div>

<div class="content_box_1">

	<div class="top_m_wrap">
			<button class="button button1" onclick="">전체</button>
			<button class="button button1" onclick="">강아지</button>
			<button class="button button1" onclick="">고양이</button>
	</div>

    <div class="board-container">
        <c:forEach var="board" items="${boards}" varStatus="status">
            <div class="board-item">
                <div class="board-image">
                    <a href="adoptionDetail?no=${board.animal_no}">
                        <img src="https://${firstImagesMap[board.animal_no] }" alt="Animal Image">
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
            <a href="adoption?page=${paging.page-1}" class="prev">&lt;</a>
        </c:otherwise>
    </c:choose>

    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
        <c:choose>
            <c:when test="${i eq paging.page}">
                <span class="current">${i}</span>
            </c:when>
            <c:otherwise>
                <a href="adoption?page=${i}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:choose>
        <c:when test="${paging.page>=paging.maxPage}">
            <span class="next disabled">&gt;</span>
        </c:when>
        <c:otherwise>
            <a href="adoption?page=${paging.page+1}" class="next">&gt;</a>
        </c:otherwise>
    </c:choose>
</div>


<c:import url="/footer" />
