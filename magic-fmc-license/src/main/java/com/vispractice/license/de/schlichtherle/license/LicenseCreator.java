package com.vispractice.license.de.schlichtherle.license;

import java.rmi.Remote;

public abstract interface LicenseCreator
  extends Remote
{
  public abstract byte[] create(LicenseContent paramLicenseContent)
    throws Exception;
}
