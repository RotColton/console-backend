# Usa una imagen base de Maven para compilar el proyecto
FROM maven:3.8.5-openjdk-17 AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos de tu proyecto al contenedor
COPY . .

# Ejecuta el comando de Maven para compilar el proyecto y generar el .jar
RUN mvn clean package -DskipTests

# Usa una imagen base de Java para ejecutar la aplicación
FROM openjdk:17-jdk

# Establece el directorio de trabajo en la imagen de ejecución
WORKDIR /app

# Copia el archivo .jar desde la fase de compilación
COPY --from=build /app/target/console-0.0.1-SNAPSHOT.jar app.jar

# Especifica el comando para iniciar la aplicación
CMD ["java", "-jar", "app.jar"]

