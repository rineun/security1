
## 👩🏻‍💻 Springboot 기본로그인 + OAuth2.0 통합 구현

<img width="1041" alt="스크린샷 2022-02-18 오후 3 02 05" src="https://user-images.githubusercontent.com/80495141/154626968-2c8a951e-f882-469e-87b9-bd23e64c2ebb.png">

## 👩🏻‍💻 시큐리티 기본
- 시큐리티 설정
- 시큐리티 회원가입
- 시큐리티 로그인
- 시큐리티 권한처리

## 👩🏻‍💻 스프링 시큐리티 OAuth2.0((구글, 페이스북,  네이버, 카카오)
- 구글로그인 준비
- 구글 회원 프로필 정보 받아보기
- Authentication객체가 가질수 있는 2가지 타입
- 구글 로그인 및 자동 회원가입 진행
- 페이스북 로그인
- 네이버 로그인
- 카카오톡 로그인



#### 세션정보 확인
 - http://localhost:8080/test/login

#### 계정 (test/test)  ROLE = ROLE_U일SER
- http://localhost:8080/user

#### 계정 (manager/manager)  ROLE = ROLE_ADMIN OR ROLE_MANAGER
- http://localhost:8080/manager

#### 계정 (admin/admin)  ROLE = ROLE_ADMIN
- http://localhost:8080/admin


### query Role update
```
update user set role = 'ROLE_MANAGER' where id =3;
update user set role = 'ROLE_ADMIN' where id =4;
COMMIT;
```









