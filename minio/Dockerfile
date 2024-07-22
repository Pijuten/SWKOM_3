# Use the official MinIO image as the base image
FROM minio/minio

# Set environment variables for MinIO
ENV MINIO_ROOT_USER=myminioaccess
ENV MINIO_ROOT_PASSWORD=S3cureP@ssw0rd

# Expose the necessary ports
EXPOSE 9000
EXPOSE 9001

# Create a directory for MinIO data
VOLUME /data

# Run MinIO server with the console address
CMD ["server", "/data", "--console-address", ":9001"]