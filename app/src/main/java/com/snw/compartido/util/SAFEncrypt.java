package com.snw.compartido.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * Encrypt forma System Ambulance Fast.
 * <p/>
 * Created by Oscar Giraldo on 18/03/2016.
 */
public class SAFEncrypt {

    private static SAFEncrypt INSTANCIA;
    private static final String mKey = "MILLAVEMAESTRASOFTNEWWORLD";

    Cipher mCipher;

    // Key de 8bytes Salt : El Gozon
    byte[] mBytes = "El Gozon".getBytes();

    // Iteration count
    int i = 19;

    public static SAFEncrypt getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new SAFEncrypt(mKey);
        }
        return INSTANCIA;
    }

    private SAFEncrypt(String passPhrase) {
        try {
            // Create the key
            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), mBytes, i);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            mCipher = Cipher.getInstance(key.getAlgorithm());

            // Prepare the parameter to the ciphers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(mBytes, i);

            // Create the ciphers
            mCipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
        } catch (java.security.InvalidAlgorithmParameterException e) {
        } catch (java.security.spec.InvalidKeySpecException e) {
        } catch (javax.crypto.NoSuchPaddingException e) {
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (java.security.InvalidKeyException e) {
        }
    }

    public String encrypt(String str) {
        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF-8");

            // Encrypt
            byte[] enc = mCipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            //String base64 = Base64.encodeBase64String(enc);
            String base64 = Base64.encodeToString(enc, 0);

            return base64;
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

}
