package com.longersec.blj.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longersec.blj.domain.User;
import com.longersec.blj.domain.UserFavourite;
import com.longersec.blj.service.UserFavouriteService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/userFavourite")
public class UserFavouriteController {

	@Autowired
	private UserFavouriteService userFavouriteService;

	@SuppressWarnings("unchecked")
	@RequestMapping("/listUserFavourite")
	@ResponseBody
	public JSONObject listUserFavourite(UserFavourite userFavourite,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultUserFavourites = new ArrayList<Object>();
		ArrayList<UserFavourite> userFavourites = new ArrayList<UserFavourite>();
		long total = 0;
		resultUserFavourites = (ArrayList<Object>)userFavouriteService.findAll(userFavourite, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultUserFavourites)) {
			userFavourites = (ArrayList<UserFavourite>)resultUserFavourites.get(0);
			total = ((ArrayList<Long>) resultUserFavourites.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(userFavourites);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addUserFavourite")
	@ResponseBody
	public JSONObject addUserFavourite(UserFavourite userFavourite, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			User user = (User) session.getAttribute("loginUser");
			userFavourite.setUser_id(user.getId());
			Boolean r = userFavouriteService.addUserFavourite(userFavourite);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editUserFavourite")
	@ResponseBody
	public JSONObject editUserFavourite(UserFavourite userFavourite, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = userFavouriteService.editUserFavourite(userFavourite);
			result.accumulate("success", r?true:false);
		}
		return result;
	}
	
	@RequestMapping("/delUserFavourite")
	@ResponseBody
	public JSONObject delUserFavourite(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = userFavouriteService.delUserFavourite(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
	
	@RequestMapping("/delUserFavouriteByDeviceId")
	@ResponseBody
	public JSONObject delUserFavouriteByDeviceId(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			User user = (User) session.getAttribute("loginUser");
			Boolean r = userFavouriteService.delUserFavouriteByDeviceId(_ids, user.getId());
			result.accumulate("success", r);
		}
		return result;
	}
}
