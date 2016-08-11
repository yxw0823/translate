package com.apps.sys.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apps.sys.domain.HotWord;
import com.apps.sys.service.IHotWordService;
import com.framework.core.action.BaseAction;
import com.framework.core.domain.PageBeanVO;

@Controller
@RequestMapping("/apps/sys/HotWordAction.do")
public class HotWordAction extends BaseAction {

 
	@Autowired
	private IHotWordService hotWordService;
	
	@RequestMapping(params = "method=gotoView")
	public String gotoView(ModelMap map,String type,PageBeanVO<HotWord> page,HttpServletRequest request,HotWord word) {
		if (type.equals("manage")) {
			page=this.hotWordService.selectHotWordPage(page, word);
			map.put("page", page);
			return "/jsp/backmng/hotword/hotword";
		}
		return "";
	}

	@RequestMapping(params = "method=delHotWord")
	public String delHotWord(ModelMap map,HotWord word, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		this.hotWordService.deleteHotWord(word);
		map.put("sort", "[{property:'w.hot_time',direction:'DESC'}]");
		return "redirect:/apps/sys/HotWordAction.do?method=gotoView&type=manage&start=0&limit=10";
	
	}
	
	 
}
