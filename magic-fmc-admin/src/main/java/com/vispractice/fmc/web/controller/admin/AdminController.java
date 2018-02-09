package com.vispractice.fmc.web.controller.admin;

import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import com.vispractice.fmc.business.entity.sys.org.SysOrgPerson;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.business.service.sys.org.ISysOrgPersonService;
import com.vispractice.fmc.web.utils.vo.AbstractController;

/**
 * 编 号：<br/>
 * 名 称：AdminController<br/>
 * 描 述：页面控制器 完成日期2016年12月14日 上午11:37:19 <br/>
 * 编码作者：sky<br/>
 */
@SuppressWarnings("rawtypes")
@Controller
public class AdminController extends AbstractController {
	@Autowired
	LocaleResolver localeResolver;

	@Autowired
	private ISysOrgElementService sysOrgElementService;
	
	@Autowired
	private ISysOrgPersonService sysOrgPersonService;

	/**
	 * 登录成功后跳转主页面
	 */
	@RequestMapping("/successHandle")
	public String successHandle() {
		return "redirect:/sys/main";
	}
	
	/**
	 * 登录系统
	 * @param request
	 * @param response
	 */
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 超时处理
		if (request.getHeader("x-requested-with") != null
			&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) { 
																								
			String jsonObject = "{\"isLoginRequired\":true}";
			String contentType = "application/json";
			response.setContentType(contentType);
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println(jsonObject);
			out.flush();
			out.close();
			
			return null;
		} else {
			ModelAndView modelAndView = new ModelAndView();
			Locale locale = localeResolver.resolveLocale(request);
			if (locale.getCountry() != "") {
				modelAndView.addObject("language",locale.getLanguage() + "_" + locale.getCountry());
			} else {
				modelAndView.addObject("language",locale.getLanguage());
			}
			modelAndView.setViewName("admin/login");
			
			return modelAndView;
		}
	}
	
	/**
	 * 切换语言
	 * @param request
	 * @param response
	 * @param languageSelect
	 * @return
	 */
	@RequestMapping("/switchLanguage")
	public String changeLanguage(HttpServletRequest request,HttpServletResponse response,String languageSelect) {
		Locale locale = new Locale(languageSelect);
		localeResolver.setLocale(request,response,locale);
		
		return "redirect:/login";
	}

	/**
	 * 主页面控制
	 * @return main_win主页面
	 */
	@RequestMapping("/sys/main")
	public ModelAndView main() {
		String username = "未知用户";

		ModelAndView mav = new ModelAndView();
		if (null != this.getCurrentUser()) {
			String userId = this.getCurrentUser().getFdId();
			username = sysOrgElementService.findByFdId(userId).getFdName();
		}

		mav.addObject("currentUser",username);
		mav.setViewName("admin/main_win");
		
		return mav;
	}

	/**
	 * 系统退出
	 */
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request,HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request,response,auth);
		}
		
		return "redirect:/login?logout";
	}
	
	/**
	 * 修改密码
	 * @param context
	 * @return
	 */
	@RequestMapping(value = "/resetPassword",method = RequestMethod.POST)
	public String resetPassword(HttpServletRequest request,HttpServletResponse response) {
		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		SysOrgPerson sysOrgPerson = this.getCurrentUser();
		
		if(md5PasswordEncoder.encodePassword(currentPassword,null).equals(sysOrgPerson.getFdPassword())) {
			if (newPassword.equals(confirmPassword)) {
				sysOrgPerson.setFdPassword(md5PasswordEncoder.encodePassword(newPassword,null));
				sysOrgPersonService.save(sysOrgPerson);
			}
		}
		
		return "redirect:/sys/main";
	}
}
