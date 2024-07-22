# Usa una imagen base con OpenJDK 11
FROM maven:3.8.6-openjdk-11
# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo pom.xml y el código fuente al directorio de trabajo
COPY pom.xml /app/
COPY init.sql /app/
COPY src /app/src

# Ejecuta el comando Maven para compilar el proyecto
RUN mvn clean install

# Establece el comando por defecto para ejecutar cuando se inicie el contenedor
CMD ["java", "-jar", "target/testAlsea-1.0.0.jar"]