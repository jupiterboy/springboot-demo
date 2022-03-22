package com.jupiterboy.springboot.rsa;

import java.security.Key;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

/**
 * @ClassName: RSATest
 * @Description: TODO
 * @Author: zhānghào
 * @Date: 2021/4/25 3:41 下午
 * @Version: v1.0
 **/
public class RSATest {

    public static void main(String[] args) throws Exception {

        // 使用公钥、私钥对象加解密
        List<Key> keyList = RSAUtils.getRSAKeyObject(1024);
        RSAPublicKey puk = (RSAPublicKey) keyList.get(0);
        RSAPrivateKey prk = (RSAPrivateKey) keyList.get(1);
        String message = "referrer:[http://www.example.com/start.html](http://www.example.com/start.htmluser_agent: Mozilla/4.08 [en] (Win98; I ;Nav)";
        String encryptedMsg = RSAUtils.encryptByPublicKey(message, puk);
        String decryptedMsg = RSAUtils.decryptByPrivateKey(encryptedMsg, prk);
        System.out.println("object key ! message ==  decryptedMsg ? " + message.equals(decryptedMsg));

        // 使用字符串生成公钥、私钥完成加解密
        List<String> keyStringList = RSAUtils.getRSAKeyString(1024);
        String pukString = keyStringList.get(0);
        String prkString = keyStringList.get(1);
        System.out.println("公钥:" + pukString);
        System.out.println("私钥:" + prkString);
        // 生成公钥、私钥
        puk = RSAUtils.getPublicKey(pukString);
        prk = RSAUtils.getPrivateKey(prkString);
        encryptedMsg = RSAUtils.encryptByPublicKey(message, puk);
        decryptedMsg = RSAUtils.decryptByPrivateKey(encryptedMsg, prk);
        System.out.println("string key ! message ==  decryptedMsg ? " + message.equals(decryptedMsg));

    }
}
