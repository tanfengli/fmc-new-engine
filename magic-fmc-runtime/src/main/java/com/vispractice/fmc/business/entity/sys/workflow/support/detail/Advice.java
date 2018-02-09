package com.vispractice.fmc.business.entity.sys.workflow.support.detail;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义前后置逻辑的内容
 * @author 张翼麟
 * @date 2015年10月21日
 *
 */
public class Advice {

	/**
	 * 解析XML时子节点是否作为数组来解析，默认值是false
	 */
	protected boolean CHILDRENISARRAY = false;
	
	/**
	 * 节点执行前需要处理的逻辑
	 */
	private List<Advice> before = new ArrayList<Advice>();
	/**
	 * 节点执行后需要处理的逻辑
	 */
	private List<Advice> after = new ArrayList<Advice>();
	/**
	 * 唯一标识
	 */
	private String unid;
	/**
	 * 具体内容
	 */
	private String content;

	public List<Advice> getBefore() {
		return before;
	}

	public void setBefore(List<Advice> before) {
		this.before = before;
	}
	
	public void addBefore(Advice beforeAdvice) {
		this.before.add(beforeAdvice);
	}

	public List<Advice> getAfter() {
		return after;
	}

	public void setAfter(List<Advice> after) {
		this.after = after;
	}
	
	public void addAfter(Advice afterAdvice) {
		this.after.add(afterAdvice);
	}

	public String getUnid() {
		return unid;
	}

	public void setUnid(String unid) {
		this.unid = unid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isCHILDRENISARRAY() {
		return CHILDRENISARRAY;
	}

}
