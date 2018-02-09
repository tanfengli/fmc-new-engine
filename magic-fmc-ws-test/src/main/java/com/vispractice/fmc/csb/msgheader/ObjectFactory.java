
package com.vispractice.fmc.csb.msgheader;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.vispractice.fmc.csb.msgheader package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.vispractice.fmc.csb.msgheader
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExtendParam }
     * 
     */
    public ExtendParam createExtendParam() {
        return new ExtendParam();
    }

    /**
     * Create an instance of {@link ExtendParamCollection }
     * 
     */
    public ExtendParamCollection createExtendParamCollection() {
        return new ExtendParamCollection();
    }

    /**
     * Create an instance of {@link MsgHeader }
     * 
     */
    public MsgHeader createMsgHeader() {
        return new MsgHeader();
    }

}
