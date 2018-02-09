package com.vispractice.fmc.business.service.sys.config.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.base.constant.CommonConstant;
import com.vispractice.fmc.business.base.domain.WfMonitorCalendarForm;
import com.vispractice.fmc.business.base.utils.CalendarUtils;
import com.vispractice.fmc.business.entity.sys.config.WfMonitorCalendarMain;
import com.vispractice.fmc.business.entity.sys.config.WfMonitorCalendarSetting;
import com.vispractice.fmc.business.entity.sys.config.repository.WfMonitorCalendarMainRepository;
import com.vispractice.fmc.business.entity.sys.config.repository.WfMonitorCalendarSettingRepository;
import com.vispractice.fmc.business.entity.sys.config.repository.WfMonitorCalendarVRepository;
import com.vispractice.fmc.business.entity.sys.config.view.WfMonitorCalendarV;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.service.sys.config.IWfMonitorCalendarMainService;

/**
 * 
 * 编  号：
 * 名  称：工作日历服务实现类
 * 描  述：WfMonitorCalendarMainServiceImpl.java
 * 完成日期：2017年12月5日 下午4:00:37
 * 编码作者："LaiJiashen"
 */
@Service
@Transactional
public class WfMonitorCalendarMainServiceImpl implements IWfMonitorCalendarMainService{
	
	@Autowired
	private WfMonitorCalendarMainRepository wfMonitorCalendarMainRepository;
	
	@Autowired
	private WfMonitorCalendarSettingRepository wfMonitorCalendarSettingRepository;
	
	@Autowired
	private WfMonitorCalendarVRepository wfMonitorCalendarVRepository;
	
	@Autowired
	private SysOrgElementRepository sysOrgElementRepository;
	
