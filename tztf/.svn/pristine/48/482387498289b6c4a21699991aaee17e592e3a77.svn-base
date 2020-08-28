package cn.movinginfo.tztf.sys.action;

/**
 * Author: ZhangKX
 * Created by Administrator on 2019/11/20.
 * Reamrk:
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.common.utils.ConfigExportHelper;
import cn.movinginfo.tztf.common.utils.Reflex;
import cn.movinginfo.tztf.sen.domain.KeyPeople;
import cn.movinginfo.tztf.sen.domain.PersonType;
import cn.movinginfo.tztf.sen.service.DefenseDepartmentService;
import cn.movinginfo.tztf.sev.domain.Sensitive;
import cn.movinginfo.tztf.sev.service.SensitiveService;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.Tffk;
import cn.movinginfo.tztf.sys.domain.User;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.TffkService;
import cn.movinginfo.tztf.sys.service.UserService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;
import net.ryian.commons.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/sev/tfback")
public class TFbackAction extends MagicAction<Tffk, TffkService> {

    @Resource
    private TffkService tffkService;

    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

    /**
     * 突发反馈管理页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/tfQuery")
    public String indexSensitiveQuery(HttpServletRequest request, Model model) {

        User user = userService.get(this.getCurrentUser().id);
        Long departmentId = user.getDepartmentId();
        Depart depart = deptService.findById(departmentId);
        if ((this.getCurrentUser().id) == 6) {
            model.addAttribute("power", 3);
        } else {
            if (depart.getTownId() != null) {
                model.addAttribute("power", 1);
            } else {
                model.addAttribute("power", 2);
            }
        }
        model.addAttribute("currentPage", 1);
        model.addAttribute("pageSize", 10);
        return "/sev/tfback/tfQuery";
    }

    /**
     * 列表查询
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/tfData")
    public String keyPeopleData(HttpServletRequest request, Model model) {
        Map<String, String> map;
        map = getParameterMap(request);
        User user = userService.get(this.getCurrentUser().id);
        Long departmentId = user.getDepartmentId();
        Depart depart = deptService.findById(departmentId);
        if ((this.getCurrentUser().id) == 6) {
            model.addAttribute("power", 3);
        } else {
            if (depart.getTownId() != null) {
                model.addAttribute("power", 1);
                map.put("userId", this.getCurrentUser().id.toString());
            } else {
                model.addAttribute("power", 2);
            }
        }
        PageInfo<?> pageInfo = entityService.queryPaged(map);
        JSONObject page = (JSONObject) JSONObject.parse(JSONObject
                .toJSONString(pageInfo, new DictFilter()));
        model.addAttribute("page", page);
        return getNameSpace() + "tfData";
    }

    /**
     * 删除接口
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteTffk")
    public String delete(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        if (!StringUtils.isEmpty(id)) {
            entityService.logicRemove(Long.parseLong(id));
        }
        return "redirect:/sev/tfback/tfQuery";
    }

    /**
     * 添加和修改
     *
     * @param request
     * @param response
     * @param model
     * @throws Exception
     */
    @RequestMapping(value = "/saveOrUpdate")
    public void suerEdit(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        Tffk tffk = bindEntity(request, entityClass);

//        Map<String, String> map = new HashMap<String, String>();
//        map = getParameterMap(request);
//        User userTemp = (User) request.getSession().getAttribute("yztUser");

        User user = userService.get(this.getCurrentUser().id);
        tffk.setUserId(this.getCurrentUser().id);
        tffk.setUserName(user.getName());
        tffk.setCreateDate(new Date());
        tffk.setUpdateDate(new Date());
        entityService.saveOrUpdate(tffk);
    }

    /**
     * 向编辑框传值
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "editTffk")
    public void edit(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Tffk tffk = entityService.get(Long.parseLong(id));
        printJson(response, com.alibaba.fastjson.JSON.toJSONString(tffk));
    }

    @RequestMapping(value = "/exportData")
    @ResponseBody
    public void exportData(HttpServletRequest request, HttpServletResponse response, String personTypeId) throws ParseException {
        List<Tffk> list = new ArrayList<>();
        User user = userService.get(this.getCurrentUser().id);
        Long departmentId = user.getDepartmentId();
        Depart depart = deptService.findById(departmentId);
        if (depart.getTownId() == null) {
            list = entityService.getAll();
        } else {
            list = entityService.getTffkByUserId(this.getCurrentUser().id);
        }

        HSSFWorkbook wb = new HSSFWorkbook();//声明工
        Sheet sheet = wb.createSheet("sheet1");//新建表
        //sheet.setDefaultColumnWidth(50);//设置表宽
        sheet.setDefaultColumnWidth((short) 25);//设置表宽
        HSSFCellStyle titleStyle = wb.createCellStyle();
        //设置标题字体样式
        org.apache.poi.hssf.usermodel.HSSFFont titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 15);//设置大小
        titleStyle.setFont(titleFont);
        titleStyle.setWrapText(true);
        titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//填充单元格
        titleStyle.setFillForegroundColor(HSSFColor.YELLOW.index);//填红色
        titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        //设置内容字体样式
        HSSFCellStyle style = wb.createCellStyle();
        org.apache.poi.hssf.usermodel.HSSFFont Font = wb.createFont();
        Font.setFontHeightInPoints((short) 11);//设置大小
        style.setFont(Font);
        style.setWrapText(true);
        String title = "反馈者,地点,事件,反馈图片,反馈时间";
        String exportjson = "";

        Map<String, Object> map = JSON.parseObject(exportjson);
        Row row1 = sheet.createRow(0);
        row1.setHeight((short) 400);//设置标题表高
        Cell cell;
        String[] titles = title.split(",");
        for (int x = 0; x < titles.length; x++) {
            cell = row1.createCell(x);
            cell.setCellValue(titles[x]);
            cell.setCellStyle(titleStyle);
        }
        int i = 0;
        SimpleDateFormat newsdfTemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String contextPath = request.getContextPath();
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;

        for (int x = 0; x < list.size(); x++) {
            row1 = sheet.createRow(++i);
            row1.setHeight((short) 300);//内容表高
            row1.createCell(0).setCellValue(list.get(x).getUserName());
            row1.createCell(1).setCellValue(list.get(x).getPlace());
            row1.createCell(2).setCellValue(list.get(x).getSomething());
            row1.createCell(3).setCellValue(basePath + list.get(x).getImagePath());
            row1.createCell(4).setCellValue(newsdfTemp.format(list.get(x).getCreateDate()));
        }


        response.reset();
        response.setContentType("application/msexcel;charset=UTF-8");
        try {
            SimpleDateFormat newsdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String date = newsdf.format(new Date());
            response.addHeader("Content-Disposition", "attachment;filename=\""
                    + new String(("导出-" + "临时" + "信息" + date + ".xls").getBytes("Utf-8"),
                    "ISO8859_1") + "\"");
            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "导出失败!");
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "导出失败!");
            e.printStackTrace();
        }
    }

}
