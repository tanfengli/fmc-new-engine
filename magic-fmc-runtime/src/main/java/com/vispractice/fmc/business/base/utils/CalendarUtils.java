package com.vispractice.fmc.business.base.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 编  号：
 * 名  称：CalendarUtils
 * 描  述：CalendarUtils.java
 * 完成日期：2017年12月5日 下午3:44:35
 * 编码作者："LaiJiashen"
 */
public class CalendarUtils
{
	/**
	 * 判断是否闰年
	 * @param year
	 * @return
	 */
	public static boolean isYesr(int year) {
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断某年某月有多少天
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getMonthDays(int year, int month) {
		int days = 0;
		switch (month) {
		case 2:
			if (isYesr(year)) {
				days = 29;
			} else {
				days = 28;
			}
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			days = 30;
			break;
		default:
			days = 31;
		}
		return days;
	}

	/**
	 * 判断某年某月距离1900-01-01有多少天
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getTotalDays(int year, int month) {
		int days = 0;
		for (int i = 1900; i < year; i++) {
			if (isYesr(i)) {
				days += 366;
			} else {
				days += 365;
			}
		}
		for (int i = 1; i < month; i++) {
			days += getMonthDays(year, i);
		}
		return days;
	}

	/**
	 * 判断某年某月的第一天是星期几
	 * @param year
	 * @param month
	 * @return
	 */
	public static int isWeek(int year, int month) {
		return (getTotalDays(year, month) + 1) % 7;
	}
	
	public static List<Integer> getRestList(int startDay,int endDay){
		if(endDay==7){
			endDay=0;
		}
		List<Integer> restList = new ArrayList<Integer>();
		for(int i=1;i<7;i++){
			startDay++;
			if(startDay>=7){
				if((startDay-7)==endDay){
					break;
				}
				restList.add(startDay-7);
			}else{
				if(startDay==endDay){
					break;
				}
				restList.add(startDay);
			}
		}
		return restList;
	}
}
