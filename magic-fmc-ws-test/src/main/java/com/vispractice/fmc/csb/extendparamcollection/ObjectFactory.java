
package com.vispractice.fmc.csb.extendparamcollection;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.vispractice.fmc.csb.extendparamcollection package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.vispractice.fmc.csb.extendparamcollection
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WfProcessTaskCollection }
     * 
     */
    public WfProcessTaskCollection createWfProcessTaskCollection() {
        return new WfProcessTaskCollection();
    }

    /**
     * Create an instance of {@link WfProcessTaskItem }
     * 
     */
    public WfProcessTaskItem createWfProcessTaskItem() {
        return new WfProcessTaskItem();
    }

    /**
     * Create an instance of {@link WfOpCollection }
     * 
     */
    public WfOpCollection createWfOpCollection() {
        return new WfOpCollection();
    }

    /**
     * Create an instance of {@link WfOpItem }
     * 
     */
    public WfOpItem createWfOpItem() {
        return new WfOpItem();
    }

    /**
     * Create an instance of {@link WfProcessVarCollection }
     * 
     */
    public WfProcessVarCollection createWfProcessVarCollection() {
        return new WfProcessVarCollection();
    }

    /**
     * Create an instance of {@link WfProcessVar }
     * 
     */
    public WfProcessVar createWfProcessVar() {
        return new WfProcessVar();
    }

}
