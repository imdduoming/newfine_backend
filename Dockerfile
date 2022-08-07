FROM adoptopenjdk:11-jdk-hotspot
COPY build/libs/*.jar app.jar
EXPOSE 5000
RUN apt-get update && apt-get install -y tzdata
RUN ls /usr/share/zoneinfo && \
ln -sf /usr/share/zoneinfo/Asia/Seoul /etc/localtime && \
echo "Asia/Seoul" >  /etc/timezone
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=ebprod","/app.jar"]