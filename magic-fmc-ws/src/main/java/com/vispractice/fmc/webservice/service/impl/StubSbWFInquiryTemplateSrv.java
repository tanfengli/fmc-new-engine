package com.vispractice.fmc.webservice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.category.SysCategoryMain;
import com.vispractice.fmc.business.entity.sys.news.SysNewsTemplate;
import com.vispractice.fmc.csb.sb_wf_inquirytemplatesrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquirytemplatesrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_inquirytemplatesrv.SBWFInquiryTemplateSrvResponse;
import com.vispractice.fmc.csb.sb_wf_inquirytemplatesrv.SBWFTempOrCateCollection;
import com.vispractice.fmc.csb.sb_wf_inquirytemplatesrv.SBWFTempOrCateItem;
import com.vispractice.fmc.webservice.service.SbWFInquiryTemplateSrv;
import com.vispractice.fmc.webservice.util.JSonUtils;
import com.vispractice.fmc.webservice.util.WSMessageResult;

/**
 * 编  号：
 * 名  称：StubSbWFInquiryTemplateSrv
 * 描  述：StubSbWFInquiryTemplateSrv.java
 * 完成日期：2017年6月23日 下午2:49:22
 * 编码作者：ZhouYanyao
 */
@Slf4j
@Service
public class StubSbWFInquiryTemplateSrv extends AbstractStubParentSrv implements SbWFInquiryTemplateSrv {
	/**
	 * 分类
	 */
	private final static int TYPE_CATE = 1;
	/**
	 * 模板
	 */
	private final static int TYPE_TPL = 2;
	
	@Override
	public ProcessResponse inquiryTemplate(ProcessRequest processRequest) {
		Date fdStartTime = new Date(System.currentTimeMillis());
		log.info(resourceUtil.getLocaleMessage("ws.enter.info",language) + JSonUtils.jsonToString(processRequest.getRequest()));
		
		//返回消息
		ProcessResponse response = new ProcessResponse();
		//返回结果
		SBWFInquiryTemplateSrvResponse result = new SBWFInquiryTemplateSrvResponse();
		String message = "";
		
		try {
			//验证结果信息
			msgHeaderSrv.validateSysData(processRequest.getRequest().getMsgHeader(),"SbWFInquiryTemplateSrv");
		
			Map<String,Object> map = processFacade.sbWFInquiryTemplateSrv(processRequest.getRequest().getWFCATEID(),processRequest.getRequest().isWFQUERYALL());
			SBWFTempOrCateCollection coll = setColl(map);
			
			result.setSBWFTEMPORCATECOLLECTION(coll);
			result.setSERVICEFLAG(WSMessageResult.FLAG_Y);
			message = resourceUtil.getLocaleMessage("ws.interface.inquiryTemplate.OK",language);
			result.setSERVICEMESSAGE(message);
			log.info(message + JSonUtils.jsonToString(coll));
		} catch (Exception e) {
			log.error(e.getMessage());
			result.setSERVICEFLAG(WSMessageResult.FLAG_N);
			result.setSERVICEMESSAGE(e.getMessage());
		}
		response.setReturn(result);
		
		//保存日志信息
		Date fdEndTime = new Date(System.currentTimeMillis());
		this.saveWebServiceLog("SbWFInquiryTemplateSrv",StubSbWFInquiryTemplateSrv.class.getName(),
				processRequest.getRequest().getMsgHeader(),fdStartTime,fdEndTime,
				result.getSERVICEFLAG(),result.getSERVICEMESSAGE());
		
		return response;
	}
	
	/**
	 * 设置分类或模板集合
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	private SBWFTempOrCateCollection setColl(Object value){
		Map val = (Map)value;
		SBWFTempOrCateCollection coll = new SBWFTempOrCateCollection();
		
		List<SysCategoryMain> cateList = (List<SysCategoryMain>) val.get(String.valueOf(TYPE_CATE));
		List<SysNewsTemplate> temList = (List<SysNewsTemplate>) val.get(String.valueOf(TYPE_TPL));
		List<SBWFTempOrCateItem> itemList = getTempOrCateItems(cateList, temList);
		coll.getSBWFTEMPORCATEITEM().addAll(itemList);
		
		return coll;
	}

	/**
	 * 把SysCategoryMain列表和SysNewsTemplate列表转换为对应的SBWFTempOrCateItem列表
	 */
	private List<SBWFTempOrCateItem> getTempOrCateItems(List<SysCategoryMain> cates,List<SysNewsTemplate> newsTemplates) {
		List<SBWFTempOrCateItem> tempOrCateItems = new ArrayList<>();
		SBWFTempOrCateItem tempOrCateItem = null;
		if (cates != null && !cates.isEmpty()) {
			for (SysCategoryMain cate : cates) {
				tempOrCateItem = getTempOrCateItem(cate);
				tempOrCateItems.add(tempOrCateItem);
			}
		}
		
		if (newsTemplates != null && !newsTemplates.isEmpty()) {
			for (SysNewsTemplate template : newsTemplates) {
				tempOrCateItem = getTempOrCateItem(template);
				tempOrCateItems.add(tempOrCateItem);
			}
		}
		
		return tempOrCateItems;
	}

	/**
	 * 把SysNewsTemplate转化为对应的SBWFTempOrCateItem对象
	 */
	private SBWFTempOrCateItem getTempOrCateItem(SysNewsTemplate template) {
		SBWFTempOrCateItem tempOrCateItem;
		tempOrCateItem = new SBWFTempOrCateItem();
		tempOrCateItem.setId(template.getFdId());
		tempOrCateItem.setName(template.getFdName());
		tempOrCateItem.setType(TYPE_TPL);
		tempOrCateItem.setChildren(new SBWFTempOrCateCollection());
		
		return tempOrCateItem;
	}

	/**
	 * 把SysCategoryMain转化为对应的SBWFTempOrCateItem对象
	 */
	private SBWFTempOrCateItem getTempOrCateItem(SysCategoryMain cate) {
		SBWFTempOrCateItem tempOrCateItem;
		tempOrCateItem = new SBWFTempOrCateItem();
		tempOrCateItem.setId(cate.getFdId());
		tempOrCateItem.setName(cate.getFdName());
		tempOrCateItem.setType(TYPE_CATE);
		if (!cate.getCategoryChildren().isEmpty()) { 
			// 分类下还有子分类
			for (SysCategoryMain child : cate.getCategoryChildren()) {
				this.getChildren(tempOrCateItem).getSBWFTEMPORCATEITEM().add(this.getTempOrCateItem(child));
			}
		}
		
		if (!cate.getAssociateModels().isEmpty()) { 
			// 分类下直接挂了模板
			for (SysNewsTemplate model : cate.getAssociateModels()) {
				this.getChildren(tempOrCateItem).getSBWFTEMPORCATEITEM().add(this.getTempOrCateItem((SysNewsTemplate)model));
			}
		}
		
		return tempOrCateItem;
	}

	/**
	 * 获取tempOrCateItem的children熟悉,规避null问题
	 * @return
	 */
	private SBWFTempOrCateCollection getChildren(SBWFTempOrCateItem tempOrCateItem) {
		if (tempOrCateItem.getChildren() == null) {
			tempOrCateItem.setChildren(new SBWFTempOrCateCollection());
		}
		
		return tempOrCateItem.getChildren();
	}
}
