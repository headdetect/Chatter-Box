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

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

// TODO: Auto-generated Javadoc
/**
 * The Class ServerUtils.
 */
public class ServerUtils {

	/**
	 * Ip to bytes.
	 *
	 * @param i the i
	 * @return the byte[]
	 */
	public static byte[] ipToBytes(int i) {
		return new byte[] { (byte) (i & 0xFF), (byte) ((i >> 8) & 0xFF), (byte) ((i >> 16) & 0xFF), (byte) ((i >> 24) & 0xFF) };
	}

	/**
	 * Ip to string.
	 *
	 * @param i the i
	 * @return the string
	 */
	public static String ipToString(byte[] i) {
		String d = "";
		for (int j = 0; j < i.length; j++)
			d += (i[j] & 0xFF) + (j == i.length - 1 ? "" : ".");
		return d;
	}

	/**
	 * Checks if is using wifi.
	 *
	 * @param c the c
	 * @return true, if is using wifi
	 */
	public static boolean isUsingWifi(Context c) {
		ConnectivityManager connManager = (ConnectivityManager) c.getSystemService(Service.CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		return mWifi.isConnected();
	}
	
	/**
	 * Gets the local ip.
	 *
	 * @param c the c
	 * @return the local ip
	 */
	public static String getLocalIp(Context c){
		if(!isUsingWifi(c)){
			throw new NullPointerException("Wifi must be enabled");
		}
		
		WifiManager wifi = (WifiManager)c.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wInfo = wifi.getConnectionInfo();
		return ipToString(ipToBytes(wInfo.getIpAddress()));
	}

}
