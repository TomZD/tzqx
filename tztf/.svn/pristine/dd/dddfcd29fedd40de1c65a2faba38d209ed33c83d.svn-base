package cn.movinginfo.tztf.common.mq;


import javax.jms.Message;

public abstract class SimpleMQConsumer extends AbstractMqTool  { 
	
	public SimpleMQConsumer(boolean ep, boolean ec) {
		super(ep,ec);
	}
	
	public SimpleMQConsumer() {
		this(false,true);
	}

	public void onMessage(Message message) {}	   
}
