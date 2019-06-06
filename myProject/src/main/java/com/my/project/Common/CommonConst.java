package com.my.project.Common;

import com.my.project.entity.LsxxBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CommonConst {

public static HashMap<String, LsxxBean> lsxxMap = new HashMap<String,LsxxBean>();
public static HashMap<String,Object> hisOddsMap = new HashMap<>();
	/**
	 * 种子url
	 */
public static  final String URL="http://bf.139cai.com/mcache/livejcjs";
/**
 * 将联赛信息放置在内存中
 * @param beanList
 */
public static void putLsxxMap(List<LsxxBean> beanList) {
	for (LsxxBean lsxxBean : beanList) {
		HashMap<String,LsxxBean> lsxxMap = CommonConst.lsxxMap;
		lsxxMap.put(lsxxBean.getLsmc(), lsxxBean);
	}
}
/**
 * 获取当前时间字符串
 * @param format 时间格式字符串
 * @return 当前时间字符串
 */
	public static String getNow(String format) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	/**
	 * 时间计算
	 * @param format 时间格式
	 * @param num 天数
	 * @return 时间字符串
	 */
	public static String getDate(String format,int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); 
		calendar.add(Calendar.DATE, num);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(calendar.getTime());
	}
	public static String getOldDate(String format,int num) {
		Calendar	calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String str = "20140501";
		try {
			calendar.setTime(sdf.parse(str));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		calendar.add(Calendar.DATE, num);
		return sdf.format(calendar.getTime());
	}
	public static int getXq(String date,String format){
		Calendar	calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			calendar.setTime(sdf.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return w;
	}
	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date getDate(String date,String format)  {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date dt = null;
		try {
			dt = sdf.parse(date);
		}catch (Exception e) {
			dt = null;
		}
		return dt;
	}
}
