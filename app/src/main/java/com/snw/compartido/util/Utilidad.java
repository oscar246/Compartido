package com.snw.compartido.util;

/**
 * Created by Oscar Giraldo on 25/04/2016.
 */
public class Utilidad {

    public static void main(String[] args) {
        String clave = "Hola Mundo";
        byte[] claveBytes = clave.getBytes();

        String encrypt = SAFEncrypt.getInstancia().encrypt(clave);
        System.out.println(encrypt.toString());
        String decrypt = SAFDecrypt.getInstancia().decrypt(clave);
        System.out.println(decrypt.toString());

    }

}
