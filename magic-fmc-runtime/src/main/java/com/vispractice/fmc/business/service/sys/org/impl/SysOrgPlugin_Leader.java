package com.vispractice.fmc.business.service.sys.org.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.base.utils.StringUtil;
import com.vispractice.fmc.business.base.constrant.SysOrgConstant;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRole;
import com.vispractice.fmc.business.entity.sys.org.beans.context.SysOrgRolePluginContext;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.service.sys.org.ISysOrgRolePlugin;
/**
 * 
 * 编  号：
 * 名  称：SysOrgPlugin_Leader
 * 描  述：SysOrgPlugin_Leader.java
 * 完成日期：2017年8月9日 下午6:34:52
 * 编码作者："LaiJiashen"
 */

@Service
public class SysOrgPlugin_Leader implements ISysOrgRolePlugin {
	
	@Autowired
	private SysOrgElementRepository sysOrgElementRepository;
	
	@Override
	public List<SysOrgElement> parse(SysOrgRolePluginContext context)
			throws Exception {
		if (context == null)
			return new ArrayList<SysOrgElement>();
		SysOrgElement element = context.getBaseElement();
		SysOrgRole role = context.getRole();
		String type = role.getParameter("type");
		String endlevel = role.getParameter("endlevel");
		int lv = Integer.parseInt(role.getParameter("level"));
		int elv = 0;
		if (StringUtil.hasLength(endlevel)) {
			elv = Integer.parseInt(endlevel);
		} else {
			elv = lv;			
		}
		if ("excludeme".equals(type)) {
			return getLeaders(this.getAllMyLeader(element), lv, elv);
		} else {
			return getLeaders(this.getAllLeader(element), lv, elv);
		}
	}

	/**
	 * 获取所有领导
	 * @Title: getAllLeader 
	 * @param element
	 * @return
	 */
	private List<SysOrgElement> getAllLeader(SysOrgElement element) {
		// 以下代码由Domino的领导获取代码翻译而成，代码逻辑有些混乱，修改时需要跟domino的逻辑同步，否则很麻烦
		List<SysOrgElement> rtnVal = new ArrayList<SysOrgElement>();
		List<String> idList = new ArrayList<String>();
		List<String> nameList = new ArrayList<String>();
		SysOrgElement curElem = element; // 当前的组织架构元素
		SysOrgElement calElem = element; // 实际参与计算的组织架构元素
		SysOrgElement lastSuperLeader = null; // 上次循环使用的上级领导
		for (; curElem != null && calElem != null;) {
			// 若参与计算的组织架构是个人或已未定义领导的岗位，则计算的实体为所在的部门
			SysOrgElement calElem_thisLeader = sysOrgElementRepository.getThisLeader(calElem.getFdId());
			SysOrgElement calElem_superLeader = sysOrgElementRepository.getSuperLeader(calElem.getFdId());
			SysOrgElement calElem_Parent = sysOrgElementRepository.getParent(calElem.getFdId());
			int curOrgType = calElem.getFdOrgType().intValue();
			if (curOrgType == SysOrgConstant.ORG_TYPE_PERSON || curOrgType == SysOrgConstant.ORG_TYPE_POST && calElem_thisLeader == null) {
				calElem = calElem_Parent;
				if (calElem == null) {
					break;
				}
				calElem_thisLeader = sysOrgElementRepository.getThisLeader(calElem.getFdId());
				calElem_superLeader = sysOrgElementRepository.getSuperLeader(calElem.getFdId());
				calElem_Parent = sysOrgElementRepository.getParent(calElem.getFdId());
			}
			// 死循环校验
			nameList.add(calElem.getFdName());
			if (idList.contains(calElem.getFdId())) {
				throw new RuntimeException("获取"+calElem.getFdName()+"的领导信息时发生领导循环错误");
			}
			idList.add(calElem.getFdId());
			// 获取直接领导，若直接领导为空或跟上次循环使用的上级领导重叠了，则忽略
			SysOrgElement thisLeader = calElem_thisLeader;
			if (!(thisLeader == null || thisLeader.equals(lastSuperLeader))) {
				rtnVal.add(thisLeader);
			}
			if (calElem.getFdOrgType().intValue() == SysOrgConstant.ORG_TYPE_ORG
					|| calElem.getFdOrgType().intValue() == SysOrgConstant.ORG_TYPE_DEPT) {
				// 若计算对象为机构或部门，则获取上级领导，并添加到返回值列表中
				// 若上级领导不为空，则下次循环采用上级领导，否则采用上级部门
				lastSuperLeader = calElem_superLeader;
				if (lastSuperLeader != null) {
					rtnVal.add(lastSuperLeader);
					curElem = lastSuperLeader;
				} else {
					curElem = calElem_Parent;
				}
				calElem = curElem;
				if (calElem == null) {
					break;
				}
				calElem_thisLeader = sysOrgElementRepository.getThisLeader(calElem.getFdId());
				calElem_superLeader = sysOrgElementRepository.getSuperLeader(calElem.getFdId());
				calElem_Parent = sysOrgElementRepository.getParent(calElem.getFdId());
			} else {
				// 注意：这里计算对象只剩下岗位了
				if (thisLeader == null)
					break;
				// 设定下一轮循环的对象，这里的逻辑实在没有搞明白，但为了跟Domino一样，也只能照抄了
				// 疑惑点：在这里calElem与curElem居然不同步了，为什么判断用的是curOrgType，为什么要判断是否岗位，不解
				calElem = thisLeader;
				if (null!=calElem.getFdId()) {
					calElem_thisLeader = sysOrgElementRepository.getThisLeader(calElem.getFdId());
					calElem_superLeader = sysOrgElementRepository.getSuperLeader(calElem.getFdId());
					calElem_Parent = sysOrgElementRepository.getParent(calElem.getFdId());
				}
				if (curOrgType == SysOrgConstant.ORG_TYPE_POST) {
					curElem = calElem_thisLeader;
				} else {
					curElem = calElem_Parent;
				}
				lastSuperLeader = null;
			}
		}
		return rtnVal;
	}

	/**
	 * 获取所有领导（不包含自己）
	 * @Title: getAllMyLeader 
	 * @param element
	 * @return
	 */
	private List<SysOrgElement> getAllMyLeader(SysOrgElement element) {
		SysOrgElement selfElement = sysOrgElementRepository.getOne(element.getFdId());
		List<SysOrgElement> rtnList = getAllLeader(element);
		if (element.getFdOrgType().intValue() == SysOrgConstant.ORG_TYPE_POST) {
			rtnList.remove(selfElement);
		} else if (element.getFdOrgType().intValue() == SysOrgConstant.ORG_TYPE_PERSON) {
			rtnList.remove(selfElement);
			rtnList.removeAll(sysOrgElementRepository.getPostByPersonId(element.getFdId()));
		}
		return rtnList;
	}
	
	/**
	 * 在领导列表中找到需要的领导
	 * @Title: getLeaders 
	 * @param leaders
	 * @param s
	 * @param e
	 * @return
	 */
	private List<SysOrgElement> getLeaders(List<SysOrgElement> leaders, int s,
			int e) {
		List<SysOrgElement> rtnList = new ArrayList<SysOrgElement>();
		if (s < 0)
			s = leaders.size() + s;
		if (e < 0)
			e = leaders.size() + e;
		int step = s > e ? -1 : 1;
		for (int i = s; i >= 0 && i < leaders.size()
				&& (s > e ? i >= e : i <= e); i += step) {
			rtnList.add(leaders.get(i));
		}
		return rtnList;
	}

}
