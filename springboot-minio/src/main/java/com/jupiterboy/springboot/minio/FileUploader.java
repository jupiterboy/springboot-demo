package com.jupiterboy.springboot.minio;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileUploader {

    public static void main(String[] args)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://192.168.50.112:9000")
                            .credentials("minioadmin", "minioadmin")
                            .build();

            // Make 'asiatrip' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("dev").build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("dev").build());
            } else {
                System.out.println("Bucket 'dev' already exists.");
            }

            // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
            // 'asiatrip'.
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("dev")
                            .object("M0000002-info.json")
                            .filename("E:/snipher/data/tcb/M0000002/info.json")
                            .build());
            System.out.println(
                    "'E:/snipher/data/tcb/M0000002/info.json' is successfully uploaded as "
                            + "object 'M0000002-info.json' to bucket 'dev'.");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
//            System.out.println("HTTP trace: " + e.httpTrace());
        }
    }
}