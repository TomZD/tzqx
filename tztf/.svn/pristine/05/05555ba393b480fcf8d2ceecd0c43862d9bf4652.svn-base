package cn.movinginfo.tztf.sen.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import cn.movinginfo.tztf.common.typhoon.AnimatedGifEncoder;
import cn.movinginfo.tztf.common.typhoon.BufferedImageBuilder;
import cn.movinginfo.tztf.common.typhoon.ConfigHelper;
import cn.movinginfo.tztf.common.typhoon.GISHelper;
import cn.movinginfo.tztf.common.typhoon.TyphoonUtil;
import cn.movinginfo.tztf.sen.model.TyphoonInfo;
import cn.movinginfo.tztf.sen.model.TyphoonInfoDetail;
import cn.movinginfo.tztf.sen.model.TyphoonInfoItem;
import cn.movinginfo.tztf.sen.model.TyphoonItemForecastInfo;
import cn.movinginfo.tztf.sen.model.TyphoonItemForecastPoint;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service(value = "typhoonTask")
public class TyphoonTask {
	private static final Logger log = LoggerFactory.getLogger(TyphoonTask.class);

    public final static int LON_INTERVAL = 111;

    private static final String TYPHOON_LOCAL_PATH=ConfigHelper.getProperty("typhoonLocalPath");
    // 获取所有发布者
    private static final String ALL_FABUZHE_SQL= "SELECT DISTINCT fabuzhe FROM typhoonnew";
    // 根据编号获得该台风的所有发布者
    private static final String ALL_FABUZHE_BIANHAO_SQL="SELECT DISTINCT fabuzhe  FROM typhoonnew WHERE bianhao=''{0}''";
	// 获取所有台风编号
    private static final String ALL_TYPHOON_SQL = "SELECT bianhao, Max(zhongwenbianhao) as zhongwenbianhao FROM typhoonnew WHERE zhongwenbianhao is not null AND zhongwenbianhao !='' GROUP BY bianhao ORDER BY bianhao DESC";
	// 获取发布者对应的所有的台风编号SQL
	private static final String ALL_TYPHOON_FABUZHE_SQL = "SELECT bianhao, goujibianhao, MAX (zhongwenbianhao) AS zhongwenbianhao FROM typhoonnew WHERE fabuzhe = ''{0}'' GROUP BY bianhao, goujibianhao ORDER BY bianhao DESC";
	// 最新的48小时的台风编号SQL
//	private static final String NEW_TYPHOON_SQL = "SELECT DISTINCT bianhao FROM typhoonnew WHERE LEFT (bianhao, 4) = DATEPART(YEAR, GETDATE()) AND DATEDIFF(HOUR, CONVERT(datetime,LEFT(bianhao, 4)+'-'+LEFT(xianzaishijian, 2)+'-'+SUBSTRING(xianzaishijian,3,2)+' '+SUBSTRING(xianzaishijian,5,2)+':00:00'), GETDATE())<24";
	private static final String NEW_TYPHOON_SQL = "SELECT DISTINCT bianhao FROM typhoonnew WHERE LEFT (bianhao, 4) = (SELECT DATE_FORMAT(NOW(), '%Y')) AND DATEDIFF( STR_TO_DATE( CONCAT(LEFT(bianhao, 4),'-',LEFT(xianzaishijian, 2),'-',SUBSTRING(xianzaishijian,3,2),' ',SUBSTRING(xianzaishijian,5,2),':00:00'),'%Y-%m-%d %H:%i:%s'), NOW())>-2";
	// 根据编号获取某个台风的多种模式的具体的信息的SQL
	private static final String TYPHOON_INFO_SQL = "SELECT DISTINCT bianhao, fabuzhe, zhongwenbianhao, xianzaishijian, yubaoshixiao,yubaoshijian, xianzaijindu, xianzaiweidu, yubaojindu, yubaoweidu, xianzaiqiya, xianzaifengli, fenglifor7, fenglifor10, yidongsudu, yidongfangxiang, yubaofengli, yubaoqiya FROM typhoonnew WHERE bianhao = ''{0}'' ORDER BY xianzaishijian DESC, fabuzhe, yubaoshixiao";
	// 所有 的台风的具体信息SQL
	private static final String ALL_TYPHOON_INFO_SQL = "SELECT DISTINCT bianhao, fabuzhe, zhongwenbianhao, xianzaishijian, yubaoshixiao,yubaoshijian, xianzaijindu, xianzaiweidu, yubaojindu, yubaoweidu, xianzaiqiya, xianzaifengli, fenglifor7, fenglifor10, yidongsudu, yidongfangxiang, yubaofengli, yubaoqiya FROM typhoonnew ORDER BY bianhao, xianzaishijian DESC, fabuzhe, yubaoshixiao";

