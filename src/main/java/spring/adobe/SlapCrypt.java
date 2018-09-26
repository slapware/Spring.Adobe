package spring.adobe;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
class SlapCrypt {

	public String decrypt(byte[] cipherText, String encryptionKey, String myIV) throws Exception{
//	    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
	    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
	    cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(myIV.getBytes("UTF-8")));
	    return new String(cipher.doFinal(cipherText),"UTF-8");
	  }
	
	public byte[] ecrypt(String plainText, String encryptionKey, String myIV) throws Exception{
//	    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
	    byte[] clean = plainText.getBytes();
	    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
	    cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(myIV.getBytes("UTF-8")));
	    byte[] encrypted = cipher.doFinal(clean);
	    return encrypted;
	  }
}
