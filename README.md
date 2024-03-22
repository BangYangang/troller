# 달디달고 달디단 🌰밤양 ***GANG***

---
## 💜프로젝트 소개💜
### 🙏Troller🙏

- **Trello**를 모티브로 한 프로젝트입니다.

### ✍사용 기술
<img src="https://img.shields.io/badge/java-2F2625?style=for-the-badge&logo=buymeacoffee&logoColor=white">
<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/spring_security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
<br>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
<img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white">
<br>
<img src="https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">
<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">


# 🌄와이어 프레임
<details>
<summary>접기 / 펼치기</summary>

<img src="https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F83c75a39-3aba-4ba4-a792-7aefe4b07895%2Fba8994b3-4616-43eb-ad53-351e2bd7f035%2FTrello.jpg?table=block&id=0f4abf42-e1d4-4d37-b7a3-250ea885a97d&spaceId=83c75a39-3aba-4ba4-a792-7aefe4b07895&width=2000&userId=a7e4938d-89f8-4619-931c-f356be2b04c6&cache=v2">
</details>


# 📈 ERD
<details>
<summary>접기 / 펼치기</summary>

<img src="https://file.notion.so/f/f/83c75a39-3aba-4ba4-a792-7aefe4b07895/f1f4f115-0940-4590-aded-cd0d49f53590/Untitled.png?id=a96439c0-fc5f-41d4-983a-2a3aa05b3035&table=block&spaceId=83c75a39-3aba-4ba4-a792-7aefe4b07895&expirationTimestamp=1711180800000&signature=HLYusbyRywTTSfj1rKb-OQ7Czi6HrPswblPeck6qlrY&downloadName=Untitled.png">
</details>

# 📝 API 명세서

#### ㅤ[유저 기능 ](docs/user.md)ㅤ ㅤ-ㅤ 👨‍🦰이효일
#### [보드 관리 기능](docs/board.md) ㅤ-ㅤ 🧒유경진
#### [컬럼 관리 기능](docs/column.md)ㅤ -ㅤ 👩‍🦰채은지
#### [카드 관리 기능](docs/card.md)ㅤ -ㅤ 👶강하늘
#### ㅤ[댓글 기능](docs/comment.md) ㅤㅤ -ㅤ 👶강하늘

# ✨기능 구현 & 🤬트러블 슈팅

### 🗂 카드&컬럼 순서 바꾸기 (동시성 제어)

- 구현 방법
  - 클라이언트에서 카드를 드래그하여 순서를 변경하면, 변경된 순서를 서버로 전송합니다.
- 트러블 슈팅
  - 토큰은 유저명을 기준으로 생성되기에, 유저명을 수정하면 기존의 토큰이 유효하지 않게 되는 문제.
  - Redis 사용시 Serializer/Deserializer 문제.
  - docker 배포시 Server 와 Redis 가 다른 컨테이너라면 연결하지 못하는 문제.
- 해결 방법
  - 유저 정보 업데이트시에도 토큰을 발급한다.
  - LocalDateTime 이 적용된 모든 필드에 @JsonSerialize, @JsonDeserialize 적용.
  - Pageable 을 상속받는 RestPage 객체 구현.
  - docker compose 를 통해 하나의 컨테이너에서 Server 와 Redis 를 관리.
                        
### 

### Redis




