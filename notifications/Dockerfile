FROM amazoncorretto:17
COPY ./notifications/build/libs/notifications-1.0.0.jar notifications-1.0.0.jar
# Exponer el puerto en el que tu aplicación se ejecutará
EXPOSE 8080
# Comando para ejecutar tu aplicación
CMD ["java", "-jar", "/notifications-1.0.0.jar"]
