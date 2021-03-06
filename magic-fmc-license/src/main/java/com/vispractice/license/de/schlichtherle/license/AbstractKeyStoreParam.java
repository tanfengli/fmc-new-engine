package com.vispractice.license.de.schlichtherle.license;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public abstract class AbstractKeyStoreParam
  implements KeyStoreParam
{
  private final Class clazz;
  private final String resource;
  
  protected AbstractKeyStoreParam(Class paramClass, String paramString)
  {
    this.clazz = paramClass;
    this.resource = paramString;
  }
  
  public InputStream getStream()
    throws IOException
  {
//    InputStream localInputStream = this.clazz.getResourceAsStream(this.resource);
    InputStream localInputStream  = new FileInputStream("C:/license/privateKeys.store");//zjs
    System.out.println(localInputStream.read());
    if (localInputStream == null) {
      throw new FileNotFoundException(this.resource);
    }
    return localInputStream;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof KeyStoreParam)) {
      return false;
    }
    AbstractKeyStoreParam localAbstractKeyStoreParam = (AbstractKeyStoreParam)paramObject;
    return (this.clazz.getResource(this.resource).equals(localAbstractKeyStoreParam.clazz.getResource(localAbstractKeyStoreParam.resource))) && (getAlias().equals(localAbstractKeyStoreParam.getAlias()));
  }
}
