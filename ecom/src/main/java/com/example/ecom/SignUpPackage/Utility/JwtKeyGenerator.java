package com.example.ecom.SignUpPackage.Utility;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtKeyGenerator {
    public static void main(String[] args) {
        // Generate a secure HS256 key
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        // Encode it as Base64 to store in properties file
        String base64Key = Encoders.BASE64.encode(key.getEncoded());
        System.out.println("Your new HS256 Base64 key is: " + base64Key);
    }
}

