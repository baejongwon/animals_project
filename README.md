# 배종원의 프로젝트 멍냥고홈 입니다.

### 목차

> 1. [사용 기술 및 개발환경](#사용-기술-및-개발환경)
> 2. [ERD테이블](#ERD테이블)
> 3. [구현 기능](#구현-기능)
>   + [회원 가입 및 관리](#회원-가입-및-관리member)

# 사용 기술 및 개발환경
server : Apach Tomcat 9 <br>
DB : MariaDB 10.6.14 <br>
Framework/Flatform : Spring MVC, SpringSecurity, MyBatis, Bootstrap, jQuery, Jsp, RESTful API<br>
Language : JAVA(version 17), Javascript, HTML5, CSS3<br>
Tool :  Git, GitHub,SQL Developer<br>
AWS : EC2 / RDS / S3

# 인프라-아키텍처
<img src="https://github.com/baejongwon/jongwon-git-img/blob/main/Infrastructure%20Architecture.jpg" width=1200px alt="ERD 테이블"> 

# 구현 기능
  
  ### 회원 가입 및 관리(member)
  [공통]
  - 사용자는 약관에 모두 동의 후 아이디 / 비밀번호 / 비밀번호 확인 / 이름 / 이메일 / 우편번호 / 주소 / 상세주소 / 전화번호 인증 후 가입
  - 비밀번호는 암호화되어 DB에 저장
  - 회원가입 시 사용한 아이디와 비밀번호로 로그인
  - 아이디나 비밀번호 찾기 시 아이디는 이름과 전화번호 / 비밀번호는 아이디와 전화번호를 사용해 찾기 가능
  - 마이페이지에서 내 정보 보기, 회원 수정, 내 예약 조회, 지난 예약, 도서 대출 목록, 회원 탈퇴가 가능
  - 카카오 아이디로 로그인이 가능
