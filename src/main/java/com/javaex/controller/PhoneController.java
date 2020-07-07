package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping("/phone") // 반복되는 주소 빼놓음
public class PhoneController {
	
	@Autowired PhoneDao phoneDao;

	@RequestMapping(value= "/list", method = {RequestMethod.GET, RequestMethod.POST}) // 사용할 주소
	public String list(Model model) {
		System.out.println("/phone/list");

		List<PersonVo> pList = phoneDao.getPersonList();

		model.addAttribute("pList", pList);

		return "list"; // 포워드할 주소
	}

	@RequestMapping(value = "/writeForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {

		return "writeForm";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("/phone/write");
		System.out.println(personVo.toString());

		phoneDao.personInsert(personVo);

		return "redirect: /phonebook3/phone/list";
	}
	
	@RequestMapping(value = "/write2", method = RequestMethod.GET)
	public String write(@RequestParam("name") String name,
						@RequestParam("hp") String hp,
						@RequestParam("company") String company) {
		
		System.out.println("/phone/write2");
		
		phoneDao.personInsert2(name, hp, company);

		return "redirect: /phonebook3/phone/list";
	}
	
	@RequestMapping(value = "/updateForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String updateForm(Model model, @RequestParam("personId") int personId) {
		
		System.out.println("/phone/updateForm");
		
		PersonVo vo = phoneDao.getPerson(personId);
		model.addAttribute("vo", vo);
		
		return "updateForm";
	}
	
	@RequestMapping(value = "/updateForm2", method = {RequestMethod.GET, RequestMethod.POST})
	public String updateForm2(Model model, @RequestParam("personId") int personId) {
		
		System.out.println("/phone/updateForm");
		
		Map<String, Object> personMap = phoneDao.getPerson2(personId);
		model.addAttribute("personMap", personMap);
		
		return "updateForm2";
	}
	
	@RequestMapping("/update")
	public String update(@ModelAttribute PersonVo personVo) {
		System.out.println("/phone/update");
		phoneDao.personUpdate(personVo);
		
		return "redirect: /phonebook3/phone/list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("personId") int personId) {
		phoneDao.personDelete(personId);

		return "redirect: /phonebook3/phone/list";
	}

	/*
	 * @RequestMapping(value="/write", method= RequestMethod.GET) public String
	 * write(@RequestParam("name") String name,
	 * 
	 * @RequestParam("hp") String hp,
	 * 
	 * @RequestParam(value = "company", required=false, defaultValue="000") String
	 * company) {
	 * 
	 * System.out.println(name+","+hp+","+company);
	 * 
	 * return "/WEB-INF/views/list.jsp"; }
	 */

}
