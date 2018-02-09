package com.vispractice.fmc.business.base.exception;

/**
 * 自定义workflow异常类
 * 
 * @author sky
 * @Date May 11, 2017
 * @version 1.0.0
 */
public class WorkflowException extends RuntimeException {

	private static final long serialVersionUID = -3156187714569229051L;

	public WorkflowException() {
		super();
	}

	public WorkflowException(String msg) {
		super(msg);
	}

	public WorkflowException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
