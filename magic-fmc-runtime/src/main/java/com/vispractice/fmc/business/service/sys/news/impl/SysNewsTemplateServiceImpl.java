package com.vispractice.fmc.business.service.sys.news.impl;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import jxl.Sheet;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.base.constant.CommonConstant;
import com.vispractice.fmc.base.utils.StringUtil;
import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.base.exception.SavingVaildateException;
import com.vispractice.fmc.business.base.utils.ExcelUtils;
import com.vispractice.fmc.business.entity.sys.category.SysCategoryMain;
import com.vispractice.fmc.business.entity.sys.category.repository.SysCategoryMainRepository;
import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
import com.vispractice.fmc.business.entity.sys.news.SysNewsTemplate;
import com.vispractice.fmc.business.entity.sys.news.SysNewsTemplateEditor;
import com.vispractice.fmc.business.entity.sys.news.SysNewsTemplateReader;
import com.vispractice.fmc.business.entity.sys.news.repository.SysNewsMainRepository;
import com.vispractice.fmc.business.entity.sys.news.repository.SysNewsTemplateEditorRepository;
import com.vispractice.fmc.business.entity.sys.news.repository.SysNewsTemplateReaderRepository;
import com.vispractice.fmc.business.entity.sys.news.repository.SysNewsTemplateRepository;
import com.vispractice.fmc.business.entity.sys.news.repository.SysNewsTemplateVRepository;
import com.vispractice.fmc.business.entity.sys.news.view.SysNewsTemplateV;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfCommonTemplate;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfHistoryTemplate;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfTemplate;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfCommonTemplateRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfHistoryTemplateRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfTemplateRepository;
import com.vispractice.fmc.business.service.category.ICategoryMainService;
import com.vispractice.fmc.business.service.sys.news.ISysNewsTemplateService;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;

@Slf4j
@Service
@Transactional
public class SysNewsTemplateServiceImpl implements ISysNewsTemplateService {
	@Autowired
	private SysNewsTemplateRepository sysNewsTemplateRepository;

	@Autowired
	private SysNewsTemplateVRepository sysNewsTemplateVRepository;

	@Autowired
	private SysCategoryMainRepository sysCategoryMainRepository;
	
	@Autowired
	private ICategoryMainService categoryMainService;

	@Autowired
	private SysNewsTemplateEditorRepository sysNewsTemplateEditorRepository;

	@Autowired
	private SysNewsTemplateReaderRepository sysNewsTemplateReaderRepository;

	@Autowired
	private SysOrgElementRepository sysOrgElementRepository;

	@Autowired
	private SysWfTemplateRepository sysWfTemplateRepository;

	@Autowired
	private SysWfCommonTemplateRepository sysWfCommonTemplateRepository;

	@Autowired
	private SysNewsMainRepository sysNewsMainRepository;
	
	@Autowired
	private SysWfHistoryTemplateRepository sysWfHistoryTemplateRepository;
	
	@Autowired
	private ISysOrgElementService sysOrgElementService;
	
	// 实体管理器

	@Override
	public Page<SysNewsTemplateV> findBySearch(final SysNewsTemplateV SysNewsTemplateV,Pageable pageable) {
		Specification<SysNewsTemplateV> spec = new Specification<SysNewsTemplateV>() {
			@Override
			public Predicate toPredicate(Root<SysNewsTemplateV> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (null != SysNewsTemplateV) {
					if (StringUtils.isNotBlank(SysNewsTemplateV.getFdName()))
						list.add(cb.like(root.<String>get("fdName"),"%" + SysNewsTemplateV.getFdName() + "%"));

					if (StringUtils.isNotBlank(SysNewsTemplateV.getFdNumberPrefix()))
						list.add(cb.like(root.<String>get("fdNumberPrefix"),"%" + SysNewsTemplateV.getFdNumberPrefix() + "%"));
					if (StringUtils.isNotBlank(SysNewsTemplateV.getFdStatus()))
						list.add(cb.equal(root.get("fdStatus"), SysNewsTemplateV.getFdStatus()));

					if (StringUtils.isNotBlank(SysNewsTemplateV.getFdCategoryId())
							&& !SysNewsTemplateV.getFdCategoryId().equals(CommonConstant.NOT_AVAILABLE_FLAG)) {
						Path<String> category = root.get("fdCategoryId");

						list.add(cb.equal(category,SysNewsTemplateV.getFdCategoryId()));
					}
				}
				
				Predicate[] p = new Predicate[list.size()];

				return cb.and(list.toArray(p));
			}
		};
		
		return sysNewsTemplateVRepository.findAll(spec,pageable);
	}

