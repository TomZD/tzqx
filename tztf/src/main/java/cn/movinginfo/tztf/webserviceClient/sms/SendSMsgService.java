
package cn.movinginfo.tztf.webserviceClient.sms;

import java.util.List;
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
@WebService(name = "SendSMsgService", targetNamespace = "http://cxf.SMsg.cn/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SendSMsgService {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns int
     * @throws ServiceException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sendMsg", targetNamespace = "http://cxf.SMsg.cn/", className = "cn.movinginfo.tztf.webserviceClient.sms.SendMsg")
    @ResponseWrapper(localName = "sendMsgResponse", targetNamespace = "http://cxf.SMsg.cn/", className = "cn.movinginfo.tztf.webserviceClient.sms.SendMsgResponse")
    public int sendMsg(
        @WebParam(name = "arg0", targetNamespace = "")
        List<String> arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1)
        throws ServiceException_Exception
    ;

}