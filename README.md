# Spring Boot Practice

Spring MVC의 요청 처리, Thymeleaf 화면 구성, JPA·QueryDSL의 DB 접근을 익힌 수업·복습 실습. 각 폴더는 독립적인 Gradle 프로젝트입니다.

## 실습 목록

| 폴더 | 내용 |
| --- | --- |
| [ch1/ch1](./ch1/ch1/) | Controller, 요청 파라미터, Model Binding, Thymeleaf |
| [ch1_teacher](./ch1_teacher/) | 강사 예제: Interceptor·AOP, 회원·게시판·공지 |
| [thymeleaf-practice](./thymeleaf-practice/thymeleaf-practice/) | 회원가입 폼, 게시판 목록, 공통 Fragment |
| [ch2/ch2](./ch2/ch2/) | Entity 관계, Repository, JPA 테스트, QueryDSL Join |
| [ch1_mission](./ch1_mission/ch1_mission/) | 로그인 확인 Interceptor 미션 · 작성 중 |

## 살펴볼 코드

- **요청→화면:** Controller의 요청값 처리와 Model 전달, Thymeleaf 템플릿 연결
- **공통 요청 처리:** 강사 예제의 HandlerInterceptor와 AOP
- **데이터 접근:** Entity 관계와 Repository 테스트, QueryDSL Join 테스트 소스

## 실행

Java 21·Gradle 기준. 실행할 프로젝트 폴더에서 해당 Wrapper를 사용합니다.

```bash
cd ch1/ch1
./gradlew bootRun
```

Windows에서는 `gradlew.bat bootRun` 사용.

`ch2/ch2`는 MySQL 스키마와 다음 환경변수 준비 후 실행합니다.

| 변수 | 값 |
| --- | --- |
| DB_URL | 로컬 springboot 스키마의 JDBC URL |
| DB_USERNAME | 실습 DB 계정 |
| DB_PASSWORD | 해당 계정의 비밀번호 |

## 진행 메모

`ch1_mission`의 LoginCheckInterceptor.preHandle()은 본문 작성 전입니다. QueryDSL 조건·집계 미션 일부는 주석 상태이며, 실행할 테스트는 각 파일에서 확인할 수 있습니다.

[통합 실습](https://github.com/Eunhye-yoo/fullstack-integration-lab) · [프로필](https://github.com/Eunhye-yoo)
