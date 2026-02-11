# Spring Boot Microservices Test Task

## Run

./mvnw -f auth-api/pom.xml clean package -DskipTests
./mvnw -f data-api/pom.xml clean package -DskipTests
docker compose up -d --build

## Endpoints

POST /api/auth/register<br>
POST /api/auth/login<br>
POST /api/process
