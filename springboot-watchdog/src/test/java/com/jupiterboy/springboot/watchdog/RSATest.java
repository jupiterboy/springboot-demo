package com.jupiterboy.springboot.watchdog;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RSATest {

    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDD9KJkJfbXzFCIO01jR/US6EfmV+aNSRo0BQP8A5Zgad/pVMS/HKtIUP2PGkfP5cs8oOTp4jv5KW0ooaY+uLdFJIso8Qje+JVmOXRXABk2c3q1m2j+2oV/pHJ+SHEBOBFsFRe81r98qXVViqbbv/yljYMucl5EJ4/bEp4h0hocAwIDAQAB";
    private static final String PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMP0omQl9tfMUIg7TWNH9RLoR+ZX5o1JGjQFA/wDlmBp3+lUxL8cq0hQ/Y8aR8/lyzyg5OniO/kpbSihpj64t0UkiyjxCN74lWY5dFcAGTZzerWbaP7ahX+kcn5IcQE4EWwVF7zWv3ypdVWKptu//KWNgy5yXkQnj9sSniHSGhwDAgMBAAECgYEAwuTnNrBDqDI/zp8cwyfoahu4lzLJza/tbdunMNnokI/88bcrz+CVwuE6rz+2OOIWl4B27N/kQlafiQ/tpuhU7aEvOuVzYq0H3ryunojwlZHXR7buEvaZeqY1HKvTdsG/idd0nppUgOjACwtiFFBV6Mjb6z6ejoCgovlGyECvbTECQQD85Zpj0fMgy/da7Vs9HpNNza5+v7xDhIgfrIP3Uyv373yiHBcQ1RURxzqQccrnBJwIUHdU9Y3Il+akOUcfxB9vAkEAxlwq/NsEoaYGIyaFJ2hkaH6TUlWxIzJimysvY5E2NwEEVuBR7QVfpXAtjHYvMoWmOR7PfJKBCgle6bmkac4CrQI/SxPewcOSaiojRNsyOvLYyEBHjVleiqzNvJl6k/IdG339giLUtiqMFrsOp9MgaXT03YMF6jL75JEz540ZkFSpAkACn8eFztPx24NrRaAvVEc3xqzmQvHz+D+rs9eNpqcj9SR8jb0nuJF6W9COciXPskrvqIOwLzP70QHrb6RILgyFAkA3SocbWWRgE/4hVw0tQQ6fPoZezoHyBFwukz/gzC4YwX2vUHvLF+hves4lMDbDdEU41inf0sKlNPAhQzl3zk3b";


    public static void main(String[] args) throws Exception {
        String mac = "5e:ec:cd:63:2a:44";

        RSAPublicKey puk = RSAUtils.getPublicKey(PUBLIC_KEY);
        RSAPrivateKey prk = RSAUtils.getPrivateKey(PRIVATE_KEY);

        System.out.println("mac: " + mac);

        String encryptedMsg = RSAUtils.encryptByPrivateKey(mac, prk);
        System.out.println("encrypted mac: " + encryptedMsg);

        String decryptedMsg = RSAUtils.decryptByPublicKey(encryptedMsg, puk);
        System.out.println("decrypted mac" + decryptedMsg);
    }
}
