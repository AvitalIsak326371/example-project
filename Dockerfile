# שלב 1: Build
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

# העתקת קבצי ה- Maven לפרויקט והתקנת התלויות
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# שלב 2: Run
FROM openjdk:17-jdk-slim
WORKDIR /app

# העתקת קובץ ה-JAR מהשלב הקודם
COPY --from=build /app/target/*.jar app.jar

# הפעלת ה-JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
