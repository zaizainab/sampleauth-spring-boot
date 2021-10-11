package com.ecomindo.onboarding.sampleauth.util;

import java.io.File;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class KeyReader {
	public static RSAPublicKey readPublicKey(File file) throws Exception {
		byte[] key=Files.readAllBytes(file.toPath());
	    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key,"RS256");
	    return (RSAPublicKey) keyFactory.generatePublic(keySpec);
	}
	
	public static RSAPrivateKey readPrivateKey(File file) throws Exception {
		byte[] key=Files.readAllBytes(file.toPath());
	    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key,"RS256");
	    return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
	}
}
