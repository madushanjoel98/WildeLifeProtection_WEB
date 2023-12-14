package wild.protection.utils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;





@Component
public class EncryptionText {
	// Encrypt and decrypt

	private static EncryptionText instance = new EncryptionText();
	private static SecretKeySpec secretKey;
		private static byte[] key;
		@Value("${wild.secret}")
		private String secret;
		private EncryptionText() {
			// The following code emulates slow initialization.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}

		}

		public static EncryptionText getInstance() {
			if (instance == null) {
				instance = new EncryptionText();
			}
			return instance;
		}
		
		public  void setKey(String myKey) {
			MessageDigest sha = null;
			try {

				key = myKey.getBytes("UTF-8");

				sha = MessageDigest.getInstance("SHA-1");

				key = sha.digest(key);

				key = Arrays.copyOf(key, 16);
				secretKey = new SecretKeySpec(key, "AES");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		//Encryption 
		public  String encrypt(String strToEncrypt) throws Exception {

			try {
				setKey(this.secret);
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				cipher.init(Cipher.ENCRYPT_MODE, secretKey);
				return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			
		}

		//Decryption
		public  String decrypt(String strToDecrypt) throws Exception {
			try {

				setKey(this.secret);
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
				cipher.init(Cipher.DECRYPT_MODE, secretKey);
				return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			
		}
		
		public  String idEncrypt(long id) throws Exception {

			try {
				setKey(this.secret);
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				cipher.init(Cipher.ENCRYPT_MODE, secretKey);
				String encryptid=String.valueOf(id);
				return Base64.getEncoder().encodeToString(cipher.doFinal(encryptid.getBytes("UTF-8")));
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			
		}

		//Decryption
		public  long idDecrypt(String strToDecrypt) throws Exception {
			try {

				setKey(this.secret);
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
				cipher.init(Cipher.DECRYPT_MODE, secretKey);
				String output=new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
				return Long.valueOf(output);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			
		}


}
