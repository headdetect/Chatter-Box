package com.headdetect.chat.Utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
	// ===========================================================
	// Constants
	// ===========================================================

	public static final String DATE_FORMAT_NOW_FULL = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_NOW_SHORT = "EEE d, HH:mm";

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

	public static String now() {
		return now(DATE_FORMAT_NOW_SHORT);
	}
	
	public static String now(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
