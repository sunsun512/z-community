# 커뮤니티

### 사용기술
- Java 8
- Spring Boot(+Gradle)
- JPA, QueryDSL
- Spring Security
- H2 Database 


### 구현 기능

#### 게시물 생성
- URL : POST localhost:8080/post
- 설명 : 로그인 필요, Body에 title과 contents 담아서 전송!
![image](https://user-images.githubusercontent.com/100753335/217154686-6b1b20bf-e407-46ef-b75a-4a15857ad08c.png)

- 응답 : 성공 - http 201, 생성 게시물 ID 반환, 실패 - http 400 에러


#### 게시물 수정
- URL : PUT localhost:8080/post/{postId}
- 설명 : 로그인 필요, Body에 title과 contents 담아서 전송!
![image](https://user-images.githubusercontent.com/100753335/217158344-c9c219a5-280c-4179-ae86-d601c3f99a97.png)

- 응답 : 성공 - http 200, 수정 게시물 ID 반환, 실패 - http 400 에러


#### 게시물 삭제
- URL : DELETE localhost:8080/post/{postId}
- 설명 : 로그인 필요, Body에 title과 contents 담아서 전송!
![image](https://user-images.githubusercontent.com/100753335/217158592-6f91741f-0dd7-462d-b05f-ea951b47611a.png)

- 응답 : 성공 - http 200, 수정 게시물 ID 반환, 실패 - http 400 에러


#### 게시물 좋아요 
- URL : POST localhost:8080/post/{postId}/like
- 설명 : 로그인 필요, 게시물Id와 사용자 accountId 사용
![image](https://user-images.githubusercontent.com/100753335/217158526-f4896aaf-8e70-4f29-8903-ed4c4b4186a5.png)

- 응답 : 성공 - http 201, 생성 게시물 ID 반환, 실패 - http 400 에러


#### 게시물 목록 조회
- URL : GET POST localhost:8080/posts
- 설명 : 로그인 필요 없음, 단 사용자가 조회시 좋아요한 게시물의 like = true, 좋아요한 게시물이 아니거나 일반사용자는 게시물의 liked = false
![image](https://user-images.githubusercontent.com/100753335/217158674-2f9e68e7-d640-4649-adb3-420253e77d20.png)

![image](https://user-images.githubusercontent.com/100753335/217158765-43a9db6c-6fee-44dd-9995-234341829500.png)

- 응답 : 성공 - 게시물 Page 반환




