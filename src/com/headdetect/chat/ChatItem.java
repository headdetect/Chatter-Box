package com.headdetect.chat;

import com.headdetect.chat.Utilities.DateUtils;

public class ChatItem {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private String message, name, date;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ChatItem(String message, String name, String date){
		this.message = message;
		this.name = name;
		this.date = date;
	}
	
	public ChatItem(String message, String name){
		this(message, name, DateUtils.now(DateUtils.DATE_FORMAT_NOW_SHORT));
	}
	
	public ChatItem(String message){
		this(message, "Console");
	}
	
	public ChatItem(){
		this("", "");
	}
	
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}


