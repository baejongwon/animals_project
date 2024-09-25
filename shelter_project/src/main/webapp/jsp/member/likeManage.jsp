<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/header" />

<link href="member.css" rel="stylesheet">
<link rel="stylesheet" href="infoBoard.css">

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/profileManage.js"></script>

<script>
var perOffset = 3;
var infoOffset = 3;
var cenOffset = 3;

// 개인분양 게시글 더보기 
function perLikeMorePosts() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var posts = JSON.parse(this.responseText); 
            var postContainer = document.querySelector(".personal-board tbody");

            posts.forEach(function(post) {
            	var animal_no = post.animal_no;  
            	var nm = post.nm; 
            	var author = post.author;
            	var time = post.time; 
            	
            	var row = `<tr>
                    <td class="num first">${'${animal_no}'}</td>
                    <td class="subject"><a href='personalContent?no=${'${animal_no}'}'>${'${nm}'}</a></td>
                    <td>${'${author}'}</td>
                    <td class="last">${'${time}'}</td>
                </tr>`;

            	postContainer.insertAdjacentHTML('beforeend', row);
            });

            perOffset += 10; 
        }
    };
    xhttp.open("GET", "perLikeMore?offset=" + perOffset + "&limit=10", true);
    xhttp.send();
}

//보호센터 좋아요 게시글 더보기 
function cenLikeMorePosts() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var posts = JSON.parse(this.responseText); 
            var postContainer = document.querySelector(".cen-board tbody");

            posts.forEach(function(post) {
            	var animal_no = post.animal_no;  
            	var nm = post.nm; 
            	var entrnc_date = post.entrnc_date; 
            	
            	var row = `<tr>
                    <td class="num first">${'${animal_no}'}</td>
                    <td class="subject"><a href='personalContent?no=${'${animal_no}'}'>${'${nm}'}</a></td>
                    <td class="last">${'${entrnc_date}'}</td>
                </tr>`;

            	postContainer.insertAdjacentHTML('beforeend', row);
            });

            cenOffset += 10; 
        }
    };
    xhttp.open("GET", "cenLikeMore?offset=" + cenOffset + "&limit=10", true);
    xhttp.send();
}

// 지식공유 게시글 더보기 
function infoLikeMorePosts() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var posts = JSON.parse(this.responseText); 
            var postContainer = document.querySelector(".info-board tbody");
            
            posts.forEach(function(post) {
            	var postNo = post.postNo;
            	var title = post.title;
            	var createdDate = post.createdDate;
            	var author = post.author;
            	var viewCount = post.viewCount;
            	
                var row = `<tr>
                    <td class="num first">${'${postNo}'}</td>
                    <td class="subject"><a href='infoBoardContent?postNo=${'${postNo}'}'>${'${title}'}</a></td>
                    <td>${'${author}'}</td>
                    <td>${'${createdDate}'}</td>
                    <td class="last">${'${viewCount}'}</td>
                </tr>`;
                postContainer.insertAdjacentHTML('beforeend', row);
            });

            infoOffset += 10; 
        }
    };
    xhttp.open("GET", "infoLikeMore?offset=" + infoOffset + "&limit=10", true);
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
		<h1>likeManage</h1>
		<p>좋아요 관리</p>
	</div>
</div>
	 <div class="sub-contents" id="contents">
	<div class="container" style="text-align: center;">
		<div class="table-utility">
			<div class="left">
				<div class="txt count">
					개인분양
				</div>
			</div>
		</div>

		<table class="table board-list personal-board">

			<colgroup>
				<col class="num">
				<col class="subject">
				<col class="counter">
				<col class="author">
			</colgroup>
			<thead>
				<tr>
					<th class="num first" scope="col">번호</th>
					<th class="subject" scope="col">제목</th>
					<th class="author" scope="col">작성자</th>
					<th class="date last" scope="col">작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="perBoard" items="${perLikeBoards}">
					<tr>
						<td scope="col" class="num first">${perBoard.animal_no}</td>
						<td scope="col" class="subject"><a href="personalContent?no=${perBoard.animal_no}">${perBoard.nm} </a></td>
						<td scope="col">${perBoard.author}</td>
						<td scope="col" class="last">${perBoard.time}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<!-- 더보기 버튼 -->
    	<button class="MoreBtn" onclick="perLikeMorePosts()">더보기</button>
	</div>	
</div>

<div class="sub-contents" id="contents">
	<div class="container" style="text-align: center;">
		<div class="table-utility">
			<div class="left">
				<div class="txt count">
					보호센터
				</div>
			</div>
		</div>

		<table class="table board-list cen-board">

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
				<c:forEach var="cenBoard" items="${cenLikeBoards}">
					<tr>
						<td scope="col" class="num first">${cenBoard.animal_no}</td>
						<td scope="col" class="subject"><a href="adoptionDetail?no=${cenBoard.animal_no}">${cenBoard.nm} </a></td>
						<td scope="col" class="last">${cenBoard.entrnc_date}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<!-- 더보기 버튼 -->
    	<button class="MoreBtn" onclick="cenLikeMorePosts()">더보기</button>
	</div>	
</div>

<div class="sub-contents" id="contents">
	<div class="container" style="text-align: center;">
		<div class="table-utility">
			<div class="left">
				<div class="txt count">
					지식공유
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
					<th class="author" scope="col">작성자</th>
					<th class="date" scope="col">작성일</th>
					<th class="counter last" scope="col">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="infoBoard" items="${infoLikeBoards}">
					<tr>
						<td scope="col" class="num first">${infoBoard.postNo}</td>
						<td scope="col" class="subject"><a href="infoBoardContent?postNo=${infoBoard.postNo}">${infoBoard.title} </a></td>
						<td scope="col">${infoBoard.author}</td>
						<td scope="col">${infoBoard.createdDate}</td>
						<td scope="col" class="last">${infoBoard.viewCount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<!-- 더보기 버튼 -->
    	<button class="MoreBtn" onclick="infoLikeMorePosts()">더보기</button>
    
	</div>	
</div>

<c:import url="/footer" />
