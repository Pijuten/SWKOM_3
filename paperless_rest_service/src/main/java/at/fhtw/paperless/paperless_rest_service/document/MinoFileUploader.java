package at.fhtw.paperless.paperless_rest_service.document;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class MinoFileUploader implements FileUploader {
    MinioClient minioClient;

    @Autowired
    public MinoFileUploader(MinioClient minioClient) {
        this.minioClient = minioClient;
    }
    @Override
    public boolean upload(String filePath) {
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

                Path path = Paths.get(filePath);
                String fileName = path.getFileName().toString();


                minioClient.uploadObject(
                        UploadObjectArgs.builder()
                                .bucket("paperless")
                                .object(fileName)
                                .filename(filePath)
                                .build());
                System.out.println(
                        filePath+" is successfully uploaded as "
                                + "object "+fileName+" to bucket 'paperless'.");
            } catch (MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException e) {
                System.out.println("Error occurred: " + e);
                return false;
            }
        return true;
    }
}
