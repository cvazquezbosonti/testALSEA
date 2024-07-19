# Usa una imagen base con OpenJDK 11
FROM openjdk:11-jdk-slim

# Establece el directorio de trabajo
COPY target/testAlsea-1.0.0.jar /app/testAlsea-1.0.0.jar



# Expon el puerto en el que la aplicación escuchará
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/testAlsea-1.0.0.jar"]