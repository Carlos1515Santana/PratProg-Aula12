pack
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
   
import javax.crypto.Cipher;

public class T_EncriptaDecriptaAES { 
   static String IV = null;
   
   public T_EncriptaDecriptaAES() { 
      IV = "AAAAAAAAAAAAAAAA";
   } 
      
   public static byte[] encrypt(String textopuro, String chaveencriptacao) throws Exception { 
      Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
      SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("ISO-8859-1"), "AES"); 
      encripta.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("ISO-8859-1"))); 
      return encripta.doFinal(textopuro.getBytes("ISO-8859-1")); 
   }
   public static String decrypt(byte[] textoencriptado, String chaveencriptacao) throws Exception{ 
      Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
      SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("ISO-8859-1"), "AES"); 
      decripta.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("ISO-8859-1"))); 
      return new String(decripta.doFinal(textoencriptado),"ISO-8859-1"); 
   } 
}