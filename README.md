 # <img src="https://user-images.githubusercontent.com/77538818/135027843-793631d9-9032-432b-a90e-37bae4ac810e.png" width="10%"> 돌고돌아서울

## 프로젝트 개요
다양한 여행 관련 플랫폼을 사용하면서 바라는 점이나 추가되었으면 하는 부분들에서 고안한 프로젝트로 기존 플랫폼들과 차별화하여 **서울시 관광 정보 제공, 플래너 작성, 여행 후기 공유, 여행 크루 모집**과 같은 기능을 가진 Spring Framework를 이용한 서울 여행 웹 애플리케이션을 제작하였습니다. 

- 특징
    - 서울 각 지역 관광지 리스트 및 상세정보, 지역 및 테마에 따른 카테고리 분류, 검색 기능 제공
    - 나만의 여행 플래너 작성 서비스 (일정 관리 가능)
    - 여행 리뷰 공유 게시판 (조회수 순으로 베스트 코스 선정, 댓글)
    - 다양한 테마의 크루 모집 및 크루 게시판을 통한 커뮤니티 기능
    - 한국관광공사 Tour API, KAKAO Map API, OpenWeather 날씨 API 사용

- 개발 기간 : 2021.07.12 ~ 2021.08.16
- 참여 인원 : 3명 (프론트엔드, 백엔드 구분 없음)
- 담당 파트
    - Kakao Map API 활용 플래너 CRUD 구현 및 플래너 관련 페이지 담당
    - Bcrypt 사용하여 사용자 PW 암호화, Spring Security를 활용한 인증, 인가 구현
    - 회원 가입, 회원 탈퇴 구현 및 마이 페이지 담당
    - AWS (Amazon Web Services)를 이용한 프로젝트 배포
    - REST API 적용 리팩토링 및 업데이트 중




## 기술 스택
- 개발 언어 : Java (1.8)
- 개발 환경 : IntelliJ IDEA
- Front-End : HTML5, CSS3, JavaScript, JQuery, Ajax
- Back-End : Spring boot, Gradle, Spring-Data-JPA, Spring-Security
- DB : Oracle 19c
- 배포 : AWS EC2 (Amazon Linux)

