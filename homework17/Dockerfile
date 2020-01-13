FROM openkbs/jdk11-mvn-py3

ENV PROJECT_DIR=/opt/project
WORKDIR $PROJECT_DIR

RUN mkdir -p $PROJECT_DIR

ADD ./pom.xml $PROJECT_DIR
ADD ./src/ $PROJECT_DIR/src

RUN mvn install

EXPOSE 8080

CMD ["java","-jar","/opt/project/target/homework17-0.0.1-SNAPSHOT.jar"]