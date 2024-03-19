# Usar una imagen base oficial de OpenJDK 17
FROM amazoncorretto:17

# Opcional: Establecer una variable de entorno
ENV MY_VARIABLE my_value

# Copiar el archivo JAR de tu aplicación en el contenedor
COPY target/notificacion.jar notificacion.jar

# Exponer el puerto en el que tu aplicación se ejecutará
EXPOSE 8080

# Comando para ejecutar tu aplicación
CMD ["java", "-jar", "/notificacion.jar"]
