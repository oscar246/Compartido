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
 * Decrypt forma System Ambulance Fast.
 * <p/>
 * Created by Oscar Giraldo on 18/03/2016.
 */
public class SAFDecrypt {

    private static SAFDecrypt INSTANCIA;
    private static final String mKey = "MILLAVEMAESTRASOFTNEWWORLD";

    Cipher mDcipher;

    // Key de 8bytes Salt : El Gozon
    byte[] mBytes = "El Gozon".getBytes();

    // Iteration count
    int i = 19;

    public static SAFDecrypt getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new SAFDecrypt(mKey);
        }
        return INSTANCIA;
    }

    private SAFDecrypt(final String passPhrase) {
        try {
            // Create the key
            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), mBytes, i);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            mDcipher = Cipher.getInstance(key.getAlgorithm());

            // Prepare the parameter to the ciphers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(mBytes, i);

            // Create the ciphers
            mDcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        } catch (java.security.InvalidAlgorithmParameterException e) {
        } catch (java.security.spec.InvalidKeySpecException e) {
        } catch (javax.crypto.NoSuchPaddingException e) {
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (java.security.InvalidKeyException e) {
        }
    }

    public String decrypt(final String str) {
        try {

            // Decode base64 to get bytes
//            byte[] dec = Base64.decodeBase64(str);
            byte[] dec = Base64.decode(str, 1);

            // Decrypt
            byte[] utf8 = mDcipher.doFinal(dec);

            // Decode using utf-8
            String bin = new String(utf8, "UTF-8");

            return bin;
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String toBin(String bin) {
        String cadena = "";
        for (int i = 0; i < bin.length(); i++) {
            cadena += bin.charAt(i);
        }
        System.out.println(cadena);
        System.out.println(bin);
        return cadena;
    }

}
