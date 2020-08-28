
package cn.movinginfo.tztf.webserviceClient.weibo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.weibo.cxf.service package. 
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

    private final static QName _SendwordResponse_QNAME = new QName("http://service.cxf.weibo.cn/", "sendwordResponse");
    private final static QName _PicsendResponse_QNAME = new QName("http://service.cxf.weibo.cn/", "picsendResponse");
    private final static QName _Sendword_QNAME = new QName("http://service.cxf.weibo.cn/", "sendword");
    private final static QName _Picsend_QNAME = new QName("http://service.cxf.weibo.cn/", "picsend");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.weibo.cxf.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendwordResponse }
     * 
     */
    public SendwordResponse createSendwordResponse() {
        return new SendwordResponse();
    }

    /**
     * Create an instance of {@link PicsendResponse }
     * 
     */
    public PicsendResponse createPicsendResponse() {
        return new PicsendResponse();
    }

    /**
     * Create an instance of {@link Sendword }
     * 
     */
    public Sendword createSendword() {
        return new Sendword();
    }

    /**
     * Create an instance of {@link Picsend }
     * 
     */
    public Picsend createPicsend() {
        return new Picsend();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link Status }
     * 
     */
    public Status createStatus() {
        return new Status();
    }

    /**
     * Create an instance of {@link WeiboResponse }
     * 
     */
    public WeiboResponse createWeiboResponse() {
        return new WeiboResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendwordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cxf.weibo.cn/", name = "sendwordResponse")
    public JAXBElement<SendwordResponse> createSendwordResponse(SendwordResponse value) {
        return new JAXBElement<SendwordResponse>(_SendwordResponse_QNAME, SendwordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PicsendResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cxf.weibo.cn/", name = "picsendResponse")
    public JAXBElement<PicsendResponse> createPicsendResponse(PicsendResponse value) {
        return new JAXBElement<PicsendResponse>(_PicsendResponse_QNAME, PicsendResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sendword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cxf.weibo.cn/", name = "sendword")
    public JAXBElement<Sendword> createSendword(Sendword value) {
        return new JAXBElement<Sendword>(_Sendword_QNAME, Sendword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Picsend }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.cxf.weibo.cn/", name = "picsend")
    public JAXBElement<Picsend> createPicsend(Picsend value) {
        return new JAXBElement<Picsend>(_Picsend_QNAME, Picsend.class, null, value);
    }

}
