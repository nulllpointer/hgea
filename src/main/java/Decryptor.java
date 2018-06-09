package main.java;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;

public class Decryptor {
    public static String text = "";

    public static byte[][] m1 = new byte[4][4];
    public static byte[][] m2 = new byte[4][4];
    public static byte[][] m3 = new byte[4][4];
    public static byte[][] m4 = new byte[4][4];


    public static byte[][] k1 = new byte[4][4];
    public static byte[][] k2 = new byte[4][4];
    public static byte[][] k3 = new byte[4][4];
    public static byte[][] k4 = new byte[4][4];

    private static byte[] k1_array = new byte[16];
    private static byte[] k2_array = new byte[16];
    private static byte[] k3_array = new byte[16];
    private static byte[] k4_array = new byte[16];

    public static byte[][] k1_prime = new byte[4][4];
    public static byte[][] k2_prime = new byte[4][4];
    public static byte[][] k3_prime = new byte[4][4];
    public static byte[][] k4_prime = new byte[4][4];

    private static byte[] k1_prime_array = new byte[16];
    private static byte[] k2_prime_array = new byte[16];
    private static byte[] k3_prime_array = new byte[16];
    private static byte[] k4_prime_array = new byte[16];

    private static byte[][] m1_prime_new = new byte[4][4];
    private static byte[][] m2_prime_new = new byte[4][4];
    private static byte[][] m3_prime_new = new byte[4][4];
    private static byte[][] m4_prime_new = new byte[4][4];


    private static byte[] hero1 = new byte[16];
    private static byte[] hero2 = new byte[16];
    private static byte[] hero3 = new byte[16];
    private static byte[] hero4 = new byte[16];

    private static byte[][] m1_prime = new byte[4][4];
    private static byte[][] m2_prime = new byte[4][4];
    private static byte[][] m3_prime = new byte[4][4];
    private static byte[][] m4_prime = new byte[4][4];


    public static byte[][] tmp1 = new byte[4][4];
    public static byte[][] tmp2 = new byte[4][4];
    public static long decryptTime;


    private static byte[][] m1_double_prime = new byte[4][4];
    private static byte[][] m2_double_prime = new byte[4][4];
    private static byte[][] m3_double_prime = new byte[4][4];
    private static byte[][] m4_double_prime = new byte[4][4];

    private static byte[] m1_array = new byte[16];
    private static byte[] m2_array = new byte[16];
    private static byte[] m3_array = new byte[16];
    private static byte[] m4_array = new byte[16];


    private static byte[][] m1_cipher = new byte[4][4];
    private static byte[][] m2_cipher = new byte[4][4];
    private static byte[][] m3_cipher = new byte[4][4];
    private static byte[][] m4_cipher = new byte[4][4];


    private static byte[] m1_cipher_array = new byte[16];
    private static byte[] m2_cipher_array = new byte[16];
    private static byte[] m3_cipher_array = new byte[16];
    private static byte[] m4_cipher_array = new byte[16];

    public static String[] key = new String[8];

    public static void main(String[] args) {
/*
        key = takeTheInput("Hello sir please enter the key");
        String ciphertext = getInputText();
*/

        System.out.println("Now Decrypting....");
        key = takeTheInput("Please enter the secret key");

        String ciphertext = Encryptor.bulkText;

        String bulkPlainText = "";
        long stDecTm = System.currentTimeMillis();
        for (int i = 0; i < ciphertext.length(); i += 8) {
            String singlecipher = ciphertext.substring(i, Math.min(i + 8, ciphertext.length()));
            String[] singleCipherBin = convertToBinaryStringArray(singlecipher);
            bulkPlainText += decrypt(key, singleCipherBin);
        }

        System.out.println("PlainText:" + bulkPlainText);

        long endDecTime = System.currentTimeMillis();
        decryptTime = (endDecTime - stDecTm);
        System.out.println("Decryption time(ms): " + decryptTime);


        // decrypt(key,singleCipher);
    }

    private static String[] convertToBinaryStringArray(String text) {
        char[] charArray = {0, 0, 0, 0, 0, 0, 0, 0};
        int i = 0;
        while (i < 8) {
            if (i < text.length()) {
                charArray[i] = text.charAt(i);
            }
            i++;
        }

        String paddedinput = new String(charArray);
        return toBinary(paddedinput, 8).split(" ");
    }

