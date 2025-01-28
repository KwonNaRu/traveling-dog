FROM gradle:jdk21 AS builder
WORKDIR /app
COPY . .

# gradlew 파일에 실행 권한 추가
RUN chmod +x ./gradlew

RUN SPRING_PROFILES_ACTIVE=local-docker ./gradlew build

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]