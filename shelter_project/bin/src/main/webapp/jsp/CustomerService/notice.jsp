<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/header" />

<style>
 .post-list-container {
        width: 100%;
        max-width: 1000px;
        margin: 40px auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 10px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    .post-list table {
        width: 100%;
        border-collapse: collapse;
    }

    .post-list th, .post-list td {
        padding: 15px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    .post-list th {
        background-color: #f9f9f9;
        font-weight: bold;
    }

    .post-list td {
        font-size: 16px;
    }

    .post-list tr:hover {
        background-color: #f1f1f1;
    }

    .post-list .post-title {
        color: #333;
        text-decoration: none;
    }

    .post-list .pagination {
        text-align: center;
        margin-top: 20px;
    }

    .post-list .pagination a {
        margin: 0 5px;
        color: #333;
        text-decoration: none;
    }

    .post-list .pagination a.current {
        font-weight: bold;
        color: #7bc043;
    }

    .search-box {
        text-align: right;
        margin-bottom: 10px;
    }

    .search-box input[type="text"] {
        padding: 5px;
        font-size: 16px;
        border: 1px solid #ddd;
        border-radius: 5px;
    }

    .search-box button {
        padding: 5px 10px;
        font-size: 16px;
        background-color: #7bc043;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    .search-box button:hover {
        background-color: #4a88e6;
    }
</style>

<div class="banner">
    <div>
        <h1>Notice</h1>
        <p>공지사항</p>
    </div>
</div>

<div class="post-list-container">
    <h2>게시글 목록</h2>
    <div class="search-box">
        <input type="text" placeholder="Search">
        <button>찾기</button>
    </div>
    <div class="post-list">
        <table>
            <thead>
                <tr>
                    <th>No</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>작성시간</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="post" items="${postList}">
                    <tr>
                        <td>${post.number}</td>
                        <td><a href="postDetail?id=${post.id}" class="post-title">${post.title}</a></td>
                        <td>${post.author}</td>
                        <td>${post.date}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="pagination">
        <a href="?page=1">&lt;</a>
        <c:forEach var="page" begin="1" end="${totalPages}">
            <a href="?page=${page}" class="${currentPage == page ? 'current' : ''}">${page}</a>
        </c:forEach>
        <a href="?page=${totalPages}">&gt;</a>
    </div>
</div>

<c:import url="/footer" />
