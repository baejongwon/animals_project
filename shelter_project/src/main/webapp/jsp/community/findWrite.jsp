<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<c:import url="/header" />
<div align="center" style="margin: 0 auto; margin-top: 100px;">
	<form action="${empty find ? 'findWriteProc' : 'findEditProc' }"
		method='post' enctype="multipart/form-data" id="form">
		<input type="hidden" name="no" value="${find.no }" />
		<tr>
			<td height="22">&nbsp;</td>
			<td height="22"><img src="../images/join/join-top-point.gif"
				width="8" height="5"> <font color="8e8170">Home
					&gt;자유게시판</font></td>
			<td height="22">&nbsp;</td>
		</tr>

		<table width="738" border="0" cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td><img src="../images/board/a-freeboard.GIF" width="738"
						height="134"></td>
				</tr>
				<tr>
					<td align="right"><font color="df516b">* 필수 입력사항 입니다.</font></td>
				</tr>
				<tr align="center">
					<td>
						<table width="729" border="0" cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<td width="154"><font color="df516b">*등록인</td>
									</font>
									<td width="574"><input type="text" name="id" size="10"
										maxlength="15"
										style="ime-mode: active; border: 1 solid #a5acb2"
										value="${find.id }"></td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td><img src="../images/board/registration-menu-line.GIF"
						width="729" height="1"></td>
				</tr>
				<tr>
					<td>
						<table width="729" border="0" cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<td width="155"><font color="df516b">*제목</td>
									<td width="574"><input type="text" name="title"
										maxlength="30" size="35"
										style="padding-left: 3px; border: 1px solid rgb(204, 204, 204);"
										value="${find.title }"> <font color="df516b">*
											12글자까지만 글 목록에 노출됩니다. </font></td>
								</tr>
							</tbody>
						</table>
				</tr>

				<tr>
					<td>
						<table width="729" border="0" cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<td width="155"><font color="df516b">*동물분류</td>
									</font>
									<td width="574"><input type="radio" name="species"
										value="DOG">강아지 <input type="radio" name="species"
										value="CAT">고양이 <input type="radio" name="species"
										value="OTHER">기타 반려동물</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>

				<tr>
					<td>
						<table width="729" border="0" cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<td width="155" height="171"><font color="df516b">*상세내용</td>
									</font>
									<td width="574" height="171"><font color="df516b"><br>
											※ 게시판의 성격과 다른글을 올리시면, 사이트 이용이 정지되실 수 있습니다. <textarea
												name="content" cols="66" rows="8" wrap="hard"
												style="ime-mode: active;">${find.content}</textarea> </font></td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>



				<tr>
					<td>
						<table width="729" border="0" cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<td width="155" height="161"><font color="df516b">사진</td>
									</font>

									<td width="574" height="161">
										<table width="574" border="0" cellpadding="0" cellspacing="0">
											<tbody>
												<tr>
													<td height="53"><b>※ 사진첨부시 주의사항</b> <br> 1. 등록가능
														한 확장자는 jp(e)g, gif, png 입니다. <br> 2. 사진의 파일명은 반드시
														영문으로 등록해 주세요. <br> 3. 사진용량이 너무 크거나, bmp 파일은 에러가 발생할 수
														있습니다.</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td>
														<table width="574" border="0" cellpadding="0"
															cellspacing="0">
															<tbody>
																<tr>
																	<td width="120">
																		<div align="left">사진1(메인사진)</div>
																	</td>
																	<td width="360">
																		<div id="divImage1">
																			<img src="${find.image }" width="35px" height="auto"
																				id="imgTag1" />
																			<button type="button" onclick='deleteImg(1)'>❌</button>
																		</div> <input type="file" name="image" size="35"
																		style="border: 1 solid #a5acb2" value=""
																		id="inputImage1" onChange="fileChange">
																	</td>
																</tr>
															</tbody>
														</table>
													</td>
												</tr>


												<tr>
													<td>
														<table width="574" border="0" cellpadding="0"
															cellspacing="0">
															<tbody>
																<tr>
																	<td width="120">
																		<div align="left">사진2</div>
																	</td>
																	<td width="360">
																		<div id="divImage2">
																			<img src="${find.image2 }" width="35px" height="auto"
																				id="imgTag2" />
																			<button type="button" onclick='deleteImg(2)'>❌</button>
																		</div> <input type="file" name="image2" size="35"
																		style="border: 1 solid #a5acb2" value=""
																		id="inputImage2">
																	</td>
																</tr>
															</tbody>
														</table>
													</td>
												</tr>
											</tbody>
										</table>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>

				<tr>
					<td height="25" align="right"><br> <br> <input
						type="submit" value="${empty find ? '글쓰기' : '수정'}"> <input
						type="button" value="목록" onclick="location.href='findForm'">
					</td>
				</tr>

			</tbody>
		</table>
	</form>

</div>
<c:import url="/footer" />


<script>
	window.onload = function() {
		// 라디오 버튼 선택
		var selectedValue = `${find.species}`;
		if (selectedValue != '') {
			var radioOptions = document.querySelectorAll('input[type="radio"]');
			for (var i = 0; i < radioOptions.length; i++) {
				if (radioOptions[i].value === selectedValue) {
					radioOptions[i].checked = true;
				}
			}
		}

		//초기 이미지 태그 설정
		var img1 = document.getElementById("imgTag1").src;
		var img2 = document.getElementById("imgTag2").src;
		var currentURL = window.location.href;
		if (img1 != currentURL) {
			document.getElementById("inputImage1").style.display = "none";
		} else {
			document.getElementById("divImage1").style.display = "none";
		}
		if (img2 != currentURL) {
			document.getElementById("inputImage2").style.display = "none";
		} else {
			document.getElementById("divImage2").style.display = "none";
		}

	};

	//기존 이미지 삭제
	function deleteImg(imageNumber) {
		if (imageNumber == 1) {
			document.getElementById("inputImage1").style.display = "block";
			document.getElementById("divImage1").style.display = "none";
			$("#form").append($('<input/>', {
				type : 'hidden',
				name : 'deleteImage',
				value : 'y'
			}));
			$("#form").appendTo('body');
		}
		if (imageNumber == 2) {
			document.getElementById("inputImage2").style.display = "block";
			document.getElementById("divImage2").style.display = "none";
			$("#form").append($('<input/>', {
				type : 'hidden',
				name : 'deleteImage2',
				value : 'y'
			}));
			$("#form").appendTo('body');
		}
	}
</script>