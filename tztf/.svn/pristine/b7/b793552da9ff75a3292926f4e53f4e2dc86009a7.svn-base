package cn.movinginfo.tztf.common.action;

import com.alibaba.fastjson.JSONObject;
import com.mashape.unirest.http.exceptions.UnirestException;

import cn.movinginfo.tztf.common.constants.SystemProperties;
import cn.movinginfo.tztf.sen.domain.DeptPermission;
import cn.movinginfo.tztf.sen.domain.SenPermission;
import cn.movinginfo.tztf.sen.service.DeptPermissionService;
import cn.movinginfo.tztf.sen.service.SenPermissionService;
import cn.movinginfo.tztf.sev.domain.Led;
import cn.movinginfo.tztf.sev.service.AlarmService;
import cn.movinginfo.tztf.sev.service.LedService;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.Role;
import cn.movinginfo.tztf.sys.domain.User;
import cn.movinginfo.tztf.sys.domain.UserXt;
import cn.movinginfo.tztf.sys.service.*;
import cn.movinginfo.tztf.sys.shiro.CaptchaException;
import cn.movinginfo.tztf.sys.shiro.UsernamePasswordCaptchaToken;
import net.ryian.commons.DigestUtils;
import net.ryian.commons.EncodeUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;




/**
 * Created by allenwc on 14/11/12.
 */
@Controller
public class LoginAction extends BaseMagicAction{
	
	@Autowired
    protected UserService userService;
	@Autowired
	protected UserXtService userXtService;

	@Autowired
	protected DeptService deptService;

	@Autowired
	protected AlarmService alarmService;
	@Autowired
	protected AlarmTypeService alarmTypeService;
	@Autowired
	protected ReleaseChannelInstanceService releaseChannelInstanceService;
	@Autowired
	protected ReleaseChannelService releaseChannelService;
	

	@Autowired
	private RoleService roleService;
	@Autowired
	protected LedService ledService;
	@Autowired
	protected RecordService recordService;
	@Autowired
	protected DeptPermissionService deptPermissionService;
	@Autowired
    private SenPermissionService senPermissionService;


    @RequestMapping(value = "/guide")
    public String login(HttpServletRequest req,HttpServletResponse response,Model model) {
    	
        return "guide";
    }
    
    @RequestMapping(value = "/{district}/guide")
    public String districtLogin(HttpServletRequest req,HttpServletResponse response,Model model,@PathVariable("district") String district) {
    	model.addAttribute("district", district);
        return "guide";
    }
    
    @RequestMapping(value = "/channelState")
    public String channelState(HttpServletRequest req,HttpServletResponse response,Model model) {
    	List<Led> allLeds = ledService.getSevLed();
    	int allLedNum = allLeds.size();
    	List<Led> trafficLeds = ledService.getTrafficLeds();
	    int trafficLedNum = trafficLeds.size();
	    List<Led> busLeds = ledService.getBusLeds();
	    int busLedNum = busLeds.size();
	    List<Led> metroLeds = ledService.getMetroLeds();
	    int metroLedNum = metroLeds.size();
	    List<Led> schoolLeds = ledService.getSchoolLeds();
	    int schoolLedNum = schoolLeds.size();
	    List<Led> cinemaLeds = ledService.getCinemaLeds();
	    int cinemaLedNum = cinemaLeds.size();
	    List<Led> weatherLeds = ledService.getWeatherLeds();
	    int weatherLedNum = weatherLeds.size();
	    model.addAttribute("allLedNum",allLedNum);
	    model.addAttribute("trafficLedNum",trafficLedNum);
	    model.addAttribute("busLedNum",busLedNum);
	    model.addAttribute("metroLedNum",metroLedNum);
	    model.addAttribute("schoolLedNum",schoolLedNum);
	    model.addAttribute("cinemaLedNum",cinemaLedNum);
	    model.addAttribute("weatherLedNum",weatherLedNum);
    	Calendar a = Calendar.getInstance();
		int year = a.get(Calendar.YEAR);
	    model.addAttribute("alarmYear", alarmService.getAlarmYear(year));
        return "channelState";
    }
    
    @RequestMapping(value = "/liveMion")
    public String liveMion(HttpServletRequest req,HttpServletResponse response,Model model) {
    	
        return "liveMion";
    }
    
