package cn.movinginfo.tztf.sen.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.alibaba.fastjson.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.common.utils.ConfigExportHelper;
import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.common.utils.Reflex;
import cn.movinginfo.tztf.sen.domain.ChineseEnglish;
import cn.movinginfo.tztf.sen.domain.KeyPeople;
import cn.movinginfo.tztf.sen.domain.PersonType;
import cn.movinginfo.tztf.sen.service.KeyPeopleService;
import cn.movinginfo.tztf.sen.service.PersonTypeService;
import cn.movinginfo.tztf.sys.domain.Area;
import cn.movinginfo.tztf.sys.domain.UserXt;
import cn.movinginfo.tztf.sys.service.AreaService;
import cn.movinginfo.tztf.sys.service.UserService;
import cn.movinginfo.tztf.sys.service.UserXtService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;


@Controller
@RequestMapping("/yzt/keyAccount")
public class AccountAction extends MagicAction<UserXt, UserXtService> {


    @Autowired
    private AreaService areaService;

    /**
     * 账号管理页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("keyAccountQuery")
    public String indexKeyPeopleQuery(HttpServletRequest request, Model model, String personTypeId) {
        model.addAttribute("currentPage", 1);
        model.addAttribute("pageSize", 10);
        return "/yzt/keyAccount/keyAccountQuery";
    }

    /**
     * 获取列表数据
     *
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/keyAccountData")
    public String tabStationData(HttpServletRequest request, Model model)
            throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map = getParameterMap(request);
        UserXt user = (UserXt) request.getSession().getAttribute("yztUser");
        String id = ConfigHelper.getProperty("id");
        if (!user.getId().toString().equals(id)) {
            map.put("id", user.getId().toString());
            model.addAttribute("del", 0);
        } else {
            model.addAttribute("del", 1);
        }
        PageInfo<?> pageInfo = entityService.queryPaged(map);
        JSONObject page = (JSONObject) JSONObject.parse(JSONObject
                .toJSONString(pageInfo, new DictFilter()));
        model.addAttribute("page", page);
        return getNameSpace() + "keyAccountData";
    }


    /**
     * 删除
     *
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteKeyAccount")
    public String delete(HttpServletRequest request, Model model) throws Exception {
        String id = request.getParameter("id");
        if (!StringUtils.isEmpty(id)) {
            entityService.logicRemove(Long.parseLong(id));
        }
        return "redirect:/yzt/keyAccount/keyAccountQuery";
    }


    @RequestMapping(value = "getAllMenu")
    @ResponseBody
    public List<Area> getCountryData(HttpServletRequest request, HttpServletResponse response) {
        List<Area> list = areaService.getTownList();
        return list;
    }

    /**
     * 新增保存和修改
     *
     * @param request
     * @param response
     * @param model
     * @throws Exception
     */
    @RequestMapping(value = "/saveOrUpdate")
    public void suerEdit(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        UserXt userXt = bindEntity(request, entityClass);

        String userName = userXt.getUserName();
        UserXt userXtCheck = entityService.selectUserByUserName(userName);

        if (userXt.getId() == null && userXtCheck != null) {
            printJson(response, com.alibaba.fastjson.JSON.toJSONString("该账号已存在!"));
            return;
        }


        userXt.setCreateDate(new Date());
        userXt.setUpdateDate(new Date());
        entityService.saveOrUpdate(userXt);
    }


    /**
     * 向编辑框传值
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "editAccount")
    @ResponseBody
    public Map<String, String> edit(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();
        String id = request.getParameter("id");
        UserXt userXt = entityService.get(Long.parseLong(id));
        Area area = areaService.selectByAreaId(userXt.getTownId());
//        printJson(response, com.alibaba.fastjson.JSON.toJSONString(userXt));
        map.put("name", userXt.getName());
        map.put("userName", userXt.getUserName());
        map.put("password", userXt.getPassword());
        if (userXt.getTownId() != null) {
            map.put("townId", userXt.getTownId().toString());
        }
        if (area != null) {
            map.put("townName", area.getTown());
        }
        return map;
    }


}
