package com.jupiterboy.springboot.minio;

import ch.qos.logback.core.rolling.helper.FileStoreUtil;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class MinioApplication implements CommandLineRunner {
    @Autowired
    MinioClient minioClient;


    public static void main(String[] args) {
        SpringApplication.run(MinioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        uploadObject();
        getObject();
    }

    private void uploadObject() throws Exception {
        minioClient.uploadObject(
                UploadObjectArgs.builder()
                        .bucket("dev")
                        .object("M0000045-info.json")
                        .filename("E:/snipher/data/tcb/M0000045/info.json")
                        .build());
        System.out.println("my-filename is uploaded to my-objectname successfully");
    }


    private void getObject() throws Exception {
        InputStream stream =
                minioClient.getObject(
                        GetObjectArgs.builder().bucket("dev").object("M0000002-info.json").build());
        getFile(stream, "E:/M0000002-info.json");
    }

    private void getFile(InputStream is, String fileName) throws Exception {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        in  = new BufferedInputStream(is);
        out = new BufferedOutputStream(new FileOutputStream(fileName));
        int len = -1;
        byte[] b = new byte[1024];
        while ((len = in.read(b)) != -1) {
            out.write(b, 0, len);
        }
        in.close();
        out.close();
    }
}
