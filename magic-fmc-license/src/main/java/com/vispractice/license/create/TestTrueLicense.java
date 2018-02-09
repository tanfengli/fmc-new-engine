package com.vispractice.license.create;
import java.io.File;
import java.util.Date;
import java.util.prefs.Preferences;

import javax.security.auth.x500.X500Principal;

import com.vispractice.license.de.schlichtherle.license.CipherParam;
import com.vispractice.license.de.schlichtherle.license.DefaultCipherParam;
import com.vispractice.license.de.schlichtherle.license.DefaultKeyStoreParam;
import com.vispractice.license.de.schlichtherle.license.DefaultLicenseParam;
import com.vispractice.license.de.schlichtherle.license.KeyStoreParam;
import com.vispractice.license.de.schlichtherle.license.LicenseContent;
import com.vispractice.license.de.schlichtherle.license.LicenseManager;
import com.vispractice.license.de.schlichtherle.license.LicenseParam;

public class TestTrueLicense {
	public final static String PRIVATEALIAS = "privatekey";
	public final static String PUBLICALIAS = "publiccert";
	public final static String KEYPWD = "fmc123";
	public final static String STOREPWD = "fmc123";
	public final static String SUBJECT = "trueLicense测试";
	//为了方便直接用的API里的例子
	//X500Princal是一个证书文件的固有格式，详见API
	public final static X500Principal DEFAULTHOLDERANDISSUER = new X500Principal("CN=tfl, OU=yxtech, O=yxtech, L=sz, ST=sz, C=ch");
	
    public static void main(String[] args) throws Exception {
    	/**************证书发布者端执行******************/
    	LicenseManager licenseManager = 
    		LicenseManagerHolder.getLicenseManager(initLicenseParams0());
    	String storePath = TestTrueLicense.class.getResource(".").getFile() + File.separator + "测试.lic";
    	licenseManager.store((createLicenseContent()), new File(storePath));
    	System.out.println("服务器端生成证书成功!");
    	/**************证书使用者端执行******************/
    	//由于用的是一个licenseManager所以重新设置验证时需要的LicenseParam
    	licenseManager.setLicenseParam(initLicenseParams1());
    	//安装证书
    	licenseManager.install(new File(storePath));
    	System.out.println("客户端安装证书成功!");
    	//验证证书
    	licenseManager.verify();
    	System.out.println("客户端验证证书成功!");
    } 
    
    //返回生成证书时需要的参数
    private static LicenseParam initLicenseParams0() {
    	Preferences preference = Preferences.userNodeForPackage(TestTrueLicense.class);
        //设置对证书内容加密的对称密码
    	CipherParam cipherParam = new DefaultCipherParam("111aaa");
        //参数1,2从哪个Class.getResource()获得密钥库
    	//参数3密钥库的别名
    	//参数4密钥库存储密码
    	//参数5密钥库密码
        KeyStoreParam privateStoreParam  = 
        	    new DefaultKeyStoreParam(TestTrueLicense.class, "privateKeys.store", PRIVATEALIAS, STOREPWD, KEYPWD);
        LicenseParam licenseParams =
        	new DefaultLicenseParam(SUBJECT, preference, privateStoreParam, cipherParam);
        return licenseParams;
    }
    
    //返回验证证书需要的参数
    private static LicenseParam initLicenseParams1() {
    	Preferences preference = Preferences.userNodeForPackage(TestTrueLicense.class);
        CipherParam cipherParam = new DefaultCipherParam("111aaa");
        
        KeyStoreParam privateStoreParam  = 
        	    new DefaultKeyStoreParam(TestTrueLicense.class, "publicCerts.store", PUBLICALIAS, STOREPWD, null);
        LicenseParam licenseParams =
        	new DefaultLicenseParam(SUBJECT, preference, privateStoreParam, cipherParam);
        return licenseParams;
    }
    //一般可以从外部表单拿到证书的内容
    //这里为了演示，把证书内容写死了
    public final static LicenseContent createLicenseContent() {
    	LicenseContent content = null;
    	content = new LicenseContent();
    	content.setSubject(SUBJECT);
    	content.setHolder(DEFAULTHOLDERANDISSUER);
    	content.setIssuer(DEFAULTHOLDERANDISSUER);
    	content.setIssued(new Date());
    	content.setNotBefore(new Date());
    	content.setNotAfter(new Date(new Date().getTime() + 1000));
    	content.setConsumerType("user");
    	content.setConsumerAmount(1);
    	content.setInfo("随便不为空的描述性信息");
    	//扩展
    	content.setExtra(new Object());
    	return content;
    }
}