package com.vispractice.license.create;
import com.vispractice.license.de.schlichtherle.license.LicenseManager;
import com.vispractice.license.de.schlichtherle.license.LicenseParam;

/**
 * LicenseManager
 * @author melina
 */
public class LicenseManagerHolder {
	
	private static LicenseManager licenseManager;
 
	public static synchronized LicenseManager getLicenseManager(LicenseParam licenseParams) {
    	if (licenseManager == null) {
    		licenseManager = new LicenseManager(licenseParams);
    	}
    	return licenseManager;
    }
}