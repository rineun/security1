
## ๐ฉ๐ปโ๐ป Springboot ๊ธฐ๋ณธ๋ก๊ทธ์ธ + OAuth2.0 ํตํฉ ๊ตฌํ

<img width="1041" alt="แแณแแณแแตแซแแฃแบ 2022-02-18 แแฉแแฎ 3 02 05" src="https://user-images.githubusercontent.com/80495141/154626968-2c8a951e-f882-469e-87b9-bd23e64c2ebb.png">

## ๐ฉ๐ปโ๐ป ์ํ๋ฆฌํฐ ๊ธฐ๋ณธ
- ์ํ๋ฆฌํฐ ์ค์ 
- ์ํ๋ฆฌํฐ ํ์๊ฐ์
- ์ํ๋ฆฌํฐ ๋ก๊ทธ์ธ
- ์ํ๋ฆฌํฐ ๊ถํ์ฒ๋ฆฌ

## ๐ฉ๐ปโ๐ป ์คํ๋ง ์ํ๋ฆฌํฐ OAuth2.0((๊ตฌ๊ธ, ํ์ด์ค๋ถ,  ๋ค์ด๋ฒ, ์นด์นด์ค)
- ๊ตฌ๊ธ๋ก๊ทธ์ธ ์ค๋น
- ๊ตฌ๊ธ ํ์ ํ๋กํ ์ ๋ณด ๋ฐ์๋ณด๊ธฐ
- Authentication๊ฐ์ฒด๊ฐ ๊ฐ์ง์ ์๋ 2๊ฐ์ง ํ์
- ๊ตฌ๊ธ ๋ก๊ทธ์ธ ๋ฐ ์๋ ํ์๊ฐ์ ์งํ
- ํ์ด์ค๋ถ ๋ก๊ทธ์ธ
- ๋ค์ด๋ฒ ๋ก๊ทธ์ธ
- ์นด์นด์คํก ๋ก๊ทธ์ธ



#### ์ธ์์ ๋ณด ํ์ธ
 - http://localhost:8080/test/login

#### ๊ณ์  (test/test)  ROLE = ROLE_U์ผSER
- http://localhost:8080/user

#### ๊ณ์  (manager/manager)  ROLE = ROLE_ADMIN OR ROLE_MANAGER
- http://localhost:8080/manager

#### ๊ณ์  (admin/admin)  ROLE = ROLE_ADMIN
- http://localhost:8080/admin


### query Role update
```
update user set role = 'ROLE_MANAGER' where id =3;
update user set role = 'ROLE_ADMIN' where id =4;
COMMIT;
```









