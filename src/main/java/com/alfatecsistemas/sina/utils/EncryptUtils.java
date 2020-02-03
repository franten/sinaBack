/**
 * Copyright 2018 Alfatec Sistemas.
 */
package com.alfatecsistemas.sina.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Encrypt utils.
 */
public class EncryptUtils {

  private EncryptUtils() {
    throw new IllegalStateException("Utility class");
  }


  /**
   * Hashing with SHA1
   *
   * @param input String to hash
   * @return String hashed
   */
  public static String sha1(final String input) {
    MessageDigest mDigest = null;
    try {
      mDigest = MessageDigest.getInstance("SHA1");
    } catch (final NoSuchAlgorithmException e) {}
    final byte[] result = mDigest.digest(input.getBytes());
    final StringBuilder sb = new StringBuilder();
    for (final byte b : result) {
      sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
    }

    return sb.toString();
  }

  public static String encrypt(final String plainText, final String key) throws Exception {
    try {
      final byte[] clean = plainText.getBytes();

      // Generating IV.
      final int ivSize = 16;
      final byte[] iv = new byte[ivSize];
      final SecureRandom random = new SecureRandom();
      random.nextBytes(iv);
      final IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

      // Hashing key.
      final MessageDigest digest = MessageDigest.getInstance("SHA-256");
      digest.update(key.getBytes("UTF-8"));
      final byte[] keyBytes = new byte[16];
      System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
      final SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

      // Encrypt.
      final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
      final byte[] encrypted = cipher.doFinal(clean);

      // Combine IV and encrypted part.
      final byte[] encryptedIVAndText = new byte[ivSize + encrypted.length];
      System.arraycopy(iv, 0, encryptedIVAndText, 0, ivSize);
      System.arraycopy(encrypted, 0, encryptedIVAndText, ivSize, encrypted.length);

      return Base64.getEncoder().encodeToString(encryptedIVAndText);
    } catch (final InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException |
            NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException |
            IllegalArgumentException e) {
      throw new Exception("Error");
    }
  }

  public static String decrypt(final String encryptedIvText, final String key) throws Exception{
    try {
      final int ivSize = 16;
      final int keySize = 16;

      final byte[] encryptedIvTextBytes = Base64.getDecoder().decode(encryptedIvText);

      // Extract IV.
      final byte[] iv = new byte[ivSize];
      System.arraycopy(encryptedIvTextBytes, 0, iv, 0, iv.length);
      final IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

      // Extract encrypted part.
      final int encryptedSize = encryptedIvTextBytes.length - ivSize;
      final byte[] encryptedBytes = new byte[encryptedSize];
      System.arraycopy(encryptedIvTextBytes, ivSize, encryptedBytes, 0, encryptedSize);

      // Hash key.
      final byte[] keyBytes = new byte[keySize];
      final MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(key.getBytes());
      System.arraycopy(md.digest(), 0, keyBytes, 0, keyBytes.length);
      final SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

      // Decrypt.
      final Cipher cipherDecrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
      final byte[] decrypted = cipherDecrypt.doFinal(encryptedBytes);

      return new String(decrypted);
    } catch (final InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException |
            NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
      throw new Exception("Error");
    }
  }
}
