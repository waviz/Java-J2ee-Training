package com.waviz.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.waviz.dao.UserDao;
import com.waviz.dao.UserDaoImpl;
import com.waviz.domain.User;


@Controller
public class HomePageController {
	@Autowired
	private UserDao userDao;
	public HomePageController(){
		super();
		new UserDaoImpl();
	}
	
	@RequestMapping("/register")
	public ModelAndView registerUser(@ModelAttribute User user)
	{
	List<String> genderList=new ArrayList<String>();
	genderList.add("male");
	genderList.add("female");
	List<String> cityList=new ArrayList<String>();
	cityList.add("delhi");
	cityList.add("gurgaon");
	cityList.add("meerut");
	cityList.add("noida");
	Map<String, Object> map=new HashMap<String, Object>();
	map.put("genderList", genderList);
	map.put("cityList", cityList);
	return new ModelAndView("register","map",map);
	}
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insertData(@ModelAttribute User user)
	{
		System.out.println("user "+user.getFirstName());
		if(user!=null){
			userDao.insertData(user);
		}
		return "redirect:/getList";
	}
	 @RequestMapping("/getList")  
	 public ModelAndView getUserLIst() {  
	  List<User> userList = userDao.getUserList();  
	  return new ModelAndView("userList", "userList", userList);  
	 } 
	 @RequestMapping(value="/edit",method=RequestMethod.GET)  
	 public ModelAndView editUser(@RequestParam String id,  
	   @ModelAttribute User user) {  
	  
	  user = userDao.getUser(id);
	  

	  List<String> genderList = new ArrayList<String>();  
	  genderList.add("male");  
	  genderList.add("female");  
	  
	  List<String> cityList = new ArrayList<String>();  
	  cityList.add("delhi");  
	  cityList.add("gurgaon");  
	  cityList.add("meerut");  
	  cityList.add("noida");  
	  
	  Map<String, Object> map = new HashMap<String, Object>();  
	  map.put("genderList", genderList);  
	  map.put("cityList", cityList);  
	  map.put("user", user);  
	  
	  return new ModelAndView("edit", "map", map);  
	  
	 } 
	 @RequestMapping("/update")  
	 public String updateUser(@ModelAttribute User user) {  
	  userDao.updateData(user);  
	  return "redirect:/getList";  
	  
	 }  
	  
	 @RequestMapping("/delete")  
	 public String deleteUser(@RequestParam String id) {  
	  System.out.println("id = " + id);  
	  userDao.deleteData(id);  
	  return "redirect:/getList";  
	 }  


}
