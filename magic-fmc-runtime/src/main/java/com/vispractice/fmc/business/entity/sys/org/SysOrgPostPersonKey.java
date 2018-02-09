package com.vispractice.fmc.business.entity.sys.org;

import java.io.Serializable;

import lombok.Data;

@Data
public class SysOrgPostPersonKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -934950862044127663L;
	
	private String fdPostid;
	
	private String fdPersonid;
	
	 /** 
     * 无参数的public构造方法，必须要有 
     */  
    public SysOrgPostPersonKey() {  
          
    }  
    
    /** 
     * 覆盖hashCode方法，必须要有 
     */  
    @Override  
    public int hashCode() {  
        final int PRIME = 31;  
        int result = 1;  
        result = PRIME * result + (fdPostid == null ? 0 : fdPostid.hashCode());  
        result = PRIME * result + (fdPersonid == null ? 0 : fdPersonid.hashCode());  
        return result;  
    }  
  
    /** 
     * 覆盖equals方法，必须要有 
     */  
    @Override  
    public boolean equals(Object obj) {  
        if(this == obj) return true;  
        if(obj == null) return false;  
        if(!(obj instanceof SysOrgPostPersonKey)) return false;  
        SysOrgPostPersonKey objKey = (SysOrgPostPersonKey)obj;  
        if(fdPostid.equalsIgnoreCase(objKey.fdPostid) &&  
        		fdPersonid.equalsIgnoreCase(objKey.fdPersonid)) {  
            return true;  
        }  
        return false;  
    }  

}
