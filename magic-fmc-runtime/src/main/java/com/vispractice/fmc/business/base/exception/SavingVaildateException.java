package com.vispractice.fmc.business.base.exception;

/**
 * 自定义保存前校验异常类
 * 
 * @author sky
 * @Date May 11, 2017
 * @version 1.0.0
 */
public class SavingVaildateException extends RuntimeException {

	private static final long serialVersionUID = -3156187714569229051L;

	public SavingVaildateException() {
		super();
	}

	public SavingVaildateException(String msg) {
		super(msg);
	}

	public SavingVaildateException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
