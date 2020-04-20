From java:8-jre
WORKDIR usr/lib
ENV MYSQL_DATABASENAME=UserDB
ENV MYSQL_URL=jdbc:mysql://localhost:3306/UserDB
ENV MYSQL_USER=app_root
ENV MYSQL_PASSWORD=root
ADD ./target/accountmanager-1.0.jar /usr/lib/accountmanager-1.0.jar
ENTRYPOINT ["java","-jar","accountmanager-1.0.jar"]