	@Override
	public SysNewsTemplate findTemplate(SysNewsTemplateV sysNewsTemplateV) {
		SysNewsTemplate sysNewsTemplate = new SysNewsTemplate();
		SysNewsTemplate template = sysNewsTemplateRepository.findOne(sysNewsTemplateV.getFdId());
		sysNewsTemplate = (SysNewsTemplate) template.clone();

		// 获取分类名称
		sysNewsTemplate.setCategoryName(sysNewsTemplateV.getCategoryName());

		// 获取创建/修改人和时间
		String alterName = "";
		if (!StringUtil.isEmpty(sysNewsTemplate.getDocAlterId())) {
			SysOrgElement element = sysOrgElementRepository.findOne(sysNewsTemplate.getDocAlterId());
			alterName = null!=element?element.getFdName():"";
		}
		String creatorName = "";
		if (!StringUtil.isEmpty(sysNewsTemplate.getDocCreatorId())) {
			SysOrgElement element = sysOrgElementRepository.findOne(sysNewsTemplate.getDocCreatorId());
			creatorName = null!=element?element.getFdName():"";
		}
		sysNewsTemplate.setDocAlterName(alterName);
		sysNewsTemplate.setDocCreatorName(creatorName);

		// 设置流程模板定义方式
		List<SysWfTemplate> wfTempList = sysWfTemplateRepository.findByFdModelId(sysNewsTemplate.getFdId());
		if (wfTempList.size() > 0) {
			SysWfTemplate wfTemp = wfTempList.get(0);
			int type = wfTemp.getFdType();
			// 设置流程模板定义方式
			switch (type) {
			case SysWfTemplate.DEFAULT_TEMPLATE:
				sysNewsTemplate.setPattern("default");
				break;
			case SysWfTemplate.OTHER_TEMPLATE:
				sysNewsTemplate.setPattern("other");
				break;
			case SysWfTemplate.DEFINE_TEMPLATE:
				sysNewsTemplate.setPattern("custom");
				break;
			}
		}

		// 找到可修改者和可使用者列表
		List<SysNewsTemplateEditor> editorList = sysNewsTemplateEditorRepository.findByFdTemplateId(sysNewsTemplateV.getFdId());
		List<SysOrgElement> elememtEditorList = new ArrayList<SysOrgElement>();
		for (SysNewsTemplateEditor editor : editorList) {
			SysOrgElement editorElement = sysOrgElementRepository.findOne(editor.getAuthEditorId());
			if (null!=editorElement) {
				elememtEditorList.add(editorElement);
			}else {
				log.warn("id为"+editor.getAuthEditorId()+"在组织架构中不存在，可能已被删除。");
			}
			
		}
		sysNewsTemplate.setTemplateEditor(elememtEditorList);

		List<SysNewsTemplateReader> readerList = sysNewsTemplateReaderRepository.findByFdTemplateId(sysNewsTemplateV.getFdId());
		List<SysOrgElement> elememtReaderList = new ArrayList<SysOrgElement>();
		for (SysNewsTemplateReader reader : readerList) {
			SysOrgElement readerElement = sysOrgElementRepository.findOne(reader.getAuthReaderId());
			if (null!=readerElement) {
				elememtReaderList.add(readerElement);
			}else {
				log.warn("id为"+reader.getAuthReaderId()+"在组织架构中不存在，可能已被删除。");
			}
		}
		sysNewsTemplate.setTemplateReader(elememtReaderList);
		

		return sysNewsTemplate;
	}
	
