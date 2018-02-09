package com.vispractice.fmc.business.service.aboutmyself;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.vispractice.fmc.business.entity.aboutmyself.AuditBillV;

public interface IAuditBillVService {
	/**
	 * 分页查询已办接口数据 <br/>
	 * @param auditBillV
	 * @param pageable
	 * @return
	 */
	Page<AuditBillV> searchTask(AuditBillV auditBillV,Pageable pageable);
}
