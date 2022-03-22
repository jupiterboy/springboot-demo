package com.jupiterboy.springboot.watchdog;

import DLL.UT_DLL;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.concurrent.Executors;

import static DLL.DLLTool.shieldPC;

public class WatchDog {

    public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDD9KJkJfbXzFCIO01jR/US6EfmV+aNSRo0BQP8A5Zgad/pVMS/HKtIUP2PGkfP5cs8oOTp4jv5KW0ooaY+uLdFJIso8Qje+JVmOXRXABk2c3q1m2j+2oV/pHJ+SHEBOBFsFRe81r98qXVViqbbv/yljYMucl5EJ4/bEp4h0hocAwIDAQAB";
    private static final String PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMP0omQl9tfMUIg7TWNH9RLoR+ZX5o1JGjQFA/wDlmBp3+lUxL8cq0hQ/Y8aR8/lyzyg5OniO/kpbSihpj64t0UkiyjxCN74lWY5dFcAGTZzerWbaP7ahX+kcn5IcQE4EWwVF7zWv3ypdVWKptu//KWNgy5yXkQnj9sSniHSGhwDAgMBAAECgYEAwuTnNrBDqDI/zp8cwyfoahu4lzLJza/tbdunMNnokI/88bcrz+CVwuE6rz+2OOIWl4B27N/kQlafiQ/tpuhU7aEvOuVzYq0H3ryunojwlZHXR7buEvaZeqY1HKvTdsG/idd0nppUgOjACwtiFFBV6Mjb6z6ejoCgovlGyECvbTECQQD85Zpj0fMgy/da7Vs9HpNNza5+v7xDhIgfrIP3Uyv373yiHBcQ1RURxzqQccrnBJwIUHdU9Y3Il+akOUcfxB9vAkEAxlwq/NsEoaYGIyaFJ2hkaH6TUlWxIzJimysvY5E2NwEEVuBR7QVfpXAtjHYvMoWmOR7PfJKBCgle6bmkac4CrQI/SxPewcOSaiojRNsyOvLYyEBHjVleiqzNvJl6k/IdG339giLUtiqMFrsOp9MgaXT03YMF6jL75JEz540ZkFSpAkACn8eFztPx24NrRaAvVEc3xqzmQvHz+D+rs9eNpqcj9SR8jb0nuJF6W9COciXPskrvqIOwLzP70QHrb6RILgyFAkA3SocbWWRgE/4hVw0tQQ6fPoZezoHyBFwukz/gzC4YwX2vUHvLF+hves4lMDbDdEU41inf0sKlNPAhQzl3zk3b";

    private static final UT_DLL dll = new UT_DLL();
    private static final long uSerial = 0L;
    private final int key1 = 7446;
    private final int key2 = 10631;
    private final int key3 = 16985;
    private final int key4 = 7839;
    private final String pwd;

