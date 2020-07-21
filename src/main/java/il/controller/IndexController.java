package il.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping("/index.do")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("indexMain");
		return mav;
	}
	
	@RequestMapping("/test.do")
	public ModelAndView test() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("testA");
		return mav;
	}
}
