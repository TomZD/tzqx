package cn.movinginfo.tztf.stb.socket;

import java.nio.charset.Charset;











import javax.annotation.PostConstruct;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringEncoder;




public class SocketStart  implements ApplicationListener<ContextRefreshedEvent>  {
	
//	@Override
//	public void onApplicationEvent(ContextRefreshedEvent arg0) {
//    	int port=Integer.valueOf(StbConUtil.getValue("port"));
//        try {
//			new Socket(port).start();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} // 启动
//		
//	}


//	public void start() {
//		
//        
//        new Thread(new Runnable() {
//        	  
//            @Override
//            public void run() {
//            	int port=Integer.valueOf(StbConUtil.getValue("port"));
//                try {
//        			new Socket(port).start();
//        		} catch (Exception e) {
//        			// TODO Auto-generated catch block
//        			e.printStackTrace();
//        		} // 启动
//            }
//        }).start();
//        
//       
//		
//	}

@Override
public void onApplicationEvent(ContextRefreshedEvent event) {
	new Thread(new Runnable() {
  	  
        @Override
        public void run() {
        	int port=Integer.valueOf(StbConUtil.getValue("port"));
            try {
    			new Socket(port).start();
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} // 启动
        }
    }).start();
	
}

	
	

	


	

}
