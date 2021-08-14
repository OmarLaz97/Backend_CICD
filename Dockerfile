FROM maven
WORKDIR /app
COPY . .
RUN mvn install
CMD mvn spring-boot:run
