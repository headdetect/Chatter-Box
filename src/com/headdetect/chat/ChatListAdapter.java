package com.headdetect.chat;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ChatListAdapter extends BaseAdapter {

	
	

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private LayoutInflater inflater;
	private Activity activity;
	private ArrayList<ChatItem> items;
	
	// ===========================================================
	// Constructors
	// ===========================================================

	
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
	
	@Override 
	public View getView(final int i, View covertView, final ViewGroup parent){
		if(covertView == null){
			covertView = this.inflater.inflate(R.layout.chat_item_ics, null);
		}
		
		ChatItem chat = items.get(i);
		
		TextView lblMessage = (TextView)covertView.findViewById(R.id.lblChatContents);
		TextView lblFrom = (TextView)covertView.findViewById(R.id.lblMessageFrom);
		TextView lblDate = (TextView)covertView.findViewById(R.id.lblDate);
		
		lblMessage.setText(chat.getMessage());
		lblFrom.setText(chat.getName());
		lblDate.setText(chat.getDate());
		
		return covertView;
	}


	@Override
	public int getCount() {
		return items.size();
	}


	@Override
	public ChatItem getItem(int position) {
		return items.get(position);
	}


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
	
	public void addItem(ChatItem item){
		items.add(item);
		this.activity.runOnUiThread(updateDataSetChanged);
	}
	
	public void removeItem(ChatItem item){
		items.remove(item);
		this.activity.runOnUiThread(updateDataSetChanged);
	}
	
	public void removeItem (int index){
		items.remove(index);
		this.activity.runOnUiThread(updateDataSetChanged);
	}
	
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


