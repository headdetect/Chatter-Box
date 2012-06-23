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

import java.text.SimpleDateFormat;
import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * The Class DateUtils.
 */
public class DateUtils {
	// ===========================================================
	// Constants
	// ===========================================================

	/** The Constant DATE_FORMAT_NOW_FULL. */
	public static final String DATE_FORMAT_NOW_FULL = "yyyy-MM-dd HH:mm:ss";
	
	/** The Constant DATE_FORMAT_NOW_SHORT. */
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

	/**
	 * The the current date and time.
	 *
	 * @return The the current date and time.
	 */
	public static String now() {
		return now(DATE_FORMAT_NOW_SHORT);
	}
	
	/**
	 * The the current date and time.
	 *
	 * @param format the format
	 * @return The the current date and time.
	 */
	public static String now(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
