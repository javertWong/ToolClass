package com.javert.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.time.DateFormatUtils;

public class DateTime {
	public static long DateInMillis = 24 * 60 * 60 * 1000L;
	public static long LeapYearInMillis = 366 * 24 * 60 * 60 * 1000L;
	public static long YearInMillis = 365 * 24 * 60 * 60 * 1000L;
	public static long MonthInMillins31 = 31L * 24L * 60L * 60L * 1000L;
	public static long MonthInMillins30 = 30L * 24L * 60L * 60L * 1000L;
	public static long MonthInMillins29 = 29L * 24L * 60L * 60L * 1000L;
	public static long MonthInMillins28 = 28L * 24L * 60L * 60L * 1000L;
	public static long WeekInMillins = 7L * 24L * 60L * 60L * 1000L;

	private int iYear;
	private int iMonth;
	private int iDay;
	public String sValue;

	public DateTime() {
		Calendar cal = Calendar.getInstance();
		this.iYear = cal.get(Calendar.YEAR);
		this.iMonth = cal.get(Calendar.YEAR) + 1;
		this.iDay = cal.get(Calendar.YEAR);
		this.sValue = CalendartoString(cal);
	}

	public DateTime(int iYear, int iMonth, int iDate) {
		this.iYear = iYear;
		this.iMonth = iMonth;
		this.iDay = iDate;
		this.sValue = getStringDate(this.iYear, this.iMonth, this.iDay);
	}

	public DateTime(String sDateInput) {
		this.iYear = Integer.parseInt(sDateInput.substring(0, 4));
		this.iMonth = Integer.parseInt(sDateInput.substring(5, 7));
		;
		this.iDay = Integer.parseInt(sDateInput.substring(8));
		this.sValue = getStringDate(this.iYear, this.iMonth, this.iDay);
	}

	public String toString() {
		return sValue;
	}

	public int getDay() {
		return iDay;
	}

	public int getMonth() {
		return iMonth;
	}

	public int getYear() {
		return iYear;
	}

	/**
	 * 根据 text 的格式 来格式化 date 的字符串格式
	 * 
	 * @param date
	 * @param text
	 * @return
	 */
	public static String format(Date date, String text) {
		SimpleDateFormat format = new SimpleDateFormat(text);
		return format.format(date);
	}

