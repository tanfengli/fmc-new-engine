package com.vispractice.fmc.business.entity.sys.notify;

import java.io.Serializable;

import lombok.Data;

@Data
public class SysNotifyTodotargetKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3801092467727849963L;

	private String fdTodoid;

	private String fdOrgid;

	/**
	 * 无参数的public构造方法，必须要有
	 */
	public SysNotifyTodotargetKey() {

	}

	/**
	 * 覆盖hashCode方法，必须要有
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (fdTodoid == null ? 0 : fdTodoid.hashCode());
		result = PRIME * result + (fdOrgid == null ? 0 : fdOrgid.hashCode());
		return result;
	}

	/**
	 * 覆盖equals方法，必须要有
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SysNotifyTodotargetKey))
			return false;
		SysNotifyTodotargetKey objKey = (SysNotifyTodotargetKey) obj;
		if (fdTodoid.equalsIgnoreCase(objKey.fdTodoid) && fdOrgid.equalsIgnoreCase(objKey.fdOrgid)) {
			return true;
		}
		return false;
	}

}
