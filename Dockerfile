FROM adoptopenjdk:11-jdk-hotspot
COPY build/libs/*.jar app.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=ebprod","/app.jar"]