	/**
	 * 保存流程模板
	 */
	@Override
	public void save(SysNewsTemplate sysNewsTemplate,SysWfTemplate wfTemp) {
		this.beforeSave(sysNewsTemplate);
		// 保存到SysNewsTemplate
		sysNewsTemplateRepository.save(sysNewsTemplate);
				
		//保存层级id
		if(StringUtils.isEmpty(sysNewsTemplate.getFdParentId()))
			sysNewsTemplate.setFdHierarchyId("x"+sysNewsTemplate.getFdId()+"x");
		else{
			SysNewsTemplate parentTemplate = sysNewsTemplateRepository.findOne(sysNewsTemplate.getFdParentId());
			if (null!=sysNewsTemplate) {
				sysNewsTemplate.setFdHierarchyId(parentTemplate.getFdHierarchyId()+sysNewsTemplate.getFdId() + "x");
			}
		}

		// 保存到工作流模板表
		if (null == wfTemp.getFdId()) {
			wfTemp.setFdModelId(sysNewsTemplate.getFdId());
			wfTemp.setFdModelName("com.ruiyi.kmss.sys.news.model.SysNewsTemplate");
			wfTemp.setFdKey("newsMainDoc");
			wfTemp.setFdVersion("0");
//			wfTemp.setFdCreateTime(new Date());
		}
		
		if (StringUtils.isNotEmpty(sysNewsTemplate.getPattern()) && sysNewsTemplate.getPattern().equals("default")) {
			// 获取默认模板
			SysWfCommonTemplate sysWfCommonTemplate = sysWfCommonTemplateRepository.findByFdIsDefault();
			if (null != sysWfCommonTemplate) {
				wfTemp.setFdFlowContent(sysWfCommonTemplate.getFdFlowContent());
			}
		}

		sysWfTemplateRepository.save(wfTemp);
		
		//保存到历史模板表
		SysWfHistoryTemplate historyTemplate = new SysWfHistoryTemplate();
		
		historyTemplate.setFdFlowContent(wfTemp.getFdFlowContent());
		historyTemplate.setFdModelName("com.ruiyi.kmss.sys.news.model.SysNewsTemplate");
		historyTemplate.setFdModelId(sysNewsTemplate.getFdId());
		historyTemplate.setFdKey("newsMainDoc");
		historyTemplate.setFdType(Long.valueOf(wfTemp.getFdType()));
		historyTemplate.setFdTemplateId(wfTemp.getFdId());
		historyTemplate.setFdTemplateType(1L);
		historyTemplate.setFdModifyTime(sysNewsTemplate.getDocAlterTime());
		historyTemplate.setFdModifyId(sysNewsTemplate.getDocAlterId());
		historyTemplate.setFdStatus(sysNewsTemplate.getFdStatus());
		historyTemplate.setFdVersion(wfTemp.getFdVersion());
		if (null!=sysNewsTemplate.getEnableTimeDate()) {
			historyTemplate.setFdEnableTime(sysNewsTemplate.getEnableTimeDate());
		}
		
		
		sysWfHistoryTemplateRepository.save(historyTemplate);
	}
	
