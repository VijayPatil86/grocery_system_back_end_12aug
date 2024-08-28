FROM openjdk:17-oracle
ADD target/grocery-system-0.0.1-SNAPSHOT.jar grocery.jar
ADD src/main/resources/log4j2.xml log4j2.xml
ADD src/main/resources/application.properties application.properties
CMD ["java", "-jar", "grocery.jar" ]