	@Autowired
	private JdbcTemplate jdbcTemplateTyphoon;

	public static void main(String[] args)
	{
		TyphoonTask task=new TyphoonTask();
		task.job2();
	}

	//生成最新的台风文件列表和历史以来的台风数据
	public void job2()
	{
		if (null != jdbcTemplateTyphoon) {
			try {
				List<TyphoonInfo> allTyphoonInfos = getAllTyphoonInfo(); //所有台风列表
                //生成历史台风文件
                for (TyphoonInfo typhoonInfo:allTyphoonInfos
                        ) {
                	String bianhao=typhoonInfo.getBianhao();
					String fileName=TYPHOON_LOCAL_PATH+"\\typhoonInfo_"+bianhao+".json";
					File file=new File(fileName);
					if(file.exists())
					{
						continue;
					}
					TyphoonInfoDetail detail = getTyphoonInfoDetail(bianhao);
					String jsonstr = JSONArray.toJSONString(detail);
					writeFileContent(fileName,jsonstr);
                }

				String newBianhao = getNewTyphoonInfo();

				if (!"".equals(newBianhao)) {// 最新台风编号不为空
					//创建新的所有台风列表文件
					String typhoonItemsXml = TyphoonUtil.createTyphoonItemsXml(allTyphoonInfos, newBianhao);
					//对新台风生成详细信息的json文件
					for (String bianhao:newBianhao.split(",")
						 ) {
						String fileName=TYPHOON_LOCAL_PATH+"\\typhoonInfo_"+bianhao+".json";
						TyphoonInfoDetail detail = getTyphoonInfoDetail(bianhao);
						String jsonstr = JSONArray.toJSONString(detail);
						writeFileContent(fileName,jsonstr);
					}
				} else {
					String outPath = ConfigHelper.getProperty("typhoonLocalPath");
					String outFile = outPath +"\\"+ "typhoonItems.xml";
					File file=new File(outFile);
					if(!file.exists()) {
						String typhoonItemsXml = TyphoonUtil.createTyphoonItemsXml(allTyphoonInfos, "");
					}
				}
			} catch (Exception e) {
				log.error("job!", e);
			}
		}
	}

