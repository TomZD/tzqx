package cn.movinginfo.tztf.common.task;

import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.stereotype.Service;

import cn.movinginfo.tztf.common.utils.DataSourceConst;
import cn.movinginfo.tztf.common.utils.DataSourceContextHolder;
import cn.movinginfo.tztf.common.utils.DateUtil;
import cn.movinginfo.tztf.sen.domain.RnflR;
import cn.movinginfo.tztf.sen.service.RnflRService;
import cn.movinginfo.tztf.sen.service.RnflRSourceService;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service("RnflRJob")
public class RnflRJob {

    @Resource
    private RnflRService rnflRService;

    @Resource
    private RnflRSourceService rnflRSourceService;

    /**
     * 同步水文站数据 并删除30天前的数据
     */
    public void job() {
        System.out.println("开始同步水文站数据");
        //获取本地表中数据最新时间
        Timestamp time = rnflRService.selectTime();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DAY_OF_MONTH, -30);
        if(time == null ) {
            time = new Timestamp(calendar1.getTimeInMillis());
        }
        //切换数据源为水文站
        DataSourceContextHolder.setDataSourceType(DataSourceConst.hyswSource);
        //查询需要同步的数据
        List<RnflR> rnflRs = rnflRSourceService.selectRnflRs(time);
        //切换数据源为一张图
        DataSourceContextHolder.setDataSourceType(DataSourceConst.tztfSource);
        //将数据同步到本地
        if (rnflRs != null && rnflRs.size() != 0) {
            rnflRService.addList(rnflRs);
            System.out.println("水文站数据同步完成");
        }
        //获取30天前的时间
        Date day = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(day);
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
        rnflRService.deleteAfterMouth(timestamp);
    }

}
