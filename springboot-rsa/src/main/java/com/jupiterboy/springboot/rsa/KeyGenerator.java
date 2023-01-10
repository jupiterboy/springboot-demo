package com.jupiterboy.springboot.rsa;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.security.interfaces.RSAPrivateKey;

public class KeyGenerator {

    public static void main(String[] args) throws Exception {
        String MAC = "90:8D:6E:C3:AC:F0";
        RSAPrivateKey prk = RSAUtils.getPrivateKey(RSAKey.PRIVATE_KEY);
        String key = RSAUtils.encryptByPrivateKey(MAC, prk);
        FileUtils.writeStringToFile(new File("keys/key"), key);
    }
}
