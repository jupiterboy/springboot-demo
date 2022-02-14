package com.jupiterboy.springboot.minio;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class GetObject {
    /** MinioClient.getObject() example. */
    public static void main(String[] args)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            /* play.min.io for test and development. */
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://192.168.50.112:9000")
                            .credentials("minioadmin", "minioadmin")
                            .build();

            /* Amazon S3: */
            // MinioClient minioClient =
            //     MinioClient.builder()
            //         .endpoint("https://s3.amazonaws.com")
            //         .credentials("YOUR-ACCESSKEY", "YOUR-SECRETACCESSKEY")
            //         .build();

            // Get input stream to have content of 'my-objectname' from 'my-bucketname'
            InputStream stream =
                    minioClient.getObject(
                            GetObjectArgs.builder().bucket("dev").object("M0000002-info.json").build());

            // Read the input stream and print to the console till EOF.
            byte[] buf = new byte[16384];
            int bytesRead;
            while ((bytesRead = stream.read(buf, 0, buf.length)) >= 0) {
                System.out.println(new String(buf, 0, bytesRead, StandardCharsets.UTF_8));
            }

            // Close the input stream.
            stream.close();
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }
}