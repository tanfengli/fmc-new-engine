package com.vispractice.fmc.business.service.sys.org.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.base.constrant.BaseTreeConstant;
import com.vispractice.fmc.business.base.constrant.SysOrgConstant;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRole;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleConf;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleLine;
import com.vispractice.fmc.business.entity.sys.org.beans.context.SysOrgRolePluginContext;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgPostPersonRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgRoleConfRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgRoleLineDefaultRoleRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgRoleLineRepository;
import com.vispractice.fmc.business.service.sys.org.ISysOrgRolePlugin;
/**
 * 
 * 编  号：
 * 名  称：SysOrgRolePluginService
 * 描  述：SysOrgRolePluginService.java
 * 完成日期：2017年8月9日 下午6:21:53
 * 编码作者："LaiJiashen"
 */
@Slf4j
@Service
public class SysOrgRolePluginService implements ISysOrgRolePlugin {
	
	@Autowired
	private SysOrgRoleConfRepository sysOrgRoleConfRepository;
	
	@Autowired
	private SysOrgElementRepository sysOrgElementRepository;
	
	@Autowired
	private SysOrgRoleLineRepository sysOrgRoleLineRepository;
	
	@Autowired
	private SysOrgRoleLineDefaultRoleRepository sysOrgRoleLineDefaultRoleRepository;
	
	@Autowired
	private SysOrgPostPersonRepository sysOrgPostPersonRepository;

	@Override
	public List<SysOrgElement> parse(SysOrgRolePluginContext pluginContext) throws Exception{
		System.out.println("进入角色线计算！");

		List<SysOrgElement> rtnList = new ArrayList<SysOrgElement>();
		if (pluginContext == null)
			return rtnList;
		SysOrgElement element = pluginContext.getBaseElement();
		SysOrgRole role = pluginContext.getRole();
		SysOrgElement roleElement = sysOrgElementRepository.findOne(role.getFdId());
		role.setElement(roleElement);
		SysOrgRoleConf conf = sysOrgRoleConfRepository.findOne(role.getFdRoleConfId());
		if (role.getElement().getFdIsAvailable() != null && 1L!=role.getElement().getFdIsAvailable()
				|| conf != null && conf.getFdIsAvailable() != null
				&& 1L!=conf.getFdIsAvailable()) {
			throw new Exception("sysOrgRole.error.notAvailable"+role.getElement().getFdName());
		}
		if (log.isDebugEnabled()) {
			log.debug("开始计算角色线：role=" + role.getElement().getFdName() + ", element="
					+ element.getFdName());
		}

		SysOrgRoleLine roleLine = getRoleLine(element, role.getFdRoleConfId());
		if (roleLine == null) {
			if (log.isDebugEnabled()) {
				log.debug("找不到角色线对应的成员");
			}
			return rtnList;
		} else {
			if (log.isDebugEnabled()) {
				log.debug("找到角色线对应的成员,其id为：" + roleLine.getFdId());
			}
		}
		int type = Integer.parseInt(role.getParameter("type"));
		int lv = Integer.parseInt(role.getParameter("level"));
		boolean includeme = false;
		if ("true".equals(role.getParameter("includeme"))
				&& roleLine.getHbmChildren() != null
				&& roleLine.getHbmChildren().size() != 0) {
			// 包括提交人自己、该用户不属于角色线成员的最底层（属于领导）
			includeme = true;
			if (lv < 0) {
				// 仅查找上级时生效
				lv += 1;
			}
		}
		switch (type) {
		case 0:
			// 单一领导
			addRoleLine(rtnList, getRoleLine(roleLine, lv), element);
			break;
		case 1:
			// 多个领导
			int lv_e = Integer.parseInt(role.getParameter("end"));
			if (includeme) {
				if (lv_e < 0) {
					// 仅查找上级时生效
					lv_e += 1;
				}
			}
			String[] ids = roleLine.getFdHierarchyId().substring(1).split(
					BaseTreeConstant.HIERARCHY_ID_SPLIT);
			int length = ids.length;
			// 获取开始和结束的索引号
			int i1 = getIndex(lv, length);
			int i2 = getIndex(lv_e, length);
			if (lv * lv_e < 0) {
				// 开始层级和结束层级不同方向，若出现交错现象，则不添加
				if (Math.abs(lv) + Math.abs(lv_e) > length) {
					break;
				}
				// 到这里，i1和i2肯定不会越界
			} else {
				// 开始层级和结束层级同方向，若双边越界，则不添加
				if (i1 < 0 && i2 < 0 || i1 >= length && i2 >= length) {
					break;
				}
				// 处理越界情况
				if (i1 < 0) {
					i1 = 0;
				} else if (i1 >= length) {
					i1 = length - 1;
				}
				if (i2 < 0) {
					i2 = 0;
				} else if (i2 >= length) {
					i2 = length - 1;
				}
			}
			// 注意：多个领导的返回是有序的
			int step = i1 > i2 ? -1 : 1;
			for (int i = i1; i1 > i2 ? i >= i2 : i <= i2; i += step) {
				addRoleLine(rtnList, (SysOrgRoleLine) sysOrgRoleLineRepository.findOne(ids[i]), element);
			}
			break;
		case 2:
			// 直接下属
		case 3:
			// 所有下属
			roleLine = getRoleLine(roleLine, lv);
			//下属列表
			List<SysOrgElement> subordinateList = new ArrayList<SysOrgElement>();
			if (roleLine != null) {
				//直接下属
				if (type == 2) {
					subordinateList = sysOrgRoleLineRepository.getDirectSubordinate(roleLine.getFdId());
				} else {//所有下属
					subordinateList = sysOrgRoleLineRepository.getAllSubordinate(roleLine.getFdHierarchyId()+"%");
				}
				rtnList.addAll(subordinateList);
				
			}
			break;
		}
		// 增加根据返回值配置过滤数据 by fuyx 2010-10-16
		if (role.getFdRtnValue() != null && role.getFdRtnValue().length() > 0) {
			int value = Integer.parseInt(role.getFdRtnValue());
			for (Iterator<SysOrgElement> it = rtnList.iterator(); it.hasNext();) {
				SysOrgElement elem = it.next();
				if ((value & elem.getFdOrgType().intValue()) <= 0) {
					it.remove();
				}
			}
		}
		return rtnList;
	
	}

