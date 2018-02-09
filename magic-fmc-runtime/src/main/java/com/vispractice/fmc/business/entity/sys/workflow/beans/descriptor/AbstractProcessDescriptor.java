package com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.vispractice.fmc.base.utils.StringUtil;
import com.vispractice.fmc.business.base.utils.BeanReaderWriter;

public class AbstractProcessDescriptor {
	private String id;

	private String name;

	private String detailClass;
	
	private static Map<String, AbstractProcessDescriptor> descriptors = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetailClass() {
		return detailClass;
	}

	public void setDetailClass(String detailClass) {
		this.detailClass = detailClass;
	}

	// 取得所有节点对象
	List<AbstractNodeDescriptor> nodeDescriptors = new ArrayList<AbstractNodeDescriptor>();

	public List<AbstractNodeDescriptor> getNodeDescriptors() {
		return nodeDescriptors;
	}

	public void setNodeDescriptors(List<AbstractNodeDescriptor> nodeDescriptors) {
		this.nodeDescriptors = nodeDescriptors;
	}

	public void addNodeDescriptor(AbstractNodeDescriptor nodeDescriptor) {
		nodeDescriptor.setAbstractWorkflowDescriptor(this);
		nodeDescriptors.add(nodeDescriptor);
	}

	public AbstractNodeDescriptor getNodeDescriptor(String nodeId) {
		if (nodeDescriptors == null || nodeId == null)
			return null;
		for (int i = 0; i < nodeDescriptors.size(); i++) {
			AbstractNodeDescriptor nodeDescriptor = nodeDescriptors.get(i);
			if (nodeId.equals(nodeDescriptor.getId()))
				return nodeDescriptor;
		}
		return null;
	}
	
	private static BeanReaderWriter beanReaderWriter = new BeanReaderWriter(
			AbstractProcessDescriptor.class);
	
	public synchronized static AbstractProcessDescriptor parse(String type)
			throws Exception {
		if (descriptors == null) {
			descriptors = new HashMap<String, AbstractProcessDescriptor>();
			// 将kmssconfig中的流程相关配置参数写死，防止项目漏配导致流程异常 @作者：曹映辉 @日期：2012年8月21日
			String[] types = new String[] { "oa" };
			for (int i = 0; i < types.length; i++) {
				if (StringUtil.isNull(types[i]))
					continue;
				String fileName = "oa-process-engine.xml";

				descriptors.put(types[i],
						(AbstractProcessDescriptor) beanReaderWriter
								.parse(getXMLString(fileName)));

			}
			return descriptors.get(type);
		}
		return descriptors.get(type);
	}
	
	private static String getXMLString(String fileName) throws Exception {
		BufferedReader in = null;
		StringBuffer buffer = new StringBuffer();
		String line = "";
		try {
			in = new BufferedReader(new InputStreamReader(
					AbstractProcessDescriptor.class
							.getResourceAsStream(fileName), "utf-8"));
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
		} finally {
			// 关闭流
			IOUtils.closeQuietly(in);
		}
		return buffer.toString();
	}
	
}
