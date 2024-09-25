<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/header" />
<link rel="stylesheet" href="center.css">
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

/* 썸네일 박스 및 레이아웃 */
.box {
    display: flex;
    gap: 20px;
    align-items: flex-start;
}

.thumbnail-box {
    width: 400px;
    height: 250px;
    border: 1px solid #ebedf2;
    padding: 10px;
    border-radius: 10px;
    text-align: center;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.thumbnail-upload input {
    display: block;
    margin: 10px auto;
}

.thumbnail-preview img {
    max-width: 100%;
    max-height: 100%;
}

.info-inputs {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    flex-grow: 1;
}

.input-group {
    display: flex;
    flex-direction: column;
    width: calc(50% - 10px); /* 한 줄에 2개씩 배치 */
}

.input-group label {
    font-size: 14px;
    font-weight: 600;
    margin-bottom: 5px;
}

.input-group input, .input-group select {
    width: 100%;
    padding: 10px;
    border: 1px solid #ebedf2;
    border-radius: 5px;
    font-size: 14px;
}

.input-group input:focus, .input-group select:focus {
    border-color: #00823E;
}
/* 썸네일 박스 스타일 */
.thumbnail-box {
    width: 400px;
    height: auto;  /* 높이 자동으로 설정 */
    border: 1px solid #ebedf2;
    padding: 10px;
    border-radius: 10px;
    text-align: center;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
}

/* 미리보기 이미지 스타일 */
.thumbnail-preview img {
    border: 1px solid #ebedf2;
    border-radius: 5px;
}

</style>

<div class="banner" style="background-image: url('img/close-up-couple-with-dog-indoors.jpg'); background-position: 0 27%;">
    <div>
        <h1>Personal Adoption</h1>
		<p>개인 분양 게시판</p>
    </div>
</div>

<div class="container" style="margin-top: 75px;">
    <form action="personalWriteProc" method="POST" enctype="multipart/form-data" onsubmit="submitContent()">
        <div class="box" style="flex-wrap: nowrap; margin-top: 20px; justify-content: space-between;">
            
            <div class="thumbnail-box">
                <label for="thumbnailUpload">썸네일(.jpg,.jpeg,.png만 가능)</label>
                <div class="thumbnail-upload">
                    <input type="file" id="thumbnailUpload" name="images" accept=".jpg,.jpeg,.png" multiple onchange="previewThumbnails(event)">
                </div>
                <div class="thumbnail-preview" id="thumbnailsPreview" style="display: flex; flex-wrap: wrap; gap: 10px;">
                    
                </div>
            </div>

            <div class="info-inputs">
                <div class="input-group">
                    <label for="petName">이름</label>
                    <input type="text" id="nm" name="nm" placeholder="반려동물 이름" required>
                </div>
				<div class="input-group">
                    <label for="spcs">종</label>
                    <select id="spcs" name="spcs">
                        <option value="dog">강아지</option>
                        <option value="cat">고양이</option>
                        <option value="etc">기타</option>
                    </select>
                </div>
                <div class="input-group">
                    <label for="breed">품종</label>
                    <input type="text" id="breeds" name="breeds" placeholder="품종 (예: 푸들)" required>
                </div>
                <div class="input-group">
                    <label for="sexdstn">성별</label>
                    <select id="sexdstn" name="sexdstn">
                        <option value="M">수컷</option>
                        <option value="W">암컷</option>
                    </select>
                </div>
                <div class="input-group">
                    <label for="age">나이</label>
                    <input type="number" id="age" name="age" placeholder="나이 (예: 3)" required>
                </div>
                <div class="input-group">
                    <label for="bdwgh">체중</label>
                    <input type="number" id="bdwgh" name="bdwgh" placeholder="체중 (kg)" step="0.01" required>
                </div>
            </div>
        </div>
        
        <div style="margin-top: 20px;">
            <textarea id="summernote" name="content"></textarea>
        </div>

        <div class="action-btn-group" style="margin-top: 20px;">
            <div class="center">
                <button type="button" class="btn btn-light btn-lg w-sm" onclick="location.href='personalBoards'">목록</button>
                <button type="submit" class="btn btn-light btn-lg w-sm">작성</button>
                <button type="button" class="btn btn-light btn-lg w-sm" onclick="location.href='personalBoards'">취소</button>
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
                url: 'personal/addImageBlobHook',
                cache: false,
                contentType: false,
                processData: false,
                data: data,
                type: "POST",
                success: function(response) {
                    console.log(response);  // 서버 응답을 콘솔에 출력하여 확인

                    // 서버가 JSON 형식으로 응답한다고 가정
                    if (response.imagePath) {
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
    
function previewThumbnails(event) {
    var thumbnailsDiv = document.getElementById('thumbnailsPreview');
    thumbnailsDiv.innerHTML = "";  

    var files = event.target.files;
    
    for (var i = 0; i < files.length; i++) {
        var file = files[i];
        
        var img = document.createElement("img");
        img.src = URL.createObjectURL(file);
        
        img.style.width = "100px";  
        img.style.height = "100px";
        img.style.objectFit = "cover"; 
        
        thumbnailsDiv.appendChild(img);
    }
}
</script>

<c:import url="/footer" />