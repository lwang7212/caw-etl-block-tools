package org.codeanywhere;

import org.codeanywhere.ConvertUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilHelp
{
	/**
	 * @return 获取唯一UUID
	 */
	public static String getUUID()
	{
		return UUID.randomUUID().toString().toUpperCase();

	}

	/**
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static Object IfNULL(Object p1, Object p2)
	{
		return p1 == null ? p2 : p1;

	}



	/**
	 * 获取本机IP地址
	 * 
	 * @return
	 */
	public static String getIpLocalAddress()
	{
		try
		{
			InetAddress[] IP = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());
			for (int i = 0; i < IP.length; i++)
			{
				if (IP[i] instanceof Inet4Address)
				{
					if (!IP[i].isLoopbackAddress())
					{
						return IP[i].getHostAddress().toString();
					}
				}

			}
		} catch (UnknownHostException t1)
		{

		}
		try
		{

			return InetAddress.getLocalHost().getHostAddress().toString();
		} catch (UnknownHostException t2)
		{

			return "0.0.0.0";
		}

	}

	/**
	 * 取每月的第一日
	 * 
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date dt)
	{
		Calendar cal = getDayCalendar(dt);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	/**
	 * 取每年的第一日
	 *
	 * @return
	 */
	public static Date getFirstDayOfYear(Date dt)
	{
		Calendar cal = getDayCalendar(dt);
		cal.set(Calendar.MONTH,0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	/**
	 * 取每月的第一日
	 *
	 * @return
	 */
	public static Date getLastDayOfMonth(Date dt)
	{
		Calendar cal = getDayCalendar(dt);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}
	/**
	 * 取每年的第一日
	 *
	 * @return
	 */
	public static Date getLastDayOfYear(Date dt)
	{
		Calendar cal = getDayCalendar(dt);
		cal.set(Calendar.MONTH,11);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		return cal.getTime();
	}

	/**
	 * 下一年度
	 *
	 * @return
	 */
	public static Date getNextYear(Date dt)
	{
		Calendar cal = getDayCalendar(dt);
		cal.add(Calendar.YEAR, 1);
		return cal.getTime();
	}
	/**
	 * /** 取指定时间的所在天的零点
	 * 
	 * @return
	 */
	public static Date getTimeOf00(Date dt, int addDays)
	{
		Calendar cal = getDayCalendar(dt);
		cal.add(Calendar.DAY_OF_MONTH, addDays);

		return cal.getTime();
	}

	private static Calendar getDayCalendar(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	/**
	 * /** 取指定时间的所在天的零点
	 * 
	 * @return
	 */
	public static Date getTimeOf00(Date dt)
	{

		return getTimeOf00(dt, 0);
	}

	/**
	 * 取指定时间的所在天的零点
	 * 
	 * @param lng
	 * @return
	 */
	public static Date getTimeOf00(long lng)
	{
		Calendar cal = getDayCalendar(new Date(lng));
		cal.setTimeInMillis(lng);
		cal.add(Calendar.DAY_OF_MONTH, 0);
		return cal.getTime();
	}

	/**
	 * 取当前时刻的分钟数
	 * 
	 * @return
	 */
	public static long getMinutes(Date begin, Date dtEnd)
	{
		long diff = dtEnd.getTime() - begin.getTime();

		return diff / (1000 * 60);

	}

	/**
	 * 指定时刻添加指定的分钟数
	 * 
	 * @return
	 */
	@Deprecated
	public static Date AddMinutes(Date dt, Integer amount)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.add(Calendar.MINUTE, amount);
		return cal.getTime();
	}
	public static Date addMinutes(Date dt, Integer amount)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.add(Calendar.MINUTE, amount);
		return cal.getTime();
	}
	@Deprecated
	public static Date AddDays(Date dt, Integer amount)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.add(Calendar.DAY_OF_MONTH, amount);
		return cal.getTime();
	}
	public static Date addDays(Date dt, Integer amount)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.add(Calendar.DAY_OF_MONTH, amount);
		return cal.getTime();
	}
	@Deprecated
	public static Date AddMonth(Date dt, Integer amount)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.add(Calendar.MONTH, amount);
		return cal.getTime();
	}
	public static Date addMonth(Date dt, Integer amount)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.add(Calendar.MONTH, amount);
		return cal.getTime();
	}
	public static Integer getMonth()
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.get(Calendar.MONTH) + 1;
	}

	public static Integer getMonth(Date dt)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt == null ? new Date() : dt);
		return cal.get(Calendar.MONTH) + 1;
	}

	public static Integer getYear(Date dt)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt == null ? new Date() : dt);
		return cal.get(Calendar.YEAR);
	}

	public static Integer getDay(Date dt)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt == null ? new Date() : dt);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	public static Integer getYearMonth(Date dt)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt == null ? new Date() : dt);
		return cal.get(Calendar.YEAR)*100+ getMonth(dt);
	}
	public static Integer getYearMonth()
	{		
		return getYearMonth(new Date());
	}
	public static Integer getYearMonth(long lng)
	{		
		return getYearMonth(new Date(lng));
	}

	/**
	 * 取指定日期的年月日
	 * @param dt 指定日期
	 * @param dayOffset 日期偏移量
	 * @return
	 */
	public static Integer getYearMonthDay(Date dt,int dayOffset)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt == null ? new Date() : dt);
		cal.add(Calendar.DAY_OF_MONTH, dayOffset);
		return cal.get(Calendar.YEAR)*10000+ (cal.get(Calendar.MONTH)+1)*100 +cal.get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 取指定日期的年月日
	 * @param dt 指定日期
	 * @return
	 */
	public static Integer getYearMonthDay(Date dt)
	{
		return getYearMonthDay(dt,0);
	}

	/**
	 * 取当日的年月日
	 * @return
	 */
	public static Integer getYearMonthDay()
	{
		return getYearMonthDay(new Date(),0);
	}
	/**
	 * 判断收号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles)
	{

		Pattern p = Pattern.compile("^((1[3-9][0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 输入非数字
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean IsNumber(String mobiles)
	{

		Pattern p = Pattern.compile("[0-9]{1,}");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 将日期和时间组合成新的时间
	 * 
	 * @param dt
	 *            日
	 * @return tm 时间
	 */
	public static Date getCurDayTime(Date dt, Date tm)
	{
		Calendar cDay = Calendar.getInstance();
		cDay.setTime(dt);
		Calendar cTm = Calendar.getInstance();
		cTm.setTime(tm);

		cDay.set(Calendar.HOUR_OF_DAY, cTm.get(Calendar.HOUR_OF_DAY));
		cDay.set(Calendar.MINUTE, cTm.get(Calendar.MINUTE));
		cDay.set(Calendar.SECOND, cTm.get(Calendar.SECOND));
		cDay.set(Calendar.MILLISECOND, cTm.get(Calendar.MILLISECOND));
		return cDay.getTime();

	}

	/**
	 * Url中附加查询字符串
	 * 
	 * @param p_url
	 * @param p_key
	 * @param p_value
	 * @return
	 */
	@Deprecated
	public static String AttachQuery(String p_url, String p_key, String p_value)
	{
		if (p_url.contains("?"))
		{
			return p_url + "&" + p_key + "=" + p_value;
		} else
		{
			return p_url + "?" + p_key + "=" + p_value;
		}
	}
	public static String attachQuery(String p_url, String p_key, String p_value)
	{
		if (p_url.contains("?"))
		{
			return p_url + "&" + p_key + "=" + p_value;
		} else
		{
			return p_url + "?" + p_key + "=" + p_value;
		}
	}
	/**
	 * 解析按Fmt日期格式
	 * 
	 * @param source
	 * @param source
	 * @return
	 */
	public static Date parseDate(String source)
	{

		try
		{
			if(source.length()>10) {
				if(source.contains("GMT"))
				{
					//utc格式
					return  new SimpleDateFormat("EEE MMM ddHH:mm:ss 'GMT' yyyy", Locale.US).parse(source);
				}
				if(source.contains("CST"))
				{
					//CST(北京时间)
					return  new SimpleDateFormat("EEE MMM ddHH:mm:ss 'CST' yyyy", Locale.US).parse(source);
				}

				if(source.contains("T"))
				{

					//UTC
					if(source.endsWith("Z"))
					{   //2016-01-05T15:06:58+0800
						//2019-04-13T16:00:00.000Z
						if(source.contains("."))
						{
							return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(source);
						}
						else {
							return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(source);
						}
					}
					else {
						// 2016-01-05T15:09:54Z
						return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(source);
					}
				}
				if(source.contains("."))
				{
					return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(source);

				}
				return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(source);
			}
			else
			{
				return new SimpleDateFormat("yyyy-MM-dd").parse(source);
			}
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			return null;
		}

	}

	/**
	 * 解析按Fmt日期格式
	 * 
	 * @param fmt
	 * @param source
	 * @return
	 */
	public static Date parseDate(String fmt, String source)
	{

		try
		{
			return new SimpleDateFormat(fmt).parse(source);
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			return null;
		}

	}

	public static Integer getDayOfMonth(long lng) {		
		return getDay(new Date(lng));
	}
	public static Integer getDayOfMonth(Date dt) {		
		return getDay(dt);
	}
	public static Integer getDayOfMonth() {		
		return getDay(new Date());
	}
	/**
	 * 对HashMap进行
	 * @param map
	 * @param compare
	 * @return
	 */
	@Deprecated
	public static <V, K> List<Map.Entry<K,V>> SortMap(Map<K,V> map,Comparator<Map.Entry<K,V>> compare)
	{
		List<Map.Entry<K,V>> list =  new ArrayList<Map.Entry<K,V>>(map.entrySet());
		Collections.sort(list, compare);

		return list;
	}
	public static <V, K> List<Map.Entry<K,V>> sortMap(Map<K,V> map,Comparator<Map.Entry<K,V>> compare)
	{
		List<Map.Entry<K,V>> list =  new ArrayList<Map.Entry<K,V>>(map.entrySet());
		Collections.sort(list, compare);

		return list;
	}
	public static void main(String[] args) throws ParseException {
		/*Map<Integer,String> map=new LinkedHashMap<Integer,String>();
		map.put(1, "this is 1");
		map.put(5, "this is 5");
		map.put(4, "this is 4");
		map.put(3, "this is 3");
		map.put(2, "this is 2");

		for(Map.Entry<Integer, String> kv :map.entrySet())
		{
			System.out.println("k="+kv.getKey() +",v="+kv.getValue());
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		for(Map.Entry<Integer, String> kv :org.codeanywhere.UtilHelp.SortMap(map, new Comparator<Map.Entry<Integer, String>>(){

			@Override
			public int compare(Entry<Integer, String> o1, Entry<Integer, String> o2) {
				 
				return o1.getKey()-o2.getKey();
			}

			}))
		{
			System.out.println("k="+kv.getKey() +",v="+kv.getValue());
		}*/

		//System.out.println(getYearMonthDay(parseDate("2017-08-30"),100));
		//System.out.println(getFirstDayOfYear(parseDate("2017-08-30")));
		System.out.println(ConvertUtil.DateToString(getFirstDayOfYear(parseDate("2017-08-30"))));
		System.out.println(ConvertUtil.DateToString(getFirstDayOfYear(new Date())));

		System.out.println(ConvertUtil.DateToString(getLastDayOfMonth(parseDate("2017-02-20"))));
		System.out.println(ConvertUtil.DateToString(getLastDayOfYear(new Date())));

	/*	System.out.println(parseDate("2016-01-05 15:06:58"));
		System.out.println(parseDate("Tue Jan 05 15:06:58 CST 2016"));
		System.out.println(parseDate("2016-01-05T15:06:58+0800"));
		System.out.println(parseDate("2016-01-05T15:09:54Z"));
		System.out.println(parseDate("2016-01-05 15:06:58.800"));*/

	}
	
}