    private static String decrypt(String[] key, String[] ciphertext_array) {
        long stDecTm = System.currentTimeMillis();


        String[] result1 = Arrays.copyOfRange(ciphertext_array, 0, 4);
        String[] result2 = Arrays.copyOfRange(ciphertext_array, 4, 8);

        String[] result3 = Arrays.copyOfRange(key, 0, 4);
        String[] result4 = Arrays.copyOfRange(key, 4, 8);

        //Cipher in quadrant form
        for (int i = 0; i < 4; i++) {
            String str = result1[i];
            String[] ary = str.split("");
            for (int j = 0; j < 4; j++) {
                m1_cipher[i][j] = Byte.parseByte(ary[j]);

            }

        }
        for (int i = 0; i < 4; i++) {
            String str = result1[i];
            String[] ary = str.split("");

            for (int j = 0; j < 4; j++) {
                int k = j + 4;
                {
                    m2_cipher[i][j] = Byte.parseByte(ary[k]);
                }

            }
        }


        for (int i = 0; i < 4; i++) {
            String str = result2[i];
            String[] ary = str.split("");
            for (int j = 0; j < 4; j++) {
                int k = j + 4;
                m4_cipher[i][j] = Byte.parseByte(ary[k]);

            }

        }


        for (int i = 0; i < 4; i++) {
            String str = result2[i];
            String[] ary = str.split("");

            for (int j = 0; j < 4; j++) {

                {
                    m3_cipher[i][j] = Byte.parseByte(ary[j]);
                }

            }
        }


        //Key in quadranr form
        for (int i = 0; i < 4; i++) {
            String str = result3[i];
            String[] ary = str.split("");
            for (int j = 0; j < 4; j++) {
                k1[i][j] = Byte.parseByte(ary[j]);

            }

        }
        for (int i = 0; i < 4; i++) {
            String str = result3[i];
            String[] ary = str.split("");

            for (int j = 0; j < 4; j++) {
                int k = j + 4;
                {
                    k2[i][j] = Byte.parseByte(ary[k]);
                }

            }
        }


        for (int i = 0; i < 4; i++) {
            String str = result4[i];
            String[] ary = str.split("");
            for (int j = 0; j < 4; j++) {
                int k = j + 4;
                k4[i][j] = Byte.parseByte(ary[k]);

            }

        }


        for (int i = 0; i < 4; i++) {
            String str = result4[i];
            String[] ary = str.split("");

            for (int j = 0; j < 4; j++) {

                {
                    k3[i][j] = Byte.parseByte(ary[j]);
                }

            }
        }


        m1_cipher_array = bidiToMono(m1_cipher);
        m2_cipher_array = bidiToMono(m2_cipher);
        m3_cipher_array = bidiToMono(m3_cipher);
        m4_cipher_array = bidiToMono(m4_cipher);


        k1_array = bidiToMono(k1);
        k2_array = bidiToMono(k2);
        k3_array = bidiToMono(k3);
        k4_array = bidiToMono(k4);


        // K1_prime after transposing K from selected random transformation matrix
        System.arraycopy(k1_array, 14, k1_prime_array, 0, 1);
        System.arraycopy(k1_array, 3, k1_prime_array, 1, 1);
        System.arraycopy(k1_array, 8, k1_prime_array, 2, 1);
        System.arraycopy(k1_array, 13, k1_prime_array, 3, 1);

        System.arraycopy(k1_array, 1, k1_prime_array, 4, 1);
        System.arraycopy(k1_array, 0, k1_prime_array, 5, 1);
        System.arraycopy(k1_array, 15, k1_prime_array, 6, 1);
        System.arraycopy(k1_array, 4, k1_prime_array, 7, 1);

        System.arraycopy(k1_array, 6, k1_prime_array, 8, 1);
        System.arraycopy(k1_array, 9, k1_prime_array, 9, 1);
        System.arraycopy(k1_array, 11, k1_prime_array, 10, 1);
        System.arraycopy(k1_array, 5, k1_prime_array, 11, 1);


        System.arraycopy(k1_array, 7, k1_prime_array, 12, 1);
        System.arraycopy(k1_array, 12, k1_prime_array, 13, 1);
        System.arraycopy(k1_array, 10, k1_prime_array, 14, 1);
        System.arraycopy(k1_array, 2, k1_prime_array, 15, 1);

        System.arraycopy(k2_array, 14, k2_prime_array, 0, 1);
        System.arraycopy(k2_array, 3, k2_prime_array, 1, 1);
        System.arraycopy(k2_array, 8, k2_prime_array, 2, 1);
        System.arraycopy(k2_array, 13, k2_prime_array, 3, 1);

        System.arraycopy(k2_array, 1, k2_prime_array, 4, 1);
        System.arraycopy(k2_array, 0, k2_prime_array, 5, 1);
        System.arraycopy(k2_array, 15, k2_prime_array, 6, 1);
        System.arraycopy(k2_array, 4, k2_prime_array, 7, 1);

        System.arraycopy(k2_array, 6, k2_prime_array, 8, 1);
        System.arraycopy(k2_array, 9, k2_prime_array, 9, 1);
        System.arraycopy(k2_array, 11, k2_prime_array, 10, 1);
        System.arraycopy(k2_array, 5, k2_prime_array, 11, 1);


        System.arraycopy(k2_array, 7, k2_prime_array, 12, 1);
        System.arraycopy(k2_array, 12, k2_prime_array, 13, 1);
        System.arraycopy(k2_array, 10, k2_prime_array, 14, 1);
        System.arraycopy(k2_array, 2, k2_prime_array, 15, 1);


        //M1 third quadrant
        System.arraycopy(k3_array, 14, k3_prime_array, 0, 1);
        System.arraycopy(k3_array, 3, k3_prime_array, 1, 1);
        System.arraycopy(k3_array, 8, k3_prime_array, 2, 1);
        System.arraycopy(k3_array, 13, k3_prime_array, 3, 1);

        System.arraycopy(k3_array, 1, k3_prime_array, 4, 1);
        System.arraycopy(k3_array, 0, k3_prime_array, 5, 1);
        System.arraycopy(k3_array, 15, k3_prime_array, 6, 1);
        System.arraycopy(k3_array, 4, k3_prime_array, 7, 1);

        System.arraycopy(k3_array, 6, k3_prime_array, 8, 1);
        System.arraycopy(k3_array, 9, k3_prime_array, 9, 1);
        System.arraycopy(k3_array, 11, k3_prime_array, 10, 1);
        System.arraycopy(k3_array, 5, k3_prime_array, 11, 1);


        System.arraycopy(k3_array, 7, k3_prime_array, 12, 1);
        System.arraycopy(k3_array, 12, k3_prime_array, 13, 1);
        System.arraycopy(k3_array, 10, k3_prime_array, 14, 1);
        System.arraycopy(k3_array, 2, k3_prime_array, 15, 1);

        System.arraycopy(k4_array, 14, k4_prime_array, 0, 1);
        System.arraycopy(k4_array, 3, k4_prime_array, 1, 1);
        System.arraycopy(k4_array, 8, k4_prime_array, 2, 1);
        System.arraycopy(k4_array, 13, k4_prime_array, 3, 1);

        System.arraycopy(k4_array, 1, k4_prime_array, 4, 1);
        System.arraycopy(k4_array, 0, k4_prime_array, 5, 1);
        System.arraycopy(k4_array, 15, k4_prime_array, 6, 1);
        System.arraycopy(k4_array, 4, k4_prime_array, 7, 1);

        System.arraycopy(k4_array, 6, k4_prime_array, 8, 1);
        System.arraycopy(k4_array, 9, k4_prime_array, 9, 1);
        System.arraycopy(k4_array, 11, k4_prime_array, 10, 1);
        System.arraycopy(k4_array, 5, k4_prime_array, 11, 1);


        System.arraycopy(k4_array, 7, k4_prime_array, 12, 1);
        System.arraycopy(k4_array, 12, k4_prime_array, 13, 1);
        System.arraycopy(k4_array, 10, k4_prime_array, 14, 1);
        System.arraycopy(k4_array, 2, k4_prime_array, 15, 1);

        k1_prime = monoToBidi(k1_prime_array);
        k2_prime = monoToBidi(k2_prime_array);
        k3_prime = monoToBidi(k3_prime_array);
        k4_prime = monoToBidi(k4_prime_array);


        //Plotting standard XY axis graph values
        System.arraycopy(m1_cipher_array, 15, m1_array, 0, 1);
        System.arraycopy(m1_cipher_array, 14, m1_array, 1, 1);
        System.arraycopy(m1_cipher_array, 13, m1_array, 2, 1);
        System.arraycopy(m1_cipher_array, 12, m1_array, 3, 1);

        System.arraycopy(m1_cipher_array, 11, m1_array, 4, 1);
        System.arraycopy(m1_cipher_array, 10, m1_array, 5, 1);
        System.arraycopy(m1_cipher_array, 9, m1_array, 6, 1);
        System.arraycopy(m1_cipher_array, 8, m1_array, 7, 1);

        System.arraycopy(m1_cipher_array, 7, m1_array, 8, 1);
        System.arraycopy(m1_cipher_array, 6, m1_array, 9, 1);
        System.arraycopy(m1_cipher_array, 5, m1_array, 10, 1);
        System.arraycopy(m1_cipher_array, 4, m1_array, 11, 1);

        System.arraycopy(m1_cipher_array, 3, m1_array, 12, 1);
        System.arraycopy(m1_cipher_array, 2, m1_array, 13, 1);
        System.arraycopy(m1_cipher_array, 1, m1_array, 14, 1);
        System.arraycopy(m1_cipher_array, 0, m1_array, 15, 1);

        m1 = monoToBidi(m1_array);


        System.arraycopy(m2_cipher_array, 12, m2_array, 0, 1);
        System.arraycopy(m2_cipher_array, 13, m2_array, 1, 1);
        System.arraycopy(m2_cipher_array, 14, m2_array, 2, 1);
        System.arraycopy(m2_cipher_array, 15, m2_array, 3, 1);

        System.arraycopy(m2_cipher_array, 8, m2_array, 4, 1);
        System.arraycopy(m2_cipher_array, 9, m2_array, 5, 1);
        System.arraycopy(m2_cipher_array, 10, m2_array, 6, 1);
        System.arraycopy(m2_cipher_array, 11, m2_array, 7, 1);

        System.arraycopy(m2_cipher_array, 4, m2_array, 8, 1);
        System.arraycopy(m2_cipher_array, 5, m2_array, 9, 1);
        System.arraycopy(m2_cipher_array, 6, m2_array, 10, 1);
        System.arraycopy(m2_cipher_array, 7, m2_array, 11, 1);


        System.arraycopy(m2_cipher_array, 0, m2_array, 12, 1);
        System.arraycopy(m2_cipher_array, 1, m2_array, 13, 1);
        System.arraycopy(m2_cipher_array, 2, m2_array, 14, 1);
        System.arraycopy(m2_cipher_array, 3, m2_array, 15, 1);

        m2 = monoToBidi(m2_array);

        System.arraycopy(m3_cipher_array, 0, m3_array, 0, 1);
        System.arraycopy(m3_cipher_array, 1, m3_array, 1, 1);
        System.arraycopy(m3_cipher_array, 2, m3_array, 2, 1);
        System.arraycopy(m3_cipher_array, 3, m3_array, 3, 1);

        System.arraycopy(m3_cipher_array, 4, m3_array, 4, 1);
        System.arraycopy(m3_cipher_array, 5, m3_array, 5, 1);
        System.arraycopy(m3_cipher_array, 6, m3_array, 6, 1);
        System.arraycopy(m3_cipher_array, 7, m3_array, 7, 1);

        System.arraycopy(m3_cipher_array, 8, m3_array, 8, 1);
        System.arraycopy(m3_cipher_array, 9, m3_array, 9, 1);
        System.arraycopy(m3_cipher_array, 10, m3_array, 10, 1);
        System.arraycopy(m3_cipher_array, 11, m3_array, 11, 1);


        System.arraycopy(m3_cipher_array, 12, m3_array, 12, 1);
        System.arraycopy(m3_cipher_array, 13, m3_array, 13, 1);
        System.arraycopy(m3_cipher_array, 14, m3_array, 14, 1);
        System.arraycopy(m3_cipher_array, 15, m3_array, 15, 1);

        m3 = monoToBidi(m3_array);


        System.arraycopy(m4_cipher_array, 3, m4_array, 0, 1);
        System.arraycopy(m4_cipher_array, 2, m4_array, 1, 1);
        System.arraycopy(m4_cipher_array, 1, m4_array, 2, 1);
        System.arraycopy(m4_cipher_array, 0, m4_array, 3, 1);

        System.arraycopy(m4_cipher_array, 7, m4_array, 4, 1);
        System.arraycopy(m4_cipher_array, 6, m4_array, 5, 1);
        System.arraycopy(m4_cipher_array, 5, m4_array, 6, 1);
        System.arraycopy(m4_cipher_array, 4, m4_array, 7, 1);

        System.arraycopy(m4_cipher_array, 11, m4_array, 8, 1);
        System.arraycopy(m4_cipher_array, 10, m4_array, 9, 1);
        System.arraycopy(m4_cipher_array, 9, m4_array, 10, 1);
        System.arraycopy(m4_cipher_array, 8, m4_array, 11, 1);


        System.arraycopy(m4_cipher_array, 15, m4_array, 12, 1);
        System.arraycopy(m4_cipher_array, 14, m4_array, 13, 1);
        System.arraycopy(m4_cipher_array, 13, m4_array, 14, 1);
        System.arraycopy(m4_cipher_array, 12, m4_array, 15, 1);

        m4 = monoToBidi(m4_array);


        //Counting 1's in K1
        int count = 0;
        for (int i = 0; i < k1_array.length; i++) {
            if (k1_array[i] == 1) {
                count++;
            }

        }
        int remainder = count % 4;

        if (remainder == 0) {
            m1_prime = m1;
            xor(m1_prime, k1_prime, m1_prime_new);
            firstquadshiftcopy(bidiToMono(m1_prime_new), hero1);
            xor(monoToBidi(hero1), k1, m1_double_prime);
        } else if (remainder == 1) {
            m1_prime = m1;
            xor(m1_prime, k1_prime, m1_prime_new);
            secondquadshiftcopy(bidiToMono(m1_prime_new), hero1);
            xor(monoToBidi(hero1), k2, m1_double_prime);

        } else if (remainder == 2) {
            m1_prime = m1;
            xor(m1_prime, k1_prime, m1_prime_new);
            thirdquadshiftcopy(bidiToMono(m1_prime_new), hero1);
            xor(monoToBidi(hero1), k3, m1_double_prime);


        } else if (remainder == 3) {
            m1_prime = m1;
            xor(m1_prime, k1_prime, m1_prime_new);
            fourthquadshiftcopy(bidiToMono(m1_prime_new), hero1);
            xor(monoToBidi(hero1), k4, m1_double_prime);
        }


        int count2 = 0;
        for (int i = 0; i < k2_array.length; i++) {
            if (k2_array[i] == 1) {
                count2++;
            }

        }
        int remainder2 = count2 % 4;

        if (remainder2 == 0) {
            m2_prime = m2;
            xor(m2_prime, k2_prime, m2_prime_new);
            firstquadshiftcopy(bidiToMono(m2_prime_new), hero2);
            xor(monoToBidi(hero2), k1, m2_double_prime);
        } else if (remainder2 == 1) {
            m2_prime = m2;
            xor(m2_prime, k2_prime, m2_prime_new);
            secondquadshiftcopy(bidiToMono(m2_prime_new), hero2);
            xor(monoToBidi(hero2), k2, m2_double_prime);

        } else if (remainder2 == 2) {
            m2_prime = m2;
            xor(m2_prime, k2_prime, m2_prime_new);
            thirdquadshiftcopy(bidiToMono(m2_prime_new), hero2);
            xor(monoToBidi(hero2), k3, m2_double_prime);


        } else if (remainder2 == 3) {
            m2_prime = m2;
            xor(m2_prime, k2_prime, m2_prime_new);
            fourthquadshiftcopy(bidiToMono(m2_prime_new), hero2);
            xor(monoToBidi(hero2), k4, m2_double_prime);
        }


        int count3 = 0;
        for (int i = 0; i < k3_array.length; i++) {
            if (k3_array[i] == 1) {
                count3++;
            }

        }
        int remainder3 = count3 % 4;


        if (remainder3 == 0) {
            m3_prime = m3;
            xor(m3_prime, k3_prime, m3_prime_new);
            firstquadshiftcopy(bidiToMono(m3_prime_new), hero3);
            xor(monoToBidi(hero3), k1, m3_double_prime);
        } else if (remainder3 == 1) {
            m3_prime = m3;
            xor(m3_prime, k3_prime, m3_prime_new);
            byte[] m3_prime_new_array = bidiToMono(m3_prime_new);
            secondquadshiftcopy(m3_prime_new_array, hero3);
            xor(monoToBidi(hero3), k2, m3_double_prime);

        } else if (remainder3 == 2) {
            m3_prime = m3;
            xor(m3_prime, k3_prime, m3_prime_new);
            thirdquadshiftcopy(bidiToMono(m3_prime_new), hero3);
            xor(monoToBidi(hero3), k3, m3_double_prime);


        } else if (remainder3 == 3) {
            m3_prime = m3;
            xor(m3_prime, k3_prime, m3_prime_new);
            fourthquadshiftcopy(bidiToMono(m3_prime_new), hero3);
            xor(monoToBidi(hero3), k4, m3_double_prime);
        }


        int count4 = 0;
        for (int i = 0; i < k4_array.length; i++) {
            if (k4_array[i] == 1) {
                count4++;
            }

        }
        int remainder4 = count4 % 4;

        if (remainder4 == 0) {
            m4_prime = m4;
            xor(m4_prime, k4_prime, m4_prime_new);
            firstquadshiftcopy(bidiToMono(m4_prime_new), hero4);
            xor(monoToBidi(hero4), k1, m4_double_prime);
        } else if (remainder4 == 1) {
            m4_prime = m4;
            xor(m4_prime, k4_prime, m4_prime_new);
            secondquadshiftcopy(bidiToMono(m4_prime_new), hero4);
            xor(monoToBidi(hero4), k2, m4_double_prime);

        } else if (remainder4 == 2) {
            m4_prime = m4;
            xor(m4_prime, k4_prime, m4_prime_new);
            thirdquadshiftcopy(bidiToMono(m4_prime_new), hero4);
            xor(monoToBidi(hero4), k3, m4_double_prime);


        } else if (remainder4 == 3) {
            m4_prime = m4;
            xor(m4_prime, k4_prime, m4_prime_new);
            fourthquadshiftcopy(bidiToMono(m4_prime_new), hero4);
            xor(monoToBidi(hero4), k4, m4_double_prime);
        }


        tmp1 = m3_double_prime;
        tmp2 = m4_double_prime;
        m3_double_prime = tmp1;
        m4_double_prime = tmp2;


        byte[][] m_plaintext = compositearray(m1_double_prime, m2_double_prime, m4_double_prime, m3_double_prime);
        byte[][] key_array = compositearray(k1, k2, k3, k4);
        byte[][] m_cipher = compositearray(m1_cipher, m2_cipher, m3_cipher, m4_cipher);

        //Conclusion
        long endDecTime = System.currentTimeMillis();


        decryptTime = (endDecTime - stDecTm);

     /*   System.out.println("Key:" + compositearraytoString(k1, k2, k4, k3));
        System.out.println("Cipher:" + compositearraytoString(m1_cipher, m2_cipher, m4_cipher, m3_cipher));
*/
        String plainText = compositearraytoString(m1_double_prime, m2_double_prime, m3_double_prime, m4_double_prime);
  /*      System.out.println("Plaintext:" + plainText);
        System.out.println("decryption time in ms : " + decryptTime);
  */      return plainText;




    }

