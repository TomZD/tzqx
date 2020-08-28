
package cn.movinginfo.tztf.webserviceClient.alarmFile;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.alarmfile.cxf package. 
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

    private final static QName _CreateAlarmFile_QNAME = new QName("http://cxf.alarmFile.cn/", "createAlarmFile");
    private final static QName _CreateAlarmFileResponse_QNAME = new QName("http://cxf.alarmFile.cn/", "createAlarmFileResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.alarmfile.cxf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateAlarmFileResponse }
     * 
     */
    public CreateAlarmFileResponse createCreateAlarmFileResponse() {
        return new CreateAlarmFileResponse();
    }

    /**
     * Create an instance of {@link CreateAlarmFile }
     * 
     */
    public CreateAlarmFile createCreateAlarmFile() {
        return new CreateAlarmFile();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateAlarmFile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cxf.alarmFile.cn/", name = "createAlarmFile")
    public JAXBElement<CreateAlarmFile> createCreateAlarmFile(CreateAlarmFile value) {
        return new JAXBElement<CreateAlarmFile>(_CreateAlarmFile_QNAME, CreateAlarmFile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateAlarmFileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cxf.alarmFile.cn/", name = "createAlarmFileResponse")
    public JAXBElement<CreateAlarmFileResponse> createCreateAlarmFileResponse(CreateAlarmFileResponse value) {
        return new JAXBElement<CreateAlarmFileResponse>(_CreateAlarmFileResponse_QNAME, CreateAlarmFileResponse.class, null, value);
    }

}
