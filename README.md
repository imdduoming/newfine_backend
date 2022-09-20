# 🏫 newfine 학생성장 동기부여 앱 프로젝트

## 1. 서비스 기획 의도

newfine 학원의 동기부여 프로그램을 위한 앱으로 학생들의 출석, 자습, 시험, 과제 성적에 따라 점수가 부여됩니다. 점수에 따른 순위를 실시간으로 학생들에게 보여주고, 순위에 대한 적절한 보상으로 학생들의 학습 동기를 향상시키는 것이 이 앱의 목표입니다. 

기존에는 학원 관리자의 수기로 이루어졌던 출석, 자습, 과제, 테스트 등의 기능들이 앱을 통해 자동으로 수행됩니다. 

- 선생님/관리자는 앱을 통해 수강생 목록 / 출석 관리 / 과제 게시글 등록 / 학생 과제 체크 / 학생 테스트 결과 확인 / 학생 랭킹 정보 확인 등의 기능을 사용할 수 있습니다.

- 학생은 앱을 통해 QR 출석체크 / 과제 확인 / 테스트 결과 확인 / 전체 랭킹 확인 / 마이페이지 등의 기능을 사용할 수 있습니다.

이처럼 기존에는 일일이 관리자의 확인이 필요하던 작업을 I’m NF 앱이 대신 수행해주어 학생과 선생님, 학원관리자 모두 보다 더 슬기로운 학원생활을 경험할 수 있습니다.

## 2. 기간 및 팀 구성

- 2021.6 월 중순  ~ 2021. 9월 초
- 서버/앱 개발자 3명

## 3. 기술스택

- Spring Boot / Java 11 / JPA / Mysql (AWS RDS) / Elastic Cache Redis
- 배포 : Github action & Elastic Beanstalk & Docker 를 사용하여 CI/CD 구축
- 앱개발 : React Native 를 사용하여 IOS & Android 동시 개발

## 4. 주요 기능
<img src="https://user-images.githubusercontent.com/60255575/190112933-204d4d05-2d6e-4bd4-8c71-8ed846d79d33.jpg"  width="650" height="350"/>
<img src="https://user-images.githubusercontent.com/60255575/190112948-34672419-0a26-497f-b5ba-755c3df1e186.jpg"  width="650" height="350"/>
<img src="https://user-images.githubusercontent.com/60255575/190112963-7fba8cd8-e79a-45b9-bc08-489b25b9c73b.jpg"  width="650" height="350"/>
<img src="https://user-images.githubusercontent.com/60255575/190112968-d4ab006d-32f0-4732-ab86-d690c891675b.jpg"  width="650" height="350"/>

## 5. 배포 아키텍처
<img src="https://user-images.githubusercontent.com/60255575/190094175-b3880897-1cda-4552-8784-be07192f4d32.png"  width="650" height="350"/>

## 6. 역할
|개발자|구현 기능|
|------|---|---|
|이수영|서버구축/배포 , 수업 , 춠석 , 자습 , 테스트 분석 , 테스트 수행|
|정문경|회원가입 및 로그인, 회원정보 , 프론트관리 , 랭킹 , 테스트업로드 , 안드로이드 배포 및 출시|
|조윤아|과제, 푸시알림 관리 , IOS 프론트 수정 , IOS 배포 및 춠시|
