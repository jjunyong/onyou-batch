FROM openjdk:8-jdk
ARG JAR_FILE=build/libs/onyou-batch.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"]