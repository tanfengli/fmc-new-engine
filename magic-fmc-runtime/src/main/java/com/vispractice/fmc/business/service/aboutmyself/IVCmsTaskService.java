package com.vispractice.fmc.business.service.aboutmyself;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.vispractice.fmc.business.entity.aboutmyself.VCmsTask;

public interface IVCmsTaskService {
	/**
	 * 分页查询待办记录
	 * @Title: taskSearch 
	 * @param docSubmit
	 * @param pageable
	 */
	Page<VCmsTask> searchVCmsTask(VCmsTask docSubmit,Pageable pageable);
}
