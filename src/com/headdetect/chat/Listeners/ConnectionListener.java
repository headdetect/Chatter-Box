package com.headdetect.chat.Listeners;

import com.headdetect.chat.Networking.Client;

public interface ConnectionListener {
	
	void onDisconnect(Client client);
	void onJoin(Client client);

}


