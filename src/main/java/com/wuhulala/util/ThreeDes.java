package com.wuhulala.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/*字符串 DESede(3DES) 加密*/

public class ThreeDes {

	public static byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58,

		(byte) 0x88, 0x20, 0x48, 0x28, 0x38, 0x25, 0x79, 0x51,

		(byte) 0xCB,

		(byte) 0xDD, 0x56, 0x16, 0x77, 0x29, 0x74,

		(byte) 0x98, 0x30, 0x40, 0x4F,

		(byte) 0xE2

		}; 
	/**
	 * 
	 * @param args在java中调用sun公司提供的3DES加密解密算法时
	 *            ，需要使
	 * 
	 *            用到$JAVA_HOME/jre/lib/目录下如下的4个jar包：
	 * 
	 *            jce.jar
	 * 
	 *            security/US_export_policy.jar
	 * 
	 *            security/local_policy.jar
	 * 
	 *            ext/sunjce_provider.jar
	 */

	private static final String Algorithm = "DESede"; // 定义加密算法,可用
														// DES,DESede,Blowfish

	// keybyte为加密密钥，长度为24字节

	// src为被加密的数据缓冲区（源）

	public static byte[] encryptMode(byte[] keybyte, byte[] src) {

		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);// 在单一方面的加密或解密
		} catch (Exception e3) {
			e3.printStackTrace();
		}

		return null;

	}

	// keybyte为加密密钥，长度为24字节

	// src为加密后的缓冲区

	public static byte[] decryptMode(byte[] keybyte, byte[] src) {

		try {

			// 生成密钥

			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

			// 解密

			Cipher c1 = Cipher.getInstance(Algorithm);

			c1.init(Cipher.DECRYPT_MODE, deskey);

			return c1.doFinal(src);

		} catch (java.security.NoSuchAlgorithmException | javax.crypto.NoSuchPaddingException e1) {

			// TODO: handle exception

			return null;


		} catch (Exception e3) {
			return null;

		}

	}

	// 转换成十六进制字符串

	public static String byte2Hex(byte[] b) {

		String hs = "";

		String stmp = "";

		for (int n = 0; n < b.length; n++) {

			stmp = (Integer.toHexString(b[n] & 0XFF));

			if (stmp.length() == 1) {

				hs = hs + "0" + stmp;

			} else {

				hs = hs + stmp;

			}

			if (n < b.length - 1)
				hs = hs + ":";

		}

		return hs.toUpperCase();

	}
/**
 * 16进制字符串转byte
 * @param hex
 * @return
 */
	public static byte[] hexStringToBytes(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

	public static int hex2int(String s) {
		if (s.startsWith("0x")) {
			// Hex-Eingabe
			char c;
			int i, b;
			boolean error = false;
			int val = 0;
			for (i = s.length() - 1, b = 1; i > 1; i--, b *= 16) {
				c = s.charAt(i);
				if (c >= '0' && c <= '9') {
					val += ((int) (c - '0') * b);
				} else if (c >= 'a' && c <= 'f') {
					val += (((int) (c - 'a') + 10) * b);
				} else if (c >= 'A' && c <= 'F') {
					val += (((int) (c - 'A') + 10) * b);
				} else {
					return -1; // Fehler
				}
			}
			return val;
		} else {
			try {
				return Integer.parseInt(s);
			} catch (NumberFormatException ex) {
				return -1; // Fehler
			}
		}
	}

	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	public static int bytes2int(byte[] b) {
		// byte[] b=new byte[]{1,2,3,4};
		int mask = 0xff;
		int temp = 0;
		int res = 0;
		for (int i = 0; i < 4; i++) {
			res <<= 8;
			temp = b[i] & mask;
			res |= temp;
		}
		return res;
	}

	/**
	 * byte数组转16进制字符串
	 * @param bArray
	 * @return
	 */
	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}
}