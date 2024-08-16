# 1단계: 빌드 단계
FROM eclipse-temurin:17-jdk as builder

# 작업 디렉토리 설정
WORKDIR /app

# Gradle Wrapper 및 소스 파일을 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# Gradle 빌드 도구 설치 및 의존성 캐싱 후 프로젝트 빌드
RUN ./gradlew build -x test

# 2단계: 실행 단계
FROM eclipse-temurin:17-jre

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 jar 파일을 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 컨테이너 실행 시 실행할 명령어 설정
ENTRYPOINT ["java","-jar","/app/app.jar"]

# 애플리케이션이 실행될 포트 지정
EXPOSE 8080
