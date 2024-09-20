# 배종원의 프로젝트 멍냥고홈 입니다.

### 목차

> 1. [사용 기술 및 개발환경](#사용-기술-및-개발환경)
> 2. [ERD테이블](#ERD테이블)
> 3. [디렉터리 구조](#디렉터리-구조)
> 4. [구현 기능](#구현-기능)
>   + [회원가입](#회원가입regist)
>   + [로그인](#로그인login)
>   + [마이페이지](#마이페이지mypage)
>   + [개인분양](#개인분양personal)
>   + [보호센터](#보호센터center)
>   + [대댓글](#대댓글comments)
>   + [좋아요](#좋아요likes)
>   + [기부하기](#기부하기support)
>   + [지식공유](#지식공유infoBoard)
> 5. [추가 구현하고 싶은 기능들](#추가-구현하고-싶은-기능들)
> 6. [프로젝트를 하며 느낀 점](#프로젝트를-하며-느낀-점)


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

```
src/main/java
├── com.shelter_project
│   ├── AwsS3Config.java
│   ├── HomeController.java
│   ├── PageDTO.java
│   ├── ShelterProjectApplication.java
│
├── center (보호센터)
│   ├── CenterController.java 
│   ├── CenterDTO.java
│   ├── CenterImgDTO.java
│   ├── CenterMapper.java
│   ├── CenterService.java
│
├── infoBoard (지식공유)
│   ├── CommentController.java
│   ├── CommentDTO.java
│   ├── CommentMapper.java
│   ├── CommentService.java
│   ├── InfoBoardController.java
│   ├── InfoBoardDTO.java
│   ├── InfoBoardMapper.java
│   ├── InfoBoardService.java
│
├── likes (좋아요)
│   ├── LikeController.java
│   ├── LikeDTO.java
│   ├── LikeMapper.java
│   ├── LikeService.java
│
├── member (회원가입/로그인)
│   ├── KaKaoService.java
│   ├── MemberController.java
│   ├── MemberDTO.java
│   ├── MemberMapper.java
│   ├── MemberService.java
│
├── personal (개인분양)
│   ├── PersonalController.java
│   ├── PersonalDTO.java
│   ├── PersonalImagesDTO.java
│   ├── PersonalMapper.java
│   ├── PersonalService.java
│
└── support (기부하기)
    └── SupportController.java
    └── SupportService.java
```

# 구현 기능
  
  ### 회원가입(regist)
  - [x] 아이디 / 비밀번호 / 비밀번호 확인 / 이름 / 이메일 / 우편번호 / 주소 / 상세주소 / 전화번호 인증 후 가입
  - [x] 비밀번호는 암호화되어 DB에 저장
  - [x] 전화번호 중복 불가능
  - [x] 전화번호 인증 문자는 3분간 유지

  ### 로그인(login)
  - [x] 가입한 ID와 비밀번호로 로그인 가능
  - [x] 카카오 계정으로 로그인이 가능
  - [x] 카카오 계정으로 로그인 시 정보를 회원 DB에 저장
  - [x] 아이디 분실 시 이름/전화번호 인증으로 찾기 가능
  - [x] 비밀번호 분실 시 이름/아이디/전화번호 인증 후 새 비밀번호로 변경

  ### 마이페이지(mypage)
 - [x] 프로필 관리에서 회원 정보 확인 및 변경 가능
 - [ ] 게시글 관리에서 좋아요 한 글과 작성한 글 확인

### 개인분양(personal)
- [x] 로그인 후 게시글 작성 가능
- [X] summernote에디터 사용함으로써 편의성 제공
- [x] 게시글 작성 시 썸네일 이미지 다중 업로드
- [x] 이름 / 품종으로 검색 가능
- [x] 카테고리(전체 / 강아지 / 고양이 / 기타)로 게시글 모아보기
- [x] 작성자만 게시글 수정 및 삭제 가능
- [x] 최신글 4개 메인페이지에 노출

### 보호센터(center)
- [x] 공공데이터 API 연동(서울 열린데이터 광장)
- [x] 스케쥴러로 10분마다 데이터를 얻어와 DB에 저장 또는 데이터 업데이트
- [x] 이름 / 품종으로 검색 가능
- [x] 카테고리(전체 / 강아지 / 고양이)로 게시글 모아보기

### 대댓글(comments)
- [x] 로그인 후 댓글 작성 가능
- [x] 대댓글 작성 시 자식 댓글은 부모 댓글 아래 위치되며, 부모 댓글 삭제 시 자식 댓글은 함께 삭제
- [x] 게시글 삭제 시 해당 게시글의 모든 댓글은 삭제

### 좋아요(likes)
- [ ] 게시글에 좋아요를 누르면 마이페이지에서 내가 좋아요 한 글을 모아보기
  
### 기부하기(support)
- [x] 로그인 후 이용 가능
- [x] 아임포트(NHN KCP)로 결제
  
### 지식공유(infoBoard)
- [x] 제목 / 작성자 로 게시글 검색
- [x] 작성자만 게시글 수정 및 삭제 가능

# 추가 구현하고 싶은 기능들
- [ ] s3에 업로드 되는 이미지에 해시값을 부여해서 중복 검사
- [ ] redis 사용
- [ ] 프로젝트 디자인 반응형 (모바일 / 테블릿 / pc)

# 프로젝트를 하며 느낀 점
팀 프로젝트와 달리 프로젝트를 처음부터 끝까지 혼자 진행하면서, 기존에 애매했던 부분들이 이해되고 개념이 정리되는 느낌을 받았습니다. <br>
또한, 이전에 맡지 못했던 공공데이터 API 연동을 직접 해보며 새로운 기술을 다루는 경험을 할 수 있어 매우 좋았습니다.<br>
전 프로젝트에서 게시판 구현 시 아쉬웠던 부분이나 구현하지 못했던 기능들을 이번 프로젝트에서 보강하며, 스스로 문제를 해결하는 성취감을 느낄 수 있었습니다.<br>
이번 프로젝트를 마무리하면서도 여전히 부족한 부분들이 보였기에, 앞으로도 지속적으로 학습하고 개선해 나갈 계획입니다.


