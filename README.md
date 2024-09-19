# 배종원의 프로젝트 멍냥고홈 입니다.

### 목차

> 1. [ERD테이블](#ERD테이블)
> 2. [사용 기술 및 개발환경](#사용-기술-및-개발환경)
> 3. [디렉터리 구조] (#디렉터리-구조)
> 4. [구현 기능](#구현-기능)
>   + [회원 가입 및 관리](#회원-가입-및-관리member)


# ERD테이블
<img src="https://github.com/baejongwon/jongwon-git-img/blob/main/20240919_185728.png" width=1200px alt="ERD테이블"> 

# 사용 기술 및 개발환경
server : Apach Tomcat 9 <br>
DB : MariaDB 10.6.14 <br>
Framework/Flatform : Spring MVC, SpringSecurity, MyBatis, Bootstrap, jQuery, Jsp, RESTful API<br>
Language : JAVA(version 17), Javascript, HTML5, CSS3<br>
Tool :  Git, GitHub,SQL Developer<br>
AWS : EC2 / RDS / S3


# 디렉터리 구조

# 구현 기능
  
  ### 회원가입(regist)
  
  - [x] 아이디 / 비밀번호 / 비밀번호 확인 / 이름 / 이메일 / 우편번호 / 주소 / 상세주소 / 전화번호 인증 후 가입
  - [x] 비밀번호는 암호화되어 DB에 저장
  - [ ] 전화번호 중복 불가능


  ### 로그인(login)
  
  - [x] 가입한 ID와 비밀번호로 로그인 가능
  - [x] 카카오 계정으로 로그인이 가능
  - [x] 카카오 계정으로 로그인 시 정보를 회원 DB에 저장
  - [x] 아이디 분실 시 이름/전화번호 인증으로 찾기 가능
  - [x] 비밀번호 분실 시 이름/아이디/전화번호 인증 후 새 비밀번호로 변경

  ### 마이페이지(mypage)
 - [x] 프로필 관리에서 회원 정보 확인 및 변경 가능
 - [ ] 게시글 관리에서 좋아요 한 글과 작성한 글 확인 가능

  ### 개인분양(personal)
  
- [x] 로그인 후 게시글 작성 가능
- [X] summernote에디터 사용함으로써 편의성 제공
- [x] 게시글 작성 시 썸네일 이미지 다중 업로드 가능
- [x] 이름 / 품종으로 검색 가능

  ### 대댓글(comments)
- [x] 로그인 후 게시글 작성 가능
  ### 좋아요(likes)

  ### 보호센터(center)
- [x] 공공데이터 API 연동(서울 열린데이터 광장)
- [x] 
  
  ### 기부하기(support)

  
  ### 지식공유(infoBoard)

  