    private static void firstquadshiftcopy(byte[] arrayin, byte[] array2copy) {
        System.arraycopy(arrayin, 0, array2copy, 0, 1);
        System.arraycopy(arrayin, 1, array2copy, 1, 1);
        System.arraycopy(arrayin, 2, array2copy, 2, 1);
        System.arraycopy(arrayin, 3, array2copy, 3, 1);

        System.arraycopy(arrayin, 5, array2copy, 4, 1);
        System.arraycopy(arrayin, 6, array2copy, 5, 1);
        System.arraycopy(arrayin, 7, array2copy, 6, 1);
        System.arraycopy(arrayin, 4, array2copy, 7, 1);

        System.arraycopy(arrayin, 10, array2copy, 8, 1);
        System.arraycopy(arrayin, 11, array2copy, 9, 1);
        System.arraycopy(arrayin, 8, array2copy, 10, 1);
        System.arraycopy(arrayin, 9, array2copy, 11, 1);


        System.arraycopy(arrayin, 15, array2copy, 12, 1);
        System.arraycopy(arrayin, 12, array2copy, 13, 1);
        System.arraycopy(arrayin, 13, array2copy, 14, 1);
        System.arraycopy(arrayin, 14, array2copy, 15, 1);
    }

    private static void secondquadshiftcopy(byte[] arrayin, byte[] array2copy) {
        System.arraycopy(arrayin, 3, array2copy, 0, 1);
        System.arraycopy(arrayin, 0, array2copy, 1, 1);
        System.arraycopy(arrayin, 1, array2copy, 2, 1);
        System.arraycopy(arrayin, 2, array2copy, 3, 1);

        System.arraycopy(arrayin, 4, array2copy, 4, 1);
        System.arraycopy(arrayin, 5, array2copy, 5, 1);
        System.arraycopy(arrayin, 6, array2copy, 6, 1);
        System.arraycopy(arrayin, 7, array2copy, 7, 1);

        System.arraycopy(arrayin, 9, array2copy, 8, 1);
        System.arraycopy(arrayin, 10, array2copy, 9, 1);
        System.arraycopy(arrayin, 11, array2copy, 10, 1);
        System.arraycopy(arrayin, 8, array2copy, 11, 1);


        System.arraycopy(arrayin, 14, array2copy, 12, 1);
        System.arraycopy(arrayin, 15, array2copy, 13, 1);
        System.arraycopy(arrayin, 12, array2copy, 14, 1);
        System.arraycopy(arrayin, 13, array2copy, 15, 1);
    }

