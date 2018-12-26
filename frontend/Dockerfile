FROM docker.xiqiao.io/java/jdk:1.8
VOLUME /tmp
ADD target/frontend-1.0.0-SNAPSHOT.jar app.jar
#RUN apk --no-cache add tzdata  && ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime &&  echo "Asia/Shanghai" > /etc/timezone
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Duser.timezone=GMT+08","/app.jar"]
