package com.vispractice.fmc.business.entity.sys.workflow.beans.detail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vispractice.fmc.business.base.utils.BeanReaderWriter;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.ProcessDescriptor;

public abstract class AbstractProcessDetail implements IProcessDetail {
	protected String description;

	protected int iconType;

	protected List<ILineDetail> lines = new ArrayList<ILineDetail>();

	protected String name;

	protected List<INodeDetail> nodes = new ArrayList<INodeDetail>();

	protected String templateId;

	protected int nodesIndex;

	protected int linesIndex;

	public String getDescription() {
		return description;
	}

	public int getIconType() {
		return iconType;
	}

	public List<ILineDetail> getLines() {
		return lines;
	}

	public String getName() {
		return name;
	}

	public List<INodeDetail> getNodes() {
		return nodes;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setIconType(int iconType) {
		this.iconType = iconType;
	}

	public void setLines(List<ILineDetail> lines) {
		this.lines = lines;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNodes(List<INodeDetail> nodes) {
		this.nodes = nodes;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public ILineDetail getLineById(String id) {
		if (lines == null || id == null)
			return null;
		for (int i = 0; i < lines.size(); i++) {
			ILineDetail lineDetail = lines.get(i);
			if (id.equals(lineDetail.getId()))
				return lineDetail;
		}
		return null;
	}

	public INodeDetail getNodeById(String id) {
		if (nodes == null || id == null)
			return null;
		for (int i = 0; i < nodes.size(); i++) {
			INodeDetail nodeDetail = nodes.get(i);
			if (id.equals(nodeDetail.getId()))
				return nodeDetail;
		}
		return null;
	}

	public void reset() {
		if (nodes != null) {
			for (int i = 0; i < nodes.size(); i++) {
				nodes.get(i).reset();
			}
		}
		if (lines != null) {
			for (int i = 0; i < lines.size(); i++) {
				lines.get(i).reset();
			}
		}
	}

	public void addNode(INodeDetail nodeDetail) {
		nodeDetail.setProcess(this);
		nodes.add(nodeDetail);
	}

	public void addLine(ILineDetail lineDetail) {
		lineDetail.setProcess(this);
		lines.add(lineDetail);
	}

	public ProcessDescriptor parseProcessDescriptor() throws Exception {
		ProcessDescriptor wfDescriptor = ProcessDescriptor.parse("<process/>");
		wfDescriptor.setName(this.getName());
		return wfDescriptor;
	}

	private static Map<String, BeanReaderWriter> beanReaderMap = new HashMap<String, BeanReaderWriter>();
//
	public synchronized static IProcessDetail parse(String xml, Class<?> cls)
			throws Exception {
		BeanReaderWriter beanReader = beanReaderMap.get(cls.getName());
		if (beanReader == null) {
			beanReader = new BeanReaderWriter(cls);
			beanReaderMap.put(cls.getName(), beanReader);
		}
		return (IProcessDetail) beanReader.parse(xml);
	}

	public String toString() {
		BeanReaderWriter beanReader = beanReaderMap.get(getClass().getName());
		if (beanReader == null) {
			beanReader = new BeanReaderWriter(getClass());
			beanReaderMap.put(getClass().getName(), beanReader);
		}
		return beanReader.write(this);
	}

	public int getLinesIndex() {
		return linesIndex;
	}

	public void setLinesIndex(int linesIndex) {
		this.linesIndex = linesIndex;
	}

	public int getNodesIndex() {
		return nodesIndex;
	}

	public void setNodesIndex(int nodesIndex) {
		this.nodesIndex = nodesIndex;
	}
}
