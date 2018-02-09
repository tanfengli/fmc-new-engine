package com.vispractice.fmc.business.service.sys.config.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.business.entity.sys.config.CompDbcp;
import com.vispractice.fmc.business.entity.sys.config.repository.CompDbcpRepository;
import com.vispractice.fmc.business.service.sys.config.ICompDbcpService;

@Transactional
@Service
public class CompDbcpServiceImpl implements ICompDbcpService{

	
	@Autowired
	private CompDbcpRepository compDbcpRepository;
	
	
	
	/**
	 * 分页排序查询
	 * */
	@Override
	public Page<CompDbcp> getDbcpPage(Pageable pageable){

		return compDbcpRepository.findAll(pageable);
	}
	
	
	/**
	 * 条件，分页，排序查询
	 * */
	@Override
	public Page<CompDbcp> findBySearch(final CompDbcp compDbcp,Pageable pageable) {  
	    Page<CompDbcp> question = compDbcpRepository.findAll(new Specification<CompDbcp>() {  
	    	
	    	//where条件拼接
			@Override
			public Predicate toPredicate(Root<CompDbcp> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				{
		            Predicate predicate = cb.conjunction();  
		            List<Expression<Boolean>> expressions = predicate.getExpressions();  
		            
		            if (StringUtils.isNotBlank(compDbcp.getFdName())) {  
		                expressions.add(cb.like(root.<String>get("fdName"), "%"+compDbcp.getFdName()+"%"));           
		            }  
		            
		            if (StringUtils.isNotBlank(compDbcp.getFdType())) {  
		                expressions.add(cb.like(root.<String>get("fdType"), "%"+compDbcp.getFdType() +"%"));      
		            }  
		            
		            if(StringUtils.isNotBlank(compDbcp.getFdDescription())) {  
		                expressions.add(cb.like(root.<String>get("fdDescription"), "%"+compDbcp.getFdDescription() +"%"));          
		            }  
		            
		            return predicate;  
		        }
			}  
	    },pageable);  

	    return question;  
	}  
	
	
	/**
	 * 增加新信息
	 * @throws Exception 
	 * */
	@Override
	public void save(CompDbcp compDbcp) throws Exception{
		
		compDbcpRepository.save(compDbcp);
	}




	/**
	 * 根据ID删除一条信息
	 * */
	@Override
	public void delById(String fdId){
		
		compDbcpRepository.deleteByfdId(fdId);
		
	}


	@Override
	public List<CompDbcp> findAll() { 
		return compDbcpRepository.findAll();
	}
	
	@Override
	public boolean testConnection(CompDbcp compDbcp) throws Exception{
		
		Connection connection = null;
		connection = this.getConnection(compDbcp);
		if (null!=connection) {
			return true;
		}else {
			return false;
		}
		
	}
	
    private Connection getConnection(CompDbcp compDbcp) throws ClassNotFoundException,SQLException,Exception{
        Connection connection = null;  
        String driver = compDbcp.getFdDriver();
        String url = compDbcp.getFdUrl();
        String username = compDbcp.getFdUsername();
        String password = compDbcp.getFdPassword();
        
        // 加载驱动  
        Class.forName(driver);  
        // 通过驱动管理类获取数据库连接  
        connection = DriverManager.getConnection(url, username, password);  
        // 关闭连接  
        connection.close();  
        return connection;  
    }


	@Override
	public CompDbcp findByFdName(String fdName) {
		return compDbcpRepository.findByfdName(fdName);
	} 
	
	
	
}