    @RequestMapping(value = "/emergencySave")
    public String emergencySave(HttpServletRequest req,HttpServletResponse response,Model model) {
    	Date date1 = new Date();
    	Date date2 = new Date(date1.getTime()+600000);
    	Date date3 = new Date(date2.getTime()+600000);
    	Date date4 = new Date(date3.getTime()+600000);
    	SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日HH时mm分");
    	String time1 = sdf.format(date1);
    	String time2 = sdf.format(date2);
    	String time3 = sdf.format(date3);
    	String time4 = sdf.format(date4);
    	model.addAttribute("time1",time1);
    	model.addAttribute("time2",time2);
    	model.addAttribute("time3",time3);
    	model.addAttribute("time4",time4);
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = format.format(m);
        Date nowTime = new Date();
        String now = format.format(nowTime);
        
        model.addAttribute("nowTime", now);
        model.addAttribute("aMonthAgo", mon);
        return "emergencySave";
    }
    
    @RequestMapping(value = "/fzjz")
    public String fzjz(HttpServletRequest req,HttpServletResponse response,Model model) {
    	
        return "fzjz";
    }
    
    @RequestMapping(value = "/dzzh")
    public String dzzh(HttpServletRequest req,HttpServletResponse response,Model model) {
    	
        return "dzzh";
    }
    
    @RequestMapping(value = "/ldxx")
    public String ldxx(HttpServletRequest req,HttpServletResponse response,Model model) {
    	
        return "ldxx";
    }
    
    @RequestMapping(value = "/tqsk")
    public String tqsk(HttpServletRequest req,HttpServletResponse response,Model model) {
    	
        return "tqsk";
    }
    
    @RequestMapping(value = "/forecastInfo")
    public String forecastInfo(HttpServletRequest req,HttpServletResponse response,Model model) {
    	
        return "forecastInfo";
    }
    
    @RequestMapping(value = "/fzjzLogin")
    public String fzjzLogin(HttpServletRequest req,HttpServletResponse response,Model model) {
    	
        return "fzjzLogin";
    }
    
    @RequestMapping(value = "/returnLogin")
    public String returnLogin(HttpServletRequest req,HttpServletResponse response,Model model) {
    	
        return "/yzt/returnLogin";
    }
    
