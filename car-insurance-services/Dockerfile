#### Stage 1: Build the application
FROM eclipse-temurin:17-jdk-alpine as build

# set working directory
RUN mkdir /app
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Build all the dependencies in preparation to go offline. 
RUN ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

#### Stage 2: Deploy the application
FROM eclipse-temurin:17-jdk-alpine

ARG DEPENDENCY=/app/target/dependency

# Copy project dependencies from the build stage
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.craftmanship.insurance.InsuranceServicesApplication"]