	public static Date parse(String date, String text) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(text);
		return format.parse(date);
	}

	/**
	 * 得到当前日期的字符串"XXXX-XX-XX"
	 * 
	 * @return
	 */
	public static String getStringDate(int iYear, int iMonth, int iDate) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, iYear);
		cal.set(Calendar.MONTH, iMonth - 1);
		cal.set(Calendar.DATE, iDate);
		return CalendartoString(cal);
	}

	/**
	 * 得到当前日期的字符串"XXXX-XX-XX"
	 * 
	 * @return
	 */
	public static String getStringDate() {
		Calendar cal = Calendar.getInstance();
		return CalendartoString(cal);
	}

	/**
	 * 根据传入的Calendar实例返回一个日期字符串"XXXX-XX-XX"
	 * 
	 * @param c
	 * @return
	 */
	public static String CalendartoString(Calendar c) {
		SimpleDateFormat dFormatter = new SimpleDateFormat("yyyy-MM-dd");
		return dFormatter.format(c.getTime());
	}

	/**
	 * 返回当前时间的字符串"XXXX-XX-XX hh:mm:ss"
	 * 
	 * @return
	 */
	public static String getStringDateTime() {
		Calendar cal = Calendar.getInstance();
		return CalendartoStringTime(cal);
	}

	/**
	 * 根据传入的Calendar实例返回一个日期字符串"XXXX-XX-XX hh:mm:ss"
	 * 
	 * @param c
	 * @return
	 */
	public static String CalendartoStringTime(Calendar c) {
		SimpleDateFormat dFormatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return dFormatter.format(c.getTime());
	}

	/**
	 * 根据传入的整数例返回一个日期字符串"XXXX-XX-XX"
	 * 
	 * @param lTime
	 * @return
	 */
	public static String CalendartoString(long lTime) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(lTime);
		return CalendartoString(c);
	}

	/**
	 * 根据传入的符串日期返回该日期00::00::00.0的毫秒数
	 */
	public static long getTimeInMillions(String sDate) {
		if (sDate == null) {
			sDate = getStringDate();
		}
		return StringtoCalendar(sDate).getTimeInMillis();
	}

	/**
	 * 根据传入的日历日期返回该日期00::00::00.0的毫秒数
	 */
	public static long getTimeInMillions(Calendar cal) {
		return getTimeInMillions(CalendartoString(cal));
	}

	/**
	 * 根据传入的日期字符串"XXXX-XX-XX",返回该季度第一天
	 * 
	 * @param sDate
	 * @return
	 */
	public static String getFirstDateOfQuarter(String sDate) {
		if (sDate == null) {
			sDate = getStringDate();
		}
		String sYear = sDate.substring(0, 4);
		String sMonth = null;
		long nMonth = Long.parseLong(sDate.substring(5, 7));
		if (nMonth >= 1 && nMonth <= 3) {
			sMonth = "01";
		} else if (nMonth <= 6) {
			sMonth = "04";
		} else if (nMonth <= 9) {
			sMonth = "07";
		} else if (nMonth <= 12) {
			sMonth = "10";
		}
		return sYear + "-" + sMonth + "-01";
	}

	/**
	 * 得到字符串日期的某一个值
	 */
	public static int get(String sDateInput, int iType) {
		Calendar cal = StringtoCalendar(sDateInput);
		return cal.get(iType);
	}

	/**
	 * 根据传入的日期字符串"XXXX-XX-XX",返回该季度最后一天
	 * 
	 * @param sDate
	 * @return
	 */
	public static String getLastDateOfQuarter(String sDate) {
		if (sDate == null) {
			sDate = getStringDate();
		}
		String sYear = sDate.substring(0, 4);
		String sMonth = null;
		String sDay = null;
		long nMonth = Long.parseLong(sDate.substring(5, 7));
		if (nMonth >= 1 && nMonth <= 3) {
			sMonth = "03";
			sDay = "31";
		} else if (nMonth <= 6) {
			sMonth = "06";
			sDay = "30";
		} else if (nMonth <= 9) {
			sMonth = "09";
			sDay = "30";
		} else if (nMonth <= 12) {
			sMonth = "12";
			sDay = "31";
		}
		return sYear + "-" + sMonth + "-" + sDay;
	}

	/**
	 * 根据传入的日期字符串"XXXX-XX-XX",返回该季度第一天
	 * 
	 * @param sDate
	 * @return
	 */
	public static String getFirstDateOfQuarterByCal(Calendar cal) {
		if (cal == null) {
			cal = Calendar.getInstance();
		}
		String sDate = CalendartoString(cal);
		String sYear = sDate.substring(0, 4);
		String sMonth = null;
		long nMonth = Long.parseLong(sDate.substring(5, 7));
		if (nMonth >= 1 && nMonth <= 3) {
			sMonth = "01";
		} else if (nMonth <= 6) {
			sMonth = "04";
		} else if (nMonth <= 9) {
			sMonth = "07";
		} else if (nMonth <= 12) {
			sMonth = "10";
		}
		return sYear + "-" + sMonth + "-01";
	}

	/**
	 * 根据传入的日期字符串"XXXX-XX-XX",返回该季度最后一天
	 * 
	 * @param sDate
	 * @return
	 */
	public static String getLastDateOfQuarterByCal(Calendar cal) {
		if (cal == null) {
			cal = Calendar.getInstance();
		}
		String sDate = CalendartoString(cal);
		String sYear = sDate.substring(0, 4);
		String sMonth = null;
		String sDay = null;
		long nMonth = Long.parseLong(sDate.substring(5, 7));
		if (nMonth >= 1 && nMonth <= 3) {
			sMonth = "03";
			sDay = "31";
		} else if (nMonth <= 6) {
			sMonth = "06";
			sDay = "30";
		} else if (nMonth <= 9) {
			sMonth = "09";
			sDay = "30";
		} else if (nMonth <= 12) {
			sMonth = "12";
			sDay = "31";
		}
		return sYear + "-" + sMonth + "-" + sDay;
	}

	/**
	 * 根据传入的日期字符串"XXXX-XX-XX",返回该月第一天
	 * 
	 * @param sDate
	 * @return
	 */
	public static String getFirstDateOfMonth(String sDate) {
		Calendar cal = null;
		if (sDate == null) {
			cal = Calendar.getInstance();
		} else {
			cal = StringtoCalendar(sDate);
		}
		cal.set(Calendar.DATE, 1);
		return CalendartoString(cal);
	}

	/**
	 * 根据传入的日期字符串"XXXX-XX-XX",返回该月最后一天
	 * 
	 * @param sDate
	 * @return
	 */
	public static String getLastDateOfMonth(String sDate) {
		Calendar cal = null;
		if (sDate == null) {
			cal = Calendar.getInstance();
		} else {
			cal = StringtoCalendar(sDate);
		}
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		return CalendartoString(cal);
	}

	/**
	 * 根据传入的日期字符串"XXXX-XX-XX",返回该月第一天
	 * 
	 * @param sDate
	 * @return
	 */
	public static String getFirstDateOfMonthByCal(Calendar cal) {
		if (cal == null) {
			cal = Calendar.getInstance();
		}
		cal.set(Calendar.DATE, 1);
		return CalendartoString(cal);
	}

	/**
	 * 根据传入的日期字符串"XXXX-XX-XX",返回该月最后一天
	 * 
	 * @param sDate
	 * @return
	 */
	public static String getLastDateOfMonthByCal(Calendar cal) {
		if (cal == null) {
			cal = Calendar.getInstance();
		}
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		return CalendartoString(cal);
	}

	/**
	 * 根据前台的日期字符串"XXXX-XX-XX"生成一个Calendar类实例
	 * 
	 * @param sDateInput
	 * @return
	 */
	public static Calendar StringtoCalendar(String sDateInput) {
		if (sDateInput == null) {
			sDateInput = getStringDate();
		}
		;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.parseInt(sDateInput.substring(0, 4)));
		c.set(Calendar.MONTH, Integer.parseInt(sDateInput.substring(5, 7)) - 1);
		c.set(Calendar.DAY_OF_MONTH,
				Integer.parseInt(sDateInput.substring(8, 10)));
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c;
	}

	/**
	 * 根据前台的日期字符串"XXXX-XX-XX 11:00:22"生成一个Calendar类实例
	 * 
	 * @param sDateInput
	 * @return
	 */
	public static Calendar StringtoCalendar1(String sDateInput) {
		if ("".equals(sDateInput) || sDateInput == null) {
			sDateInput = getStringDateTime();
		}
		;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.parseInt(sDateInput.substring(0, 4)));
		c.set(Calendar.MONTH, Integer.parseInt(sDateInput.substring(5, 7)) - 1);
		c.set(Calendar.DAY_OF_MONTH,
				Integer.parseInt(sDateInput.substring(8, 10)));
		c.set(Calendar.HOUR_OF_DAY,
				Integer.parseInt(sDateInput.substring(11, 13)));
		c.set(Calendar.MINUTE, Integer.parseInt(sDateInput.substring(14, 16)));
		c.set(Calendar.SECOND, Integer.parseInt(sDateInput.substring(17, 19)));
		c.set(Calendar.MILLISECOND, 0);
		return c;
	}

	/**
	 * 根据前台的日期字符串"XXXX-XX-XX"返回当前的星期数
	 * 
	 * @param sDateInput
	 * @return
	 */
	public static int getDayOfWeek(String sDateInput) {
		if (sDateInput == null) {
			sDateInput = getStringDate();
		}
		return StringtoCalendar(sDateInput).get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 根据前台的日期字符串"XXXX-XX-XX"得到传入日期所在周的第一天，返回"XXXX-XX-XX"
	 * 
	 * @param sDateInput
	 * @return
	 */
	public static String getFirstDayOfWeek(String sDateInput) {
		if (sDateInput == null) {
			sDateInput = getStringDate();
		}
		int n = getDayOfWeek(sDateInput);
		n = n * (-1);
		Calendar c = StringtoCalendar(sDateInput);
		c.add(Calendar.DAY_OF_MONTH, n + 1);
		return CalendartoString(c);
	}

	/**
	 * 根据前台的日期字符串"XXXX-XX-XX"得到传入日期所在周的最后一天，返回"XXXX-XX-XX"
	 * 
	 * @param sDateInput
	 * @return
	 */
	public static String getLastDayOfWeek(String sDateInput) {
		if (sDateInput == null) {
			sDateInput = getStringDate();
		}
		int n = getDayOfWeek(sDateInput);
		n = 6 - n;
		Calendar c = StringtoCalendar(sDateInput);
		c.add(Calendar.DAY_OF_MONTH, n + 1);
		return CalendartoString(c);
	}

	/**
	 * 根据前台的日期字符串"XXXX-XX-XX"得到传入日期所在月所在周的第一天，返回"XXXX-XX-XX"
	 * 
	 * @param sDateInput
	 * @return
	 */
	public static String getFirstDayOfWeekByMonth(String sDateInput) {
		if (sDateInput == null) {
			sDateInput = getStringDate();
		}
		int sInputMonth = Integer.parseInt(sDateInput.substring(5, 7));
		int n = getDayOfWeek(sDateInput);
		n = n * (-1);
		Calendar c = StringtoCalendar(sDateInput);
		c.add(Calendar.DAY_OF_MONTH, n + 1);
		if (sInputMonth != c.get(Calendar.MONTH) + 1) {
			return sDateInput.substring(0, 8) + "01";
		} else {
			return CalendartoString(c);
		}
	}

	/**
	 * 根据前台的日期字符串"XXXX-XX-XX"得到传入日期所在月所在周的最后一天，返回"XXXX-XX-XX"
	 * 
	 * @param sDateInput
	 * @return
	 */
	public static String getLastDayOfWeekByMonth(String sDateInput) {
		if (sDateInput == null) {
			sDateInput = getStringDate();
		}
		int sInputMonth = Integer.parseInt(sDateInput.substring(5, 7));
		int n = getDayOfWeek(sDateInput);
		n = 6 - n;
		Calendar c = StringtoCalendar(sDateInput);
		c.add(Calendar.DAY_OF_MONTH, n + 1);
		if (sInputMonth != c.get(Calendar.MONTH) + 1) {
			return getLastDateOfMonth(sDateInput);
		} else {
			return CalendartoString(c);
		}
	}

	/**
	 * 根据前台的日期字符串"XXXX-XX-XX"得到传入日期所在月所在旬的第一天，返回"XXXX-XX-XX"
	 * 
	 * @param sDateInput
	 * @return
	 */
	public static String getFirstDayOfXunByMonth(String sDateInput) {
		if (sDateInput == null) {
			sDateInput = getStringDate();
		}
		int sInputMonth = Integer.parseInt(sDateInput.substring(5, 7));
		int n = Integer.parseInt(sDateInput.substring(8, 10));
		if (n == 31) {
			n = -10;
		} else {
			n = ((n - 1) % 10) * (-1);
		}
		Calendar c = StringtoCalendar(sDateInput);
		c.add(Calendar.DAY_OF_MONTH, n);
		if (sInputMonth != c.get(Calendar.MONTH) + 1) {
			return sDateInput.substring(0, 8) + "01";
		} else {
			return CalendartoString(c);
		}
	}

	/**
	 * 根据前台的日期字符串"XXXX-XX-XX"得到传入日期所在月所在旬的最后一天，返回"XXXX-XX-XX"
	 * 
	 * @param sDateInput
	 * @return
	 */
	public static String getLastDayOfXunByMonth(String sDateInput) {
		if (sDateInput == null) {
			sDateInput = getStringDate();
		}
		int sInputMonth = Integer.parseInt(sDateInput.substring(5, 7));
		int n = Integer.parseInt(sDateInput.substring(8, 10));
		if ((n - 0) < 21) {
			n = 9 - ((n - 1) % 10);
			Calendar c = StringtoCalendar(sDateInput);
			c.add(Calendar.DAY_OF_MONTH, n);
			if (sInputMonth != c.get(Calendar.MONTH) + 1) {
				return getLastDateOfMonth(sDateInput);
			} else {
				return CalendartoString(c);
			}
		} else {
			return getLastDateOfMonth(sDateInput);
		}

	}

	/**
	 * 根据日期字符串"XXXX-XX-XX"，加天数，返回加后的日期字符串"XXXX-XX-XX"
	 * 
	 * @param sDateInput
	 * @return
	 */
	public static String getAddDay(String sDateInput, int iAdd) {
		if (sDateInput == null) {
			sDateInput = getStringDate();
		}
		Calendar c = StringtoCalendar(sDateInput);
		c.add(Calendar.DAY_OF_MONTH, iAdd);
		return CalendartoString(c);
	}

	public static Date getAddDay(Date date, int iAdd) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, iAdd);
		return c.getTime();
	}

	public static Date getAddMinute(Date date, int iAdd) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, iAdd);
		return c.getTime();
	}

	/**
	 * 根据日期字符串"XXXX-XX-XX"，加月数，返回加后的日期字符串"XXXX-XX-XX"
	 * 
	 * @param sDateInput
	 * @param iAdd
	 * @return
	 */
	public static String getAddMonth(String sDateInput, int iAdd) {
		if (sDateInput == null) {
			sDateInput = getStringDate();
		}
		Calendar c = StringtoCalendar(sDateInput);
		c.add(Calendar.MONTH, iAdd);
		return CalendartoString(c);
	}

	/**
	 * 根据日期字符串"XXXX-XX-XX"，加年数，返回加后的日期字符串"XXXX-XX-XX"
	 * 
	 * @param sDateInput
	 * @param iAdd
	 * @return
	 */
	public static String getAddYear(String sDateInput, int iAdd) {
		if (sDateInput == null) {
			sDateInput = getStringDate();
		}
		Calendar c = StringtoCalendar(sDateInput);
		c.add(Calendar.YEAR, iAdd);
		return CalendartoString(c);
	}

	/**
	 * 得到某个日期字符串"XXXX-XX-XX"的完整格式"XXXX年XX月XX日"
	 * 
	 * @param sDateInput
	 * @return
	 */
	public static String getCNString(String sDateInput) {
		if (sDateInput == null) {
			sDateInput = getStringDate();
		}
		return sDateInput.substring(0, 4) + "年" + sDateInput.substring(5, 7)
				+ "月" + sDateInput.substring(8, 10) + "日";
	}

	/**
	 * 得到某个日期字符串"XXXX-XX-XX"的完整格式"XXXX年XX月XX日"
	 * 
	 * @param sDateInput
	 * @return
	 */
	public static String getCNString(long lMillions) {
		String sDate = CalendartoString(lMillions);
		return getCNString(sDate);
	}

	/**
	 * 得到某个Calendar的完整日期格式"XXXX年XX月XX日"
	 * 
	 * @param c
	 * @return
	 */
	public static String getCNString(Calendar c) {
		return getCNString(CalendartoString(c));
	}

	/**
	 * 根据某日期字符串得到某年某月在系统中的计分考核起日
	 */
	public static String getMonthStartDay(int iYear, int iMonth) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, iYear);
		c.set(Calendar.MONTH, iMonth - 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return CalendartoString(c);

	}

	/**
	 * 根据某日期字符串得到某年某月在系统中的计分考核止日
	 */
	public static String getMonthEndDay(int iYear, int iMonth) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, iYear);
		c.set(Calendar.MONTH, iMonth);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return CalendartoString(c);
	}

	/**
	 * 根据某日期字符串得到该月在系统中的计分考核哪一个月
	 */
	public static String getMonthOfPointCheck(String sDateInput) {
		if (sDateInput == null) {
			sDateInput = getStringDate();
		}
		String s = sDateInput.substring(0, 4) + sDateInput.substring(5, 7);
		return s;
	}

	/**
	 * 以刑期的格式返回两个日期相差多少年多少月多少天
	 * 
	 * @param lStart
	 * @param lEnd
	 * @return
	 */
	public static long dateDiff(Calendar cStart, Calendar cEnd) {
		return dateDiff(cStart.getTimeInMillis(), cEnd.getTimeInMillis());
	}

	/**
	 * 以刑期的格式返回两个日期相差多少年多少月多少天
	 * 
	 * @param lStart
	 * @param lEnd
	 * @return
	 */
	public static long dateDiff(String sStart, String sEnd) {
		long lStart = getTimeInMillions(sStart);
		long lEnd = getTimeInMillions(sEnd);

		return dateDiff(lStart, lEnd);
	}
	
	/**
     * 获取两个日期相差几个月
     * @param start
     * @param end
     * @return
     */
    public static int getMonthInterval(Date start, Date end) {
		if (start.after(end)) {
			Date t = start;
			start = end;
			end = t;
		}
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(start);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		Calendar temp = Calendar.getInstance();
		temp.setTime(end);
		temp.add(Calendar.DATE, 1);
 
		int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
 
		if ((startCalendar.get(Calendar.DATE) == 1)&& (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month + 1;
		} else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month;
		} else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {
			return year * 12 + month;
		} else {
			return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
		}
	}

	/**
	 * 以刑期的格式返回两个日期相差多少年多少月多少天
	 * 
	 * @param lStart
	 * @param lEnd
	 * @return
	 */
	public static long dateDiff(long lStart, long lEnd) {
		long lDiff = lEnd - lStart;
		Calendar cal = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
		cal.setTimeInMillis(lStart);
		cal1.setTimeInMillis(lEnd);

		int iStartYear = cal.get(Calendar.YEAR);
		int iEndYear = cal1.get(Calendar.YEAR);
		int iDiffYear = 0;
		int i = iStartYear;
		long lYearInMilis = isLeapYear(i + 1) ? LeapYearInMillis : YearInMillis;
		for (; i <= iEndYear && lDiff >= lYearInMilis;) {
			lDiff -= lYearInMilis;
			iDiffYear++;
			i++;
			lYearInMilis = isLeapYear(i + 1) ? LeapYearInMillis : YearInMillis;
		}

		iStartYear = i;
		cal.add(Calendar.YEAR, iDiffYear);

		int iDiffMonth = 0;
		while (cal.getTimeInMillis() <= lEnd) {
			iDiffMonth++;
			cal.add(Calendar.MONTH, 1);
		}
		long iDiffDay = 0;
		cal.add(Calendar.MONTH, -1);
		iDiffMonth--;
		lDiff = lEnd - cal.getTimeInMillis();
		iDiffDay = lDiff / DateInMillis;

		lDiff = iDiffYear * 10000L + iDiffMonth * 100 + iDiffDay;
		return lDiff;
	}

	/**
	 * 判断输入是否为闰年
	 * 
	 * @param iyear
	 * @return
	 */
	public static boolean isLeapYear(int iyear) {
		if ((iyear & 3) != 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 计算两个日期相差的年数或月数或天数(精确到天)
	 * 
	 * @param cStart
	 * @param cEnd
	 * @param iType
	 * @return
	 */
	public static int dateDiffByDay(Calendar cStart, Calendar cEnd, int iType) {
		return dateDiffByDay(cStart.getTimeInMillis(), cEnd.getTimeInMillis(),
				iType);
	}

	/**
	 * 计算两个日期相差的年数或月数或数(精确到天)
	 * 
	 * @param cStart
	 * @param cEnd
	 * @param iType
	 * @return
	 */
	public static int dateDiffByDay(long lStart, long lEnd, int iType) {
		long lDiff = lEnd - lStart;
		if (iType == Calendar.DATE) {
			return (int) (lDiff / DateInMillis);
		}

		Calendar cal = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
		cal.setTimeInMillis(lStart);
		cal1.setTimeInMillis(lEnd);

		int iStartYear = cal.get(Calendar.YEAR);
		int iEndYear = cal1.get(Calendar.YEAR);
		int iDiffYear = 0;
		int i = iStartYear;
		long lYearInMilis = isLeapYear(i + 1) ? LeapYearInMillis : YearInMillis;
		for (; i <= iEndYear && lDiff >= lYearInMilis;) {
			lDiff -= lYearInMilis;
			iDiffYear++;
			i++;
			lYearInMilis = isLeapYear(i + 1) ? LeapYearInMillis : YearInMillis;
		}
		iStartYear = i;
		cal.add(Calendar.YEAR, iDiffYear);
		int iDiffMonth = 0;
		while (cal.getTimeInMillis() <= lEnd) {
			iDiffMonth++;
			cal.add(Calendar.MONTH, 1);
		}
		if (iType == Calendar.YEAR) {
			return iDiffYear;
		} else if (iType == Calendar.MONTH) {
			return iDiffYear * 12 + iDiffMonth;
		} else {
			return -10000;
		}
	}

	public static int dateDiff(Date start, Date end, int iType) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		long syear = calendar.get(Calendar.YEAR);
		calendar.setTime(end);
		long eyear = calendar.get(Calendar.YEAR);
		return (int) (eyear - syear);
	}

	/**
	 * 计算两个日期相差的年数或月数或天数(不精确到天)
	 * 
	 * @param cStart
	 * @param cEnd
	 * @param iType
	 * @return
	 */
	public static int dateDiff(Calendar cStart, Calendar cEnd, int iType) {
		if (iType == Calendar.DATE) {
			return dateDiff(cStart.getTimeInMillis(), cEnd.getTimeInMillis(),
					iType);
		}
		int iStartYear = cStart.get(Calendar.YEAR);
		int iEndYear = cEnd.get(Calendar.YEAR);
		int iYear = iEndYear - iStartYear;
		if (iType == Calendar.YEAR) {
			return iYear;
		}
		int iStartMonth = 0;
		int iEndMonth = 0;
		int iFlag = 1;
		if (cStart.getTimeInMillis() > cEnd.getTimeInMillis()) {
			iStartMonth = cEnd.get(Calendar.MONTH);
			iEndMonth = cStart.get(Calendar.MONTH);
			iFlag = -1;
			iYear = iYear * (-1);
		} else {
			iStartMonth = cStart.get(Calendar.MONTH);
			iEndMonth = cEnd.get(Calendar.MONTH);
		}
		int iMonth = iEndMonth - iStartMonth;
		if (iType == Calendar.MONTH) {
			return iFlag * (iYear * 12 + iMonth);
		}
		return 0;
	}

	/**
	 * 计算两个日期相差的年数或月数或数(不精确到天)
	 * 
	 * @param cStart
	 * @param cEnd
	 * @param iType
	 * @return
	 */
	public static int dateDiff(long lStart, long lEnd, int iType) {
		Calendar calStart = Calendar.getInstance();
		Calendar calEnd = Calendar.getInstance();
		calStart.setTimeInMillis(lStart);
		calEnd.setTimeInMillis(lEnd);
		return dateDiff(calStart, calEnd, iType);
	}

	/**
	 * 获得输入参数相应日期的月份最后一天
	 */
	public static Calendar getLastDay(Calendar cal) {
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1 * cal.get(Calendar.DATE));
		return cal;
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String ss = "10:45";
		Date d = DateTime.StringToDate(ss);
		int i = 1;
		while (i < 13) {
			String s = Integer.toString(i).trim();
			if (i < 10) {
				s = "0" + s;
			}
			// System.out.println("输入月份：" + i);
			// System.out.println("起日" + getMonthStartDay(2006,i));
			// System.out.println("止日" + getMonthEndDay(2006,i));

			// System.out.println("2006" + "年" + i + "月" + 18 + "号属于" +
			// getMonthOfPointCheck("2006" + "-" + s + "-" + 18 ));
			System.out.println("2006" + "年" + i + "月" + 28 + "号属于 周"
					+ getFirstDayOfWeekByMonth("2006" + "-" + s + "-" + 28)
					+ " 到 "
					+ getLastDayOfWeekByMonth("2006" + "-" + s + "-" + 28));
			i++;
		}

		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 2006);
		c.set(Calendar.MONTH, 2);
		c.set(Calendar.DAY_OF_MONTH, 30);
		System.out.println(CalendartoString(c));
		c.add(Calendar.MONTH, -1);
		System.out.println(CalendartoString(c));
		System.out.println(getStringDate(2007, 9, 9));

	}

	public static String GetNextDay(String sDate) {

		if (!DateTime.DayExist(sDate)) {
			return sDate;
		}

		Date d = DateTime.StringToDate(sDate);
		d.setTime(d.getTime() + 24 * 3600 * 1000);

		return DateTime.GetDateString(d);
	}

	public static boolean DayExist(String TestDate) {

		Date t_Date = DateTime.StringToDate(TestDate);

		if (DateTime.GetDateString(t_Date) == TestDate) {
			return true;
		} else {
			return false;
		}
	} /* DayExists */

	@SuppressWarnings("deprecation")
	public static String GetDateString(Date dateval) {
		String sMonth;
		String sDay;
		int sTempM = dateval.getMonth() + 1;
		if (sTempM < 10) {
			sMonth = "0" + Integer.toString(sTempM);
		} else {
			sMonth = Integer.toString(sTempM);
		}
		int sTempD = dateval.getDate();
		if (sTempD < 10) {
			sDay = "0" + Integer.toString(sTempD);
		} else {
			sDay = Integer.toString(sTempD);
		}
		return Integer.toString(dateval.getYear()) + "-" + sMonth + "-" + sDay;

	}

	@SuppressWarnings("deprecation")
	public static String getDateString(Date dateval) {
		String sMonth;
		String sDay;
		int sTempM = dateval.getMonth() + 1;
		if (sTempM < 10) {
			sMonth = "0" + Integer.toString(sTempM);
		} else {
			sMonth = Integer.toString(sTempM);
		}
		int sTempD = dateval.getDate();
		if (sTempD < 10) {
			sDay = "0" + Integer.toString(sTempD);
		} else {
			sDay = Integer.toString(sTempD);
		}
		return Integer.toString(dateval.getYear()) + "-" + sMonth + "-" + sDay;

	}

	@SuppressWarnings("deprecation")
	public static Date StringToDate(String sDate) {
		Date result = new Date(Integer.parseInt(sDate.substring(0, 4)),
				Integer.parseInt(sDate.substring(5, 7)) - 1,
				Integer.parseInt(sDate.substring(8, 10)));
		return result;
	} /* StringToDate */

	public static int GetEom(int Year, int Month) {
		int result = 0;
		int i_Month = Month - 0;

		switch (i_Month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			result = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			result = 30;
			break;
		case 2:
			if (DateTime.isLeapYear(Year)) {
				result = 29;
			} else {
				result = 28;
			}
			break;
		default:
		}

		return result;
	}

	public static int Cycle; // 考核周期
	public static int DeadLine;// 省级积极分子入监期限
	static {
		// SysParamService pa =
		// (SysParamService)ServiceManager.getSM().getService(SysParamService.SVN);
		// SecurityEngine se =
		// (SecurityEngine)ServiceManager.getSM().getService(SecurityEngine.SVN);
		String sTemp = "25";
		// try{
		// sTemp =
		// pa.getParam("svr","local","cycle",se.getInternalConnection(),null);
		// }catch(Exception e){
		// e.printStackTrace();
		// }
		Cycle = Integer.parseInt(sTemp);
		Cycle = 31;
		// se.getLogger().debug("Init DateTime.Cycle with " + Cycle);

		sTemp = "2";
		// try{
		// sTemp =
		// pa.getParam("svr","local","deadline",se.getInternalConnection(),null);
		// }catch(Exception e){
		// e.printStackTrace();
		// }
		DeadLine = Integer.parseInt(sTemp);
		// se.getLogger().debug("Init DateTime.DeadLine with " + DeadLine);
	}

	/**
	 * 将日期格式的字符串转换为长整型
	 * 
	 * @param date
	 * @return
	 */
	public static long convert2long(String date) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sf.parse(date).getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0l;
	}

	/**
	 * 将长整型数字转换为日期格式的字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String convert2String(long time) {
		if (time > 0l) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date(time);
			return sf.format(date);
		}
		return "";
	}

	/**
	 * 将 HH:mm格式的时间字符串转换为当天的时间
	 * 
	 * @param str
	 *            格式 HH:mm
	 * @return
	 */
	public static Date convertDate(String str) {

		String el = "^\\d{2}\\s*:\\s*\\d{2}$";
		try {

			Pattern p = Pattern.compile(el);
			Matcher m = p.matcher(str);
			boolean b = m.matches();
			if (!b)
				throw new Exception("[str]格式不对");

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY,
					Integer.parseInt(str.split(":")[0]));
			calendar.set(Calendar.MINUTE, Integer.parseInt(str.split(":")[1]));
			calendar.set(Calendar.SECOND, 0);
			return calendar.getTime();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 比较两个日期的大小，输入格式"XXXX-XX-XX"，day1<day2,返回-1，day1=day2，返回0，day1>day2返回1
	 * 
	 * @param day1
	 * @param day2
	 * @return
	 */
	public static int compareTwoDays(String day1, String day2) {
		Calendar calendar1 = StringtoCalendar(day1);
		Calendar calendar2 = StringtoCalendar(day2);

		return calendar1.compareTo(calendar2);
	}

	/**
	 * 比较两个日期的大小，输入格式"XXXX-XX-XX hh:mm:ss"，day1<day2,返回-1，day1=day2，返回0，day1>
	 * day2返回1
	 * 
	 * @param day1
	 * @param day2
	 * @return
	 */
	public static int compareTwoDays2(String day1, String day2) {
		Calendar calendar1 = StringtoCalendar1(day1);
		Calendar calendar2 = StringtoCalendar1(day2);
		return calendar1.compareTo(calendar2);
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 计算两个日期之间相差月数  
	 * @param date1 较大日期
	 * @param date2 较小日期
	 * @return
	 * @throws ParseException
	 */
    public static int getMonthSpace(String date1, String date2) throws ParseException{
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));
        if(c1.getTimeInMillis() < c2.getTimeInMillis()) return 0;
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if(month1 < month2 || month1 == month2 && day1 < day2) yearInterval --;
        // 获取月数差值
        int monthInterval =  (month1 + 12) - month2;
        if(day1 < day2) monthInterval --;
        monthInterval %= 12;
        return yearInterval * 12 + monthInterval;
    }
    
    /**
     * 校验日期是否是正确的日期
     * @Title: validateCorrectTime
     * @param dateStr
     * @return 参数
     * @return boolean 返回类型
     */
    public static boolean validateCorrectTime(String dateStr) {
        String msg ="";
        String[] data = new String[3];
        boolean flag = true; // 若不符合规则将值改为false
        String year = "[0-9]{4}";// 年
        String month = "[0-9]||0[0-9]||1[12]";// 月
        String day = "[0-9]||[0-2][0-9]||3[01]";// 天
        int YEAR = 0;
        String str = dateStr;// 输入的字符串
        data = str.split("[-/.+]");
        // 最基本的检查格式 begin
        if (!data[0].matches(year)) {
            msg = "年不对";
            flag = false;
        }
        if (!data[1].matches(month)) {
            msg = "月不对";
            flag = false;
        }
        if (!data[2].matches(day)) {
            msg = "日不对";
            flag = false;
        }
        YEAR = Integer.valueOf(data[0]);
        boolean leapYear = validateLeapYear(YEAR);// 为true是闰年否则是 非闰年
        if (leapYear) {// 闰年
            if (data[1].matches("0[2]||2")) {// 这里是闰年的2月
                if (!data[2].matches("0[1-9]||[1-9]||1[0-9]||2[0-9]")) {
                    flag = false;
                    msg = "2月份的天数不对";
                }
            }
        } else {// 非闰年
            if (data[1].matches("0[2]||2")) {// 这里是平年的2月
                if (!data[2].matches("0[1-9]||[1-9]||1[0-9]||2[0-8]")) {
                    flag = false;
                    msg = "2月份的天数不对";
                }
            }
        }

        // 下面判断除了2月份的大小月天数
        if (data[1].matches("0[13578]||[13578]||1[02]")) {// 这里是大月
            if (!data[2].matches("0[1-9]||[1-9]||[12][0-9]||3[01]")) {
                flag = false;
                msg = data[2] + " 天数不对";
            }
        } else if (data[1].matches("0[469]||[469]||11")) {// 这里是小月
            if (!data[2].matches("0[1-9]||[1-9]||[12][0-9]||30")) {
                flag = false;
                msg = data[2] + " 天数不对";
            }
        }

        if (flag) {
            msg = "日期格式正确";
        }
        
        return flag;
    }
    
    /**
     * 检查是否是闰年
     * 
     * @param year
     * @return
     */
    public static boolean validateLeapYear(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {// 是闰年
            return true;
        } else {
            return false;
        }
    }

}
