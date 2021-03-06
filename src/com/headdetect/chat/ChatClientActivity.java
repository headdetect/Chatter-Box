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
package com.headdetect.chat;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.headdetect.chat.Listeners.ChatListener;
import com.headdetect.chat.Listeners.ConnectionListener;
import com.headdetect.chat.Networking.Client;
import com.headdetect.chat.Networking.Server;
import com.headdetect.chat.Utilities.ServerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ChatClientActivity.
 */
public class ChatClientActivity extends Activity {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private ChatListAdapter chatAdapter;

	private Button btnSendMessage;
	
	private EditText txtMessage;
	
	private ListView lstMessages;
	
	private Dialog mDialog;

	private Client mClient;
	
	private Server mServer;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		showChatSelection();

		chatAdapter = new ChatListAdapter(this, new ArrayList<ChatItem>());

		lstMessages = (ListView) findViewById(R.id.lstChat);
		lstMessages.setAdapter(chatAdapter);

		txtMessage = (EditText) findViewById(R.id.txtSay);
		txtMessage.setOnEditorActionListener(txtMessageEditorActionListener);

		btnSendMessage = (Button) findViewById(R.id.btnSend);
		btnSendMessage.setOnClickListener(btnSendMessageClickListener);

		Client.setOnChatListener(chatListener);
		Client.setOnConnectionListener(connectionListener);

	}

	// ===========================================================
	// Methods
	// ===========================================================

	private void showChatSelection() {

		mDialog = new Dialog(this);
		mDialog.setContentView(R.layout.layout_chat_choose);
		mDialog.setTitle("Chat Room");
		mDialog.setCancelable(false);

		final EditText txtServer = (EditText) mDialog.findViewById(R.id.txtAddress);
		final TextView lblServer = (TextView) mDialog.findViewById(R.id.lblAddress);
		final RadioButton mHost = (RadioButton) mDialog.findViewById(R.id.rdioHost);
		final RadioButton mJoin = (RadioButton) mDialog.findViewById(R.id.rdioJoin);

		try {
			lblServer.setText(ServerUtils.getLocalIp(this));
		} catch (NullPointerException e) {
			mHost.setEnabled(false);
			mHost.setChecked(false);
			lblServer.setText("Wifi must be enabled to host");
			mJoin.setChecked(true);
			txtServer.setVisibility(View.VISIBLE);
		}

		((Button) mDialog.findViewById(R.id.btnChoose)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mDialog.findViewById(R.id.progLoading).setVisibility(View.VISIBLE);
				new SetupChat().execute(mHost.isChecked(), mJoin.isChecked() ? txtServer.getText().toString() : "");
			}
		});

		mHost.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					mJoin.setChecked(false);
					txtServer.setVisibility(View.INVISIBLE);
					lblServer.setVisibility(View.VISIBLE);
				}

			}
		});

		mJoin.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					mHost.setChecked(false);
					txtServer.setVisibility(View.VISIBLE);
					lblServer.setVisibility(View.INVISIBLE);
				}

			}
		});

		mDialog.show();

	}

	private final OnClickListener btnSendMessageClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			sendMessage();
		}
	};

	private final OnEditorActionListener txtMessageEditorActionListener = new OnEditorActionListener() {

		@Override
		public boolean onEditorAction(TextView v, int id, KeyEvent event) {
			if (id == EditorInfo.IME_ACTION_NEXT || id == EditorInfo.IME_ACTION_DONE)
				sendMessage();
			return true;
		}
	};

	/** The chat listener. */
	private final ChatListener chatListener = new ChatListener() {

		@Override
		public void onChat(String message) {
			chatAdapter.addItem(new ChatItem("<html>" + message + "</html>", "Friend"));
		}
	};

	/** The connection listener. */
	private final ConnectionListener connectionListener = new ConnectionListener() {

		@Override
		public void onDisconnect(Client client) {
			chatAdapter.addItem(new ChatItem(client.getName() + " left the chat room", ""));
		}

		@Override
		public void onJoin(Client client) {
			chatAdapter.addItem(new ChatItem(client.getName() + " joined the chat room", ""));
		}
	};

	/**
	 * Send the message.
	 */
	public void sendMessage() {

		String message = txtMessage.getText().toString();
		
		if(message == null || message.isEmpty())
			return;
		
		message = message.replace(">", "&gt;");
		message = message.replace("<", "&lt;");
		

		try {
			if (mServer != null) {
				mServer.sendMessage(message);
				chatAdapter.addItem(new ChatItem(message, "You"));
			} else if (mClient != null) {
				mClient.sendMessage(message);
				chatAdapter.addItem(new ChatItem(message, "You"));
			} else {
				return;
			}
		} catch (Exception e) {
			chatAdapter.addItem(new ChatItem(e.getMessage(), "<font color='red'>Error</font>"));
			return;
		}

		txtMessage.setText("");
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	/**
	 * The Class SetupChat.
	 */
	private class SetupChat extends AsyncTask<Object, Void, Boolean> {

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
		@Override
		protected Boolean doInBackground(Object... args) {

			try {

				if (!(Boolean)args[0]) {
					String address = args[1].toString();
					mClient = Client.connect(address);
					if (mClient == null)
						return true;
					new Thread(mClient).start();
				} else {
					mServer = new Server();
					new Thread(mServer).start();
				}

			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}
			return false;
		}

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(Boolean errors) {
			
			if (errors)
				Toast.makeText(getApplicationContext(), "Something went wrong while trying to set up chat\nDid you type the address right?", Toast.LENGTH_LONG).show();
			else
				mDialog.dismiss();

		}
	}

}