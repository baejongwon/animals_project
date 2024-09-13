<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<c:import url="/header" />
<div align="center">

	<table width="738" border="0" cellpadding="0" cellspacing="0" style="margin: 0 auto; margin-top: 100px;">
		<tbody>
			<tr>
				<td><img src="../images/board/a-freeboard.GIF" width="738"
					height="134"></td>
			
			

			<table width="729" border="0" cellpadding="0" cellspacing="0"
				align="center">
				<tbody>
					<tr>
						<td width="158"><font color="df516b">사용자</td>
						<td width="571">${find.id }</td>
						<br>
						<br>

					</tr>
				</tbody>
			</table>
			</td>
			</tr>

			<tr>
				<td>
					<table width="729" border="0" cellpadding="0" cellspacing="0"
						align="center">
						<tbody>
							<tr>
								<td width="158"><font color="df516b">제목</td>
								<hr width="800">
								<td width="571">${find.title }</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>

			</tr>
			<tr>
				<td>
					<table width="729" border="0" cellpadding="0" cellspacing="0"
						align="center">
						<tbody>
							<tr>
								<td width="158"><font color="df516b">동물종류</td>
								<hr width="800">
								<td width="571">${find.species_kr }</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>

			</tr>
			<tr>
				<td>
					<table width="729" border="0" cellpadding="0" cellspacing="0"
						align="center">
						<tbody>
							<tr>
								<hr width="800">
								<td width="158"><font color="df516b">내용</td>
								<td width="571"><br>${find.content }<br>&nbsp;&nbsp;
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>

			<c:if test="${!empty find.image }">
				<hr width="800">
				<img src="${find.image }" width="400px" height="auto" />
			</c:if>

			<c:if test="${!empty find.image2 }">
				<br />
				<br />
				<img src="${find.image2 }" width="400px" height="auto" />
			</c:if>


			<table width="720" border="0" cellpadding="0" cellspacing="0">
				<tbody>
					<tr height="20">
					</tr>
					<tr>
						<td colspan="3"><hr /></td>
					</tr>
					<c:forEach var="comment" items="${find.comments }">
						<tr height="40">
							<td width="50">&nbsp;</td>
							<td width="652"><b> <font color="12436c">${comment.name }</font></b>&nbsp;(${comment.created_at })
								 <button type="button" onclick='openPop(${comment.no},${comment.certification_no},"${comment.password}")'>❌</button> <br>${comment.content }</td>
							<td width="27">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="3"><hr /></td>
						</tr>
					</c:forEach>

					<tr>
						<td width="50">&nbsp;</td>
						<table width="652" border="0" cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<td width="112" bgcolor="f4f4f4">
										<div align="center">
											<b>댓글달기</b>
										</div>
									</td>
									<td width="540">
										<table width="540" border="0" height="58" cellpadding="3"
											cellspacing="0">
											<font color="df516b">이름 <input type="text"
												name="cname" maxlength="10" size="15"
												style="padding-left: 3px; border: 1px solid rgb(204, 204, 204);"
												value="" id="cname">
											</font>&nbsp;&nbsp;&nbsp;&nbsp;
											<font color="df516b">비밀번호 <input type="password"
												name="cpass" maxlength="6" size="11"
												style="padding-left: 3px; border: 1px solid rgb(204, 204, 204);"
												id="cpass"></font>
										</table> <input type="text" name="comment" size="50" maxlength="100"
										style="padding-left: 3px; border: 1px solid rgb(204, 204, 204); width: 300px"
										value="" id="comment">
										<button type="button" onclick="insertComment()">댓글달기</button>
									</td>
								</tr>
							</tbody>
						</table>
						</td>
					</tr>
				</tbody>
			</table>

			<tr>
				<table width="729" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="50" height="54">&nbsp;</td>
						<td width="652" height="54" align="right"></td>
						<button type="button" onclick="location.href='findForm'">목록</button>
						<button type="button" onclick="location.href='editFind?no=${find.no }'">수정</button>
						<button type="button" onclick="deleteCheck(${find.no})">삭제</button>
					</tr>
				</table>
			</tr>
		</tbody>
	</table>
</div>

<!-- 댓글 삭제하기 모달 팝업 -->
<div id="modalWrap" style="display:none;">
  <div id="modalContent">
    <div id="modalBody">
      <div>
      	<span style="font-size:25px;">댓글 삭제하기</span> 
      	<span id="closeBtn" onclick="$('#modalWrap').hide();">&times;</span>
      </div> 
      <div class="divCenter">
		비밀번호 :&nbsp;&nbsp;<input type="password" id="deleteCommentPassword">
      </div> 
      <div class="divCenter">
		<button type="button" onclick='deleteCommentCheck()'>삭제하기</button>
      </div> 
    </div>
  </div>
</div>
<c:import url="/footer" />
<script>
	//글 삭제
	function deleteCheck(no){
		console.log(no);
		if(window.confirm("삭제하시겠습니까?")) {
			//삭제
			location.href='deleteFind?no=' + no;  
		}
	}
	
	//삭제 댓글 정보를 담을 객체
	var commentObj = {};
	
	//댓글 삭제 > 모달팝업 오픈
	function openPop(no,certificationNo,password){
		console.log(no,certificationNo,password)
		commentObj = {
			no : no,
			certification_no : certificationNo,
			password : password
		};
		
		$("#deleteCommentPassword").val("");
		$("#modalWrap").attr("style", "display:block");
	}

	//댓글 삭제 > 비밀번호 확인
	function deleteCommentCheck(no){
		let inputVal = $("#deleteCommentPassword").val();
		if(inputVal == commentObj.password){
			console.log('삭제할게요~')
			$("#modalWrap").attr("style", "display:none");
			location.href='deleteCommentCheck()?#no=' + commentObj(no);
		}else{
			alert('비밀번호가 틀렸습니다.');
		}
	} 
	
	//댓글 달기
	function insertComment(){
		let name = $("#cname").val();
		let password = $("#cpass").val();
		let content = $("#comment").val();
		
		$.ajax({ 
            type:"POST", 
            contentType : "application/json",
            url:"insertComment",
            data: JSON.stringify ({
            	certification_no : "${find.no}",
                name: name,
                password : password,
                content : content
            }),
            datatype: "application/json",
            success:function(result){ 
                console.log(result);
                location.reload();
            }
		
        });
	}
	
</script>
<style>
#modalWrap {
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 300px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

#modalBody {
  width: 300px;
  height: 100px;
  padding: 30px;
  margin: 0 auto;
  border: 3px solid #df516b;
  background-color: #fff;
  display: flex;
  flex-direction: column;
}

#closeBtn {
  float:right;
  font-weight: bold;
  color: #777;
  font-size:25px;
  cursor: pointer;
  margin-left: auto;
}

.divCenter {
	display:flex; 
	align-items:center; 
	justify-content: center; 
	margin-top:30px; 
}
</style>