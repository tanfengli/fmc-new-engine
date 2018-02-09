package com.vispractice.fmc.business.entities.sys.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vispractice.fmc.business.entities.sys.view.PositionManagemetV;
 
@Repository
public interface PositionManagemetVRepository
		extends JpaRepository<PositionManagemetV, Long>, JpaSpecificationExecutor<PositionManagemetV> {
	
	@Query("select count(*) from PositionManagemetV a where a.fdName=?1 and a.fdId not in ?2")
	public int findByFdName(String fdName,String fdId);
	
	public PositionManagemetV findByFdIdAndFdName(String fdId,String fdName);
	
	@Query("from PositionManagemetV a where a.fdIsAvailable=?1")
	public List<PositionManagemetV> getIfAvailable(Long fdIsAvailable);
}