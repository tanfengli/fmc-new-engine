/**
 * 
 */
package com.vispractice.fmc.business.entity.sys.workflow.support.subprocess.configuration;

import java.util.Set;

/**
 * 枚举类型Util类
 * 
 * @author fuyx
 * 
 */
public class EnumUtils {

	public static ValueEquals findByValueEqual(Set<? extends ValueEquals> set,
			Object value) {
		for (ValueEquals e : set) {
			if (e.valueEquals(value)) {
				return e;
			}
		}
		return null;
	}
}
