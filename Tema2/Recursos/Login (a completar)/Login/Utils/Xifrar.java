/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author joange
 */
public class Xifrar {
    

	/* Retorna un hash a partir de un tipo y un texto */
	public static String getHash(String txt, String hashType) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance(hashType);
			byte[] array = md.digest(txt.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/* Retorna un hash MD5 a partir de un texto de 32 de longitud*/
	public static String md5(String txt) {
		return Xifrar.getHash(txt, "MD5");
	}

	/* Retorna un hash SHA1 a partir de un texto de 40 de longitud*/
	public static String sha1(String txt) {
		return Xifrar.getHash(txt, "SHA1");
	}

}