	/**
	 * 保存模板前操作
	 * @Title: beforeSave 
	 * @param sysNewsTemplate
	 */
	private void beforeSave(SysNewsTemplate sysNewsTemplate) throws SavingVaildateException{
		
		String templateId = sysNewsTemplate.getFdId();
		List<SysNewsTemplate> sameNameTemplateList = sysNewsTemplateRepository.findByFdName(sysNewsTemplate.getFdName());
		if (sameNameTemplateList.size()>1) {
			throw new SavingVaildateException("流程模板名称重复，请修改！");
		}else if (sameNameTemplateList.size()==1&&!sameNameTemplateList.get(0).getFdId().equals(templateId)) {
			throw new SavingVaildateException("流程模板名称重复，请修改！");
		}
		
		List<SysNewsTemplate> samePrefixTemplateList = sysNewsTemplateRepository.findByFdNumberPrefix(sysNewsTemplate.getFdNumberPrefix());
		if (samePrefixTemplateList.size()>1) {
			throw new SavingVaildateException("流程模板前缀重复，请修改！");
		}else if (samePrefixTemplateList.size()==1&&!samePrefixTemplateList.get(0).getFdId().equals(templateId)) {
			throw new SavingVaildateException("流程模板前缀重复，请修改！");
		}
		
	}

	@Override
	public Boolean delete(String fdId) {
		List<SysWfTemplate> list = sysWfTemplateRepository.findByFdModelId(fdId);
		List<SysNewsMain> billList = sysNewsMainRepository.findByFdTemplateId(fdId);

		// 当有单据模板用到该模板时,禁止删除
		if (billList.size() > 0) {
			return false;
		}

		if (list.size() > 0) {
			sysWfTemplateRepository.delete(list.get(0));
		}

		sysNewsTemplateRepository.delete(fdId);

		return true;
	}

	@Override
	public void enable(String ids,String status) {
		List<String> idList = StringUtil.getStrList(ids);
		List<SysNewsTemplate> sysNewsTemplateList = sysNewsTemplateRepository.findAll(idList);

		for (SysNewsTemplate sysNewsTemplate : sysNewsTemplateList) {
			sysNewsTemplate.setFdStatus(status);
		}
		
		sysNewsTemplateRepository.save(sysNewsTemplateList);
	}
	
	@Override
	public List<SysNewsTemplate> exportTemplate(String ids) {
		List<String> idList = StringUtil.getStrList(ids);
		List<SysNewsTemplate> sysNewsTemplates = sysNewsTemplateRepository.findAll(idList);
		for (SysNewsTemplate sysNewsTemplate : sysNewsTemplates) {
			SysCategoryMain sysCategoryMain = sysCategoryMainRepository.findOne(sysNewsTemplate.getFdCategoryId());
			sysNewsTemplate.setDocCategory(sysCategoryMain);
			
			List<SysWfTemplate> sysWfTemplates = sysWfTemplateRepository.findByFdModelId(sysNewsTemplate.getFdId());
			sysNewsTemplate.setSysWfTemplates(sysWfTemplates);
		}
		
		return sysNewsTemplates;
	}
	
	public boolean importTemplate(InputStream is) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			Sheet[] sheets = ExcelUtils.getSheets(is);
			Sheet sheet1 = sheets[0];
			Sheet sheet2 = sheets[1];
			Sheet sheet3 = sheets[2];
			
