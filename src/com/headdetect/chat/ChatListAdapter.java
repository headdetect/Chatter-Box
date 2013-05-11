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
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class ChatListAdapter.
 */
public class ChatListAdapter extends BaseAdapter {

	
	

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	/** The inflater. */
	private LayoutInflater inflater;
	
	/** The activity. */
	private Activity activity;
	
	/** The items. */
	private ArrayList<ChatItem> items;
	
	// ===========================================================
	// Constructors
	// ===========================================================

	
	/**
	 * Instantiates a new chat list adapter.
	 *
	 * @param activity the activity
	 * @param items the items
	 */
	public ChatListAdapter(Activity activity, ArrayList<ChatItem> items) {
		this.inflater = LayoutInflater.from(activity.getApplicationContext());
		this.activity = activity;
		this.items = items;
	}
	
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override 
	public View getView(final int i, View covertView, final ViewGroup parent){
		if(covertView == null){
			covertView = this.inflater.inflate(R.layout.chat_item_ics, null);
		}
		
		ChatItem chat = items.get(i);
		
		TextView lblMessage = (TextView)covertView.findViewById(R.id.lblChatContents);
		TextView lblFrom = (TextView)covertView.findViewById(R.id.lblMessageFrom);
		TextView lblDate = (TextView)covertView.findViewById(R.id.lblDate);
		
		lblMessage.setText(Html.fromHtml(chat.getMessage()));
		lblFrom.setText(chat.getName());
		lblDate.setText(chat.getDate());
		
		return covertView;
	}


	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return items.size();
	}


	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public ChatItem getItem(int position) {
		return items.get(position);
	}


	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) throws IndexOutOfBoundsException {
		if(position < getCount() && position >= 0 ){
            return position;
        }
		return 0;
	}
	

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Adds the item.
	 *
	 * @param item the item
	 */
	public void addItem(ChatItem item){
		items.add(item);
		this.activity.runOnUiThread(updateDataSetChanged);
	}
	
	/**
	 * Removes the item.
	 *
	 * @param item the item
	 */
	public void removeItem(ChatItem item){
		items.remove(item);
		this.activity.runOnUiThread(updateDataSetChanged);
	}
	
	/**
	 * Removes the item.
	 *
	 * @param index the index
	 */
	public void removeItem (int index){
		items.remove(index);
		this.activity.runOnUiThread(updateDataSetChanged);
	}
	
	/** The update data set changed. */
	private final Runnable updateDataSetChanged = new Runnable(){

		@Override
		public void run() {
			notifyDataSetChanged();
		}
		
	};

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}


