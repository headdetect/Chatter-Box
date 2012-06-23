/*

﻿ *    Copyright 2012 Brayden (headdetect) Lopez
 *    
 *    Dual-licensed under the Educational Community License, Version 2.0 and
 *	the GNU General Public License Version 3 (the "Licenses"); you may
 *	not use this file except in compliance with the Licenses. You may
 *	obtain a copy of the Licenses at
 *
 *		http://www.opensource.org/licenses/ecl2.php
 *		http://www.gnu.org/licenses/gpl-3.0.html
 *
 *		Unless required by applicable law or agreed to in writing
 *	software distributed under the Licenses are distributed on an "AS IS"
 *	BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 *	or implied. See the Licenses for the specific language governing
 *	permissions and limitations under the Licenses.
 * 
 */
package com.headdetect.chat.Utilities;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

// TODO: Auto-generated Javadoc
/**
 * The Class Crypto.
 */
public class Crypto {
	
	
	// ===========================================================
	// Constants
	// ===========================================================
	
	/** The Constant CIPHER_TYPE. */
	private static final String CIPHER_TYPE = "AES/CBC/PKCS5Padding";
	
	/** The Constant ENCRYPTION_TYPE. */
	private static final String ENCRYPTION_TYPE = "AES";

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	

	/**
	 * Decrypts the text.
	 *
	 * @param text the text
	 * @param key the key
	 * @return the string
	 * @throws Exception the exception
	 */
	public static String decrypt(String text, byte[] key) throws Exception {

		if(text == null || text.isEmpty())
			return "";
		
		Cipher cipher = Cipher.getInstance(CIPHER_TYPE);

		byte[] keyBytes = new byte[16];

		int len = key.length;

		if (len > keyBytes.length)
			len = keyBytes.length;

		System.arraycopy(key, 0, keyBytes, 0, len);

		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ENCRYPTION_TYPE);
		IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

		byte[] results = cipher.doFinal(Base64.decode(text, Base64.DEFAULT));
		
		return new String(results, "UTF-8");

	}

	/**
	 * Encrypt the text.
	 *
	 * @param text the text
	 * @param key the key
	 * @return the string
	 * @throws Exception the exception
	 */
	public static String encrypt(String text, byte[] key) throws Exception {
		
		
		Cipher cipher = Cipher.getInstance(CIPHER_TYPE);

		byte[] keyBytes = new byte[16];

		int len = key.length;
		if (len > keyBytes.length)
			len = keyBytes.length;

		System.arraycopy(key, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ENCRYPTION_TYPE);
		IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
		
		byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
		
		return Base64.encodeToString(results, Base64.DEFAULT);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	


}
