package com.vispractice.fmc.business.entity.sys.category;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "SYS_CATEGORY_MAIN_EDITOR")
public class SysCategoryMainEditor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "AUTH_EDITOR_ID")
	private String authEditorId;

	@Column(name = "FD_CATEGORY_ID")
	private String fdCategoryId;

}