	/**
	 * 获取相对于roleLine的lv层级的的角色成员
	 * 
	 * @param roleLine
	 * @param lv
	 * @return
	 * @throws Exception
	 */
	private SysOrgRoleLine getRoleLine(SysOrgRoleLine roleLine, int lv) throws Exception{
		String[] ids = roleLine.getFdHierarchyId().substring(1).split(BaseTreeConstant.HIERARCHY_ID_SPLIT);
		int index = getIndex(lv, ids.length);
		if (index >= 0 && index <= ids.length - 1) {
			if (log.isDebugEnabled()) {
				log.debug("获取角色线时，超过指定层级：" + lv);
			}
			return sysOrgRoleLineRepository.findOne(ids[index]);
		} else {
			return null;
		}
	}

	/**
	 * 获取lv对应的索引号
	 * 
	 * @param lv
	 * @param length
	 * @return
	 */
	private int getIndex(int lv, int length) {
		if (lv > 0) {
			return lv - 1;
		} else {
			return length + lv - 1;
		}
	}

	/**
	 * 添加一个角色线中的成员（如果这个成员是机构或者部门，则返回原来的值）
	 * 
	 * @param rtnList
	 * @param roleLine
	 * @param element
	 */
	private void addRoleLine(List<SysOrgElement> rtnList,SysOrgRoleLine roleLine, SysOrgElement element) {
		if (null == roleLine || null == roleLine.getFdMemberId()) {
			return ;
		}
		SysOrgElement sysOrgRoleMember = sysOrgElementRepository.findOne(roleLine.getFdMemberId());
		if (sysOrgRoleMember != null) {
			int orgType = sysOrgRoleMember.getFdOrgType().intValue();
			if (orgType == SysOrgConstant.ORG_TYPE_ORG || orgType == SysOrgConstant.ORG_TYPE_DEPT) {
				rtnList.add(element);
			} else {
				rtnList.add(sysOrgRoleMember);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private SysOrgRoleLine getRoleLine(SysOrgElement element,String confId) {
		// 注意：ids的顺序是按优先度排序的
		List<String> ids = new ArrayList<String>();
		// 传递的element优先度最高
		ids.add(element.getFdId());
		int orgType = element.getFdOrgType().intValue();
		if (orgType == SysOrgConstant.ORG_TYPE_PERSON) {
			// 其次是岗位
			List postIds = sysOrgPostPersonRepository.findFdPostId(element.getFdId());
			if (postIds.size() > 1) {
				List<String> result = sysOrgRoleLineDefaultRoleRepository.findDefaultPostId(confId, element.getFdId());
				if (!result.isEmpty()) {
					String defaultPostId = result.get(0);
					if (postIds.contains(defaultPostId)) {
						postIds.remove(defaultPostId);
						ids.add(defaultPostId);
					}
				}
			}
			ids.addAll(postIds);
		} else if (orgType == SysOrgConstant.ORG_TYPE_POST) {
			// 若岗位里面只有一个人，则这个人也加入搜索范围
			List<String> persons = sysOrgPostPersonRepository.findFdPersonId(element.getFdId());
			if (persons.size() == 1)
				ids.add(persons.get(0));
		}
		// 最后是部门id，从最近的排到最远的
		String[] deptIds = element.getFdHierarchyId().substring(1).split(BaseTreeConstant.HIERARCHY_ID_SPLIT);
		for (int i = deptIds.length - 2; i >= 0; i--) {
			ids.add(deptIds[i]);
		}
		if (log.isDebugEnabled()) {
			log.debug("搜索角色线位置，ID顺序为：" + ids);
		}
		// 查找组织架构ID和角色线ID
		List<SysOrgRoleLine> result = sysOrgRoleLineRepository.findFdIdAndMemberId(ids, confId);
		if (!result.isEmpty()) {
			// 根据优先度搜索返回值
			for (int i = 0; i < ids.size(); i++) {
				String id = ids.get(i);
				for (int j = 0; j < result.size(); j++) {
					SysOrgRoleLine values = result.get(j);
					if (id.equals(values.getFdMemberId())) {
						return sysOrgRoleLineRepository.findOne(values.getFdId());
					}
				}
			}
		}
		return null;
	}

}
