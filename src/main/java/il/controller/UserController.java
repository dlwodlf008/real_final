package il.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import il.user.model.UserDAO;
import il.user.model.UserDTO;

@Controller
public class UserController {
   
   @Autowired
   private UserDAO userDao;
   
   @RequestMapping("/userJoin.do")
   public ModelAndView user() {
      
      ModelAndView mav = new ModelAndView();
      mav.setViewName("user/userJoin");
      return mav;
   }
   
   @RequestMapping("/userjoin.do")
   public ModelAndView userJoin(UserDTO dto) {
      int result = userDao.userJoin(dto);
      String msg = null;
      if(result==1) {
       
         msg =   "ooo����Ʈ�� ���Ű� ȯ���մϴ�.";
      }else {
         msg = "ȸ�����Կ� �����ϼ̽��ϴ�.";
      }
      ModelAndView mav = new ModelAndView();
      mav.addObject("msg",msg);   
      mav.setViewName("user/userMsg");
      
      return mav;
   }
  
   @RequestMapping("/idCheck.do")
   public ModelAndView check(
         @RequestParam(value="id")String id){
    
      int result = userDao.idCheck(id);
      String msg = null;
      if(result==0) {
         msg = "<span style=color:blue>��밡���� ���̵��Դϴ�.</span>";
      }else {
         msg = "<span style=color:red>�ߺ��� ���̵��Դϴ�.</span>";
      }
    
      ModelAndView mav = new ModelAndView();
      mav.addObject("msg", msg);
      mav.setViewName("user/idCheckOk");
      
      return mav;
   }
   
   @RequestMapping("/nicknameCheck.do")
   public ModelAndView nicknameCheck(
         @RequestParam(value="nickname")String nickname) {
      
      int result = userDao.nicknameCheck(nickname);
      String msg = null;
      
      if(result==0) {
         msg = "<span style=color:blue>��밡���� �г����Դϴ�.</span>";
      }else {
         msg = "<span style=color:red>�ߺ��� �г����Դϴ�.</span>";
      }
      
      ModelAndView mav = new ModelAndView();
      mav.addObject("msg", msg);
      mav.setViewName("user/idCheckOk");
      return mav;
   }
   
   
   @RequestMapping("/loginForm.do")
   public ModelAndView loginForm() {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("user/login");
      return mav;
   }
   
   @RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/login");
		return mav;
	}
   
   @RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView loginSubmit(
			@RequestParam("userid") String userid,
			@RequestParam("userpwd") String userpwd,
			HttpSession session) {
			
	   	ModelAndView mav = new ModelAndView();
		String pwd = userDao.loginCheck(userid);
		if(pwd != null && pwd.equals(userpwd)) {
			String username = userDao.getUserInfo(userid);
			session.setAttribute("id", userid);
			session.setAttribute("name", username);
			mav.addObject("msg",username+"�α��μ���!");
			mav.addObject("goUrl","index.do");
			mav.setViewName("user/userMsg");
		} else {
			mav.addObject("msg","�α��ν���!");
			mav.addObject("goUrl", "login.do");
			mav.setViewName("user/userMsg");
		}
		return mav;
   }
   
   @RequestMapping("/logout.do")
   public ModelAndView logout(
		   HttpServletRequest req) {
	   
	   HttpSession session = req.getSession();
	   session.invalidate();  
	   ModelAndView mav = new ModelAndView();
	   mav.setViewName("redirect:/index.do");
	   return mav;
   }
   
}