    private static void thirdquadshiftcopy(byte[] arrayin, byte[] array2copy) {
        System.arraycopy(arrayin, 2, array2copy, 0, 1);
        System.arraycopy(arrayin, 3, array2copy, 1, 1);
        System.arraycopy(arrayin, 0, array2copy, 2, 1);
        System.arraycopy(arrayin, 1, array2copy, 3, 1);

        System.arraycopy(arrayin, 7, array2copy, 4, 1);
        System.arraycopy(arrayin, 4, array2copy, 5, 1);
        System.arraycopy(arrayin, 5, array2copy, 6, 1);
        System.arraycopy(arrayin, 6, array2copy, 7, 1);

        System.arraycopy(arrayin, 8, array2copy, 8, 1);
        System.arraycopy(arrayin, 9, array2copy, 9, 1);
        System.arraycopy(arrayin, 10, array2copy, 10, 1);
        System.arraycopy(arrayin, 11, array2copy, 11, 1);


        System.arraycopy(arrayin, 13, array2copy, 12, 1);
        System.arraycopy(arrayin, 14, array2copy, 13, 1);
        System.arraycopy(arrayin, 15, array2copy, 14, 1);
        System.arraycopy(arrayin, 12, array2copy, 15, 1);
    }

    private static void fourthquadshiftcopy(byte[] arrayin, byte[] array2copy) {
        System.arraycopy(arrayin, 1, array2copy, 0, 1);
        System.arraycopy(arrayin, 2, array2copy, 1, 1);
        System.arraycopy(arrayin, 3, array2copy, 2, 1);
        System.arraycopy(arrayin, 0, array2copy, 3, 1);

        System.arraycopy(arrayin, 6, array2copy, 4, 1);
        System.arraycopy(arrayin, 7, array2copy, 5, 1);
        System.arraycopy(arrayin, 4, array2copy, 6, 1);
        System.arraycopy(arrayin, 5, array2copy, 7, 1);

        System.arraycopy(arrayin, 11, array2copy, 8, 1);
        System.arraycopy(arrayin, 8, array2copy, 9, 1);
        System.arraycopy(arrayin, 9, array2copy, 10, 1);
        System.arraycopy(arrayin, 10, array2copy, 11, 1);


        System.arraycopy(arrayin, 12, array2copy, 12, 1);
        System.arraycopy(arrayin, 13, array2copy, 13, 1);
        System.arraycopy(arrayin, 14, array2copy, 14, 1);
        System.arraycopy(arrayin, 15, array2copy, 15, 1);
    }

