#!/bin/bash
# Define tu ID de proyecto y la ubicación de tu registro
PROJECT_ID="kubernetes-sistemas-distribui"
REGISTRY="gcr.io"
# Lista de tus servicios
services=("notifications")
# Itera sobre cada servicio, construye su imagen Docker y la sube al registro
for service in "${services[@]}"; do
  echo "Construyendo y subiendo la imagen para $service..."
  # Construye la imagen
  # shellcheck disable=SC2086
  docker build -t $REGISTRY/$PROJECT_ID/$service:latest ./"$service"
  # Sube la imagen al registro
  docker push $REGISTRY/$PROJECT_ID/"$service":latest
  echo "Imagen para $service construida y subida correctamente."
done
