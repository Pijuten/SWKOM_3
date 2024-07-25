# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Install Tesseract OCR
RUN apt-get update && apt-get install -y tesseract-ocr

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file to the container
COPY build/libs/paperless_ocr_service-0.0.1.jar /app/rest-service.jar

# Expose the port the service runs on
EXPOSE 8082

COPY ["tessdata_best", "/app/tessdata_best"]
ENV TESSDATA_PREFIX /app/tessdata_best
# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/rest-service.jar"]