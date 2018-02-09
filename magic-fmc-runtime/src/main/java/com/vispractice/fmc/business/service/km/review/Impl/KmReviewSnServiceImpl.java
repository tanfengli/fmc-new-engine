package com.vispractice.fmc.business.service.km.review.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.business.entity.km.review.KmReviewSn;
import com.vispractice.fmc.business.entity.km.review.repository.KmReviewSnRepository;
import com.vispractice.fmc.business.service.km.review.IKmReviewSnService;


@Service
public class KmReviewSnServiceImpl implements IKmReviewSnService{
	
	/**
	 * 流水号持久化服务
	 */
	@Autowired
	private KmReviewSnRepository KmReviewSnRepo;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String getSerialNumber(KmReviewSn snContext) throws Exception{
		
		String modelName = snContext.getFdModelName();
		String templateId = snContext.getFdTemplateId();
		String prefix = snContext.getFdPrefix();
		//获取当前日期
		Date curDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String curActualDate = format.format(curDate);
		snContext.setFdDate(curActualDate);
		String flowNumber = "";
		//待更新的流水号实体
		KmReviewSn kmReviewSn = new KmReviewSn();

		//查询符合条件的流水号
		List<KmReviewSn> snList = KmReviewSnRepo.findByFdModelNameAndFdTemplateIdAndFdPrefix(modelName, templateId, prefix);
		
		// 如果有相应记录，取流水号
		if (snList.size() >= 1) {
			kmReviewSn = snList.get(0);
			String curSqlDate = kmReviewSn.getFdDate();
			if (curActualDate.equalsIgnoreCase(curSqlDate)) {
				flowNumber = createSerialNumber(kmReviewSn);
				// 把记录中的流水号+1
				kmReviewSn.setFdMaxNumber(kmReviewSn.getFdMaxNumber() + 1);
			} else {
				snContext.setFdMaxNumber(new Long(1));
				flowNumber = createSerialNumber(snContext);
				kmReviewSn.setFdDate(curActualDate);
				kmReviewSn.setFdMaxNumber(new Long(2));
			}
			KmReviewSnRepo.save(kmReviewSn);
			if (snList.size() > 1) {
				snList.remove(kmReviewSn);
				for (KmReviewSn kmReviewSnTemp : snList) {
					KmReviewSnRepo.delete(kmReviewSnTemp);
				}
			}
		} else {
			snContext.setFdMaxNumber(new Long(1));
			flowNumber = createSerialNumber(snContext);
			// 插入一条记录
			kmReviewSn.setFdDate(curActualDate);
			kmReviewSn.setFdModelName(snContext.getFdModelName());
			kmReviewSn.setFdMaxNumber(new Long(2));
			kmReviewSn.setFdTemplateId(snContext.getFdTemplateId());
			kmReviewSn.setFdPrefix(snContext.getFdPrefix());
			KmReviewSnRepo.save(kmReviewSn);
		}
		
		
		return flowNumber;
	}
	
	
	
	
	/**
	 * 根据上下文生成流水号，规则为“前缀+日期+最大号”，最大号不足4位补“0”。
	 * 
	 * @param kmReviewSnContext
	 *            上下文，用于传递查询流水号的参数及返回信息
	 * @return String;
	 * @throws Exception
	 */
	private String createSerialNumber(KmReviewSn kmReviewSnContext)
			throws Exception {
		
		StringBuffer sb = new StringBuffer();
		//字符串拼接：前缀+日期+最大号
		//前缀
		sb.append(kmReviewSnContext.getFdPrefix());
		//日期
		String date = kmReviewSnContext.getFdDate().replaceAll("-", "");
		sb.append(date);
		String sn_str = formatNumber(kmReviewSnContext.getFdMaxNumber().longValue());
		//最大号
		sb.append(sn_str);

		return sb.toString();
	}
	
	
	
	
	
	/**
	 * 格式化数字,没有四位长度时,补足四位
	 * 
	 * @param number
	 * @return
	 */
	private String formatNumber(long number) {
		StringBuffer buffer = new StringBuffer();
		if (number < 10L) {
			buffer.append("000");
		} else if (number < 100L) {
			buffer.append("00");
		} else if (number < 1000) {
			buffer.append("0");
		}
		buffer.append(number);
		return buffer.toString();
	}
	
	
	
	
	
	
	
	
	
	

}
