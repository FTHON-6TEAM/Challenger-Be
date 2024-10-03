#베이스 이미지
FROM openjdk:17.0.2-jdk-slim-buster AS builder

#작업 디렉토리 설정
WORKDIR /app

#의존성 설정을 위한 파일 복사
COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle

#의존성 다운로드 및 캐시 활용
RUN ./gradlew dependencies --no-daemon || return 0

#소스 코드 복사
COPY src/main ./src/main

#어플리케이션 빌드
RUN ./gradlew clean bootJar --no-daemon

FROM openjdk:17.0.2-slim-buster

WORKDIR /app

#빌드된 jar 파일 복사
COPY  --from=builder /app/build/libs/challenger.jar challenger.jar

# 포트 노출
EXPOSE 8080

# 어플리케이션 실행
ENTRYPOINT ["java", "-jar","challenger.jar"]