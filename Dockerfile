# 1. Usar una imagen base de Java 17 (igual a la de tu PC)
FROM amazoncorretto:17

# 2. Crear carpeta de trabajo dentro del servidor
WORKDIR /app

# 3. Copiar todos tus archivos al servidor
COPY . .

# 4. Dar permisos de ejecución al instalador de Gradle
RUN chmod +x gradlew

# 5. Construir la aplicación (crear el archivo .jar)
RUN ./gradlew bootJar --no-daemon

# 6. Comando para arrancar la app
# Usamos $PORT para que Render asigne el puerto automáticamente
CMD java -Dserver.port=$PORT -jar build/libs/*.jar 