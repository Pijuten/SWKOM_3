package at.fhtw.paperless.paperless_rest_service.document;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MinoFileUploaderTest {

    @Mock
    private MinioClient minioClient;

    @InjectMocks
    private MinoFileUploader minoFileUploader;

    @BeforeEach
    public void setUp() {
        minoFileUploader = new MinoFileUploader();
        minoFileUploader.minioClient = minioClient;
    }

    @Test
    public void testUpload() throws MinioException, IOException, InvalidKeyException, NoSuchAlgorithmException {
        // Arrange
        String filePath = "C:\\Users\\StanislausCichocki\\IdeaProjects\\Paperless\\paperless_rest_service\\src\\test\\java\\at\\fhtw\\paperless\\paperless_rest_service\\document\\testfile.txt";

        when(minioClient.bucketExists(any(BucketExistsArgs.class))).thenReturn(false);
        doNothing().when(minioClient).makeBucket(any(MakeBucketArgs.class));
        doReturn(null).when(minioClient).uploadObject(any(UploadObjectArgs.class));

        // Act
        boolean result = minoFileUploader.upload(filePath);

        // Assert
        assertTrue(result);
        verify(minioClient, times(1)).bucketExists(any(BucketExistsArgs.class));
        verify(minioClient, times(1)).makeBucket(any(MakeBucketArgs.class));
        verify(minioClient, times(1)).uploadObject(any(UploadObjectArgs.class));
    }
}