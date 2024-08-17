# Step 1: Builder Stage
FROM gradle:7.6-jdk17-alpine as builder
WORKDIR /build

# Gradle 캐시 활용을 위한 디렉토리 설정
COPY gradle /build/gradle
COPY build.gradle settings.gradle /build/

# 의존성만 다운로드 (빌드 단계에서 불필요한 빌드 방지)
RUN gradle dependencies --no-daemon

# 전체 소스 코드 복사 후 애플리케이션 빌드
COPY . /build
RUN gradle build -x test --no-daemon --parallel

# Step 2: Application Stage
FROM openjdk:17.0-slim
WORKDIR /app

# wait-for-it.sh 스크립트를 다운로드 및 설치
COPY wait-for-it.sh /app/wait-for-it.sh
RUN chmod +x /app/wait-for-it.sh

# 빌드된 JAR 파일 복사 (명시적으로 이름 지정)
COPY --from=builder /build/build/libs/*-SNAPSHOT.jar ./app.jar

EXPOSE 8080

# root 권한으로 실행 (nobody 대신)
ENTRYPOINT ["/app/wait-for-it.sh", "mysql:3306", "--", "java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "-Dsun.net.inetaddr.ttl=0", "app.jar"]
