package com.vispractice.fmc.business.service.sys.read.impl;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.business.base.constrant.SysDocConstant;
import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
import com.vispractice.fmc.business.entity.sys.news.repository.SysNewsMainRepository;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.read.SysReadLog;
import com.vispractice.fmc.business.entity.sys.read.SysReadLogV;
import com.vispractice.fmc.business.entity.sys.read.repository.SysReadLogRepository;
import com.vispractice.fmc.business.entity.sys.read.repository.SysReadLogVRepository;
import com.vispractice.fmc.business.service.sys.read.ISysReadLogService;

/**
 * 编  号：
 * 名  称：SysReadLogServiceImpl
 * 描  述：阅读日志接口实现类
 * 完成日期：2017年10月18日 下午4:50:15
 * 编码作者：LaiJiashen
 */
@Service
@Transactional
public class SysReadLogServiceImpl implements ISysReadLogService{
	@Autowired
	private SysReadLogRepository sysReadLogRepository;
	
	@Autowired
	private SysReadLogVRepository sysReadLogVRepository;
	
	@Autowired
	private SysNewsMainRepository sysNewsMainRepository;
	
	/**
	 * 获取当前单据已阅人员
	 * @param billId
	 * @return
	 */
	@Override
	public String getReaderNames(String billId){
		List<SysOrgElement> readerElemntList = sysReadLogRepository.getReaderElements(billId,new Long(SysReadLog.READ_LOG_TYPE_PROCESS));
		StringBuffer sb = new StringBuffer();
		String readerName = null;
		for (SysOrgElement sysOrgElement : readerElemntList) {
			sb.append(sysOrgElement.getFdName());
			sb.append(";");
		}
		readerName = readerElemntList.size()==0?sb.toString():sb.substring(0, sb.length()-1);
		
		return readerName;
	}
	
	/**
	 * 获取当前单据被阅读的记录
	 * @param billId 单据id
	 * @param pageable 分页参数
	 * @return
	 */
	@Override
	public Page<SysReadLogV> getReaderList(String billId,Pageable pageable) {
		Page<SysReadLogV> page = sysReadLogVRepository.findByFdModelId(billId,pageable);
		
		return page;
	}
	
	/**
	 * 阅读次数(点击率)仅统计发布阶段的阅读记录
	 * @param billId
	 * @return
	 */
	@Override
	public Long countReadTimes(String billId) {
		Long count = sysReadLogRepository.countReadTimes(billId,new Long(SysReadLog.READ_LOG_TYPE_PUBLISH));
		
		return count;
	}
	
	/**
	 * 插入一条阅读日志信息
	 */
	@Override
	public void save(String billId,String readerId) throws Exception {
		SysReadLog sysReadLog = new SysReadLog();
		Long readType = null;
		
		if (null==billId||null==readerId) {
			throw new Exception("单据编号或阅读者id不能为空.");
		}
		SysNewsMain sysNewsMain = sysNewsMainRepository.findOne(billId);
		if (null==sysNewsMain) {
			throw new Exception("单据(id=" + billId + ")不存在.");
		}
		readType = (long)(SysDocConstant.DOC_STATUS_PUBLISH.equals(sysNewsMain.getDocStatus()) ? SysReadLog.READ_LOG_TYPE_PUBLISH : SysReadLog.READ_LOG_TYPE_PROCESS);
		
		sysReadLog.setFdModelId(billId);
		sysReadLog.setFdReaderId(readerId);
		sysReadLog.setFdIsNewVersion(1L);
		sysReadLog.setFdModelName("com.ruiyi.kmss.sys.news.model.SysNewsMain");
		sysReadLog.setFdReadTime(new Date());
		sysReadLog.setFdReadType(readType);
		sysReadLog.setFdReaderClientIp(this.getIpAdd());
		
		sysReadLogRepository.save(sysReadLog);
	}
	
    /** 
     * 根据网卡获得IP地址 
     * @return 
     * @throws SocketException 
     * @throws UnknownHostException 
     */  
    private String getIpAdd() throws SocketException, UnknownHostException{  
        String ip="";  
        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {  
            NetworkInterface intf = en.nextElement();  
            String name = intf.getName();  
            if (!name.contains("docker") && !name.contains("lo")) {  
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {  
                    //获得IP  
                    InetAddress inetAddress = enumIpAddr.nextElement();  
                    if (!inetAddress.isLoopbackAddress()) {  
                        String ipaddress = inetAddress.getHostAddress().toString();  
                        if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {  
                              
//                            System.out.println(ipaddress);  
                            if(!"127.0.0.1".equals(ip)){  
                                ip = ipaddress;  
                            }  
                        }  
                    }  
                }  
            }  
        }  
        return ip;  
    }  
	
}
