package com.longersec.blj.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.longersec.blj.domain.Menu;
import com.longersec.blj.service.MenuService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@RequestMapping("/listMenu")
	@ResponseBody
	public JSONObject listMenu(Menu menu,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultMenus = new ArrayList<Object>();
		ArrayList<Menu> menus = new ArrayList<Menu>();
		long total = 0;
		resultMenus = (ArrayList<Object>)menuService.findAll(menu, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultMenus)) {
			menus = (ArrayList<Menu>)resultMenus.get(0);
			total = ((ArrayList<Long>) resultMenus.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(menus);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addMenu")
	@ResponseBody
	public JSONObject addMenu(Menu menu, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = menuService.addMenu(menu);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editMenu")
	@ResponseBody
	public JSONObject editMenu(Menu menu, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = menuService.editMenu(menu);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delMenu")
	@ResponseBody
	public JSONObject delMenu(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = menuService.delMenu(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
