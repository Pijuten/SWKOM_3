package at.fhtw.paperless.paperless_rest_service.document;

import io.minio.*;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Component
public class MinoFileUploader implements FileUploader {
    MinioClient minioClient;

    @Autowired
    public MinoFileUploader(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public boolean upload(MultipartFile file) {
        try {

            // Make 'paperless' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("paperless").build());
            if (!found) {
                // Make a new bucket called 'paperless'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("paperless").build());
            } else {
                System.out.println("Bucket 'paperless' already exists.");
            }

            Path path = Paths.get(Objects.requireNonNull(file.getOriginalFilename()));
            String fileName = path.getFileName().toString();


            InputStream inputStream = file.getInputStream();
            minioClient.putObject(
                    PutObjectArgs.builder().bucket("paperless").object(fileName).stream(
                                    inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());
            inputStream.close();
        } catch (MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            System.out.println("Error occurred: " + e);
            return false;
        }
        return true;
    }
}
