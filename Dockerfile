FROM openjdk:11
VOLUME /tmp
COPY build/libs/*.jar chat-api.jar
ENTRYPOINT ["java","-jar","/chat-api.jar","--add-to-start=logging-slf4j"]

