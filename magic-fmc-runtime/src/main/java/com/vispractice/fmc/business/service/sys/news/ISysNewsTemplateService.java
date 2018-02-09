package com.vispractice.fmc.business.service.sys.news;

import java.io.InputStream;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.news.SysNewsTemplate;
import com.vispractice.fmc.business.entity.sys.news.view.SysNewsTemplateV;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfTemplate;

public interface ISysNewsTemplateService {
	/**
	 * 实现流程: 流程模板视图分页查询<br/>
	 * @param SysNewsTemplateV 模板视图bean
	 * @param pageable 分页参数
	 * @return 
	 */
	public Page<SysNewsTemplateV> findBySearch(SysNewsTemplateV SysNewsTemplateV, Pageable pageable);

	/**
	 * 实现流程: 获取模板详情<br/>
	 * @param sysNewsTemplateV
	 * @return
	 */
	public SysNewsTemplate findTemplate(SysNewsTemplateV sysNewsTemplateV);

	/**
	 * 根据标识获取流程模板
	 * @param fdId
	 * @return
	 */
	public SysNewsTemplate findTemplateByFdId(String fdId);
	
	/**
	 * 实现流程: 删除模板<br/>
	 * @param fdId 模板id
	 * @return 成功返回true
	 */
	public Boolean delete(String fdId);

	/**
	 * 实现流程: 获取起草单据信息
	 * @Title: findTemplateV 
	 * @param fdId 模板ID
	 * @return 模板视图bean
	 */
	public SysNewsTemplateV getDraftInfo(String fdId,String currUserId);

	/**
	 * 实现流程: 模板保存<br/>
	 * @param sysNewsTemplate SysNewsTemplate实体
	 * @param wfTemp SysWfTemplate实体
	 */
	public void save(SysNewsTemplate sysNewsTemplate,SysWfTemplate wfTemp);

	/**
	 * 实现流程: 启用/禁用流程模板<br/>
	 * @param ids 模板id字符串，格式 id1;id2
	 * @param status 启用为1，禁用为0
	 */
	public void enable(String ids,String status);
	
	/**
	 * 导出模板
	 * @param ids
	 * @return
	 */
	public List<SysNewsTemplate> exportTemplate(String ids);
	
	/**
	 * 导入模板
	 * @param ids
	 * @return
	 */
	public boolean importTemplate(InputStream is);

	/**
	 * 根据分类ID获取该分类及其子孙类的所有模板
	 * @param cateId
	 * @return
	 */
	public List<SysNewsTemplate> findAllByAncestorsCateId(String cateId);
}
