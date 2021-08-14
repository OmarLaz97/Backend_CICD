FROM maven
WORKDIR /app
COPY . .
RUN mvn install -DskipTests
CMD mvn spring-boot:run
