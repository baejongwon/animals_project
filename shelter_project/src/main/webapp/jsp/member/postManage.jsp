<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/header" />

<link href="member.css" rel="stylesheet">
<link rel="stylesheet" href="infoBoard.css">

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/profileManage.js"></script>

<script>
var perOffset = 3;  // 개인분양 게시글의 초기
var infoOffset = 3;  // 지식공유 게시글의 초기

// 개인분양 게시글 더보기 
function perMorePosts() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var posts = JSON.parse(this.responseText); 
            var postContainer = document.querySelector(".personal-board tbody");

            posts.forEach(function(post) {
            	var animal_no = post.animal_no;  
            	var nm = post.nm; 
            	var time = post.time; 
            	
            	var row = `<tr>
                    <td class="num first">${'${animal_no}'}</td>
                    <td class="subject"><a href='personalContent?no=${'${animal_no}'}'>${'${nm}'}</a></td>
                    <td class="last">${'${time}'}</td>
                </tr>`;

            	postContainer.insertAdjacentHTML('beforeend', row);
            });

            perOffset += 10; 
        }
    };
    xhttp.open("GET", "personalMore?offset=" + perOffset + "&limit=10", true);
    xhttp.send();
}

// 지식공유 게시글 더보기 
function infoMorePosts() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var posts = JSON.parse(this.responseText); 
            var postContainer = document.querySelector(".info-board tbody");
            
            posts.forEach(function(post) {
            	var postNo = post.postNo;
            	var title = post.title;
            	var createdDate = post.createdDate;
            	var viewCount = post.viewCount;
            	
                var row = `<tr>
                    <td class="num first">${'${postNo}'}</td>
                    <td class="subject"><a href='infoBoardContent?postNo=${'${postNo}'}'>${'${title}'}</a></td>
                    <td>${'${createdDate}'}</td>
                    <td class="last">${'${viewCount}'}</td>
                </tr>`;
                postContainer.insertAdjacentHTML('beforeend', row);
            });

            infoOffset += 10; 
        }
    };
    xhttp.open("GET", "infoMore?offset=" + infoOffset + "&limit=10", true);
    xhttp.send();
}
	
</script>

<style>

	.MoreBtn{
	border: 1px solid #dedede;
    background: #fff;
    padding: 7px 10px;
    cursor: pointer;
    border-radius: 5px;
	}
	
	.txt{
	font-size: 23px;
    font-weight: 600;
    }
</style>

<div class="banner" style="background-image: url('img/close-up-couple-with-dog-indoors.jpg'); background-position: 0 27%;">
	<div>
		<h1>PostManage</h1>
		<p>게시글 관리</p>
	</div>
</div>
	 <div class="sub-contents" id="contents">
	<div class="container" style="text-align: center;">
		<div class="table-utility">
			<div class="left">
				<div class="txt count">
					개인분양 게시글
				</div>
			</div>
		</div>

		<table class="table board-list personal-board">

			<colgroup>
				<col class="num">
				<col class="subject">
				<col class="date">
			</colgroup>
			<thead>
				<tr>
					<th class="num first" scope="col">번호</th>
					<th class="subject" scope="col">제목</th>
					<th class="date last" scope="col">작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="perBoard" items="${perBoards}">
					<tr>
						<td scope="col" class="num first">${perBoard.animal_no}</td>
						<td scope="col" class="subject"><a href="personalContent?no=${perBoard.animal_no}">${perBoard.nm} </a></td>
						<td scope="col" class="last">${perBoard.time}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<!-- 더보기 버튼 -->
    	<button class="MoreBtn" onclick="perMorePosts()">더보기</button>
	</div>	
</div>

<div class="sub-contents" id="contents">
	<div class="container" style="text-align: center;">
		<div class="table-utility">
			<div class="left">
				<div class="txt count">
					지식공유 게시글
				</div>
			</div>
		</div>

		<table class="table board-list info-board">

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
					<th class="date" scope="col">작성일</th>
					<th class="counter last" scope="col">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="infoBoard" items="${infoBoards}">
					<tr>
						<td scope="col" class="num first">${infoBoard.postNo}</td>
						<td scope="col" class="subject"><a href="infoBoardContent?postNo=${infoBoard.postNo}">${infoBoard.title} </a></td>
						<td scope="col">${infoBoard.createdDate}</td>
						<td scope="col" class="last">${infoBoard.viewCount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<!-- 더보기 버튼 -->
    	<button class="MoreBtn" onclick="infoMorePosts()">더보기</button>
    
	</div>	
</div>

<c:import url="/footer" />
