- **Language**
  - kotlin
 
- **Library/Framework**
  - Spring Boot(2.6.3) - multi module
  - JPA(Spring Data JPA)
  - Kafka
  - Spring Cloud Gateway(Nginx)
  - Junit 5
  - Swagger
 
- **Infrastructure**
  - MariaDB
  - Redis
  - Docker 

###  프로젝트 특징 
  - Kafka 를 통해 모든 대출 심사 요청을 `CONSUMER` 를 통해 `CSS` 로 전달.
  - Kafka 의 topic 이름은 `loanRequest` 이며, `replication-factor` 는 1이다.
  - Nginx 를 통한 Proxy 서버를 `CONSUMER` 어플리케이션과 `CSS` 어플리케이션 사이에 구축하였다.
  - 구현한 모든 어플리케이션은 `Docker` 을 통해 `Excutable File` 로 생성하였다.
  - 데이터베이스와 관련된 구현(Redis, JPA 등)은 `Domain` 모듈에 구현하였다.