    private static String compositearraytoString(byte[][] k1, byte[][] k2, byte[][] k3, byte[][] k4) {
        byte[][] key_array = new byte[8][8];
        byte[] key_array_1D = new byte[16];


        byte[][] result1 = new byte[k1.length + k2.length][];
        System.arraycopy(k1, 0, result1, 0, k1.length);
        System.arraycopy(k2, 0, result1, k1.length, k2.length);

        byte[][] result2 = new byte[k3.length + k4.length][];

        System.arraycopy(k3, 0, result2, 0, k3.length);
        System.arraycopy(k4, 0, result2, k3.length, k4.length);

        key_array = new byte[result1.length + result2.length][];

        System.arraycopy(result1, 0, key_array, 0, result1.length);
        System.arraycopy(result2, 0, key_array, result1.length, result2.length);

        String[] encodedOutput = new String[8];
        encodedOutput[0] = "" + key_array[0][0] + key_array[0][1] + key_array[0][2] + key_array[0][3] + key_array[4][0] + key_array[4][1] + key_array[4][2] + key_array[4][3];
        encodedOutput[1] = "" + key_array[1][0] + key_array[1][1] + key_array[1][2] + key_array[1][3] + key_array[5][0] + key_array[5][1] + key_array[5][2] + key_array[5][3];

        encodedOutput[2] = "" + key_array[2][0] + key_array[2][1] + key_array[2][2] + key_array[2][3] + key_array[6][0] + key_array[6][1] + key_array[6][2] + key_array[6][3];

        encodedOutput[3] = "" + key_array[3][0] + key_array[3][1] + key_array[3][2] + key_array[3][3] + key_array[7][0] + key_array[7][1] + key_array[7][2] + key_array[7][3];

        encodedOutput[4] = "" + key_array[12][0] + key_array[12][1] + key_array[12][2] + key_array[12][3] + key_array[8][0] + key_array[8][1] + key_array[8][2] + key_array[8][3];

        encodedOutput[5] = "" + key_array[13][0] + key_array[13][1] + key_array[13][2] + key_array[13][3] + key_array[9][0] + key_array[9][1] + key_array[9][2] + key_array[9][3];

        encodedOutput[6] = "" + key_array[14][0] + key_array[14][1] + key_array[14][2] + key_array[14][3] + key_array[10][0] + key_array[10][1] + key_array[10][2] + key_array[10][3];

        encodedOutput[7] = "" + key_array[15][0] + key_array[15][1] + key_array[15][2] + key_array[15][3] + key_array[11][0] + key_array[11][1] + key_array[11][2] + key_array[11][3];


        char hero[] = new char[8];
        for (int i = 0; i < encodedOutput.length; i++) {
            int parseInt = Integer.parseInt(encodedOutput[i], 2);
            char c = (char) parseInt;
            hero[i] = c;

        }
        String encodedString = new String(hero).toString();


        return encodedString;
    }

