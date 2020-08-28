package cn.movinginfo.tztf.stb.socket;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;




import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.stb.model.Stb;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;



public class ServerHandler extends ChannelInboundHandlerAdapter{
	private StbConnectHandler connectHandler;
	private String kid="";
	
	//连接建立
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	Channel ch = ctx.channel();
		InetSocketAddress address = (InetSocketAddress) ch.remoteAddress();
		String ip = address.getAddress().getHostAddress();
		System.out.println(ip);
        System.out.println(ctx.channel().localAddress().toString() + " 通道已激活！");
    }

    //连接关闭
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    	connectHandler=new StbConnectHandler();
    	System.out.println(kid+"已退出");
        System.out.println(ctx.channel().localAddress().toString() + " 通道不活跃！");
        if(!kid.equals("")){
        	connectHandler.stbDisconnected(ctx,kid);
        }
        // 关闭流

    }

    private String getMessage(ByteBuf buf) {
        byte[] con = new byte[buf.readableBytes()];
        buf.readBytes(con);
        try {
            return new String(con, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public  boolean isJsonObject(String content) {
       
        if(StringUtils.isBlank(content))
            return false;
        try {
            JSONObject jsonStr = JSONObject.parseObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean  getStatus (String []time){
    	SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    	String now=format.format(new Date());
    	int nowtime=Integer.parseInt(now.split(":")[0])*60+Integer.parseInt(now.split(":")[1]);
    	int first=Integer.parseInt(time[0].split(":")[0])*60+Integer.parseInt(time[0].split(":")[1]);
    	int last=Integer.parseInt(time[1].split(":")[0])*60+Integer.parseInt(time[1].split(":")[1]);
    	if(nowtime>first&&nowtime<last){
    		return true;
    	}
    	return false;
    }
    public String getErr(String err){
    	JSONObject jsonStr = new JSONObject();
    	jsonStr.put("result", "fail");
    	jsonStr.put("err", err);
    	return jsonStr.toJSONString();
    }



  //读取客户端发送过来的信息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
    	connectHandler=new StbConnectHandler();
        ByteBuf buf = (ByteBuf) msg;
        String rev = getMessage(buf);
        System.out.println("客户端数据:" + rev);
        if(isJsonObject(rev)){
        	JSONObject jsonStr = JSONObject.parseObject(rev);

        			if(jsonStr.containsKey("id")){
                		Stb stb=new Stb();
                		String id=jsonStr.getString("id");
                		kid=id;
                		boolean status=false;
                		if(jsonStr.getString("status").equals("OPEN_ALL")){
                			status=true;
                		}
                		if(jsonStr.containsKey("latlon")){
                			Double lat =Double.valueOf(jsonStr.getString("latlon").split(",")[0]);
                    		Double lon =Double.valueOf(jsonStr.getString("latlon").split(",")[1]);
                    		stb.setLat(lat);
                    		stb.setLon(lon);
                		}
                		if(jsonStr.containsKey("lat")&&!jsonStr.getString("lat").equals("null")){
                			Double lat =Double.valueOf(jsonStr.getString("lat"));
                			stb.setLat(lat);
                		}
                		if(jsonStr.containsKey("lon")&&!jsonStr.getString("lon").equals("null")){
                			Double lon =Double.valueOf(jsonStr.getString("lon"));
                			stb.setLon(lon);
                		}
                		if(jsonStr.containsKey("street")&&!jsonStr.getString("street").equals("null")){
                    		stb.setArea(jsonStr.getString("street"));
                		}
                		if(jsonStr.containsKey("address")&&!jsonStr.getString("address").equals("null")){
                    		stb.setAddress(jsonStr.getString("address"));
                		}
                		stb.setEquipmentcode(id);
                		stb.setSwitchStatus(status);
                		connectHandler.stbSwitchStatus(ctx,stb);
                	}
        			else{
        	        	ctx.write(getErr("200"));
        	        	ctx.flush();
        	        }

        	
        }else{
        	ctx.write(getErr("200"));
        	ctx.flush();
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
       
    }

    //服务端发生异常的操作
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息：\r\n" + cause.getMessage());
    }
//  //读取客户端发送过来的信息
//  @Override
//  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//  	JSONObject result=new JSONObject();
//  	SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
//      ByteBuf buf = (ByteBuf) msg;
//      String rev = getMessage(buf);
//      System.out.println("客户端数据:" + rev);
//      if(isJsonObject(rev)){
//      	JSONObject jsonStr = JSONObject.parseObject(rev);
//      	if(jsonStr.containsKey("id")){
//      		String id=jsonStr.getString("id");
//      		kid=id;
//      		if(TimeConUtil.isContains(id)){
//      			String []time=TimeConUtil.getValue(id).split("-");
//      			if(getStatus(time)){
//      				result.put("result", "sucess");
//      				result.put("id", id);
//      				result.put("status", "OPEN_ALL");
//      				result.put("version", "1.1.1");
//      			}else{
//      				result.put("result", "sucess");
//      				result.put("id", id);
//      				result.put("status", "CLOSE_ALL");
//      				result.put("version", "1.1.1");
//      			}
//      			ctx.write(result.toJSONString());
//              	ctx.flush();
//      		}else{
//      			TimeConUtil.add(id);
//      			String []time=TimeConUtil.getValue(id).split("-");
//      			if(getStatus(time)){
//      				result.put("result", "sucess");
//      				result.put("id", id);
//      				result.put("status", "OPEN_ALL");
//      				result.put("version", "1.1.1");
//      			}else{
//      				result.put("result", "sucess");
//      				result.put("id", id);
//      				result.put("status", "CLOSE_ALL");
//      				result.put("version", "1.1.1");
//      			}
//      			ctx.write(result.toJSONString());
//              	ctx.flush();
//      			
//      		}
//      	}
////      	else if(jsonStr.containsKey("new")){
////      		String nid=jsonStr.getString("new");
////      		if(TimeConUtil.isContains(nid)){
////      			ctx.write(getErr("100"));
////              	ctx.flush();
////      		}else{
////      			TimeConUtil.add(nid);
////      			result.put("result", "sucess");
////  				result.put("new", nid);
////      			ctx.write(result.toJSONString());
////              	ctx.flush();
////      		}
////      	}
//      }else{
//      	ctx.write(getErr("200"));
//      	ctx.flush();
//      }
//
//  }

}
