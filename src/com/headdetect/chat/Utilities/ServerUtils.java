package com.headdetect.chat.Utilities;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class ServerUtils {

	public static byte[] ipToBytes(int i) {
		return new byte[] { (byte) (i & 0xFF), (byte) ((i >> 8) & 0xFF), (byte) ((i >> 16) & 0xFF), (byte) ((i >> 24) & 0xFF) };
	}

	public static String ipToString(byte[] i) {
		String d = "";
		for (int j = 0; j < i.length; j++)
			d += (i[j] & 0xFF) + (j == i.length - 1 ? "" : ".");
		return d;
	}

	public static boolean isUsingWifi(Context c) {
		ConnectivityManager connManager = (ConnectivityManager) c.getSystemService(Service.CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		return mWifi.isConnected();
	}
	
	public static String getLocalIp(Context c){
		if(!isUsingWifi(c)){
			throw new NullPointerException("Wifi must be enabled");
		}
		
		WifiManager wifi = (WifiManager)c.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wInfo = wifi.getConnectionInfo();
		return ipToString(ipToBytes(wInfo.getIpAddress()));
	}

}
