package com.vispractice.fmc.business.entity.sys.news.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.news.SysNewsMainOeditor;

@Repository
public interface SysNewsMainOeditorRepository
		extends JpaRepository<SysNewsMainOeditor, String>, JpaSpecificationExecutor<SysNewsMainOeditor> {
	
	public List<SysNewsMainOeditor> findByFdMainId(String mainId);
	
	@Modifying
	@Query("delete from SysNewsMainOeditor o where o.fdMainId = ?1 and o.authOtherEditorId = ?2")
	void deleteByFdMainIdAndAuthOtherEditorId(String fdMainId, String authOtherEditorId);
}
