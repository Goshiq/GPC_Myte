FROM openjdk

ENV MAVEN_VERSION 3.8.2

RUN mkdir -p /usr/share/maven \
  && curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz \
    | tar -xzC /usr/share/maven --strip-components=1 \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven

ADD . .

RUN mvn -N io.takari:maven:wrapper \
  && mvn clean package

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./target/GPC_myte-0.0.1-SNAPSHOT.jar"]
