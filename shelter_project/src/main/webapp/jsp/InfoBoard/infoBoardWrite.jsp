<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/header" />
<link rel="stylesheet" href="infoBoard.css">
<link rel="stylesheet"
	href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css">

<div class="banner">
	<div>
		<p>반려동물 지식 나눔</p>
	</div>
</div>

<div class="container">
	<form action="infoBoardWriteProc" method="POST">
		<div>
			<label for="title">제목</label><br> <input type="text" id="title"
				name="title" required style="width: 100%; padding: 8px;">
		</div>
		<div style="margin-top: 20px;">
			<label for="content">내용</label><br>
			<div id="editor"></div>
			<textarea id="content" name="content" style="display: none;"></textarea>
		</div>

		<div class="action-btn-group" style="margin-top: 20px;">
			<div class="center">
				<button type="button" class="btn btn-light btn-lg w-sm"
					onclick="location.href=''">목록</button>
				<button type="button" class="btn btn-light btn-lg w-sm"
					onclick="location.href=''">작성</button>
				<button type="button" class="btn btn-light btn-lg w-sm"
					onclick="location.href=''">취소</button>
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
		previewStyle : 'vertical'
	});

	// Form 제출 시, editor의 내용을 textarea로 전송
	function submitContent() {
		const content = editor.getMarkdown(); // 또는 editor.getHTML();
		document.querySelector('#content').value = content;
	}
</script>

<c:import url="/footer" />