    private static byte[][] compositearray(byte[][] k1, byte[][] k2, byte[][] k3, byte[][] k4) {
        byte[][] key_array = new byte[8][8];
        byte[] key_array_1D = new byte[16];

        byte[][] result1 = new byte[k1.length + k2.length][];
        System.arraycopy(k1, 0, result1, 0, k1.length);
        System.arraycopy(k2, 0, result1, k1.length, k2.length);

        byte[][] result2 = new byte[k3.length + k4.length][];

        System.arraycopy(k3, 0, result2, 0, k3.length);
        System.arraycopy(k4, 0, result2, k3.length, k4.length);

        key_array = new byte[result1.length + result2.length][];
        byte[][] dest = key_array;

        System.arraycopy(result1, 0, key_array, 0, result1.length);
        System.arraycopy(result2, 0, key_array, result1.length, result2.length);

        String[] encodedOutput = new String[8];
        encodedOutput[0] = "" + key_array[0][0] + key_array[0][1] + key_array[0][2] + key_array[0][3] + key_array[4][0] + key_array[4][1] + key_array[4][2] + key_array[4][3];
        encodedOutput[1] = "" + key_array[1][0] + key_array[1][1] + key_array[1][2] + key_array[1][3] + key_array[5][0] + key_array[5][1] + key_array[5][2] + key_array[5][3];

        encodedOutput[2] = "" + key_array[2][0] + key_array[2][1] + key_array[2][2] + key_array[2][3] + key_array[6][0] + key_array[6][1] + key_array[6][2] + key_array[6][3];

        encodedOutput[3] = "" + key_array[3][0] + key_array[3][1] + key_array[3][2] + key_array[3][3] + key_array[7][0] + key_array[7][1] + key_array[7][2] + key_array[7][3];

        encodedOutput[4] = "" + key_array[12][0] + key_array[12][1] + key_array[12][2] + key_array[12][3] + key_array[8][0] + key_array[8][1] + key_array[8][2] + key_array[8][3];

        encodedOutput[5] = "" + key_array[13][0] + key_array[13][1] + key_array[13][2] + key_array[13][3] + key_array[9][0] + key_array[9][1] + key_array[9][2] + key_array[9][3];

        encodedOutput[6] = "" + key_array[14][0] + key_array[14][1] + key_array[14][2] + key_array[14][3] + key_array[10][0] + key_array[10][1] + key_array[10][2] + key_array[10][3];

        encodedOutput[7] = "" + key_array[15][0] + key_array[15][1] + key_array[15][2] + key_array[15][3] + key_array[11][0] + key_array[11][1] + key_array[11][2] + key_array[11][3];


        char hero[] = new char[8];
        for (int i = 0; i < encodedOutput.length; i++) {
            int parseInt = Integer.parseInt(encodedOutput[i], 2);
            char c = (char) parseInt;
            hero[i] = c;

        }
        String encodedString = new String(hero).toString();
        return dest;
    }

