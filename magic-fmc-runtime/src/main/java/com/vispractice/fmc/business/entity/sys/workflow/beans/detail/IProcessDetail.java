package com.vispractice.fmc.business.entity.sys.workflow.beans.detail;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.ProcessDescriptor;

public interface IProcessDetail {
	public static final int ICONTYPE_BIG = 1;

	public static final int ICONTYPE_SMALL = 2;

	public abstract String getTemplateId();

	public abstract void setTemplateId(String templateId);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract String getDescription();

	public abstract void setDescription(String description);

	public abstract int getIconType();

	public abstract void setIconType(int iconType);

	public abstract List<INodeDetail> getNodes();

	public abstract void setNodes(List<INodeDetail> nodes);

	public abstract INodeDetail getNodeById(String id);

	public abstract List<ILineDetail> getLines();

	public abstract void setLines(List<ILineDetail> lines);

	public abstract ILineDetail getLineById(String id);

	public abstract ProcessDescriptor parseProcessDescriptor()throws Exception;

	public abstract void reset();
}
