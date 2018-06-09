package main.java;

import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;
import org.omg.PortableServer.THREAD_POLICY_ID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

class DesEncrypter {
    Cipher ecipher;

    Cipher dcipher;

    DesEncrypter(SecretKey key) throws Exception {
        ecipher = Cipher.getInstance("DES");
        dcipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.ENCRYPT_MODE, key);
        dcipher.init(Cipher.DECRYPT_MODE, key);
    }

    public String encrypt(String str) throws Exception {
        // Encode the string into bytes using utf-8
        byte[] utf8 = str.getBytes("UTF8");

        // Encrypt
        byte[] enc = ecipher.doFinal(utf8);

        // Encode bytes to base64 to get a string
        return new sun.misc.BASE64Encoder().encode(enc);
    }

    public String decrypt(String str) throws Exception {
        // Decode base64 to get bytes
        byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

        byte[] utf8 = dcipher.doFinal(dec);

        // Decode using utf-8
        return new String(utf8, "UTF8");
    }

    public static void main(String[] argv) throws Exception {

        SecretKey key = KeyGenerator.getInstance("DES").generateKey();
        DesEncrypter encrypter = new DesEncrypter(key);


        String content = readFile("inputfile", StandardCharsets.UTF_8);


        long stEncTm = System.currentTimeMillis();
        String encrypted = encrypter.encrypt(content);

        Thread.sleep(300);

        long endEncTm = System.currentTimeMillis();


        long stDcrTm = System.currentTimeMillis();
        String decrypted = encrypter.decrypt(encrypted);
        Thread.sleep(300);

        long endDcrTm = System.currentTimeMillis();


        System.out.println("Encryption Time(ms):" + (endEncTm - stEncTm));
        System.out.println("Decryption Time(ms):" + (endDcrTm - stDcrTm));


    }

    static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }


}


