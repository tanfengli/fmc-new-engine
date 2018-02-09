
package com.vispractice.fmc.webservice.callback;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.vispractice.fmc.webservice.callback package. 
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

    private final static QName _NodeComplementCallback_QNAME = new QName("http://webservice.facade.core.boe.eas.fssc.vispractice.com/", "nodeComplementCallback");
    private final static QName _NodeComplementCallbackResponse_QNAME = new QName("http://webservice.facade.core.boe.eas.fssc.vispractice.com/", "nodeComplementCallbackResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.vispractice.fmc.webservice.callback
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WorkFlowParms }
     * 
     */
    public WorkFlowParms createWorkFlowParms() {
        return new WorkFlowParms();
    }

    /**
     * Create an instance of {@link WorkFlowParms.Vars }
     * 
     */
    public WorkFlowParms.Vars createWorkFlowParmsVars() {
        return new WorkFlowParms.Vars();
    }

    /**
     * Create an instance of {@link WorkFlowParms.Attrs }
     * 
     */
    public WorkFlowParms.Attrs createWorkFlowParmsAttrs() {
        return new WorkFlowParms.Attrs();
    }

    /**
     * Create an instance of {@link NodeComplementCallback }
     * 
     */
    public NodeComplementCallback createNodeComplementCallback() {
        return new NodeComplementCallback();
    }

    /**
     * Create an instance of {@link NodeComplementCallbackResponse }
     * 
     */
    public NodeComplementCallbackResponse createNodeComplementCallbackResponse() {
        return new NodeComplementCallbackResponse();
    }

    /**
     * Create an instance of {@link WorkFlowParms.Vars.Entry }
     * 
     */
    public WorkFlowParms.Vars.Entry createWorkFlowParmsVarsEntry() {
        return new WorkFlowParms.Vars.Entry();
    }

    /**
     * Create an instance of {@link WorkFlowParms.Attrs.Entry }
     * 
     */
    public WorkFlowParms.Attrs.Entry createWorkFlowParmsAttrsEntry() {
        return new WorkFlowParms.Attrs.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NodeComplementCallback }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.facade.core.boe.eas.fssc.vispractice.com/", name = "nodeComplementCallback")
    public JAXBElement<NodeComplementCallback> createNodeComplementCallback(NodeComplementCallback value) {
        return new JAXBElement<NodeComplementCallback>(_NodeComplementCallback_QNAME, NodeComplementCallback.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NodeComplementCallbackResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.facade.core.boe.eas.fssc.vispractice.com/", name = "nodeComplementCallbackResponse")
    public JAXBElement<NodeComplementCallbackResponse> createNodeComplementCallbackResponse(NodeComplementCallbackResponse value) {
        return new JAXBElement<NodeComplementCallbackResponse>(_NodeComplementCallbackResponse_QNAME, NodeComplementCallbackResponse.class, null, value);
    }

}
