package com.vispractice.fmc.business.entities.setting.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entities.setting.view.GroupAdmin;

/**
 * 
 * 编  号：<br/>
 * 名  称：GroupAdminRepository<br/>
 * 描  述：<br/>
 * 完成日期：2016年12月15日 下午4:42:50<br/>
 * 编码作者："LaiJiashen"<br/>
 */


@Repository
public interface GroupAdminRepository extends JpaRepository<GroupAdmin, String>, JpaSpecificationExecutor<GroupAdmin>  {

	List<GroupAdmin> findByFdCateid(String fdCateid);

	
	
}
