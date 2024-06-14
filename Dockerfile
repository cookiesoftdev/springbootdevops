# Use uma imagem base do OpenJDK
FROM openjdk:17-jdk-slim

# Adicione um volume apontando para /tmp
VOLUME /tmp

# Copie o JAR do aplicativo para o contêiner
COPY target/app.jar app.jar

# Exponha a porta que o Spring Boot usa
EXPOSE 8080

# Execute o JAR do aplicativo
ENTRYPOINT ["java", "-jar", "/app.jar"]