## 돌고돌아서울 깃허브 주소
[돌고돌아서울 github](https://github.com/kki7823/doldolseo)


## 돌고돌아서울 웹사이트
[돌고돌아서울 둘러보기](http://52.78.185.163:8080/doldolseo/main)

( test용 계정 - ID : test, PASSWORD : test1234! )

## 설계
### ERD
![doldolseo_erd2](https://user-images.githubusercontent.com/77538818/133370496-71dbe3f5-4e21-40a7-a100-e2b0b16a76e7.png)

### 스토리보드
![storyboard](https://user-images.githubusercontent.com/77538818/135047527-2c1d76f6-67e9-4ba0-bc68-7925f2d6afc1.gif)


## 스크린샷
### 메인 & 지역별 게시판
![main,area](https://user-images.githubusercontent.com/77538818/135030622-f1b72c20-26c1-4c43-9a1e-590b8d95a22c.gif)

### 회원가입 & 로그인
![join login](https://user-images.githubusercontent.com/77538818/135034052-716ba254-5c9f-49f8-b703-65df01e04515.gif)


### 플래너 작성, 조회
![planner](https://user-images.githubusercontent.com/77538818/135028402-521c60f9-cea3-4dd7-b237-82768dfd8ba8.gif)

### 리뷰글 작성, 조회
![review](https://user-images.githubusercontent.com/77538818/135034017-dd3ed081-eba8-4b53-963e-4384910cb6a2.gif)


### 크루 가입 및 크루 게시판 조회
![crew](https://user-images.githubusercontent.com/77538818/135034093-37c764b3-4124-498f-86e7-52841bc53d6a.gif)


## 협업 방식
- DB 및 Server : ERD Cloud를 먼저 작성하여 DB 설계 관련 회의을 통해 최종 ERD 완성 후 데이터베이스 구축, 테스트 데이터 삽입 후 AWS에 프로젝트를 배포하여 서버 공유

<img width="900" alt="스크린샷 2021-09-24 오후 4 06 53" src="https://user-images.githubusercontent.com/77538818/134632776-04f24149-9465-4987-a406-36488967e07a.png">


- 각 기능별로 브랜치를 생성하여 커밋룰에 따라서 작업, TO DO LIST를 만들어 팀원들의 진행 상황과 일정 체크 공유
<img src="https://user-images.githubusercontent.com/77538818/134633058-19429a65-7f85-4773-bf38-b8029bf9e532.png" width="37%" /><img src="https://user-images.githubusercontent.com/77538818/134633235-756b79db-df26-438e-9658-5155c9c233d4.png" width="63%" />




## 문제해결 아카이브

1. Open API에서 제공하는 여러 데이터를 Controller에게 전달하여 DB에 저장하는 방법
- 고민 : 플래너 작성시 키워드로 장소 검색 후 일정 추가시 장소에 대한 정보(x좌표, y좌표)를 어떻게 보관하고 플래너 저장시 여러 개의 일정 데이터를 어떤 방식으로 Controller에게 전달할 수 있을까?
- 해결 : 검색한 장소 목록에서 일정 추가 버튼 클릭시 일정 관련 div가 생성되는데 이때 div 태그에 임의 속성명을 지정하여 속성값으로 x좌표,y좌표를 전달하였고 플래너 저장 버튼 클릭시 ajax를 호출하고 일정 관련 데이터들을 배열에 담아 Controller에게 json 형식으로 전달하였습니다.
    
![스크린샷 2021-10-25 오후 10 35 23](https://user-images.githubusercontent.com/77538818/140046628-e1ae3fee-89fd-458f-be7e-0fd5016ad9af.png)
    
KAKAO Map API에서 조회된 데이터 기반으로 일정 추가시 일정 관련 div 생성하는 함수

![스크린샷 2021-10-12 오후 12 02 32](https://user-images.githubusercontent.com/77538818/140046645-c973c19d-7c4f-4d3e-bd56-266224d75c35.png)

플래너 저장시 배열에 일정 관련 데이터를 넣어 json 타입으로 Controller에게 전달

![스크린샷 2021-10-20 오후 7 06 47](https://user-images.githubusercontent.com/77538818/140046650-c2f4e8a0-51f5-462b-aed4-2d4f7adea9b1.png)
    
@RequestBody 어노테이션을 이용해 List<Plan> 타입의  planList 객체와 바인딩하여 Service 단에서 JPA의 save() 메소드로 Database에 Plan 데이터 저장    
    

2. Spring MVC 패턴으로 개발한 프로젝트를 RESTful 하게 리팩토링하는 방법
- 고민 : @Controller 어노테이션으로 View를 반환하는 Controller에서 @RestController로 변경하면 View를 반환할 수가 없는데 어떤 방식으로 URI의 요청결과를 반환해야 할까?
- 해결 : View를 반환하는 Controller, 데이터를 반환하는 APIController로 분리하였고 데이터 저장,수정, 삭제의 경우 클라이언트 화면에서 버튼을 누르는 등의 동작에 따라 자바스크립트로 비동기 요청을 보냈습니다. 이런 요청들에 대해 PlannerAPIController에서 가장 깔끔한 JSON 형식으로 이동할 URI와 응답 결과를 반환하였습니다.

![스크린샷 2021-10-25 오후 11 08 57](https://user-images.githubusercontent.com/77538818/140046928-2e68d379-7f7e-4787-9558-c6166a027cf8.png)
	
플래너 상세 페이지에서 플래너 삭제 버튼 클릭시 실행되는 자바스크립트 함수로 ajax 실행하여PlannerAPIController의 플래너 삭제 메소드 호출

![스크린샷 2021-10-25 오후 11 16 15](https://user-images.githubusercontent.com/77538818/140046949-c9bc253c-d4f6-4c57-b1ef-e3fe887cd315.png)
	
삭제할 플래너가 존재하지 않을 경우 오류 메시지와 상태 반환, 삭제가 완료되었다면 플래너 목록으로 이동하는 URI와 성공 메세지 반환

3. AWS EC2에 프로젝트 배포시 이미지 파일 저장 경로
- 고민 : 프로젝트 내부에 이미지 경로를 설정하여 저장할 경우 수정한 프로젝트를  AWS E2C 서버에 올릴 때마다 프로젝트에 사용되는 이미지 데이터들이 날아가는데 어떤 방식으로 변경해야 할까?
- 해결 : 프로젝트 내부 경로 (tomcat/webapps/프로젝트/이미지 폴더명) 이 아닌 외부(webapps 폴더 외부)에 이미지 업로드 폴더 생성 후 톰캣의 server.xml에서 사진 업로드 경로를 설정하여 사용함으로써 해결하였습니다.

