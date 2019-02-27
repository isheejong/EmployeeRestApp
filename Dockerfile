FROM openjdk:8-alpine

RUN apk add maven \
    && mvn -v \
    && mkdir /app

COPY . /app/

# build the package
RUN cd /app && mvn clean package

# install tomcat
RUN wget https://www-eu.apache.org/dist/tomcat/tomcat-7/v7.0.93/bin/apache-tomcat-7.0.93.tar.gz \
    && tar -zxf apache-tomcat-7.0.93.tar.gz \
    && cp /app/target/RestServiceMaven-1.0-SNAPSHOT.war apache-tomcat-7.0.93/webapps/EmployeeRestService.war

WORKDIR apache-tomcat-7.0.93/

EXPOSE 8080

CMD ["bin/catalina.sh", "run"]
