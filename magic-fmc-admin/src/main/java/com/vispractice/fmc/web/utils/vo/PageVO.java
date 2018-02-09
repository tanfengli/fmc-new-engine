package com.vispractice.fmc.web.utils.vo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageVO {

	private int number;

	private int size;

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	public Pageable getPageable() {
		return new PageRequest(this.number, this.size);
	}
}
