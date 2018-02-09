package com.vispractice.fmc.business.entity.sys.category.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.vispractice.fmc.business.entity.sys.category.view.CategoryMain;

@Repository
public interface CategoryMainRepository extends JpaRepository<CategoryMain,String>,JpaSpecificationExecutor<CategoryMain> {
	public List<CategoryMain> findByFdId(String fdId);
}
