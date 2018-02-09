package com.vispractice.fmc.webservice;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * 接口服务配置信息
 * @author 
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	/**
	 * 接口运行环境注册
	 * @param applicationContext
	 * @return
	 */
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		
		return new ServletRegistrationBean(servlet, "/ws/*");
	}
	
	/**
	 * 查询待办服务
	 * @param sbWFInquiryTaskSrvSchema
	 * @return
	 */
	@Bean(name = "sbWFInquiryTaskSrv")
	public DefaultWsdl11Definition sbWFInquiryTaskSrv(XsdSchema sbWFInquiryTaskSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFInquiryTaskSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFInquiryTaskSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_InquiryTaskSrv");
		wsdl11Definition.setSchema(sbWFInquiryTaskSrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFInquiryTaskSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFInquiryTaskSrv/sbWFInquiryTaskSrv.xsd"));
	}
	
	/**
	 * 流程启动服务
	 * @param sbWFStartProcessSrvSchema
	 * @return
	 */
	@Bean(name = "sbWFStartProcessSrv")
	public DefaultWsdl11Definition sbWFStartProcessSrv(XsdSchema sbWFStartProcessSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFStartProcessSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFStartProcessSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_StartProcessSrv");
		wsdl11Definition.setSchema(sbWFStartProcessSrvSchema);
	
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFStartProcessSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFStartProcessSrv/sbWFStartProcessSrv.xsd"));
	}
	
	/**
	 * 流程作废服务
	 * @param sbWFAbandonSrvSchema
	 * @return
	 */
	@Bean(name = "sbWFAbandonSrv")
	public DefaultWsdl11Definition sbWFAbandonSrv(XsdSchema sbWFAbandonSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFAbandonSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFAbandonSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_AbandonSrv");
		wsdl11Definition.setSchema(sbWFAbandonSrvSchema);
	
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFAbandonSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFAbandonSrv/sbWFAbandonSrv.xsd"));
	}
	
	/**
	 * 流程审批服务
	 * @param sbWFApprovalSrvSchema
	 * @return
	 */
	@Bean(name = "sbWFApprovalSrv")
	public DefaultWsdl11Definition sbWFApprovalSrv(XsdSchema sbWFApprovalSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFApprovalSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFApprovalSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_ApprovalSrv");
		wsdl11Definition.setSchema(sbWFApprovalSrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFApprovalSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFApprovalSrv/sbWFApprovalSrv.xsd"));
	}
	
	/**
	 * 流程节点审批服务
	 * @param sbWFNodeApprovalSrv
	 * @return
	 */
	@Bean(name = "sbWFNodeApprovalSrv")
	public DefaultWsdl11Definition sbWFNodeApprovalSrv(XsdSchema sbWFNodeApprovalSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFNodeApprovalSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFNodeApprovalSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_Node_ApprovalSrv");
		wsdl11Definition.setSchema(sbWFNodeApprovalSrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFNodeApprovalSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFNodeApprovalSrv/sbWFNodeApprovalSrv.xsd"));
	}
	
	/**
	 * 流程实例审批服务
	 * @param sbWFInstanceApprovalSrv
	 * @return
	 */
	@Bean(name = "sbWFInstanceApprovalSrv")
	public DefaultWsdl11Definition sbWFInstanceApprovalSrv(XsdSchema sbWFInstanceApprovalSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFInstanceApprovalSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFInstanceApprovalSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_Instance_ApprovalSrv");
		wsdl11Definition.setSchema(sbWFInstanceApprovalSrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFInstanceApprovalSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFInstanceApprovalSrv/sbWFInstanceApprovalSrv.xsd"));
	}
	
	/**
	 * 组织导入服务
	 * @param sbWFOrgImportSrvSchema
	 * @return
	 */
	@Bean(name = "sbWFOrgImportSrv")
	public DefaultWsdl11Definition sbWFOrgImportSrv(XsdSchema sbWFOrgImportSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFOrgImportSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFOrgImportSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_OrgImportSrv");
		wsdl11Definition.setSchema(sbWFOrgImportSrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFOrgImportSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFOrgImportSrv/sbWFOrgImportSrv.xsd"));
	}
	
	/**
	 * 岗位导入服务
	 * @param sbWFPostImportSrvSchema
	 * @return
	 */
	@Bean(name = "sbWFPostImportSrv")
	public DefaultWsdl11Definition sbWFPostImportSrv(XsdSchema sbWFPostImportSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFPostImportSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFPostImportSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_PostImportSrv");
		wsdl11Definition.setSchema(sbWFPostImportSrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFPostImportSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFPostImportSrv/sbWFPostImportSrv.xsd"));
	}
	
	/**
	 * 人员导入服务
	 * @param sbWFPersonImportSrvSchema
	 * @return
	 */
	@Bean(name = "sbWFPersonImportSrv")
	public DefaultWsdl11Definition sbWFPersonImportSrv(XsdSchema sbWFPersonImportSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFPersonImportSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFPersonImportSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_PersonImportSrv");
		wsdl11Definition.setSchema(sbWFPersonImportSrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFPersonImportSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFPersonImportSrv/sbWFPersonImportSrv.xsd"));
	}
	
	/**
	 * 查询流程状态服务
	 * @param sbWFInquiryProcessStatusSrvSchema
	 * @return
	 */
	@Bean(name = "sbWFInquiryProcessStatusSrv")
	public DefaultWsdl11Definition sbWFInquiryProcessStatusSrv(XsdSchema sbWFInquiryProcessStatusSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFInquiryProcessStatusSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFInquiryProcessStatusSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_InquiryProcessStatusSrv");
		wsdl11Definition.setSchema(sbWFInquiryProcessStatusSrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFInquiryProcessStatusSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFInquiryProcessStatusSrv/sbWFInquiryProcessStatusSrv.xsd"));
	}
	
	/**
	 * 查询已办服务
	 * @param sbWFInquiryTaskDoneSrvSchema
	 * @return
	 */
	@Bean(name = "sbWFInquiryTaskDoneSrv")
	public DefaultWsdl11Definition sbWFInquiryTaskDoneSrv(XsdSchema sbWFInquiryTaskDoneSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFInquiryTaskDoneSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFInquiryTaskDoneSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_InquiryTaskDoneSrv");
		wsdl11Definition.setSchema(sbWFInquiryTaskDoneSrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFInquiryTaskDoneSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFInquiryTaskDoneSrv/sbWFInquiryTaskDoneSrv.xsd"));
	}
	
	/**
	 * 查询我的申请服务
	 * @param sbWFInquiryApplySrvSchema
	 * @return
	 */
	@Bean(name = "sbWFInquiryApplySrv")
	public DefaultWsdl11Definition sbWFInquiryApplySrvSrv(XsdSchema sbWFInquiryApplySrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFInquiryApplySrv");
		wsdl11Definition.setLocationUri("/ws/sbWFInquiryApplySrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_InquiryApplySrv");
		wsdl11Definition.setSchema(sbWFInquiryApplySrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFInquiryApplySrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFInquiryApplySrv/sbWFInquiryApplySrv.xsd"));
	}
	
	/**
	 * 查询审批历史服务
	 * @param sbWFInquiryProcessHistorySrvSchema
	 * @return
	 */
	@Bean(name = "sbWFInquiryProcessHistorySrv")
	public DefaultWsdl11Definition sbWFInquiryProcessHistorySrv(XsdSchema sbWFInquiryProcessHistorySrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFInquiryProcessHistorySrv");
		wsdl11Definition.setLocationUri("/ws/sbWFInquiryProcessHistorySrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_InquiryProcessHistorySrv");
		wsdl11Definition.setSchema(sbWFInquiryProcessHistorySrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFInquiryProcessHistorySrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFInquiryProcessHistorySrv/sbWFInquiryProcessHistorySrv.xsd"));
	}
	
	/**
	 * 查询驳回节点服务
	 * @param sbWFInquiryRejectNodeSrvSchema
	 * @return
	 */
	@Bean(name = "sbWFInquiryRejectNodeSrv")
	public DefaultWsdl11Definition sbWFInquiryRejectNodeSrv(XsdSchema sbWFInquiryRejectNodeSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFInquiryRejectNodeSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFInquiryRejectNodeSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_InquiryProcessHistorySrv");
		wsdl11Definition.setSchema(sbWFInquiryRejectNodeSrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFInquiryRejectNodeSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFInquiryRejectNodeSrv/sbWFInquiryRejectNodeSrv.xsd"));
	}
	
	/**
	 * 查询流程日志服务
	 * @param sbWFInquiryProcessLogSrvSchema
	 * @return
	 */
	@Bean(name = "sbWFInquiryProcessLogSrv")
	public DefaultWsdl11Definition sbWFInquiryProcessLogSrv(XsdSchema sbWFInquiryProcessLogSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFInquiryProcessLogSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFInquiryProcessLogSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_InquiryProcessLogSrv");
		wsdl11Definition.setSchema(sbWFInquiryProcessLogSrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFInquiryProcessLogSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFInquiryProcessLogSrv/sbWFInquiryProcessLogSrv.xsd"));
	}
	
	/**
	 * 流程授权服务
	 * @param sbWFAuthorizeSrvSchema
	 * @return
	 */
	@Bean(name = "sbWFAuthorizeSrv")
	public DefaultWsdl11Definition sbWFAuthorizeSrv(XsdSchema sbWFAuthorizeSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFAuthorizeSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFAuthorizeSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_AuthorizeSrv");
		wsdl11Definition.setSchema(sbWFAuthorizeSrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFAuthorizeSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFAuthorizeSrv/sbWFAuthorizeSrv.xsd"));
	}

	
	/**
	 * 常用审批意见查询服务
	 * @Title: sbWFInquiryCommonOpinionSrv 
	 * @param sbWFInquiryCommonOpinionSrvSchema
	 * @return
	 */
	@Bean(name = "sbWFInquiryCommonOpinionSrv")
	public DefaultWsdl11Definition sbWFInquiryCommonOpinionSrv(XsdSchema sbWFInquiryCommonOpinionSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBInquiryCommonOpinionSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFInquiryCommonOpinionSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_InquiryCommonOpinionSrv");
		wsdl11Definition.setSchema(sbWFInquiryCommonOpinionSrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFInquiryCommonOpinionSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFInquiryCommonOpinionSrv/sbWFInquiryCommonOpinionSrv.xsd"));
	}
	
	/**
	 * 流程模板查询服务
	 * @Title: sbWFInquiryTemplateSrv 
	 * @param sbWFInquiryTemplateSrvSchema
	 * @return
	 */
	@Bean(name = "sbWFInquiryTemplateSrv")
	public DefaultWsdl11Definition sbWFInquiryTemplateSrv(XsdSchema sbWFInquiryTemplateSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFInquiryTemplateSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFInquiryTemplateSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_InquiryTemplateSrvSchema");
		wsdl11Definition.setSchema(sbWFInquiryTemplateSrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFInquiryTemplateSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFInquiryTemplateSrv/sbWFInquiryTemplateSrv.xsd"));
	}
	
	/**
	 * 流程节点信息查询服务
	 * @param sbWFGetNodesSrvSchema
	 * @return
	 */
	@Bean(name = "sbWFGetNodesSrv")
	public DefaultWsdl11Definition sbWFGetNodesSrv(XsdSchema sbWFGetNodesSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFGetNodesSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFGetNodesSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_GetNodesSrv");
		wsdl11Definition.setSchema(sbWFGetNodesSrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFGetNodesSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFGetNodesSrv/sbWFGetNodesSrv.xsd"));
	}
	
	/**
	 * 流程待阅查询服务
	 * @param sbWFReadStaySrvSchema
	 * @return
	 */
	@Bean(name = "sbWFInquiryReadSrv")
	public DefaultWsdl11Definition sbWFInquiryReadSrv(XsdSchema sbWFInquiryReadSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFInquiryReadSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFInquiryReadSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_InquiryReadSrv");
		wsdl11Definition.setSchema(sbWFInquiryReadSrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFInquiryReadSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFInquiryReadSrv/sbWFInquiryReadSrv.xsd"));
	}
	
	/**
	 * 查询指派节点服务
	 * @param sbWFApprovalPersonSrvSchema
	 * @return
	 */
	@Bean(name = "sbWFApprovalPersonSrv")
	public DefaultWsdl11Definition sbWFApprovalPersonSrv(XsdSchema sbWFApprovalPersonSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFApprovalPersonSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFApprovalPersonSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_ApprovalPersonSrv");
		wsdl11Definition.setSchema(sbWFApprovalPersonSrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFApprovalPersonSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFApprovalPersonSrv/sbWFApprovalPersonSrv.xsd"));
	}
	
	/**
	 * 指派节点审批服务
	 * @param sbWFApprovalHandlerSrvSchema
	 * @return
	 */
	@Bean(name = "sbWFApprovalHandlerSrv")
	public DefaultWsdl11Definition sbWFApprovalHandlerSrv(XsdSchema sbWFApprovalHandlerSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFApprovalHandlerSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFApprovalHandlerSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_ApprovalHandlerSrv");
		wsdl11Definition.setSchema(sbWFApprovalHandlerSrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFApprovalHandlerSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFApprovalhandlerSrv/sbWFApprovalhandlerSrv.xsd"));
	}
	
	/**
	 * 转办/转交服务
	 * @param sbWFTransHandlerSrvSchema
	 * @return
	 */
	@Bean(name = "sbWFTransHandlerSrv")
	public DefaultWsdl11Definition sbWFTransHandlerSrv(XsdSchema sbWFTransHandlerSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFTransHandlerSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFTransHandlerSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_TransHandlerSrv");
		wsdl11Definition.setSchema(sbWFTransHandlerSrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFTransHandlerSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFTransHandlerSrv/sbWFTransHandlerSrv.xsd"));
	}
	
	/**
	 * 查询下一审批节点服务
	 * @param sbWFNextNodesSrvSchema
	 * @return
	 */
	@Bean(name = "sbWFNextNodesSrv")
	public DefaultWsdl11Definition sbWFNextNodesSrv(XsdSchema sbWFNextNodesSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFNextNodesSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFNextNodesSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_NextNodesSrv");
		wsdl11Definition.setSchema(sbWFNextNodesSrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFNextNodesSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFNextNodesSrv/sbWFNextNodesSrv.xsd"));
	}
	
	/**
	 * 加签服务
	 * @param sbWFAddSignHandlerSrvSchema
	 * @return
	 */
	@Bean(name = "sbWFAddSignHandlerSrv")
	public DefaultWsdl11Definition sbWFAddSignHandlerSrv(XsdSchema sbWFAddSignHandlerSrvSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("SBWFAddSignHandlerSrv");
		wsdl11Definition.setLocationUri("/ws/sbWFAddSignHandlerSrv");
		wsdl11Definition.setTargetNamespace("http://csb.fmc.vispractice.com/SB_WF_AddSignHandlerSrv");
		wsdl11Definition.setSchema(sbWFAddSignHandlerSrvSchema);
		
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema sbWFAddSignHandlerSrvSchema() {
		return new SimpleXsdSchema(new ClassPathResource("META-INF/resources/schemas/sbWFAddSignHandlerSrv/sbWFAddSignHandlerSrv.xsd"));
	}
}