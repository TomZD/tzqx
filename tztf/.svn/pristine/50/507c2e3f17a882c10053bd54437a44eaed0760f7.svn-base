package cn.movinginfo.tztf.sev.action;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sev.domain.Sensitive;
import cn.movinginfo.tztf.sev.service.SensitiveService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;
import net.ryian.commons.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/sev/sensitive")
public class SensitiveAction extends MagicAction<Sensitive, SensitiveService> {

    @Resource
    private SensitiveService sensitiveService;

    /**
     * 敏感词管理页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/sensitiveQuery")
    public String indexSensitiveQuery(HttpServletRequest request, Model model) {
        model.addAttribute("currentPage", 1);
        model.addAttribute("pageSize", 10);
        return "/sev/sensitive/sensitiveQuery";
    }

    /**
     * 列表查询
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/sensitiveData")
    public String keyPeopleData(HttpServletRequest request, Model model) {
        Map<String, String> map;
        map = getParameterMap(request);
        PageInfo<?> pageInfo = entityService.queryPaged(map);
        JSONObject page = (JSONObject) JSONObject.parse(JSONObject
                .toJSONString(pageInfo, new DictFilter()));
        model.addAttribute("page", page);
        return getNameSpace() + "sensitiveData";
    }

    /**
     * 删除接口
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteSensitive")
    public String delete(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        if (!StringUtils.isEmpty(id)) {
            entityService.logicRemove(Long.parseLong(id));
        }
        return "redirect:/sev/sensitive/sensitiveQuery";
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
        Sensitive sensitive = bindEntity(request, entityClass);
        Sensitive sensitive1 = sensitiveService.selectByWord(sensitive.getWord());
        if (sensitive1 != null) {
            printJson(response, com.alibaba.fastjson.JSON.toJSONString("该敏感词已存在!"));
            return;
        }
        entityService.saveOrUpdate(sensitive);
    }

    /**
     * 向编辑框传值
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "editKeyPeople")
    public void edit(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Sensitive sensitive = entityService.get(Long.parseLong(id));
        printJson(response, com.alibaba.fastjson.JSON.toJSONString(sensitive));
    }

}
