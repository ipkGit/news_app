FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} news_app.jar
ENTRYPOINT ["java", "-jar", "news_app.jar"]