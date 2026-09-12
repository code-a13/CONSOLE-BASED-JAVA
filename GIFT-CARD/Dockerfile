FROM eclipse-temurin:17-jdk-jammy

# Install ttyd for web-based terminal access
RUN apt-get update && apt-get install -y ttyd

WORKDIR /app

# Copy all files from the GIFT-CARD folder into /app
COPY . /app

# Compile all Java files in the directory
RUN javac *.java

# Start ttyd and execute SystemManager on the dynamically assigned PORT
CMD ttyd -p ${PORT:-8080} java SystemManager