    private static String[] takeTheInput(String a) {

        String inp = a;
        System.out.println(inp);
        Scanner scanner = new Scanner(in);

        text = scanner.next();
        char[] charArray = {0, 0, 0, 0, 0, 0, 0, 0};
        int i = 0;
        while (i < 8) {
            if (i < text.length()) {
                charArray[i] = text.charAt(i);
            }
            i++;
        }

        String paddedinput = new String(charArray);
        String[] input = toBinary(paddedinput, 8).split(" ");

//        String[] input = {"01010100", "01000101", "01010011", "01010100", "01001000", "01000111", "01000101", "01000001"};

        return input;

    }

    private static byte[] bidiToMono(final byte[][] array) {
        int rows = array.length, cols = array[0].length;
        byte[] mono = new byte[(rows * cols)];
        for (int i = 0; i < rows; i++)
            System.arraycopy(array[i], 0, mono, (i * cols), cols);
        return mono;
    }

    private static byte[][] monoToBidi(final byte[] array) {
        if (array.length != (16))
            throw new IllegalArgumentException("Invalid array length");

        byte[][] bidi = new byte[4][4];
        for (int i = 0; i < 4; i++)
            System.arraycopy(array, (i * 4), bidi[i], 0, 4);

        return bidi;
    }

    private static byte[][] xor(final byte[][] array1, final byte[][] array2, final byte[][] dest) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int sum;
                for (int x = 0; x < 4; x++) {
                    sum = array1[j][x] ^ array2[j][x];
                    dest[j][x] = (byte) sum;
                }
            }

        }

        return dest;
    }

    private static String toBinary(String plaintextbin, int bits) {
        String result = "";
        String tmpStr;
        int tmpInt;
        char[] messChar = plaintextbin.toCharArray();

        for (int i = 0; i < messChar.length; i++) {
            tmpStr = Integer.toBinaryString(messChar[i]);
            tmpInt = tmpStr.length();
            if (tmpInt != bits) {
                tmpInt = bits - tmpInt;
                if (tmpInt == bits) {
                    result += tmpStr;
                } else if (tmpInt > 0) {
                    for (int j = 0; j < tmpInt; j++) {
                        result += "0";
                    }
                    result += tmpStr;
                } else {
                    System.err.println("argument 'bits' is too small");
                }
            } else {
                result += tmpStr;
            }
            result += " "; // separator
        }

        return result;
    }

    private static String getInputText() {
        System.out.println("Please enter the cipher text :");
        Scanner scanner = new Scanner(in);

        text = scanner.next();

//        String[] input = {"01010100", "01000101", "01010011", "01010100", "01001000", "01000111", "01000101", "01000001"};

        return text;

    }


}
