package com.vispractice.fmc.business.entity.sys.category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "SYS_CATEGORY_MAIN_READER")
public class SysCategoryMainReader {

	@Id
	@Column(name = "AUTH_READER_ID")
	private String authReaderId;

	@Column(name = "FD_CATEGORY_ID")
	private String fdCategoryId;

}
