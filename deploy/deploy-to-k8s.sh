#!/bin/bash

# Lista de tus servicios
services=("citizen" "document" "notifications" "operators")

# Itera sobre cada servicio y lo despliega utilizando kubectl
for service in "${services[@]}"; do
  echo "Desplegando $service a Kubernetes..."

  # Despliega el servicio
  kubectl apply -f ./"$service"/deployment.yaml

  echo "$service desplegado correctamente."
done
