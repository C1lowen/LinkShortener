FROM openjdk:17

WORKDIR /app

COPY target/ShortURL-0.0.1-SNAPSHOT.jar ShortURL-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "ShortURL-0.0.1-SNAPSHOT.jar"]