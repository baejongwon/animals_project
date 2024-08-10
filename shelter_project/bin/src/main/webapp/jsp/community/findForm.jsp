<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
table td{
	text-align:center;
}
</style>
<c:import url="/header" />
<div style="margin: 0 auto; margin-top: 100px;">
<tr>
	<td bgcolor="#FFFFFF" valign="top" width="738">
		<table width="761" border="0" cellpadding="0" cellspacing="0" align="center">
			<tbody>
				<tr>
					<td><img src="../images/board/a-freeboard.GIF" width="738"
						height="134"></td>
				</tr>
			</tbody>
		</table>
	</td>
</tr>
<table border="0" align="center">
	<tr>
		<th width="60"><font color="df516b">NO</font></th>
		<th width="60"><font color="df516b">사진</font></th>
		<th width="370"><font color="df516b">제목</font></th>
		<th width="60"><font color="df516b">작성인</font></th>
		<th width="100"><font color="df516b">작성일</font></th>
		
	</tr>

	<c:forEach var="find" items="${finds}">
		<tr>
			<td style="">${find.no }</td>
			<td>
				<c:if test="${!empty find.image }">
				<img src="${find.image }" width="45px" height="45px"/>
				</c:if>
				<c:if test="${empty find.image }"></c:if>
			</td>
			<td onclick="location.href='findContent?no=${find.no }'" style="cursor:pointer;">
				${find.title }</td>
			<td>${find.id }</td>
			<td>${find.time }</td>
		</tr>
	</c:forEach>
	
<tr>
	<td colspan="6">${result }</td>
</tr>
</table>


<c:choose>
	<c:when test="${empty finds }">
		<h1 align="center">등록된 게시판이 없습니다.</h1>
	</c:when>
</c:choose>


<tr>
	<table width="800" border="0" cellpadding="0" cellspacing="0"
		align="right">
		<td>
			<div>
				<button onclick="location.href='findWrite'">글작성</button>

			</div>
		</td>
	</table>
</tr>

</div>
<c:import url="/footer" />
