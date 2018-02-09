package com.vispractice.fmc.business.entity.sys.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.org.SysOrgPostPerson; 
 

@Repository
public interface SysOrgPostPersonRepository extends JpaRepository<SysOrgPostPerson,String>,JpaSpecificationExecutor<SysOrgPostPerson> {
	
	@Query("select s.fdPersonid from SysOrgPostPerson s,SysOrgElement e where e.fdId=s.fdPersonid and e.fdIsAvailable=1 and s.fdPostid = ?1 ")
	public List<String> findFdPersonId(String fdPostId); 
	
	@Query("select s.fdPostid from SysOrgPostPerson s where s.fdPersonid = ?1")
	public List<String> findFdPostId(String fdPersonId); 
	
	@Modifying
	@Query("delete from SysOrgPostPerson where fdPostid = ?1")
	public void delete(String fdPostId);
	
	/**
	 * 通过岗位id删除（原生态sql）
	 * @Title: delByPostId 
	 * @param fdPostid
	 */
	@Modifying
	@Query(value="delete from sys_org_post_person where fd_postid = ?1",nativeQuery = true)
	public void delByPostId(String fdPostid);
	
	/**
	 * 通过人员id删除（原生态sql）
	 * @Title: delByPersonId 
	 * @param fdPersonid
	 */
	@Modifying
	@Query(value="delete from sys_org_post_person where fd_personid = ?1",nativeQuery = true)
	public void delByPersonId(String fdPersonid);
	
	/**
	 * 插入一条数据
	 * @Title: addOne 
	 * @param personId 人员fdid
	 * @param postId 岗位fdid
	 */
	@Modifying
	@Query(value = "insert into SYS_ORG_POST_PERSON (FD_PERSONID,FD_POSTID) values (?1, ?2)",nativeQuery = true)
	public void addOne(String personId,String postId);
	
	SysOrgPostPerson findByFdPersonidAndFdPostid(String personId, String postId);
	
}