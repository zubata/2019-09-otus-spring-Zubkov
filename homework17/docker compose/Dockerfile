FROM openkbs/jdk11-mvn-py3

ENV PROJECT_DIR=/opt/project

RUN mkdir -p $PROJECT_DIR

COPY ./homework17.jar $PROJECT_DIR/

EXPOSE 8080

CMD ["java","-jar","/opt/project/homework17.jar"]