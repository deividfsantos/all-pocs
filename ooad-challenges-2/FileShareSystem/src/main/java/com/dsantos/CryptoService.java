package com.dsantos;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;
import java.util.Arrays;

public class CryptoService {
    private static final String ALG = "AES";
    private static final String TRANSFORM = "AES/GCM/NoPadding";
    private static final int KEY_BITS = 128;
    private static final int IV_LENGTH = 12; // recommended for GCM
    private static final int TAG_LENGTH_BIT = 128;
    private final SecretKey key;
    private final SecureRandom random = new SecureRandom();

    public CryptoService() {
        this.key = generateKey();
    }

    private SecretKey generateKey() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(ALG);
            kg.init(KEY_BITS);
            return kg.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] encrypt(byte[] plain) {
        try {
            byte[] iv = new byte[IV_LENGTH];
            random.nextBytes(iv);
            Cipher cipher = Cipher.getInstance(TRANSFORM);
            GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);
            cipher.init(Cipher.ENCRYPT_MODE, key, spec);
            byte[] cipherText = cipher.doFinal(plain);
            byte[] out = new byte[iv.length + cipherText.length];
            System.arraycopy(iv, 0, out, 0, iv.length);
            System.arraycopy(cipherText, 0, out, iv.length, cipherText.length);
            return out;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] decrypt(byte[] encrypted) {
        try {
            byte[] iv = Arrays.copyOfRange(encrypted, 0, IV_LENGTH);
            byte[] cipherText = Arrays.copyOfRange(encrypted, IV_LENGTH, encrypted.length);
            Cipher cipher = Cipher.getInstance(TRANSFORM);
            GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);
            cipher.init(Cipher.DECRYPT_MODE, key, spec);
            return cipher.doFinal(cipherText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
