
package cn.movinginfo.tztf.webserviceClient.warnWeibo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WarnWeiBoService", targetNamespace = "http://cxf.WarnWeibo.cn/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WarnWeiBoService {


    /**
     * 
     * @param arg0
     * @return
     *     returns cn.movinginfo.tztf.webserviceClient.warnWeibo.Status
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sendword", targetNamespace = "http://cxf.WarnWeibo.cn/", className = "cn.movinginfo.tztf.webserviceClient.warnWeibo.Sendword")
    @ResponseWrapper(localName = "sendwordResponse", targetNamespace = "http://cxf.WarnWeibo.cn/", className = "cn.movinginfo.tztf.webserviceClient.warnWeibo.SendwordResponse")
    public Status sendword(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns cn.movinginfo.tztf.webserviceClient.warnWeibo.Status
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "picsend", targetNamespace = "http://cxf.WarnWeibo.cn/", className = "cn.movinginfo.tztf.webserviceClient.warnWeibo.Picsend")
    @ResponseWrapper(localName = "picsendResponse", targetNamespace = "http://cxf.WarnWeibo.cn/", className = "cn.movinginfo.tztf.webserviceClient.warnWeibo.PicsendResponse")
    public Status picsend(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

}
