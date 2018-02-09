package com.vispractice.fmc.business.service.sys.news;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.news.view.ProcessReadV;

public interface IProcessReadService {
	/**
	 * 查看待阅信息
	 * @param processReadV
	 * @param pageable
	 * @return
	 */
	public Page<ProcessReadV> searchProcessRead(ProcessReadV processReadV,Pageable pageable);

	/**
	 * 设置为已阅
	 * @param todoId 通知表ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	public void setIsRead(String todoId, String userId) throws Exception;

	/**
	 * 
	 * 计算已读/未读消息
	 * @Title: count 
	 * @param userNo 用户编号
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map count(String userNo);
}