			for (int i = 1; i < sheet1.getRows(); i++) {
				SysCategoryMain categoryMain = new SysCategoryMain();
				int index = 0;
				String fdId = ExcelUtils.getCellValue(sheet1,i,index++);
				if (fdId == null || "".equals(fdId.trim())) {
					continue;
				}
				categoryMain.setFdId(fdId);
				categoryMain.setFdName(ExcelUtils.getCellValue(sheet1,i,index++));
				SysCategoryMain scm = sysCategoryMainRepository.findByFdName(categoryMain.getFdName());
				if(StringUtils.isNotEmpty(fdId) && scm == null){
					categoryMain.setFdId(null);
				}else{
					categoryMain.setFdId(scm.getFdId());
				}
				categoryMain.setFdHierarchyId(ExcelUtils.getCellValue(sheet1,i,index++));
				
				String fdOrder = ExcelUtils.getCellValue(sheet1,i,index++);
				if (fdOrder != null && !"".equals(fdOrder.trim())) {
					categoryMain.setFdOrder(Long.parseLong(fdOrder));
				}
				categoryMain.setFdModelName(ExcelUtils.getCellValue(sheet1,i,index++));
				
				String createDate = ExcelUtils.getCellValue(sheet1,i,index++);
				if (createDate != null && !"".equals(createDate.trim())) {
					categoryMain.setDocCreateTime(new java.util.Date(sdf.parse(createDate).getTime()));
				}
				
				String alterDate = ExcelUtils.getCellValue(sheet1,i,index++);
				if (alterDate != null && !"".equals(alterDate.trim())) {
					categoryMain.setDocAlterTime(new java.util.Date(sdf.parse(alterDate).getTime()));
				}
				
				categoryMain.setFdIsinheritMaintainer(Integer.parseInt(ExcelUtils.getCellValue(sheet1,i,index++)));
				categoryMain.setFdIsinheritUser(Integer.parseInt(ExcelUtils.getCellValue(sheet1,i,index++)));
				categoryMain.setAuthReaderFlag(Integer.parseInt(ExcelUtils.getCellValue(sheet1,i,index++)));
				
				String creatorId = ExcelUtils.getCellValue(sheet1,i,index++);
				categoryMain.setDocCreateId(creatorId);
				String alterorId = ExcelUtils.getCellValue(sheet1,i,index++);
				categoryMain.setDocAlterorId(alterorId);
				String parentId = ExcelUtils.getCellValue(sheet1,i,index++);
				if (parentId != null && !"".equals(parentId.trim())) {
					SysCategoryMain parentCategory = sysCategoryMainRepository.findOne(parentId.trim());
					if (parentCategory != null) {
						categoryMain.setFdParentId(parentId);
					} else {
						categoryMain.setFdParentId("");
					}
				}
				categoryMain = categoryMainService.save(categoryMain,creatorId);
				
				for (int j = 1;j < sheet2.getRows();j++) {
					index=0;
					SysNewsTemplate template = new SysNewsTemplate();
					template.setFdId(ExcelUtils.getCellValue(sheet2,j,index++));
					template.setFdName(ExcelUtils.getCellValue(sheet2,j,index++));
					List<SysNewsTemplate> snt = sysNewsTemplateRepository.findByFdName(template.getFdName());
					if (StringUtils.isNoneEmpty(template.getFdId()) && snt.size() == 0) {
						template.setFdId(null);
					} else {
						template.setFdId(snt.get(0).getFdId());
					}
					
					createDate = ExcelUtils.getCellValue(sheet2,j,index++);
					if (createDate != null && !"".equals(createDate.trim())) {
						template.setDocCreateTime(sdf.parse(createDate));
					}
					String fdImportance = ExcelUtils.getCellValue(sheet2,j,index++);
					if (fdImportance != null && !"".equals(fdImportance.trim())) {
						template.setFdImportance(Long.parseLong(fdImportance));
					}

					template.setFdNumberPrefix(ExcelUtils.getCellValue(sheet2,j,index++));
					template.setDocContent(ExcelUtils.getCellValue(sheet2,j,index++));
					template.setFdUseForm(ExcelUtils.getCellValue(sheet2,j,index++));
					template.setFdAppLink( ExcelUtils.getCellValue(sheet2,j,index++));
					String readFlag = ExcelUtils.getCellValue(sheet2,j,index++);
					if (readFlag != null && !"".equals(readFlag.trim())) {
						template.setAuthReaderFlag(Long.parseLong(readFlag));
					}
					String downLoad = ExcelUtils.getCellValue(sheet2,j,index++);
					if (downLoad != null && !"".equals(downLoad.trim())) {
						template.setAuthTmpAttNodownload(Long.parseLong(downLoad));
					}
					
					template.setAuthTmpAttNocopy(ExcelUtils.getCellValue(sheet2,j,index++));
					template.setFdStatus(ExcelUtils.getCellValue(sheet2,j,index++));
					String noPrint = ExcelUtils.getCellValue(sheet2,j,index++);
					if (noPrint != null && !"".equals(noPrint.trim())) {
						template.setAuthTmpAttNoprint(Long.parseLong(noPrint));
					}
					fdOrder = ExcelUtils.getCellValue(sheet2,j,index++);
					if (fdOrder != null && !"".equals(fdOrder.trim())) {
						template.setFdOrder(Long.parseLong(fdOrder));
					}
					template.setFdStyle(ExcelUtils.getCellValue(sheet2,j,index++));
					template.setFdContentType(ExcelUtils.getCellValue(sheet2,j,index++));
					ExcelUtils.getCellValue(sheet2,j,index++);
					template.setFdCategoryId(categoryMain.getFdId());
					template.setDocCreatorId(ExcelUtils.getCellValue(sheet2,j,index++));
					template.setAuthAreaId(ExcelUtils.getCellValue(sheet2,j,index++));
					template.setBusiSysId(ExcelUtils.getCellValue(sheet2,j,index++));
					String readerFlag = ExcelUtils.getCellValue(sheet2,j,index++);
					if (readerFlag != null && !"".equals(readerFlag.trim())) {
						template.setAuthNotReaderFlag(Long.parseLong(readerFlag));
					}
					template.setFdHierarchyId(ExcelUtils.getCellValue(sheet2,j,index++));
					template.setDocAlterId(ExcelUtils.getCellValue(sheet2,j,index++));
					alterDate = ExcelUtils.getCellValue(sheet2,j,index++);
					if (alterDate != null && !"".equals(alterDate.trim())) {
						template.setDocAlterTime(sdf.parse(alterDate));
					}
					String inheritMaintainer = ExcelUtils.getCellValue(sheet2,j,index++);
					if (inheritMaintainer != null && !"".equals(inheritMaintainer.trim())) {
						template.setFdIsinheritMaintainer(Long.parseLong(inheritMaintainer)); 
					}
					String inheritUser = ExcelUtils.getCellValue(sheet2,j,index++);
					if (inheritUser != null && !"".equals(inheritUser.trim())) {
						template.setFdIsinheritUser(Long.parseLong(inheritUser)); 
					}
					String fdChageAtt = ExcelUtils.getCellValue(sheet2,j,index++);
					if (fdChageAtt != null && !"".equals(fdChageAtt.trim())) {
						template.setFdChageAtt(Long.parseLong(fdChageAtt));
					}
					template.setFdParentId(ExcelUtils.getCellValue(sheet2,j,index++));

					index=0;
					SysWfTemplate wfTemplate = new SysWfTemplate();
					wfTemplate.setFdId(ExcelUtils.getCellValue(sheet3,j,index++));
					if(StringUtils.isEmpty(template.getFdId())){
						wfTemplate.setFdId(null);
					}else{
						SysWfTemplate swt = sysWfTemplateRepository.findByFdModelId(template.getFdId()).get(0);
						wfTemplate.setFdId(swt.getFdId());
					}
					
					wfTemplate.setFdFlowContent(ExcelUtils.getCellValue(sheet3,j,index++));
					wfTemplate.setFdModelName(ExcelUtils.getCellValue(sheet3,j,index++));
					ExcelUtils.getCellValue(sheet3,j,index++);
					wfTemplate.setFdModelId(template.getFdId());
					wfTemplate.setFdKey(ExcelUtils.getCellValue(sheet3,j,index++));
					String nodeNum = ExcelUtils.getCellValue(sheet3,j,index++);
					if (nodeNum != null && !"".equals(nodeNum.trim())) {
						wfTemplate.setFdNodeNum(Integer.parseInt(nodeNum));
					}
					String fdType = ExcelUtils.getCellValue(sheet3,j,index++);
					if (fdType != null && !"".equals(fdType.trim())) {
						wfTemplate.setFdType(Integer.parseInt(fdType));
					}
					String wfCreateDate = ExcelUtils.getCellValue(sheet3,j,index++);
					if (wfCreateDate != null && !"".equals(wfCreateDate.trim())) {
						wfTemplate.setFdCreateTime(new java.util.Date(sdf.parse(wfCreateDate).getTime()));
					}
					wfTemplate.setFdVersion(ExcelUtils.getCellValue(sheet3,j,index++));
					wfTemplate.setFdCommonId(ExcelUtils.getCellValue(sheet3,j,index++));
					wfTemplate.setFdCreatorId(ExcelUtils.getCellValue(sheet3,j,index++));
					
					this.save(template,wfTemplate);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public SysNewsTemplateV getDraftInfo(String fdId,String currUserId) {
		SysNewsTemplateV templateV = sysNewsTemplateVRepository.findOne(fdId);
		
		String fdProcessInfoXml = this.getProcessInfoXml();
		templateV.setExtendParam1(fdProcessInfoXml);
		String fdHandlerRoleInfoIds = this.getCurrentUserRoles(currUserId)[0];
		templateV.setExtendParam2(fdHandlerRoleInfoIds);

		return templateV;
	}
	
	/**
	 * 
	 * 生成起草人的processInfoXml
	 * @return
	 */
	private String getProcessInfoXml() {
		StringBuffer sb = new StringBuffer();
		sb.append("	<processor isArray=\"false\" CHILDRENISARRAY=\"true\" identifyKey=\""
				+ OAConstant.IDENTITY_PROCESSOR + "\" identifyText=\"" + "处理人"
				+ "\">\n");
		sb.append("		<info \n");
		sb.append("			nodeId=\"" + "N2" + "\"\n");

		sb.append("			toRefuseThisNodeId=\"" + ""
				+ "\"\n");
		sb.append("			toRefuseThisHandlerName=\"" + ""
				+ "\"\n");
		sb.append("			reToHandlerId=\"" + "" + "\"\n");
		sb.append("			currHandlerOffset=\"" + "0" + "\"\n");
		sb.append("		 />\n");
		sb.append("</processor>");
		
		return sb.toString();
	}
	
	
	/**
	 * 取得当前用户的信息（包括岗位）
	 * @return String[2] String[0]为Id字符串,String[1]为Name字符串
	 */
	@SuppressWarnings({"rawtypes"})
	private String[] getCurrentUserRoles(String fdUserId) {
		SysOrgElement currentUser = sysOrgElementRepository.findOne(fdUserId);
		String[] rtnValue = new String[2];
		rtnValue[0] = currentUser.getFdId();
		rtnValue[1] = currentUser.getFdName();

		// 获取岗位
		List currentUserPost = sysOrgElementService.findPosts(fdUserId);
		if (currentUserPost != null && currentUserPost.size() > 0) {
			rtnValue[0] += ";";
			rtnValue[1] += ";";
			for (int i = 0; i < currentUserPost.size(); i++) {
				SysOrgElement postElement = (SysOrgElement) currentUserPost.get(i);
				if (i == currentUserPost.size() - 1) {
					rtnValue[0] += postElement.getFdId();
					rtnValue[1] += postElement.getFdName();
				} else {
					rtnValue[0] += postElement.getFdId() + ";";
					rtnValue[1] += postElement.getFdName() + ";";
				}
			}
		}
		
		// 去除结尾的
		if (rtnValue[0].endsWith(";")) {
			rtnValue[0].substring(0, rtnValue[0].length() - 1);
			rtnValue[1].substring(0, rtnValue[1].length() - 1);
		}

		return rtnValue;
	}
	
	@Override
	public List<SysNewsTemplate> findAllByAncestorsCateId(String cateId){
		List<String> cateIds = sysCategoryMainRepository.findIdsByFdId(cateId);
		
		return sysNewsTemplateRepository.findAllByCateIds(cateIds);
	}

	
	@Override
	public SysNewsTemplate findTemplateByFdId(String fdId) {
		SysNewsTemplate sysNewsTemplate = sysNewsTemplateRepository.findOne(fdId);
		
		return sysNewsTemplate;
	}
}
