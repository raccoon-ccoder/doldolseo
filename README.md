# 돌고돌아서울

## 프로젝트 개요
다양한 여행 관련 플랫폼을 사용하면서 바라는 점이나 추가되었으면 하는 부분들에서 고안한 프로젝트로 기존 플랫폼들과 차별화하여 **서울시 관광 정보 제공, 플래너 작성, 여행 후기 공유, 여행 크루 모집**과 같은 기능을 가진 Spring Framework를 이용한 서울 여행 웹 애플리케이션을 제작하였습니다. 

## 프로젝트 기간 & 팀원
- 2021.07.12 ~ 2021.08.16
- 김경일(팀장), 백정연(팀원), 이영(팀원)

## 기술 스택
- 개발 언어 : Java (1.8)
- 개발 환경 : IntelliJ IDEA
- Front-End : HTML5, CSS3, JavaScript, JQuery, Ajax
- Back-End : Spring boot, Gradle, Spring-Data-JPA, Spring-Security
- DB : Oracle 19c
- 배포 : AWS EC2 (Amazon Linux)

## ERD
![doldolseo_erd2](https://user-images.githubusercontent.com/77538818/133370496-71dbe3f5-4e21-40a7-a100-e2b0b16a76e7.png)

## 돌고돌아서울 깃허브 주소
[돌고돌아서울 github](https://github.com/kki7823/doldolseo)

## 돌고돌아서울 웹사이트
[돌고돌아서울 둘러보기](http://52.78.185.163:8080/doldolseo/main)

## Features
- 회원가입, 로그인, 회원 탈퇴
- 여행 플래너, 후기 및 댓글 작성
- 크루 생성, 가입 및 크루 활동글, 댓글 작성
- 서울 각 지역 관광지 정보 확인


## What I Did
- Spring Security를 활용한 회원가입, 로그인, 회원 탈퇴 기능 및 회원 관련 페이지 담당
- 카카오지도 API 활용 여행 플래너 CRUD 기능 및 플래너 관련 페이지 담당
- Oracle 클라우드, AWS (Amazon Web Services)를 이용한 프로젝트 배포 

## 협업 방식
- DB 및 Server : ERD Cloud를 먼저 작성하여 DB 설계 관련 회의을 통해 최종 ERD 완성 후 데이터베이스 구축, 테스트 데이터 삽입 후 AWS에 프로젝트를 배포하여 서버 공유

<img width="900" alt="스크린샷 2021-09-24 오후 4 06 53" src="https://user-images.githubusercontent.com/77538818/134632776-04f24149-9465-4987-a406-36488967e07a.png">


- 각 기능별로 브랜치를 생성하여 커밋룰에 따라서 작업, TO DO LIST를 만들어 팀원들의 진행 상황과 일정 체크 공유

<img width="300" height="300" src="https://user-images.githubusercontent.com/77538818/134633058-19429a65-7f85-4773-bf38-b8029bf9e532.png" align="left"/> <img width="530" height="250" src="https://user-images.githubusercontent.com/77538818/134633235-756b79db-df26-438e-9658-5155c9c233d4.png" align="right"/>




## 문제해결 아카이브

 + 3명이 함께 작업을 하기에 효율적인 프로젝트 관리와 협업에 대한 고민
	 - 프로젝트 제작에 앞서 변수 네이밍, 주석 형식 지정과 같은 작업을 진행
	 - 프로젝트는 Github에서 관리하고 기능별로 브랜치를 생성하여 작업 후 commit하고 변경된 사항을 Pull Request하는 방식으로 진행

+ 시행착오를 줄이고자 작업 시작 전 프로젝트 기능, UI 관련 세부 사항까지 정리
	- 팀원들의 아이디어를 취합하여 와이어프레임 작성
	- 기능 사항 회의 완료 후 각자 맡은 페이지에 대한 스토리보드 작성 
	사진 추가나 링크 추가
	 
+ 서로 어떤 작업을 하고 있는지 잘 몰라서 생기는 의사소통 문제와 오해
	- To Do List를 작성하고 아침마다 오늘 할 작업을 팀원에게 브리핑하여 크로스체크하며 일정 조율
	- Commit 메세지 규칙을 정함으로써 직관적으로 서로의 작업을 공유 

+ 수정한 프로젝트를 AWS E2C 서버에 올릴 때마다 프로젝트 내부 폴더에 있던 이미지 데이터들이 날아가는 문제
	- tomcat의 webapps 폴더 내부가 아닌 외부에 이미지 업로드 폴더를 생성하고 server.xml에서 사진 업로드 경로를 설정하여 사용함으로써 해결  

+ 플래너 작성시 1개가 아닌, 여러 개의 데이터(일정)을 어떤 방식으로 Controller에게 넘기고 DB에 저장해야할지 고민
	- Plan 객체 구성에 필요한 데이터(날짜, 장소명, x좌표, y좌표, 메모, 시간)을 div 태그의 속성으로 부여 후 ajax를 통해 2차원 배열로 Controller에게 전달
	사진 추가
	- Service에서 전달 받은 String 타입의 2차원 배열을 알맞은 타입으로 가공 후 JPA의 saveAll() 메서드로 DB에 저장
	사진 추가 

