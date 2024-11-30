package com.petshop.petshop_inventory.infra.security.config;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class HmacEncryption {

    private static final String HMAC_ALGORITHM = "HmacSHA256";

    /**
     * Método para encriptar una clave usando HMAC-SHA256
     *
     * @param key la clave a encriptar
     * @return la clave encriptada en formato Base64
     * @throws Exception si ocurre algún error durante el proceso
     */
    public static String encryptKey(String key) throws Exception {
        // Crear un mensaje fijo o un dato base para el HMAC
        String data = "data_fijo_para_hmac";

        // Crear el SecretKeySpec con la clave proporcionada
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMAC_ALGORITHM);

        // Inicializar el algoritmo HMAC con el SecretKeySpec
        Mac mac = Mac.getInstance(HMAC_ALGORITHM);
        mac.init(secretKeySpec);

        // Generar el HMAC para los datos fijos
        byte[] hmacBytes = mac.doFinal(data.getBytes());

        // Retornar el resultado en Base64
        return Base64.getEncoder().encodeToString(hmacBytes);
    }

}