    static{
        try{
            dll.InitiateLock(uSerial);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public WatchDog() {
        this("abcdefgh");
    }

    public WatchDog(String pwd) {
        this.pwd = pwd;
    }

    public int write(int start, String msg) throws Exception {
        int length = msg.length();
        int padding = 4 - length % 4;
        StringBuilder sb = new StringBuilder(msg);
        for (int i = 0; i < padding; i++) {
            sb.append(" ");
        }
        int step = 4;
        int count = sb.length() / step;
        for(int i=1;i<=count;i++){
            String subStr = sb.substring(step*(i-1), step*i);
            dll.WriteLock(start + i - 1, subStr, pwd, uSerial, 1);
        }
        return count;
    }

    public String read(int start, int end) throws Exception {
        byte[] bytes = new byte[4];
        StringBuilder sb = new StringBuilder();
        for(int i=start;i<end;i++){
            dll.ReadLock(i, bytes, pwd, uSerial, 1);
            String subStr = new String(bytes);
            sb.append(subStr);
        }
        return sb.toString().trim();
    }

    private RSAPrivateKey getPrivateKey() throws Exception {
        return RSAUtils.getPrivateKey(PRIVATE_KEY);
    }

    private RSAPublicKey getPublicKey() throws Exception {
        return RSAUtils.getPublicKey(PUBLIC_KEY);
    }

    public String encrypt(String msg) throws Exception {
        RSAPrivateKey prk = getPrivateKey();
        return RSAUtils.encryptByPrivateKey(msg, prk);
    }

    public String decrypt(String encryptedMsg) throws Exception {
        RSAPublicKey puk = getPublicKey();
        return RSAUtils.decryptByPublicKey(encryptedMsg, puk);
    }

    public boolean validate(int key1, int key2, int key3, int key4) throws Exception {
        int lock[] = new int[1];
        int random = (int) (1073741824 * (Math.random()-0.5)+107374182 * (Math.random()-0.5));
        boolean dogVal = dll.Lock32_Function(random, lock, uSerial);
        int funVal = shieldPC(random, key1, key2, key3, key4);
        return dogVal == true && lock[0] == funVal;
    }

    public boolean validate(int[] keys) throws Exception {
        return validate(keys[0], keys[1], keys[2], keys[3]);
    }

    private int writeKeys(int start, int key1, int key2, int key3, int key4) throws Exception {
        String msg = key1 + ":" + key2 + ":" + key3 + ":" + key4;
        String encryptedMsg = encrypt(msg);
        return write(start, encryptedMsg);
    }

    private int[] readKeys(int start, int end) throws Exception {
        String encryptedMsg = read(start, end);
        String decryptedMsg = decrypt(encryptedMsg);
        String[] keys = decryptedMsg.split(":");
        return new int[]{Integer.valueOf(keys[0]), Integer.valueOf(keys[1]), Integer.valueOf(keys[2]), Integer.valueOf(keys[3])};
    }

    public String readPublicKey() throws Exception {
        return read(1, PUBLIC_KEY.length()/4 + 1);
    }

    public void writePublicKey() throws Exception {
        write(1, PUBLIC_KEY);
    }

    public void writeKeys() throws Exception {
        writeKeys(PUBLIC_KEY.length()/4 + 1 , key1, key2, key3, key4);
    }

    public int[] readKeys() throws Exception {
        String msg = key1 + ":" + key2 + ":" + key3 + ":" + key4;
        String encryptedMsg = encrypt(msg);
        return readKeys(PUBLIC_KEY.length()/4 + 1, PUBLIC_KEY.length()/4 + 1 + encryptedMsg.length()/4);
    }

    public boolean validatePublicKey() throws Exception {
        String publicKey = readPublicKey();
        if(!WatchDog.PUBLIC_KEY.equals(publicKey)) {
            return false;
        }
        return true;
    }

    public boolean validateKeys() throws Exception {
        int[] keys = readKeys();
        return validate(keys[0], keys[1], keys[2], keys[3]);
    }

    public boolean validate() throws Exception {
        return validatePublicKey() && validateKeys();
    }

    public int[] findKeys(){
        int random = (int) (1073741824 * (Math.random()-0.5)+107374182 * (Math.random()-0.5));
        int[] keys = new int[4];
        int lock[] = new int[1];
        for(int key1 = 1; key1 < 100000; key1++) {
            for(int key2 = 1; key2 < 100000; key2++) {
                for(int key3 = 1; key3 < 100000; key3++) {
                    for(int key4 = 1; key4 < 100000; key4++) {
                        System.out.println("key1:" + key1 + " key2:" + key2 + " key3:" + key3 + " key4:" + key4);
                        try{
                            boolean dogVal = dll.Lock32_Function(random, lock, uSerial);
                            int funVal = shieldPC(random, key1, key2, key3, key4);
                            if (dogVal == true && lock[0] == funVal) {
                                keys[0] = key1;
                                keys[1] = key2;
                                keys[2] = key3;
                                keys[3] = key4;
                                return keys;
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            WatchDog dog = new WatchDog();
            int start = 1;
            String msg = "hello world";
            String encryptedMsg1 = dog.encrypt(msg);
            System.out.println("encryptedMsg1:"+encryptedMsg1);
            int count = dog.write(start, encryptedMsg1);
            String encryptedMsg2 = dog.read(start, start + count - 1);
            System.out.println("encryptedMsg2:"+encryptedMsg2);
            String decryptedMsg = dog.decrypt(encryptedMsg2);
            System.out.println(decryptedMsg);

            start = start + count;
            count = dog.writeKeys(start, 7446,10631,16985,7839);
            System.out.println("count:"+count);
            int[] keys = dog.readKeys(start, start + count - 1);
            System.out.println(keys[0] + ", " + keys[1] + ", " + keys[2] + ", " + keys[3]);
            System.out.println(dog.validate(keys[0], keys[1], keys[2], keys[3]));

//            WatchDog dog = new WatchDog();
//            dog.writePublicKey();
//            dog.writeKeys();
//            String publicKey = dog.readPublicKey();
//            System.out.println(publicKey.equals(PUBLIC_KEY));
//            int[] keys = dog.readKeys();
//            System.out.println(keys[0] + ", " + keys[1] + ", " + keys[2] + ", " + keys[3]);
//            System.out.println(dog.validate(keys));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
