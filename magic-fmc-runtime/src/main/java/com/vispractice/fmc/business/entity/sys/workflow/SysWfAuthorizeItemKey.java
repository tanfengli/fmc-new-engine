package com.vispractice.fmc.business.entity.sys.workflow;

import java.io.Serializable;

import lombok.Data;

@Data
public class SysWfAuthorizeItemKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1073639607584960487L;
	
	private String fdAuthorizeOrgId;
	
	private String fdAuthorizeId;
	
	 /** 
     * 无参数的public构造方法，必须要有 
     */  
    public SysWfAuthorizeItemKey() {  
          
    }  
    
    /** 
     * 覆盖hashCode方法，必须要有 
     */  
    @Override  
    public int hashCode() {  
        final int PRIME = 31;  
        int result = 1;  
        result = PRIME * result + (fdAuthorizeOrgId == null ? 0 : fdAuthorizeOrgId.hashCode());  
        result = PRIME * result + (fdAuthorizeId == null ? 0 : fdAuthorizeId.hashCode());  
        return result;  
    }  
  
    /** 
     * 覆盖equals方法，必须要有 
     */  
    @Override  
    public boolean equals(Object obj) {  
        if(this == obj) return true;  
        if(obj == null) return false;  
        if(!(obj instanceof SysWfAuthorizeItemKey)) return false;  
        SysWfAuthorizeItemKey objKey = (SysWfAuthorizeItemKey)obj;  
        if(fdAuthorizeOrgId.equalsIgnoreCase(objKey.fdAuthorizeOrgId) &&  
        		fdAuthorizeId.equalsIgnoreCase(objKey.fdAuthorizeId)) {  
            return true;  
        }  
        return false;  
    }  

}
