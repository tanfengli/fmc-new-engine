package com.vispractice.fmc.business.service.sys.config;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.config.SysBusiSys;
import com.vispractice.fmc.business.entity.sys.config.view.SysBusiSysV;

/**
 * 业务系统与流程系统标识服务
 * @author zhouyanyao
 */
public interface ISysBusiSysService {
	/**
	 * 分页查看业务系统标识记录
	 * @param sysBusiSys
	 * @param pageable
	 * @return
	 */
	public Page<SysBusiSysV> findAll(SysBusiSysV sysBusiSys,Pageable pageable);

	/**
	 * 分页搜索业务系统标识记录
	 * @param sysBusiSys
	 * @param pageable
	 * @return
	 */
	public Page<SysBusiSysV> findBySearch(final SysBusiSysV sysBusiSys,Pageable pageable);

	/**
	 * 保存业务系统标识记录
	 */
	public SysBusiSys save(SysBusiSys sysBusiSys);

	/**
	 * 根据标识查询业务系统标识记录
	 */
	public SysBusiSys get(String id);

	/**
	 * 删除业务系统标识记录
	 */
	public void delete(SysBusiSys sysBusiSys);

	/**
	 * 查找所有业务系统标识记录
	 * @return
	 */
	public List<SysBusiSys> findAll();
	
	/**
	 * 根据业务系统编码查看业务系统记录
	 * @param fdCode
	 * @return
	 */
	public SysBusiSys getByFdCode(String fdCode);

	/**
	 * 根据是否有效获取业务系统
	 * @param fdEnabled
	 * @return
	 */
	public List<SysBusiSys> findByFdEnabled(Long fdEnabled);
}
