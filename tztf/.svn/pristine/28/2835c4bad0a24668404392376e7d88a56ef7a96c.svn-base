package cn.movinginfo.tztf.sen.action;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.sen.domain.Menu;
import cn.movinginfo.tztf.sen.service.DeptPermissionService;
import cn.movinginfo.tztf.sen.service.MenuService;
import cn.movinginfo.tztf.sen.service.SenPermissionService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;
import net.ryian.commons.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/yzt/menu")
@SuppressWarnings("serial")
public class MenuAction extends MagicAction<Menu, MenuService> {

    @Resource
    private MenuService menuService;
    @Autowired
    private SenPermissionService senPermissionService;
	@Autowired
	private DeptPermissionService deptPermissionService;
    
    
    
    /**
     * 菜单管理页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("index")
    public String indexMenu(HttpServletRequest request, Model model) {
        model.addAttribute("currentPage", 1);
        model.addAttribute("pageSize", 10);
        return "/yzt/menu/menuQuery";
    }


    /**
     * 获取列表数据
     *
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/menuData")
    public String menuData(HttpServletRequest request, Model model) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map = getParameterMap(request);
        String menuId = ConfigHelper.getProperty("primaryMenu");// menuId=1
        map.put("menuId", menuId);
        PageInfo<?> pageInfo = entityService.queryPaged(map);
        JSONObject page = (JSONObject) JSONObject.parse(JSONObject.toJSONString(pageInfo, new DictFilter()));
        model.addAttribute("page", page);
        return getNameSpace() + "menuData";
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
        Menu menu = bindEntity(request, entityClass);
        String type = menu.getType();
        Menu m = null;
        if(!type.equals("")) {
        	 m = menuService.selectByType(type);
        }
        
		if (m != null) {
            if (menu.getId() == null) {
                printJson(response, com.alibaba.fastjson.JSON.toJSONString("该类型已存在!"));
                return;
            } else {
                if (m != null && !m.getId().equals(menu.getId())) {
                    printJson(response, com.alibaba.fastjson.JSON.toJSONString("该类型已存在!"));
                    return;
                }
            }
        }
        Long pid = menu.getPid();
        Menu lastMenu = entityService.get(pid);// 获取上级菜单
        Long menuId = lastMenu.getMenuId();
        if (menuId.equals(Long.parseLong(ConfigHelper.getProperty("titleBar")))) {// menuId=0
            List<Menu> menus = entityService.getMenuListByPid(pid);
            menu.setValue(String.valueOf(menus.size() + 1));
        }
        menu.setMenuId(menuId + (long) 1);
        entityService.saveOrUpdate(menu);
    }

    /**
     * 向编辑框传值
     *
     * @param request
     * @param response
     * @param id
     */
    @RequestMapping(value = "getEditMenuId")
    public void getEditMenuId(HttpServletRequest request, HttpServletResponse response, Long id) {
        Menu menu = entityService.get(id);
        Long pid = menu.getPid();
        Menu superMenu = entityService.get(pid);
        List<Menu> menuTitles = entityService.getOtherMenu(pid);
        for (Menu m : menuTitles) {
            m.setName(entityService.getMenuName(m.getId(), m.getName()));
        }
        menu.setPidName(entityService.getMenuName(superMenu.getId(), superMenu.getName()));
        menu.setLiMenu(menuTitles);
        printJson(response, com.alibaba.fastjson.JSON.toJSONString(menu));
    }

    /**
     * 获取标题下拉框
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "getAllTitleMenu")
    public void getAllTitleMenu(HttpServletRequest request, HttpServletResponse response) {
        List<Menu> menuTitles = entityService.getAllTitleMenu();
        for (Menu m : menuTitles) {
            m.setName(entityService.getMenuName(m.getId(), m.getName()));
        }
        printJson(response, com.alibaba.fastjson.JSON.toJSONString(menuTitles));
    }

    /**
     * 向编辑框传值
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "editMenu")
    public void editMenu(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Menu menu = entityService.get(Long.parseLong(id));
        printJson(response, com.alibaba.fastjson.JSON.toJSONString(menu));
    }

    /**
     * 删除
     *
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteMenu")
    public String deleteMenu(HttpServletRequest request, Model model) throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isEmpty(id)) {
            entityService.deleteMenu(Long.parseLong(id));
        }
        return "redirect:/yzt/menu/index";
    }

    

}
