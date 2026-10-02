# Spring Boot Practice

수업 예제와 개인 미션으로 Spring MVC, Thymeleaf, JPA를 학습한 기록입니다. 각 폴더는 **독립적인 Gradle 프로젝트**이며, 완성된 하나의 서비스가 아닙니다.

## Contents

| 경로 | 확인할 내용 | 구분 |
| --- | --- | --- |
| [`ch1/ch1`](./ch1/ch1/) | Controller, 요청 파라미터, Model Binding, Thymeleaf·Layout/Fragment | 수업 실습 |
| [`ch1_mission/ch1_mission`](./ch1_mission/ch1_mission/) | 로그인 확인 Interceptor 미션 골격 | 개인 미션 · 미완성 |
| [`ch1_teacher`](./ch1_teacher/) | MVC·Interceptor·AOP 및 회원·게시판·공지 예제 | 수업/비교용 코드 |
| [`thymeleaf-practice/thymeleaf-practice`](./thymeleaf-practice/thymeleaf-practice/) | 회원가입 폼, 게시판 목록, 공통 공지 Fragment | 복습 실습 |
| [`ch2/ch2`](./ch2/ch2/) | Entity 관계, Repository, JPA 동작 테스트, QueryDSL Join 예제 | DB/JPA 수업 실습 |

## Current Scope

`ch1_mission/ch1_mission`의 `LoginCheckInterceptor.preHandle()`은 현재 반환값·처리 본문이 비어 있어 컴파일 가능한 완료 구현으로 안내할 수 없습니다. 수업용 `ch1_teacher`의 Interceptor/AOP와 개인 미션의 구현 상태를 구분합니다.

**환경:** Java 21, Spring Boot 4.1.1, Gradle. `ch2`에서는 MySQL, Spring Data JPA, QueryDSL 5.0.0을 사용합니다. 그 외 폴더는 각자의 `build.gradle`에 적힌 의존성을 확인하세요.

## Learning Scope

- **MVC·화면:** `@Controller`, GET/POST 매핑, 요청값 바인딩, Model 전달, Thymeleaf 템플릿·Fragment.
- **요청 공통 처리:** 수업 예제의 `HandlerInterceptor`·AOP 구조와 개인 미션 골격 비교.
- **JPA:** `@ManyToOne`, `@OneToOne` 관계와 Repository 동작을 테스트 코드로 확인.
- **QueryDSL:** `QuerydslJoinTest`에 `@Test`가 붙은 Join 테스트 코드가 있습니다. 이번 문서 검토에서 실행한 테스트는 아닙니다. 조건 조회·집계·동적 검색을 다룬 `BoardQuerydslTest` 및 `BoardQuerydslMissionTest`는 현재 주석 상태이므로 완료한 테스트로 표시하지 않습니다.

테스트 파일의 존재가 모든 예제의 통과를 뜻하지는 않습니다. 특히 `ch2/ch2`는 데이터베이스 연결 설정이 필요한 실습입니다.

## Run

프로젝트별 폴더로 이동하여 각 프로젝트의 Gradle Wrapper를 사용합니다. 예를 들어 macOS/Linux에서 `ch1`을 실행하려면:

~~~bash
cd ch1/ch1
./gradlew bootRun
~~~

Windows에서는 같은 폴더에서 `gradlew.bat bootRun`을 실행할 수 있습니다. 데이터베이스를 사용하는 `ch2/ch2`는 `application.properties`의 로컬 DB 설정을 먼저 확인해야 합니다.
