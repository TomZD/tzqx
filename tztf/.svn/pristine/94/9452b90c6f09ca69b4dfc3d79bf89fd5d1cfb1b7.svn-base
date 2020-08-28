package cn.movinginfo.tztf.common.task;

import cn.grassinfo.ncparser.NcDataParser;
import cn.grassinfo.ncparser.NcPageData;
import cn.grassinfo.ncparser.NcParseConfig;
import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.common.utils.DateUtil;
import cn.movinginfo.tztf.common.utils.FileUtil;
import cn.movinginfo.tztf.sen.model.TownInfo;
import cn.movinginfo.tztf.sen.model.TownJXH;
import cn.movinginfo.tztf.sen.service.TownInfoService;
import cn.movinginfo.tztf.sen.service.TownJXHService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("JXHJob")
public class JXHJob {
    @Autowired
    private TownJXHService townJXHService;
    @Autowired
    private TownInfoService townInfoService;

    /**
     * 获取精细化预报内容
     */
    public void job() {
        getJxhData();
    }

    /**
     * 获取精细化数据
     */
    private void getJxhData() {
        //获取乡镇信息
        List<TownInfo> townInfoList = townInfoService.getTownInfo();
        //获取要存的元素
        String jxhElements = ConfigHelper.getProperty("JXHElements");
        Date dateNow = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateNow);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        //获取连续15天的数据
        for (int i = 0; i < 15; i++) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
            String yyyyMMdd = DateUtil.format(cal.getTime(), "yyyyMMdd");
            String yyyy = DateUtil.format(cal.getTime(), "yyyy");
            String MM = DateUtil.format(cal.getTime(), "MM");
            String jxhPath = ConfigHelper.getProperty("JXHPath").replace("yyyyMMdd", yyyyMMdd).replace("yyyy", yyyy).replace("MM", MM);
            String[] fileList = FileUtil.getFileList(jxhPath);
            if (fileList == null)
                continue;
            for (String file : fileList) {
                //获取预报时间
                String[] attr = file.split("\\.");
                String forecastDateString = attr[1];
                String element = attr[3];
                if (jxhElements.indexOf(element) < 0)
                    continue;
                Date forecastDate = DateUtil.parse(forecastDateString, "yyyyMMddHHmmss");
                //获取文件内容
                NcDataParser parser = new NcDataParser();
                byte[] bytes = null;
                try {
                    bytes = FileUtil.readFileToBytes(jxhPath + file);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                List<NcPageData> ncPageList = parser.parse(bytes, forecastDate, new NcParseConfig());
                if (ncPageList.size() == 0)
                    continue;
                NcPageData ncPage = null;
                //处理风速文件
                ncPageList=processUVNC(ncPageList);
                ncPage = ncPageList.get(0);
                List<TownJXH> townJXHList = new ArrayList<TownJXH>();
                Integer xLength = (int) ((ncPage.getEndLon() - ncPage.getStartLon()) / ncPage.getResX());
                Integer yLength = (int) ((ncPage.getEndLat() - ncPage.getStartLat()) / ncPage.getResY());
                for (TownInfo townInfo : townInfoList) {
                    Integer index = getIndex(ncPage.getStartLon(), ncPage.getStartLat(), ncPage.getResX(), ncPage.getResY(), xLength, yLength, townInfo.getLon(), townInfo.getLat());
                    double value = ncPage.getDataD()[index];
                    TownJXH t = new TownJXH();
                    t.setDateForecast(forecastDate);
                    t.setTownCode(townInfo.getIiiii());
                    switch (element) {
                        case "T":
                            t.setT(new BigDecimal(value).setScale(2, RoundingMode.UP));
                            break;
                        case "Ww12":
                            t.setWw12(String.format("%.0f", value));
                            break;
                        case "Ww03":
                            t.setWw3(String.format("%.0f", value));
                            break;
                        case "WindMean":
                            if(value>0&&value<50) {
                                t.setWindS(new BigDecimal(value).setScale(2, RoundingMode.UP));
                            }
                            else {
                                t.setWindS(null);
                            }
                            double value2=ncPageList.get(1).getDataD()[index];
                            t.setWindD(new BigDecimal(value2).setScale(2, RoundingMode.UP));
                            break;
                        case "Cloud":
                            t.setCloud(new BigDecimal(value).setScale(2, RoundingMode.UP));
                            break;
                        case "Rh":
                            t.setRh(new BigDecimal(value).setScale(2, RoundingMode.UP));
                            break;
                        case "Pr03":
                            t.setPr03(new BigDecimal(value).setScale(2, RoundingMode.UP));
                            break;
                        case "Pr06":
                            t.setPr06(new BigDecimal(value).setScale(2, RoundingMode.UP));
                            break;
                        case "Pr12":
                            t.setPr12(new BigDecimal(value).setScale(2, RoundingMode.UP));
                            break;
                        case "Pr24":
                            t.setPr24(new BigDecimal(value).setScale(2, RoundingMode.UP));
                            break;
                        case "TMax24":
                            t.setTmax(new BigDecimal(value).setScale(2, RoundingMode.UP));
                            break;
                        case "TMin24":
                            t.setTmin(new BigDecimal(value).setScale(2, RoundingMode.UP));
                            break;
                    }
                    townJXHList.add(t);
                }
                townJXHService.insertOrUpateData(townJXHList);
            }
        }
    }

    private List<NcPageData> processUVNC(List<NcPageData> ncPageList)
    {
        List<NcPageData> result=new ArrayList<NcPageData>();
        if(ncPageList.size()<=1)
            return ncPageList;
        NcPageData ncPage = ncPageList.get(0);
        NcPageData ncPage2 = ncPageList.get(1);
        String a="";
        Integer xLength = (int) ((ncPage.getEndLon() - ncPage.getStartLon()) / ((double)Math.round(ncPage.getResX()*100)/100))+1;
        Integer yLength = (int) ((ncPage.getEndLat() - ncPage.getStartLat()) / ((double)Math.round(ncPage.getResY()*100)/100))+1;
        for (int y = 0; y < yLength; y++) {
            for (int x = 0; x < xLength; x++) {
                int subscript = xLength * y + x;
                if (subscript < ncPage.getDataD().length) {
                    double u=ncPage.getDataD()[subscript];
                    double v=ncPage2.getDataD()[subscript];
                    double windSpeed = Math.sqrt(u * u + v * v);
                    double windAngle = (270 - Math.atan(v / u) * 180 / Math.PI) % 360;
                    ncPage.getDataD()[subscript] = (double)Math.round(windSpeed*100)/100;
                    ncPage2.getDataD()[subscript] = (double)Math.round(windAngle*100)/100;
                }
            }
        }
        result.add(ncPage);
        result.add(ncPage2);
        return result;
    }

    private Integer getIndex(double startX, double startY, double resX, double resY, Integer xLength, Integer yLength, double lon, double lat) {
        Integer result = 0;
        double minDX = 180;
        double minDY = 90;
        for (int i = 0; i < yLength; i++) {
            for (int j = 0; j < xLength; j++) {
                double x = startX + j * resX;
                double y = startY + i * resY;
                double dX = Math.abs(lon - x);
                double dY = Math.abs(lat - y);
                if (dX <= minDX && dY <= minDY) {
                    minDX = dX;
                    minDY = dY;
                    result = i * xLength + j;
                }
            }
        }
        return result;
    }
}
