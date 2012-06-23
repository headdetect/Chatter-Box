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

import com.headdetect.chat.Utilities.DateUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ChatItem.
 */
public class ChatItem {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	/** The date. */
	private String message, name, date;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	/**
	 * Instantiates a new chat item.
	 *
	 * @param message the message
	 * @param name the name
	 * @param date the date
	 */
	public ChatItem(String message, String name, String date){
		this.message = message;
		this.name = name;
		this.date = date;
	}
	
	/**
	 * Instantiates a new chat item.
	 *
	 * @param message the message
	 * @param name the name
	 */
	public ChatItem(String message, String name){
		this(message, name, DateUtils.now(DateUtils.DATE_FORMAT_NOW_SHORT));
	}
	
	/**
	 * Instantiates a new chat item.
	 *
	 * @param message the message
	 */
	public ChatItem(String message){
		this(message, "Console");
	}
	
	/**
	 * Instantiates a new chat item.
	 */
	public ChatItem(){
		this("", "");
	}
	
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
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


