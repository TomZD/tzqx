package cn.movinginfo.tztf.common.mq;


import javax.jms.Message;

public class SimpleMQProducer extends AbstractMqTool  { 
	
	public SimpleMQProducer(boolean ec, boolean ep) {
		super(ec,ep);
	}
	
	public SimpleMQProducer() {
		this(true,false);
	}

	public void onMessage(Message message) {}	   
}
