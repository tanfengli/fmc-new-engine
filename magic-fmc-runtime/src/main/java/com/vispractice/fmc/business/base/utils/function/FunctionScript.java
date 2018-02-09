package com.vispractice.fmc.business.base.utils.function;

public class FunctionScript {
	public FunctionScript() {
	}

	public FunctionScript(String functionScript) {
		this(functionScript, null);
	}

	public FunctionScript(String functionScript, String prepareScript) {
		this.functionScript = functionScript;
		this.prepareScript = prepareScript;
	}

	private String prepareScript;

	private String functionScript;

	/**
	 * 获取函数定义部分的代码
	 * 
	 * @return
	 */
	public String getPrepareScript() {
		return prepareScript;
	}

	/**
	 * 设置函数定义部分的代码
	 * 
	 * @param prepareScript
	 */
	public void setPrepareScript(String prepareScript) {
		this.prepareScript = prepareScript;
	}

	/**
	 * 获取函数调用代码
	 * 
	 * @return
	 */
	public String getFunctionScript() {
		return functionScript;
	}

	/**
	 * 设置函数调用代码
	 * 
	 * @param functionScript
	 */
	public void setFunctionScript(String functionScript) {
		this.functionScript = functionScript;
	}
}
