<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<div class="banner" style="background-image: url('img/close-up-couple-with-dog-indoors.jpg'); background-position: 0 27%;">
    <div>
       	<h1>Information</h1>
		<p>반려동물 지식 나눔</p>
    </div>
</div>

<div class="container" style="margin-top: 75px;">
    <form action="infoBoardWriteProc" method="POST" enctype="multipart/form-data" onsubmit="submitContent()">

        <div class="box">
            <select class="category" id="category" name="category">
                <option value="ALL" selected="selected">공통</option>
                <option value="DOG">강아지</option>
                <option value="CAT">고양이</option>
            </select> 
            <input type="text" id="title" name="title" placeholder="제목을 입력해주세요." required>
        </div>

        <div style="margin-top: 20px;">
            <textarea id="summernote" name="content"></textarea>
        </div>

        <div class="action-btn-group" style="margin-top: 20px;">
            <div class="center">
                <button type="button" class="btn btn-light btn-lg w-sm" onclick="location.href='infoBoard'">목록</button>
                <button type="submit" class="btn btn-light btn-lg w-sm">작성</button>
                <button type="button" class="btn btn-light btn-lg w-sm" onclick="location.href='infoBoard'">취소</button>
            </div>
        </div>
    </form>
</div>

<script>
$(document).ready(function() {
    // Summernote 초기화
    $('#summernote').summernote({
        height: 500,                 // 에디터 높이 설정
        minHeight: null,             // 최소 높이 설정
        maxHeight: null,             // 최대 높이 설정
        focus: true,                 // 페이지 로드 후 포커스 설정
        lang: "ko-KR",
        toolbar: [
		    // 글자 크기 설정
		    ['fontsize', ['fontsize']],
		    // 글자 [굵게, 기울임, 밑줄, 취소 선, 지우기]
		    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
		    // 글자색 설정
		    ['color', ['color']],
		    // 표 만들기
		    ['table', ['table']],
		    // 서식 [글머리 기호, 번호매기기, 문단정렬]
		    ['para', ['ul', 'ol', 'paragraph']],
		    // 줄간격 설정
		    ['height', ['height']],
		    // 이미지 첨부
		    ['insert',['picture']]
		  ],
		// 추가한 글꼴
			fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
			 // 추가한 폰트사이즈
			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72','96'],
        callbacks: {
            onImageUpload: function(files) {
                for (var i = 0; i < files.length; i++) {
                    uploadImage(files[i]);
                }
            }
        }
    });

        // 이미지 업로드 처리
        function uploadImage(file) {
            var data = new FormData();
            data.append("image", file);
            $.ajax({
                url: 'infoBoard/addImageBlobHook',
                cache: false,
                contentType: false,
                processData: false,
                data: data,
                type: "POST",
                success: function(response) {
                    console.log(response); 

                    if (response.imagePath) {
                    	console.log(response.imagePath);
                        var imageUrl = response.imagePath;
                        
                        $('#summernote').summernote('insertImage', imageUrl, function ($image) {
                            $image.attr('src', imageUrl);
                            $image.css('width', '50%');
                            $image.attr('class', 'img-responsive');  // 이미지 응답성 추가
                        });
                    } else {
                        console.error('이미지 경로가 반환되지 않았습니다.');
                    }
                },
                error: function(data) {
                    console.log('이미지 업로드 실패');
                }
            });
        }

        // Form 제출 시, Summernote의 내용을 textarea에 저장
        function submitContent() {
            var content = $('#summernote').summernote('code'); // HTML 코드로 저장
            document.querySelector('textarea[name="content"]').value = content;
        }
    });
</script>

<c:import url="/footer" />
