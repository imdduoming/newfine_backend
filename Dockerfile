FROM adoptopenjdk:11-jdk-hotspot
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=ebprod","/app.jar"]