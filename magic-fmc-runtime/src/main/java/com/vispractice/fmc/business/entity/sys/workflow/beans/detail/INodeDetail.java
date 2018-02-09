package com.vispractice.fmc.business.entity.sys.workflow.beans.detail;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.NodeDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.support.detail.Advice;

public interface INodeDetail {
	public abstract void setProcess(IProcessDetail process);

	public abstract IProcessDetail loadDetail();

	public abstract String getDescription();

	public abstract void setDescription(String description);

	public abstract String getId();

	public abstract void setId(String id);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract int getX();

	public abstract void setX(int x);

	public abstract int getY();

	public abstract void setY(int y);

	public abstract List<ILineDetail> loadOutLines();

	public abstract NodeDescriptor parseNodeDescriptor();

	public abstract List<ILineDetail> loadInLines();

	public String getHandlerIds();

	public void setHandlerIds(String handlerIds);

	public String getHandlerNames();

	public void setHandlerNames(String handlerNames);
	
	public String getHandlerDescs();

	public void setHandlerDescs(String handlerDescs);

	public abstract void reset();
	
	/**
	 * 获取绑定的Advice对象
	 * @author 张翼麟
	 * @date 2015年10月21日
	 * @return
	 */
	public Advice getAdvice();
	
	/**
	 * 获取节点类型
	 * @author 张翼麟
	 * @date 2016年4月8日
	 * @return 节点类型
	 */
	public String getType();

}