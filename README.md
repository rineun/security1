# security1
스프링부투 기본로그인 + OAuth2.0 통합 구현

<img width="1041" alt="스크린샷 2022-02-18 오후 3 02 05" src="https://user-images.githubusercontent.com/80495141/154626968-2c8a951e-f882-469e-87b9-bd23e64c2ebb.png">

### 세션정보 확인
http://localhost:8080/test/login

### 계정 : test/test,  ROLE = ROLE_USER
http://localhost:8080/user

### 계정 : manager/manager,  ROLE = ROLE_ADMIN OR ROLE_MANAGER
http://localhost:8080/manager

### 계정 : admin/admin,  ROLE = ROLE_ADMIN
http://localhost:8080/admin


## query Role update
```
update user set role = 'ROLE_MANAGER' where id =3;
update user set role = 'ROLE_ADMIN' where id =4;
COMMIT;
```



OAuth2-client

provider (구글, 페이스북,  네이버, 카카오)




