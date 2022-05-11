FROM adoptopenjdk:11-jdk-hotspot

ARG JAR_FILE=newfine_backend/build/libs/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 5000

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=ebprod","/app.jar"]