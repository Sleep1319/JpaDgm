<img src="https://capsule-render.vercel.app/api?type=wave&color=auto&height=200&section=header&width=1000&text=JpaProject%20&fontSize=90" />
<div>  
  <h3>개발 기간</h3>
  23.11.06 ~ 23.11.10
  <h3>참여 인원</h3>
  4명
  <h3>사용 언어</h3>
  <img src="https://img.shields.io/badge/Java-FF7800?style=flat&logo=Java&logoColor=white"/>
  <img src="https://img.shields.io/badge/Javascript-F7DF1E?style=flat&logo=Javascript&logoColor=white"/>
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=HTML-5&logoColor=white"/>
  <img src="https://img.shields.io/badge/CSS-1572B6?style=flat&logo=CSS&logoColor=white"/>
  
  <h3>사용 툴</h3>
  <img src="https://img.shields.io/badge/intellijidea-000000?style=flat&logo=intellijidea&logoColor=white"/>
  <img src="https://img.shields.io/badge/visualstudiocode-007ACC?style=flat&logo=visualstudiocode&logoColor=white"/>
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=flat&logo=springboot&logoColor=white"/>
  
  <h3>메인 개발 환경</h3>
  <img src="https://img.shields.io/badge/intellijidea-000000?style=flat&logo=intellijidea&logoColor=white"/>
  
  <h3>부트 의존성</h3>
  <img src="https://img.shields.io/badge/thymeleaf-005F0F?style=flat&logo=thymeleaf&logoColor=white"/>
  <img src="https://img.shields.io/badge/jpa-83B81A?style=flat&logo=jpa&logoColor=white"/>
  <img src="https://img.shields.io/badge/web-0085CA?style=flat&logo=web&logoColor=white"/>
  <img src="https://img.shields.io/badge/lombok-FF9A00?style=flat&logo=lombok&logoColor=white"/>
  <img src="https://img.shields.io/badge/h2-000000?style=flat&logo=h2&logoColor=white"/>
  <img src="https://img.shields.io/badge/validation-EF2D5E?style=flat&logo=validation&logoColor=white"/>
  <img src="https://img.shields.io/badge/devtools-0099E5?style=flat&logo=devtools&logoColor=white"/>
  <img src="https://img.shields.io/badge/mysql-4479A1?style=flat&logo=mysql&logoColor=white"/>

  <hr>
  <h2>jpa를 활용한 MySql 기반 쇼핑몰 구현 프로젝트</h2>
  Jpa의 이해와 CRUD의 흐름을 이해 하기 위해 시작한 프로젝트 이다 
  <hr>
</div>
<div>

<h3>기능</h3>
<ul>
<li>회원 가입, 로그인, 정보 수정</li>
<li>상품 구매 주문 목록 조회</li>
<li>관리자 계정 전용 회원 정보 조회, 수정, 삭제</li>
<li>관리자 계정 전용 상품 정보 등록, 수정, 삭제</li>
</ul>
    <hr>

<h3>이슈</h3>
<ul>
<li>결제후 주문등록 Lazy관련 에러 => Eager로 처리(추가로 부하가 심해짐)</li>
<li>결제후 주문페이지 이동시 디비에 주문 등록 되기 전에 사이트 로딩</li>
<li>게시판 최신순 출력, 페이징 처리 에러</li>
<li>게시판 댓글 에러</li>
</ul>
<hr>

<h3>추후 업데이트, 수정 계획</h3>
<ul>
<li>주문 등록 연관테이블 Lazy로 변경후 JPQL활용 식으로 이용</li>
<li>게시판 순서 페이징처리 재설정</li>
<li>게시판 상세보기 css 추가</li>
<li>상품 이미지 추가</li>
<li>결제 전 결제금액 검증식 필요</li>
</ul>

<pre>
*주의사항*
userBuy.html 스크립트 구문 포트원 테스트 결제 번호 필요
yml 본인 mysql 계정정보 등록 필요
yml 27구문 update로 변경시 InitDb 클래스파일 주석처리 필요
</pre>
</div>
