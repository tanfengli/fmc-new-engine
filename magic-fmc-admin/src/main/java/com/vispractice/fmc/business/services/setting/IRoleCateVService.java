package com.vispractice.fmc.business.services.setting;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entities.setting.view.RoleCateV;
import com.vispractice.fmc.business.entities.sys.SysAuthCategory;

/**
 * 编  号：<br/>
 * 名  称：IRoleCateVService<br/>
 * 描  述：<br/>
 * 完成日期：2016年12月26日 上午10:34:04<br/>
 * 编码作者：
 */
public interface IRoleCateVService {
	/**
	 * 查询
	 */
	Page<RoleCateV> findByExample(RoleCateV roleCate, Pageable pageable);

	
	/**
	 * 保存
	 */
	void save(SysAuthCategory authCate);
	
	/**
	 * 删除
	 */
	void deleteByFdId(RoleCateV roleCate);
	
	/**
	 * 不分页查询
	 */
	public List<RoleCateV> getIdAndName();


	Page<RoleCateV> findBySearch(RoleCateV roleCate, Pageable pageable);

	/**
	 * 根据ID获取角色分类
	 * @param fdId
	 * @return
	 */
	public SysAuthCategory findOne(String fdId);
	

}