	@Override
	public Page<WfMonitorCalendarV> findAll(final WfMonitorCalendarV wfMonitorCalendarV,Pageable pageable){
		Specification<WfMonitorCalendarV> spec = new Specification<WfMonitorCalendarV>() {
			@Override
			public Predicate toPredicate(Root<WfMonitorCalendarV> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				List<Expression<Boolean>> expressions = predicate.getExpressions();

				// 工作日历名称
				if (StringUtils.isNotBlank(wfMonitorCalendarV.getFdName())) {
					expressions.add(cb.like(root.<String> get("fdName"),"%" + wfMonitorCalendarV.getFdName() + "%"));
				}
				// 工作日历年份
				if (StringUtils.isNotBlank(wfMonitorCalendarV.getFdYear())) {
					expressions.add(cb.like(root.<String> get("fdYear"),"%" + wfMonitorCalendarV.getFdYear() + "%"));
				}
				// 工作日历年份
				if (StringUtils.isNotBlank(wfMonitorCalendarV.getFdEnabled())) {
					expressions.add(cb.like(root.<String> get("fdEnabled"),"%" + wfMonitorCalendarV.getFdEnabled() + "%"));
				}

				return predicate;
			}
		};
		
		Page<WfMonitorCalendarV> page = wfMonitorCalendarVRepository.findAll(spec, pageable);
		return page;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public WfMonitorCalendarForm getCalendarView(String calendarId,String myYear,String week1,String week2){
		WfMonitorCalendarForm wfMonitorCalendarForm = new WfMonitorCalendarForm();
		//xx年每月第一天为星期几
		List<Integer> bjAttDateList = new ArrayList<Integer>();
		//XX年每月有多少天
		List<Integer> bjDaysList = new ArrayList<Integer>();
		//日历列表
		List<List<WfMonitorCalendarSetting>> calendarList= new ArrayList<List<WfMonitorCalendarSetting>>();
		
		int startDay=Integer.parseInt(week2);
		int endDay=Integer.parseInt(week1);
		
		List<Integer> restList = CalendarUtils.getRestList(startDay, endDay);
		
		if(StringUtils.isEmpty(myYear) || myYear.isEmpty()){
			List<WfMonitorCalendarSetting> dayList1 = wfMonitorCalendarSettingRepository.findByFdCalendarId(calendarId);
			if (dayList1.size()>0) {
				return this.getCalendarView(calendarId);
			}else {
				myYear = String.valueOf(new Date().getYear()+1900);
			}
			
		}
		
		// 1、得到每月第一天是星期几与得到每月有多少天
		for(int i=0; i<12; i++){
			//得到每月第一天是星期几
			bjAttDateList.add(CalendarUtils.isWeek(Integer.parseInt(myYear), (i+1)));
			//得到每月有多少天
			bjDaysList.add(CalendarUtils.getMonthDays(Integer.parseInt(myYear), (i+1)));
		}

		for(int i=1;i<13;i++){
			List<WfMonitorCalendarSetting> mothList = new ArrayList<WfMonitorCalendarSetting>();
			List<WfMonitorCalendarSetting>dayList = wfMonitorCalendarSettingRepository.
					findByFdCalendarIdAndFdYearAndFdMonthOrderByFdDay(calendarId, myYear, String.valueOf(i));
			int monthDay=bjDaysList.get(i-1);
			if(dayList.size()==0){
				for(int j=0;j<monthDay;j++){
					WfMonitorCalendarSetting syswf = new WfMonitorCalendarSetting();
					syswf.setFdYear(myYear);
					syswf.setFdMonth(String.valueOf(i));
					syswf.setFdDay(String.valueOf(j+1));
					syswf.setFdType(String.valueOf(WfMonitorCalendarMain.WORK));
					int week = (CalendarUtils.isWeek(Integer.parseInt(myYear), (i))+j)%7;//日期的星期
					
					//每月休息日
					if(restList.contains(week)){
						syswf.setFdType(String.valueOf(WfMonitorCalendarMain.REST));
					}
					
					
					mothList.add(syswf);
					
				}
			}else{
				//补全工作日
				for(int j=0;j<monthDay;j++){
					WfMonitorCalendarSetting syswf = new WfMonitorCalendarSetting();
					syswf.setFdYear(myYear);
					syswf.setFdMonth(String.valueOf(i));
					syswf.setFdDay(String.valueOf(j+1));
					boolean flag = true;
					for (WfMonitorCalendarSetting WfMonitorCalendarSetting : dayList) {
						if(WfMonitorCalendarSetting.getFdDay().equals((j+1)+"")){
							mothList.add(WfMonitorCalendarSetting);
							flag=false;
						}
					}
					if(flag){
						syswf.setFdType(String.valueOf(WfMonitorCalendarMain.WORK));
						mothList.add(syswf);
					}
					
					int week = (CalendarUtils.isWeek(Integer.parseInt(myYear), (i))+j)%7;//日期的星期
					
					//每月休息日
					if(restList.contains(week)){
						syswf.setFdType(String.valueOf(WfMonitorCalendarMain.REST));
					}
				}
				
			}
			calendarList.add(mothList);
		}
		
		wfMonitorCalendarForm.setFirstDateList(bjAttDateList);
		wfMonitorCalendarForm.setDaysPerMonList(bjDaysList);
		wfMonitorCalendarForm.setCalendarList(calendarList);
		return wfMonitorCalendarForm;
	}
	
	/**
	 * 直接从数据库读取已存在的日历
	 * @Title: getCalendarView 
	 * @param calendarId
	 * @return
	 */
	private WfMonitorCalendarForm getCalendarView(String calendarId){
		WfMonitorCalendarForm wfMonitorCalendarForm = new WfMonitorCalendarForm();
		//xx年每月第一天为星期几
		List<Integer> bjAttDateList = new ArrayList<Integer>();
		//XX年每月有多少天
		List<Integer> bjDaysList = new ArrayList<Integer>();
		//日历列表
		List<List<WfMonitorCalendarSetting>> calendarList= new ArrayList<List<WfMonitorCalendarSetting>>();
		String myYear = "";
		
		List<WfMonitorCalendarSetting> dayList1 = wfMonitorCalendarSettingRepository.findByFdCalendarId(calendarId);
		if (dayList1.size()>0) {
			myYear = dayList1.get(0).getFdYear();
		}
		// 
		for(int i=0; i<12; i++){
			//得到每月第一天是星期几
			bjAttDateList.add(CalendarUtils.isWeek(Integer.parseInt(myYear), (i+1)));
			//得到每月有多少天
			bjDaysList.add(CalendarUtils.getMonthDays(Integer.parseInt(myYear), (i+1)));
		}
		// 按月份生成当前年年历
		for(int i=1;i<13;i++){
			List<WfMonitorCalendarSetting> mothList = new ArrayList<WfMonitorCalendarSetting>();
			List<WfMonitorCalendarSetting> dayList = wfMonitorCalendarSettingRepository.
					findByFdCalendarIdAndFdYearAndFdMonthOrderByFdDay(calendarId, myYear, String.valueOf(i));
			int monthDay=bjDaysList.get(i-1);
			
			for(int j=0;j<monthDay;j++){
				WfMonitorCalendarSetting syswf = new WfMonitorCalendarSetting();
				syswf.setFdYear(myYear);
				syswf.setFdMonth(String.valueOf(i));
				syswf.setFdDay(String.valueOf(j+1));
				boolean flag = true;
				for (WfMonitorCalendarSetting WfMonitorCalendarSetting : dayList) {
					if(WfMonitorCalendarSetting.getFdDay().equals((j+1)+"")){
						mothList.add(WfMonitorCalendarSetting);
						flag=false;
					}
				}
				if(flag){
					syswf.setFdType(String.valueOf(WfMonitorCalendarMain.WORK));
					mothList.add(syswf);
				}
			}
			calendarList.add(mothList);
		}
		wfMonitorCalendarForm.setFirstDateList(bjAttDateList);
		wfMonitorCalendarForm.setDaysPerMonList(bjDaysList);
		wfMonitorCalendarForm.setCalendarList(calendarList);
		return wfMonitorCalendarForm;
	}

	@Override
	public List<WfMonitorCalendarMain> getEnabled(String fdId) {
		return wfMonitorCalendarMainRepository.findByFdEnabled(CommonConstant.AVAILABLE_FLAG);
	}

	@Override
	public void saveCalendarAndDays(WfMonitorCalendarForm wfMonitorCalendarForm,String creatorId) {
		
		WfMonitorCalendarMain calendarMain = wfMonitorCalendarForm.getWfMonitorCalendarMain();
		wfMonitorCalendarMainRepository.save(calendarMain);
		String calendarId = calendarMain.getFdId();
		
		//保存范围
		List<SysOrgElement> oldElementList = sysOrgElementRepository.findByFdCalendarId(calendarId);
		for (SysOrgElement sysOrgElement : oldElementList) {
			sysOrgElement.setFdCalendarId(null);
		}
		sysOrgElementRepository.save(oldElementList);
		if (StringUtils.isNotEmpty(calendarMain.getFdScopeIds())) {
			String[] idArray = calendarMain.getFdScopeIds().split(";");
			List<String> elementIdList = new ArrayList<String>();
			for (String string : idArray) {
				elementIdList.add(string);
			}
			List<SysOrgElement> newOrgElementList = sysOrgElementRepository.findByFdIdIn(elementIdList);
			for (SysOrgElement sysOrgElement : newOrgElementList) {
				sysOrgElement.setFdCalendarId(calendarId);
			}
			sysOrgElementRepository.save(newOrgElementList);
		}
		
		wfMonitorCalendarSettingRepository.deleteByFdCalendarId(calendarId);
		List<List<WfMonitorCalendarSetting>> dayList = wfMonitorCalendarForm.getCalendarList();
		List<WfMonitorCalendarSetting> newList = new ArrayList<WfMonitorCalendarSetting>();
		List<WfMonitorCalendarSetting> removeList = new ArrayList<WfMonitorCalendarSetting>();
		// 去除工作日
		for (List<WfMonitorCalendarSetting> list : dayList) {
			for (int i = 0; i < list.size(); i++) {
				WfMonitorCalendarSetting wfMonitorCalendarSetting = list.get(i);
				if (String.valueOf(WfMonitorCalendarMain.WORK).equals(wfMonitorCalendarSetting.getFdType())) {
					removeList.add(wfMonitorCalendarSetting);
					continue;
				}
				wfMonitorCalendarSetting.setFdCalendarId(calendarId);
				wfMonitorCalendarSetting.setFdCreatedDate(new Date());
				wfMonitorCalendarSetting.setFdCreatedBy(creatorId);
			
			}
			newList.addAll(list);
		}
		newList.removeAll(removeList);
		wfMonitorCalendarSettingRepository.save(newList);
		
	}

	@Override
	public void deleteByFdId(String calendarId) {
		wfMonitorCalendarMainRepository.delete(calendarId);
		wfMonitorCalendarSettingRepository.deleteByFdCalendarId(calendarId);
	}

}