	private TyphoonInfoDetail getTyphoonInfoDetail(String bianhao) throws Exception
	{
		List<TyphoonInfo> typhoonInfoList= getTyphoonInfo(bianhao);
		TyphoonInfoDetail detail=new TyphoonInfoDetail();
		detail.setId(bianhao);
		detail.setName(typhoonInfoList.get(0).getZhongwenbianhao());
		detail.setType(TyphoonUtil.getTypeByFengli(typhoonInfoList.get(0).getXianzaifengli()));
		HashMap<String,TyphoonInfoItem> map1=new HashMap<>(); //保存台风实况路径
		HashMap<String,List<TyphoonInfo>> map2=new HashMap<>(); //保存台风预报路径
		for (TyphoonInfo info: typhoonInfoList
				) {
			if(!isInteger(info.getYubaoshixiao())) continue;
			if(Integer.parseInt(info.getYubaoshixiao())==0)
			{
				if(!map1.containsKey(info.getXianzaishijian()))
				{
					String key=info.getXianzaishijian();
					TyphoonInfoItem item=new TyphoonInfoItem();
					item.setLon(info.getXianzaijindu());
					item.setLat(info.getXianzaiweidu());
					item.setDistance(info.getXianzaijindu(),info.getXianzaiweidu());
					item.setLv7(info.getFenglifor7());
					item.setLv10(info.getFenglifor10());
					item.setMoveVelocity(info.getYidongsudu());
					item.setPressure(info.getXianzaiqiya());
					item.setTime(info.getXianzaishijian());
					item.setWindPower(TyphoonUtil.getWindPowerByFengli(info.getXianzaifengli()));
					item.setWindVelocity(info.getXianzaifengli());
					item.setDirection(info.getYidongfangxiang());
					item.setType(TyphoonUtil.getTypeByFengli(info.getXianzaifengli()));
					map1.put(key,item);
				}
			}
			else
			{
				if(!map2.containsKey(info.getXianzaishijian()))
				{
					String key=info.getXianzaishijian();
					List<TyphoonInfo> list=new ArrayList<>();
					map2.put(key,list);
				}
				List<TyphoonInfo> list=map2.get(info.getXianzaishijian());
				list.add(info);
				map2.put(info.getXianzaishijian(),list);
			}
		}
		List<TyphoonInfoItem> typhoonInfoItemList=new ArrayList<>();
		for (Map.Entry entry1:map1.entrySet()
				) {
			String time=entry1.getKey().toString();
			TyphoonInfoItem item=(TyphoonInfoItem)entry1.getValue();
			List<TyphoonItemForecastInfo> forecasts=new ArrayList<>();
			List<TyphoonInfo> list=map2.get(time);
			if(list!=null&&list.size()>0)
			{
				Map<String,List<TyphoonInfo>> map3=list.stream().collect(Collectors.groupingBy(TyphoonInfo::getFabuzhe));
				for (Map.Entry entry2:map3.entrySet()
						) {
					String name=entry2.getKey().toString();
					List<TyphoonInfo> list1=(List<TyphoonInfo>)entry2.getValue();
					TyphoonItemForecastInfo forecastInfo=new TyphoonItemForecastInfo();
					forecastInfo.setName(name);
					List<TyphoonItemForecastPoint> points=new ArrayList<>();
					for (TyphoonInfo info:list1
							) {
						TyphoonItemForecastPoint point=new TyphoonItemForecastPoint();
						point.setLat(info.getYubaoweidu());
						point.setLon(info.getYubaojindu());
						point.setDistance(info.getYubaojindu(), info.getYubaoweidu());
						point.setPressure(info.getYubaoqiya());
						point.setWindPower(TyphoonUtil.getWindPowerByFengli(info.getYubaofengli()));
						point.setTime(info.getYubaoshijian());
						point.setWindVelocity(info.getYubaofengli());
						points.add(point);
					}
					Collections.sort(points,(a,b)->a.getTime().compareTo(b.getTime()));
					forecastInfo.setPoints(points);
					forecasts.add(forecastInfo);
				}
			}
			item.setForecasts(forecasts);
			typhoonInfoItemList.add(item);
		}
		Collections.sort(typhoonInfoItemList,(a,b)->a.getTime().compareTo(b.getTime()));
		detail.setTyphoonInfoItems(typhoonInfoItemList);
		return detail;
	}

