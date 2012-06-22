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

package com.headdetect.chat.Networking;

import static com.headdetect.chat.Utilities.Constants.PORT;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.headdetect.chat.Listeners.ChatListener;
import com.headdetect.chat.Listeners.ConnectionListener;
import com.headdetect.chat.Utilities.Crypto;

public class Client implements Runnable {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private BufferedReader reader;
	private DataOutputStream writer;
	private boolean disconnecting;
	private byte[] cryptoKey;
	private String name;

	private static ChatListener chatListener;
	private static ConnectionListener connectionListener;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Client(Socket s) throws IOException {
		
		cryptoKey = new byte[16];

		try {
			reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
			writer = new DataOutputStream(s.getOutputStream());
		} catch (IOException e) {
			disconnect();
			return;
		}

	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void setOnChatListener(ChatListener listener) {
		chatListener = listener;
	}

	public static void setOnConnectionListener(ConnectionListener listener) {
		connectionListener = listener;
	}
	
	public BufferedReader getReader() {
		return reader;
	}
	
	public byte[] getKey(){
		return cryptoKey;
	}
	public void setKey(byte[] key) {
		cryptoKey = key;
	}


	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void run() {
		if (connectionListener != null) {
			connectionListener.onJoin(this);
		}
		
		try {

			while (!disconnecting) {

				String read = null;
				if ((read = reader.readLine()) != null) {
					if (chatListener != null) {
						chatListener.onChat(Crypto.decrypt(read, cryptoKey));
					}
				}
				else{
					return;
				}
				Thread.sleep(5);
			}

		} catch (IOException e) {
			disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			disconnect();
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public static Client connect(String address) throws IOException {
		InetAddress localAddress = InetAddress.getByName(address);
		InetSocketAddress localSocketAddress = new InetSocketAddress(localAddress, PORT);
		
		Socket socket = new Socket();
		socket.connect(localSocketAddress, 5000);
		Client client = new Client(socket);
		socket.getInputStream().read(client.cryptoKey, 0, 16);
		
		System.out.println("Client -> " + new String(client.cryptoKey));
		
		return client;
	}

	public void sendMessage(String message) throws IOException, Exception {
		writer.writeUTF(Crypto.encrypt(message, cryptoKey));
	}

	public void disconnect() {

		if (connectionListener != null) {
			connectionListener.onDisconnect(this);
		}

		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		reader = null;
		writer = null;

	}


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