    /**
     * 管理页面的框架
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("Index")
    public String Index(HttpServletRequest request, Model model) {
    	UserXt user = (UserXt) request.getSession().getAttribute("yztUser");
        if (user != null) {
            Long id = user.getId();
            List<DeptPermission> deptPermissionList = deptPermissionService.getDeptPermissionBydeptId(id);
            if (deptPermissionList.size() != 0) {
                List<SenPermission> list = new ArrayList<>();
                for (DeptPermission dp : deptPermissionList) {
                    SenPermission senper = senPermissionService.get(dp.getPermissionId());
                    String twoPermissionId = dp.getTwoPermissionId();
                    if(!StringUtils.isEmpty(twoPermissionId)) {
                    	String[] arr = twoPermissionId.split(",");
                    	List<Object> idList = Arrays.asList(arr);
                    	List<SenPermission> twoList = senPermissionService.getPermissionInId(idList);
                    	senper.setTwoPermissionList(twoList);
                    }
                    list.add(senper);
                }
                //System.out.println(list);
                model.addAttribute("senPersionList", list);
            }
        }
        return "/yzt/index";
    }
    
    @RequestMapping(value = "/jtzs")
    public void jtzsImg(HttpServletRequest req,HttpServletResponse response,Model model) throws UnirestException, IOException {
    	String lonlat = req.getParameter("lonlat");
    	String pulbishUrl = SystemProperties.getProperty("publish_url");
		String url = pulbishUrl+"/jtzs/img?lonlat="+lonlat;
//    	String url = "http://115.238.43.206:8160/arcgis/rest/services/jiaotong/jiaotong12/MapServer/export?dpi=96&transparent=true&format=png8&bbox=119.7064375364547,30.170702848067002,120.34227127668709,30.399576084445172&bboxSR=4326&imageSR=4326&size=1192%2C620&f=image";
    	File file = new File(SystemProperties.APP_PATH+"/tmp/"+UUID.randomUUID()+".png");
    	if(!file.getParentFile().exists()){
    		file.getParentFile().mkdirs();
    	}
    	FileUtils.copyURLToFile(new URL(url), file);
    	FileInputStream fis;
        fis = new FileInputStream(file);
    	long size = file.length();
        byte[] temp = new byte[(int) size];
        fis.read(temp, 0, (int) size);
        fis.close();
        byte[] data = temp;
        OutputStream out = response.getOutputStream();
        response.setContentType("image/png");
        out.write(data);
        file.delete();
        out.flush();
        out.close();

//    	pr
    }
    
    
    
    
//    @RequestMapping(value = "/publishStatus")
//    public String publishStatus(HttpServletRequest req,HttpServletResponse response,Model model) {
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    	Calendar a = Calendar.getInstance();
//		int year = a.get(Calendar.YEAR);
//		model.addAttribute("alarmYear", alarmService.getAlarmYear(year));
//    	Alarm alarm=alarmService.getNewAlarm();
//    	model.addAttribute("alarm", alarm);
//    	if(Long.valueOf(alarm.getTypeId())>40){
//    		String image =  "./static/images/warnsmall/logo.png" ;
//        	model.addAttribute("image", image);
//    	}else{
//    		AlarmType alarmType = alarmTypeService.get(Long.valueOf(alarm.getTypeId()));
//        	String image =  "./static/images/warnsmall/" + alarmType.getImage();
//        	model.addAttribute("image", image);
//    	}
//    	
//    	List<ReleaseChannelInstance> releaList = releaseChannelInstanceService.queryByVersion(alarm.getPubNo(),
//				Integer.valueOf(alarm.getVersion()));
//		List<Map<String, String>> content = new ArrayList<Map<String, String>>();
//		for (int i = 0; i < releaList.size(); i++) {
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("content", releaList.get(i).getContent());
//			map.put("channel", releaseChannelService.get(releaList.get(i).getChannelId()).getName());
//			map.put("releaseState", releaList.get(i).getReleaseState());
//			map.put("update",sdf.format(releaList.get(i).getUpdate_date()) );
//			map.put("id", releaList.get(i).getId().toString());
//			content.add(map);
//		}
//		JSONObject obj = new JSONObject();
//		obj.put("data", JSONArray.parseArray(JSONArray.toJSONString(content)));
//		model.addAttribute("data", JSONArray.parseArray(JSONArray.toJSONString(content)));
//		//printJson(response, obj.toString());
//    	
//        return "publishStatus";
//    }
    

    @RequestMapping(value = "/login")
    public String login1(HttpServletRequest req,HttpServletResponse response,Model model) {

    	String exceptionClassName = (String)req.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        String error = null;
        if(CaptchaException.class.getName().equals(exceptionClassName)){
        	error = "验证码错误！请重新输入";
        }
        if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(AccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        }



		String username=req.getParameter("username");
        if ((username!=null)&&!(username.equals(""))){
        	User user = userService.findUserByUserName(username);
        	if(user==null){
        		error = "用户名/密码错误";
        	}else{
        		
            }
            model.addAttribute("error",error);
        	}
        if((req.getParameter("password")!=null)&&(error!=null)){
        	/*String jsonResult = JSON.toJSONString(error);
        	return jsonResult;*/
        	JSONObject obj = new JSONObject();
        	obj.put("error1", error);
        	printJson(response, obj.toString());

        	
        }

        if(this.getCurrentUser() !=null){
        		return "redirect:/sys/index";
        	
        }
        return "login";
    }
    
    
    /**
	 * 登录验证
	 * @param username
	 * @param password
	 * @param req
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/checkUser", method = RequestMethod.POST)
	@ResponseBody
	public int checkUser(@RequestParam("username") String username,
			@RequestParam("password") String password,HttpServletRequest req, HttpSession httpSession) {
		String captcha=req.getParameter("captcha");
		HttpSession session = req.getSession();
		int result = -1;
		if(!session.getAttribute("checkCode").toString().equalsIgnoreCase(captcha)) {
			result = -3;//验证码错误
			return result;
		}
		UserXt userXt = userXtService.findUserByUserName(username);
		if(userXt==null)
		{
			result=-2;
			return result;
		}
//		String salt = userXt.getSalt();
//		byte[] hashPassword = DigestUtils.sha1(password.getBytes(), Hex.decode(salt), 1024);
		String passwd = userXt.getPassword();
//		if (passwd.equals(EncodeUtils.encodeHex(hashPassword))) {
		if (passwd.equals(password)) {
			List<DeptPermission> deptPermissionList = deptPermissionService.getDeptPermissionBydeptId(userXt.getId());
        	if(deptPermissionList.size() != 0) {
        		// 登录成功
    			result = 1;
    			UserXt yztUser = new UserXt();
    			yztUser.setUserName(username);
    			yztUser.setId(userXt.getId());
    			httpSession.setAttribute("yztUser", yztUser);


        	}else {
        		result =-4;//权限不够
        	}
			
		} else {
			// 密码不正确
			result = 0;
		}
		return result;
	}
	
	/**
	 * 退出登录，清空session
	 * @param request
	 * @return
	 */
	@RequestMapping("Exit")
	public void Exit(HttpServletRequest request,HttpServletResponse response) {
		UserXt user = (UserXt) request.getSession().getAttribute("yztUser");
		if (user != null) {
			try {
				request.getSession().removeAttribute("yztUser");
			} catch (Exception e) {
				user = null;
			}
		}
		try {
			response.sendRedirect("fzjzLogin");
		} catch (IOException e) {
			logger.error("exception {0}",e);
		}
	}
    
    
    /**
	 * 验证码
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "imageCode")
	public String getImageCode(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		try {
			int width = 100;
			int height = 42;
			// 取得一个4位随机字母数字字符串
			String s = RandomStringUtils.random(4, false, true);

			// 保存入session,用于与用户的输入进行比较.
			// 注意比较完之后清除session.
			HttpSession session = request.getSession();
			session.setAttribute("checkCode", s);

			response.setContentType("images/jpeg");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			ServletOutputStream out = response.getOutputStream();
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.getGraphics();
			// 设定背景色
			g.setColor(getRandColor(200, 250));
			g.fillRect(0, 0, width, height);

			// 设定字体
			Font mFont = new Font("Times New Roman", Font.BOLD, 24);// 设置字体
			g.setFont(mFont);

			// 画边框
			// g.setColor(Color.BLACK);
			// g.drawRect(0, 0, width - 1, height - 1);

			// 随机产生干扰线，使图象中的认证码不易被其它程序探测到
			g.setColor(getRandColor(160, 200));
			// 生成随机类
			Random random = new Random();
			for (int i = 0; i < 155; i++) {
				int x2 = random.nextInt(width);
				int y2 = random.nextInt(height);
				int x3 = random.nextInt(12);
				int y3 = random.nextInt(12);
				g.drawLine(x2, y2, x2 + x3, y2 + y3);
			}

			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));

			g.drawString(s, 20, 30);

			// 图象生效
			g.dispose();
			// 输出图象到页面
			ImageIO.write((BufferedImage) image, "JPEG", out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Color getRandColor(int fc, int bc) { // 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
    
    
    @RequestMapping(value = "/{district}/login")
	public String login2(HttpServletRequest req,HttpServletResponse response,@PathVariable("district") String district, Model model) {
    	String exceptionClassName = (String)req.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        String error = null;
        if(CaptchaException.class.getName().equals(exceptionClassName)){
        	error = "验证码错误！请重新输入";
        }
        if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(AccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } 
        String username=req.getParameter("username");
        if ((username!=null)&&!(username.equals(""))){
        	User user = userService.findUserByUserName(username);
        	if(user==null){
        		error = "用户名/密码错误";
        	}else{
        		
            }
            model.addAttribute("error",error);
        	}
        if((req.getParameter("password")!=null)&&(error!=null)){
        	/*String jsonResult = JSON.toJSONString(error);
        	return jsonResult;*/
        	JSONObject obj = new JSONObject();
        	obj.put("error1", error);
        	printJson(response, obj.toString());
        	
        }

        if(this.getCurrentUser() !=null){

        	return "redirect:/sys/index";
        }
        return "login";
	}
    
    @RequestMapping(value = "/yjptLogin")
    public String yjptLogin(HttpServletRequest req,HttpServletResponse response,Model model) {
    	String departName = req.getParameter("departName");
    	String userName = req.getParameter("username");
    	String realName = req.getParameter("realname");
    	String loginType = "1";
    	Depart depart = deptService.findByName(departName);
    	if(depart!=null){//判断部门是否已存在
    		User user = userService.findUserByUserName(userName);
    		if(user==null){//用户是否已存在系统
    			user = new User();
    			user.setDepart(depart);
    			user.setDepartmentId(depart.getId());
    			user.setName(realName);
    			user.setUserName(userName);
    			user.setValid(1);
    			user.encryptUserPassword("111111");
    			user.setAreaId(depart.getAreaId());
    			user.setCreateDate(new Date());
    			user.setIsUse("1");
    			user.setIsYjpt("1");
    			Long userId = userService.saveOrUpdate(user);
    			List<User> users = userService.getByDepartmentId(depart.getId());
    			if(users!=null&&users.size()>0){
    				User deptUser = users.get(0);
    				List<Role> roleList = roleService.getRolesByUser(deptUser.getId());
    				String roleIds = "";
    				for(Role role:roleList){
    					roleIds += role.getId() + ",";
    				}
    				if(roleIds.length()>0){
    					roleIds = roleIds.substring(0, roleIds.length()-1);
    				}
    				roleService.saveUserRoles(userId, roleIds, userId);
    			}
    		}
    	}
    	 
 
		String captcha = null;
		
		
	//	String departName = getDepart(request);
 
 
		String host = req.getRemoteHost();
    	Subject currentUser = SecurityUtils.getSubject();
    	UsernamePasswordCaptchaToken token = new UsernamePasswordCaptchaToken(userName, "111111".toCharArray(),false,null,host,loginType); 
    	// 开始进入shiro的认证流程
    	currentUser.login(token);
    	if(this.getCurrentUser() !=null){

        	return "redirect:/sys/index";
        }
    	return "yjpt_login";
    }

    @RequestMapping(value = "/typhoon")
    public String typhoon(HttpServletRequest req,HttpServletResponse response,Model model) {
    	
        return "typhoon";
    }

   
}
