# Usar una imagen base oficial de OpenJDK 17
FROM amazoncorretto:17
# Copiar el archivo JAR de tu aplicación en el contenedor
COPY ./notifications/build/libs/notifications-1.0.0.jar notifications-1.0.0.jar
# Exponer el puerto en el que tu aplicación se ejecutará
EXPOSE 8080
# Comando para ejecutar tu aplicación
CMD ["java", "-jar", "/notifications-1.0.0.jar"]
