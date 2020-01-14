FROM openkbs/jdk11-mvn-py3

ENV PROJECT_DIR=/opt/project
WORKDIR $PROJECT_DIR

RUN mkdir -p $PROJECT_DIR

ADD ./pom.xml $PROJECT_DIR
ADD ./src/ $PROJECT_DIR/src

RUN mvn install

COPY /target/homework17*.jar $PROJECT_DIR/app.jar

EXPOSE 8080

CMD ["java","-jar","/opt/project/app.jar"]