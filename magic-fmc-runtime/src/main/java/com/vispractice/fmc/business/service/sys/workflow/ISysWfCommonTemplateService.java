package com.vispractice.fmc.business.service.sys.workflow;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfCommonTemplate;
import com.vispractice.fmc.business.entity.sys.workflow.view.SysWfCommonTemplateV;

public interface ISysWfCommonTemplateService {

	public Page<SysWfCommonTemplateV> findAll(SysWfCommonTemplateV sysWfCommonTemplateV, Pageable pageable);

	public Page<SysWfCommonTemplateV> findBySearch(final SysWfCommonTemplateV sysWfCommonTemplateV, Pageable pageable);

	/**
	 * 保存
	 */
	public void save(SysWfCommonTemplate sysWfCommonTemplate);

	/**
	 * 查询
	 */
	public SysWfCommonTemplate get(String id);

	/**
	 * 删除
	 */
	public void deleteByFdId(String fdId);

	/**
	 * 
	 */
	public List<SysWfCommonTemplate> findRoot();
	
	/**
	 *  验证：默认的通用流程模板有且只能有一个
	 */
	public int findByFdIsDefault(String fdIsDefault,String fdId);

	public String getContent(String fdId) throws Exception;

	/**
	 * 获取默认模板
	 * @Title: getDefaultTemplate 
	 * @return 不存在默认模板则返回null
	 */
	public SysWfCommonTemplate getDefaultTemplate();
}
