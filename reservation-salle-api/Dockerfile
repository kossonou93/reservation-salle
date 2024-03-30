# Utiliser une image JDK 17 comme base
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le jar de l'application dans le conteneur
COPY target/rom-reservation-api-0.0.1-SNAPSHOT.jar /app/rom-reservation-api-0.0.1-SNAPSHOT.jar

# Exposer le port sur lequel l'application écoute
EXPOSE 8080

# Commande pour exécuter l'application lorsque le conteneur démarre
CMD ["java", "-jar", "rom-reservation-api-0.0.1-SNAPSHOT.jar"]
