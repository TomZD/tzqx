
package cn.movinginfo.tztf.webserviceClient.getXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CreateM4XmlAndImageResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "createM4XmlAndImageResult"
})
@XmlRootElement(name = "CreateM4XmlAndImageResponse")
public class CreateM4XmlAndImageResponse {

    @XmlElement(name = "CreateM4XmlAndImageResult")
    protected boolean createM4XmlAndImageResult;

    /**
     * ��ȡcreateM4XmlAndImageResult���Ե�ֵ��
     * 
     */
    public boolean isCreateM4XmlAndImageResult() {
        return createM4XmlAndImageResult;
    }

    /**
     * ����createM4XmlAndImageResult���Ե�ֵ��
     * 
     */
    public void setCreateM4XmlAndImageResult(boolean value) {
        this.createM4XmlAndImageResult = value;
    }

}
