/**
 * 
 */
package com.vispractice.fmc.business.entity.sys.workflow.support.subprocess.configuration;

/**
 * 表达式或名称值通用类
 * 
 * @author fuyx
 * 
 */
public class TextValue {

	private String text;

	private String value;

	public TextValue(String text, String value) {
		super();
		setText(text);
		setValue(value);
	}

	public String getText() {
		return text;
	}

	private void setText(String text) {
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	private void setValue(String value) {
		this.value = value;
	}
}
