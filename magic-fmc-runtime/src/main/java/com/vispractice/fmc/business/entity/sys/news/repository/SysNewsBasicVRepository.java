package com.vispractice.fmc.business.entity.sys.news.repository;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.vispractice.fmc.business.entity.sys.news.view.SysNewsBasicV;

public interface SysNewsBasicVRepository extends PagingAndSortingRepository<SysNewsBasicV,String>{
	List<SysNewsBasicV> findByFdId(String fdId);
}
