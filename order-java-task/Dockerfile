FROM gradle:8.8.0-jdk21-alpine AS builder
WORKDIR /app
COPY . .
RUN gradle build -x test --no-daemon

FROM azul/zulu-openjdk-alpine:21-latest
WORKDIR /app
COPY --from=builder /app/build/libs/order-java-task-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
CMD ["java", "-jar", "app.jar"]