	private TyphoonInfoDetail getTyphoonInfoDetailFromFile(String bianhao)throws Exception
	{
		String fileName=TYPHOON_LOCAL_PATH+"\\typhoonInfo_"+bianhao+".json";
		File file=new File(fileName);
		if(file.exists())
		{
			String content=read(fileName);
			TyphoonInfoDetail detail = JSON.parseObject(content,TyphoonInfoDetail.class);
			return detail;
		}
		else
		{
			return null;
		}
	}
	/**
	* @Author: BaoLongHui
	* @Description 根据编号获得该台风的所有发布者
	* @Date 18:36 2018/4/28
	*/
	public List<String> getPublisher(String bianhao)
	{
	    String sql=MessageFormat.format(ALL_FABUZHE_BIANHAO_SQL,bianhao);
		return this.getAllPublisher(sql);
	}
	/**
	* @Author: BaoLongHui
	* @Description 获得所有发布者
	* @Date 16:17 2018/4/28
	*/
	public List<String> getAllPublisher()
	{
		return this.getAllPublisher(ALL_FABUZHE_SQL);
	}
	private List<String> getAllPublisher(String SQL)
	{
		List<String> result=jdbcTemplateTyphoon.query(SQL, new ResultSetExtractor<List<String>>() {
			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<String> result=new ArrayList<>();
				while (rs.next())
				{
					String publisher=rs.getString("fabuzhe");
					result.add(publisher);
				}
				return result;
			}
		});
		return result;
	}

	/**
	 * 获取台风简易列表，包括全部新台风，活动台风
	 * 
	 * @return List<TyphoonInfo>
	 */
	public List<TyphoonInfo> getAllTyphoonInfo() throws Exception {
		List<TyphoonInfo> result = jdbcTemplateTyphoon.query(ALL_TYPHOON_SQL,
				new ResultSetExtractor<List<TyphoonInfo>>() {
					@Override
					public List<TyphoonInfo> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<TyphoonInfo> result = new ArrayList<TyphoonInfo>();
						while (rs.next()) {
							TyphoonInfo e = new TyphoonInfo();
							e.setBianhao(rs.getString("bianhao"));
							e.setZhongwenbianhao(rs.getString("zhongwenbianhao"));
							result.add(e);
						}
						return result;
					}

				});

		return result;
	}

	/**
	 * 获取48小时内的台风编号
	 * 
	 * @return
	 */
	public String getNewTyphoonInfo() throws Exception {
		String result = jdbcTemplateTyphoon.query(NEW_TYPHOON_SQL, new ResultSetExtractor<String>() {

			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				String result = "";
				while (rs.next()) {
					result += rs.getString("bianhao") + ",";
				}
				if (!StringUtils.isEmpty(result)) {
					result = result.substring(0, result.length() - 1);
				}
				return result;
			}

		});
		return result;
	}

	/**
	 * 获取台风具体的路径信息(包含各种模式的预报)
	 * 
	 * @param bianhao
	 *            编号
	 * @return
	 */
	public List<TyphoonInfo> getTyphoonInfo(String bianhao) throws Exception {
		String typhoonInfoSql = MessageFormat.format(TYPHOON_INFO_SQL, bianhao);
		List<TyphoonInfo> result = jdbcTemplateTyphoon.query(typhoonInfoSql,
				new ResultSetExtractor<List<TyphoonInfo>>() {
					@Override
					public List<TyphoonInfo> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<TyphoonInfo> result = new ArrayList<TyphoonInfo>();
						String zhongwenbianhao = "";
						while (rs.next()) {
							if(StringUtils.isNotEmpty(rs.getString("zhongwenbianhao"))){
								zhongwenbianhao = rs.getString("zhongwenbianhao");
							}
							TyphoonInfo e = new TyphoonInfo();
							e.setBianhao(StringUtils.defaultIfEmpty(rs.getString("bianhao"), ""));
							e.setZhongwenbianhao(StringUtils.defaultIfEmpty(zhongwenbianhao, ""));
							e.setXianzaishijian(StringUtils.defaultIfEmpty(rs.getString("xianzaishijian"), ""));
							e.setXianzaifengli(StringUtils.defaultIfEmpty(rs.getString("xianzaifengli"), ""));
							e.setXianzaijindu(StringUtils.defaultIfEmpty(rs.getString("xianzaijindu"), "").split("E")[0]);
							e.setXianzaiweidu(StringUtils.defaultIfEmpty(rs.getString("xianzaiweidu"), "").split("N")[0]);
							e.setXianzaiqiya(StringUtils.defaultIfEmpty(rs.getString("xianzaiqiya"), ""));
							e.setYubaoshixiao(StringUtils.defaultIfEmpty(rs.getString("yubaoshixiao"), ""));
							e.setFabuzhe(StringUtils.defaultIfEmpty(rs.getString("fabuzhe"), ""));
							e.setYubaofengli(StringUtils.defaultIfEmpty(rs.getString("yubaofengli"), ""));
							e.setYubaojindu(StringUtils.defaultIfEmpty(rs.getString("yubaojindu"), "").split("E")[0]);
							e.setYubaoweidu(StringUtils.defaultIfEmpty(rs.getString("yubaoweidu"), "").split("N")[0]);
							e.setYubaoqiya(StringUtils.defaultIfEmpty(rs.getString("yubaoqiya"), ""));
							e.setYidongfangxiang(StringUtils.defaultIfEmpty(rs.getString("yidongfangxiang"), ""));
							e.setYidongsudu(StringUtils.defaultIfEmpty(rs.getString("yidongsudu"), ""));
							e.setFenglifor7(StringUtils.defaultIfEmpty(rs.getString("fenglifor7"), ""));
							e.setFenglifor10(StringUtils.defaultIfEmpty(rs.getString("fenglifor10"), ""));
							e.setYubaoshijian(StringUtils.defaultIfEmpty(rs.getString("yubaoshijian"), ""));
							result.add(e);
						}
						return result;
					}

				});
		return result;
	}


	/**
	 * @Author: BaoLongHui
	 * @Description 获取海宁国家站的经纬度
	 * @Date 14:18 2018/4/26
	 */
	public Point2D getHaiNingLocation()
	{
		String location=ConfigHelper.getProperty("HaiNingLocation");
		String[] s= location.split("\\|");
		Point2D result=new Point2D.Float();
		result.setLocation(Float.parseFloat(s[0]),Float.parseFloat(s[1]));
		return result;
	}

    /**
    * @Author: BaoLongHui
    * @Description 导出台风动态图
    * @Date 10:22 2018/4/27
    */
    public byte[] exportTyphoonGif(HttpServletRequest request, HttpServletResponse response,String bianhao) throws Exception
    {
		String typhoonGifDir=request.getSession().getServletContext().getRealPath("/typhoonGif");
		File typhoondir=new File(typhoonGifDir);
		if (!typhoondir.exists()) {
			typhoondir.mkdirs();
		}
		String gifName =typhoonGifDir+"\\"+"typhoon_"+bianhao+".gif";
		File gif=new File(gifName);
		if(gif.exists())
		{
			return getBytes(gif);
		}
        String tempDir=request.getSession().getServletContext().getRealPath("/temp");
        File dir=new File(tempDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String BaseMapPath=ConfigHelper.getProperty("typhoonMap");
        String MapRange=ConfigHelper.getProperty("typhoonMapRange");
        String[] points= MapRange.split("\\|");
        //底图左上角经纬度
        Point2D leftTop=new Point2D.Float();
        leftTop.setLocation(Float.parseFloat(points[0].split(",")[0]),Float.parseFloat(points[0].split(",")[1]));
        leftTop=GISHelper.location2Mercator(leftTop);
        //底图右下角经纬度
        Point2D rightBottom=new Point2D.Float();
        rightBottom.setLocation(Float.parseFloat(points[1].split(",")[0]),Float.parseFloat(points[1].split(",")[1]));
        rightBottom=GISHelper.location2Mercator(rightBottom);

        Image bimgsrc=Toolkit.getDefaultToolkit().getImage(BaseMapPath);
        BufferedImage bimg= BufferedImageBuilder.toBufferedImage(bimgsrc);
        int imgWidth=bimg.getWidth();
        int imgHeight=bimg.getHeight();
        //得到Graphics2D 对象
//        Graphics2D g2d=bimg.createGraphics();
//        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
        // 抗锯齿
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //画海宁站点位置
//        drawHaiNingPoint(g2d,imgWidth,imgHeight,leftTop,rightBottom);
        //画台风图例
//        drawTyphoonLegend(g2d,imgWidth,imgHeight);
        //画台风路径
        List<String> tempImgs=drawTyphoonRoad(bimg,tempDir,bianhao,imgWidth,imgHeight,leftTop,rightBottom);
		String[] strings = new String[tempImgs.size()];
		jpgToGif(tempImgs.toArray(strings),gifName,100);
        return getBytes(new File(gifName));

    }
    /**
    * @Author: BaoLongHui
    * @Description 在台风底图上画海宁坐标点
    * @Date 10:23 2018/4/27
    */
    private void drawHaiNingPoint(Graphics2D g2d,int imgWidth,int imgHeight,Point2D leftTop,Point2D rightBottom)
    {
        String zuobiaodian=ConfigHelper.getProperty("zuobiaodian");
        Point2D haiNingLocation=getHaiNingLocation();
        Image zhandianimgsrc=Toolkit.getDefaultToolkit().getImage(zuobiaodian);
        BufferedImage zhandianimg= BufferedImageBuilder.toBufferedImage(zhandianimgsrc);
        Point2D drawPoint=getDrawLocation(imgWidth,imgHeight,leftTop,rightBottom,haiNingLocation);
        g2d.drawImage(zhandianimg,(int)(drawPoint.getX()-zhandianimg.getWidth()/2),(int)(drawPoint.getY()-zhandianimg.getHeight()),zhandianimg.getWidth(),zhandianimg.getHeight(),null);
    }

    /**
    * @Author: BaoLongHui
    * @Description 在台风底图上画台风路径
    * @Date 10:35 2018/4/27
    */
    private List<String> drawTyphoonRoad(BufferedImage bimg ,String tempDir,String bianhao,int imgWidth,int imgHeight,Point2D leftTop,Point2D rightBottom) throws Exception
    {
		List<String> tempImgs=new ArrayList<>();

        TyphoonInfoDetail detail=getTyphoonInfoDetailFromFile(bianhao);
        for(int i=0;i<detail.getTyphoonInfoItems().size();i++)
        {
        	try
			{
				Graphics2D g2d =bimg.createGraphics();
				g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				TyphoonInfoItem typhoonInfo=detail.getTyphoonInfoItems().get(i);
				Point2D lonLat=new Point2D.Float();
				lonLat.setLocation(Double.parseDouble(typhoonInfo.getLon()), Double.parseDouble( typhoonInfo.getLat()));
				Point2D drawPoint=getDrawLocation(imgWidth,imgHeight,leftTop,rightBottom,lonLat);
				String typhoonType=TyphoonUtil.getTypeByFengli( typhoonInfo.getWindVelocity());
				Color drawColor=getTyphoonColor(typhoonType);
				g2d.setColor(drawColor);
				g2d.fillOval((int)drawPoint.getX()-4,(int)drawPoint.getY()-4,8,8);
				g2d.setColor(Color.black);
				g2d.setStroke(new BasicStroke(1));
				g2d.drawOval((int)drawPoint.getX()-4,(int)drawPoint.getY()-4,8,8);
				if(i>0) //获得台风的上一个坐标位置
				{
					Point2D lastlonLat=new Point2D.Float();
					TyphoonInfoItem lasttyphoonInfo=detail.getTyphoonInfoItems().get(i-1);
					lastlonLat.setLocation(Double.parseDouble(lasttyphoonInfo.getLon()), Double.parseDouble( lasttyphoonInfo.getLat()));
					Point2D lastdrawPoint=getDrawLocation(imgWidth,imgHeight,leftTop,rightBottom,lastlonLat);
					if(Math.abs((int)lastdrawPoint.getX()-(int)drawPoint.getX())>8 || Math.abs((int)drawPoint.getY()-(int)lastdrawPoint.getY())>8)
					{
						int x1,x2,y1,y2;
						if(Math.abs((int)lastdrawPoint.getX()-(int)drawPoint.getX())>8 && Math.abs((int)drawPoint.getY()-(int)lastdrawPoint.getY())>8)
						{
							if(lastdrawPoint.getX()>drawPoint.getX())
							{
								x1=(int)drawPoint.getX()+8;
								x2=(int)lastdrawPoint.getX();
							}
							else
							{
								x1=(int)drawPoint.getX();
								x2=(int)lastdrawPoint.getX()+8;
							}
							if(lastdrawPoint.getY()>drawPoint.getY())
							{
								y1=(int)drawPoint.getY()+8;
								y2=(int)lastdrawPoint.getY();
							}
							else
							{
								y1=(int)drawPoint.getY();
								y2=(int)lastdrawPoint.getY()+8;
							}
						}
						else if(Math.abs((int)lastdrawPoint.getX()-(int)drawPoint.getX())>8 && Math.abs((int)drawPoint.getY()-(int)lastdrawPoint.getY())<=8) {
							if (lastdrawPoint.getX() > drawPoint.getX()) {
								x1 = (int) drawPoint.getX() + 8;
								y1 = (int) drawPoint.getY() + 4;
								x2 = (int) lastdrawPoint.getX();
								y2 = (int) lastdrawPoint.getY() + 4;
							} else {
								x1 = (int) drawPoint.getX();
								y1 = (int) drawPoint.getY() + 4;
								x2 = (int) lastdrawPoint.getX() + 8;
								y2 = (int) lastdrawPoint.getY() + 4;

							}
						}
						else
						{
							if(lastdrawPoint.getY()>drawPoint.getY())
							{
								x1=(int)drawPoint.getX()+4;
								y1=(int)drawPoint.getY()+8;
								x2=(int)lastdrawPoint.getX()+4;
								y2=(int)lastdrawPoint.getY();


							}
							else
							{
								x1=(int)drawPoint.getX()+4;
								y1=(int)drawPoint.getY();
								x2=(int)lastdrawPoint.getX()+4;
								y2=(int)lastdrawPoint.getY()+8;
							}

						}
						x1=x1-4;
						x2=x2-4;
						y1=y1-4;
						y2=y2-4;
						g2d.drawLine(x1,y1,x2,y2);
					}

				}
				//画风圈半径
				{
					BufferedImage newimg=copyBufferedImage(bimg);
					Graphics2D g2 =newimg.createGraphics();
					if(StringUtils.isNotBlank(typhoonInfo.getLv7().trim()) && !typhoonInfo.getLv7().trim().equals("9999"))
					{
						Double fenglifor7=Double.parseDouble(typhoonInfo.getLv7().trim()); //7级风圈半径
						Point2D Level7WindlonLat=new Point2D.Float();
						double windRadius=fenglifor7/LON_INTERVAL;
						Level7WindlonLat.setLocation(lonLat.getX()-windRadius,lonLat.getY()+windRadius);
						Point2D windCircleDrawPoint= getDrawLocation(imgWidth,imgHeight,leftTop,rightBottom,Level7WindlonLat);
						int windCircleWidth=(int)(drawPoint.getX()-windCircleDrawPoint.getX())*2;
						g2.setColor(new Color(51,186,78,255));
						g2.drawOval((int)windCircleDrawPoint.getX(),(int)windCircleDrawPoint.getY(),windCircleWidth,windCircleWidth);
						g2.setColor(new Color(51,186,78,120));
						g2.fillOval((int)windCircleDrawPoint.getX(),(int)windCircleDrawPoint.getY(),windCircleWidth,windCircleWidth);
					}
					String fileName =UUID.randomUUID().toString();
					ImageIO.write(newimg, "png", new File(tempDir+"\\"+fileName+".png"));
					tempImgs.add(tempDir+"\\"+fileName+".png");
				}
			}
			catch (Exception ex)
			{
				log.error(ex.toString());
				continue;
			}
        }
        return tempImgs;
    }

    /**
    * @Author: BaoLongHui
    * @Description 在台风底图上画台风图例
    * @Date 14:47 2018/4/27
    */
    private void drawTyphoonLegend(Graphics2D g2d,int imgWidth,int imgHeight)
    {
        int x=imgWidth-120;
        int y=imgHeight-190;
        String typhoonLegendPath=  ConfigHelper.getProperty("typhoonLegend");
        Image imgsrc=Toolkit.getDefaultToolkit().getImage(typhoonLegendPath);
        BufferedImage img= BufferedImageBuilder.toBufferedImage(imgsrc);
        g2d.drawImage(img,x,y,img.getWidth(),img.getHeight(),null);
//        g2d.drawImage( img.getScaledInstance(img.getWidth(),  img.getHeight(), Image.SCALE_SMOOTH),x, y,null);
    }

    /**
    * @Author: BaoLongHui
    * @Description 根据经纬度获取该点在图片上的位置
    * @Date 14:45 2018/4/27
    */
    private Point2D getDrawLocation(int imgWidth, int imgHeight,Point2D leftTop,Point2D rightBottom,Point2D yourLocation)
    {
        Point2D location=GISHelper.location2Mercator(yourLocation);
        Point2D result=new Point2D.Float();
        Double x=((location.getX()-leftTop.getX())/(rightBottom.getX()-leftTop.getX()))*imgWidth;
        Double y=((location.getY()-leftTop.getY())/(rightBottom.getY()-leftTop.getY()))*imgHeight;
        result.setLocation(x,y);
        return  result;
    }
    /**
    * @Author: BaoLongHui
    * @Description 获得台风类型对应的图例颜色
    * @Date 14:45 2018/4/27
    */
    private Color getTyphoonColor(String typhoonType)
    {
        Color color=new Color(0,255,3);
        switch (typhoonType)
        {
            case "热带低压":
                color=new Color(0,255,3);
                break;
            case "热带风暴":
                color=new Color(0,96,254);
                break;
            case "强热带风暴":
                color=new Color(253,250,0);
                break;
            case "台风":
                color=new Color(253,172,3);
                break;
            case "强台风":
                color=new Color(240,114,246);
                break;
            case "超强台风":
                color=new Color(253,0,2);
                break;
            default:
                color=new Color(0,255,3);
                break;
        }
        return color;
    }

    /**
    * @Author: BaoLongHui
    * @Description 从当前BufferedImage创建一个新的BufferedImage对象,但并不包含数据
    * @Date 9:32 2018/4/28
    */
    public BufferedImage createBufferedImage(BufferedImage src){
        ColorModel cm = src.getColorModel();
        BufferedImage image = new BufferedImage(cm,
                cm.createCompatibleWritableRaster(
                        src.getWidth(),
                        src.getHeight()),
                cm.isAlphaPremultiplied(), null);
        return image;
    }
    /**
    * @Author: BaoLongHui
    * @Description 克隆一个BufferedImage对象
    * @Date 9:29 2018/4/28
    */
    public BufferedImage copyBufferedImage(BufferedImage bimage)
    {
        BufferedImage bimage2 = new BufferedImage(bimage.getWidth(), bimage.getHeight(), bimage.getType());
        bimage2.setData(bimage.getData());
        return bimage2;

    }
	/**
	 * 把多张jpg图片合成一张
	 * @param pic String[] 多个jpg文件名 包含路径
	 * @param newPic String 生成的gif文件名 包含路径
	 * @param playTime int 播放的延迟时间
	 */
	private synchronized static void jpgToGif(String pic[], String newPic, int playTime) {
		try {
			AnimatedGifEncoder e = new AnimatedGifEncoder();
			e.setRepeat(0);//-1:不循环,0:总是循环播放
			e.start(newPic);
			BufferedImage src[] = new BufferedImage[pic.length];
			for (int i = 0; i < src.length; i++) {
				e.setDelay(playTime); //设置播放的延迟时间
				src[i] = ImageIO.read(new File(pic[i])); // 读入需要播放的jpg文件
				e.addFrame(src[i]);  //添加到帧中
			}
			e.finish();
		} catch (Exception e) {
			System.out.println( "jpgToGif Failed:");
			e.printStackTrace();
		}
	}

	/**
	 * @Author: BaoLongHui
	 * @Description 返回字节
	 * @Date 16:08 2018/4/28
	 */
	private byte[] getBytes(String fileFullPath) throws Exception
	{
		File file=new File(fileFullPath);
		return this.getBytes(file);
	}
	/**
	* @Author: BaoLongHui
	* @Description 返回字节
	* @Date 16:08 2018/4/28
	*/
	private byte[] getBytes(File file) throws Exception
	{
		FileInputStream fis=new FileInputStream(file);
		long fileSize = file.length();
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length
				&& (numRead = fis.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}
		fis.close();
		return buffer;
	}
	/**
	* @Author: BaoLongHui
	* @Description 向指定的文件写入内容
	* @Date 19:12 2018/4/28
	*/
	private void writeFileContent(String filePath,String content)throws IOException
	{
		File file = new File(filePath);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
		} else {
			file.delete();
		}
		file.createNewFile();
		// write
		OutputStreamWriter writerStream = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
//		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(writerStream);
		bw.write(content);
		bw.flush();
		bw.close();
//		fw.close();
//		writerStream.flush();
		writerStream.close();
	}

	private String read(String fileName)
	{
		StringBuilder sb=new StringBuilder();
		try {
			BufferedReader in=new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
			try {
				String s;
				while ((s=in.readLine())!=null)
				{
					sb.append(s);
					sb.append("\n");
				}
			}
			finally {
				in.close();
			}
		}
		catch (IOException ex)
		{

		}
		return sb.toString();
	}

	/*方法二：推荐，速度最快
	 * 判断是否为整数
	 * @param str 传入的字符串
	 * @return 是整数返回true,否则返回false
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
}
