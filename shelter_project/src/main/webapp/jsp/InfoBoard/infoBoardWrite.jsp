<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/header" />
<link rel="stylesheet" href="infoBoard.css">


<style>
.category {
	width: 100px;
	border: 1px solid #ebedf2;
	box-sizing: border-box;
	border-radius: 10px;
	padding: 9px 13px;
	font-weight: 400;
	font-size: 14px;
	line-height: 16px;
	margin-right: 10px;
}

.box {
	display: flex;
	align-items: center;
}

.box input {
	width: 100%;
	padding: 8px;
	padding: 12px;
	border: none;
	border-bottom: 2px solid  #ebedf2;
}
</style>
<div class="banner">
	<div>
		<p>반려동물 지식 나눔</p>
	</div>
</div>

<div class="container" style="margin-top: 75px;">
	<form action="infoBoardWriteProc" method="POST" enctype="multipart/form-data" onsubmit="submitContent()">

		<div class="box">
			<select class="category" id="category" name = "category">
				<option value="ALL" selected="selected">공통</option>
				<option value="DOG">강아지</option>
				<option value="CAT">고양이</option>
			</select> <input type="text" id="title" name="title" placeholder="제목을 입력해주세요."
				required>
		</div>

		<div style="margin-top: 20px;">
			<div id="editor"></div>
			<textarea id="content" name="content" style="display: none;"></textarea>
		</div>

		<div class="action-btn-group" style="margin-top: 20px;">
			<div class="center">
				<button type="button" class="btn btn-light btn-lg w-sm"
					onclick="location.href='infoBoard'">목록</button>
				<button type="submit" class="btn btn-light btn-lg w-sm">작성</button>
				<button type="button" class="btn btn-light btn-lg w-sm"
					onclick="location.href='infoBoard'">취소</button>
			</div>
		</div>
	</form>
</div>
<!-- TOAST UI Editor Script -->
<script
	src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<script>
	// TOAST UI Editor 초기화
	const editor = new toastui.Editor({
		
		el : document.querySelector('#editor'),
		height : '400px',
		initialEditType : 'wysiwyg',
		previewStyle : 'vertical',
		hooks:{
			addImageBlobHook: (blob, callback) => {
	    		// blob : Java Script 파일 객체
	    		//console.log(blob);
	    		
	    		const formData = new FormData();
	        	formData.append('image', blob);
	        	
	        	let url = '';
	   			$.ajax({
	           		type: 'POST',
	           		enctype: 'multipart/form-data',
	           		url: '/addImageBlobHook',
	           		data: formData,
	           		dataType: 'json',
	           		processData: false,
	           		contentType: false,
	           		cache: false,
	           		timeout: 600000,
	           		success: function(data) {
	           			console.log('ajax 이미지 업로드 성공');
	           			url += data.imageUrl;
	           			console.log(url);
	           			callback(url, '');
	           		},
	           		error: function(e) {
	           			console.log('ajax 이미지 업로드 실패');
	           			callback('image_load_fail', '이미지 업로드 실패');
	           		}
	           	});
	    	}
		}
	});

	// Form 제출 시, editor의 내용을 textarea로 전송
	function submitContent() {
		const content = editor.getMarkdown(); // 또는 editor.getHTML();
		document.querySelector('#content').value = content;
	}
</script>

<c:import url="/footer" />
