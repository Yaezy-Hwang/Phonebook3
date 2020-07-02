package com.javaex.controller;

import java.util.List;

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

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST }) // 사용할 주소
	public String list(Model model) {
		System.out.println("/phone/list");

		PhoneDao phonedao = new PhoneDao();
		List<PersonVo> pList = phonedao.getPersonList();

		model.addAttribute("pList", pList);

		return "/WEB-INF/views/list.jsp"; // 포워드할 주소
	}

	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {

		return "/WEB-INF/views/writeForm.jsp";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("/phone/write");
		System.out.println(personVo.toString());

		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personInsert(personVo);

		return "redirect: /phonebook3/phone/list";
	}
	
	@RequestMapping("/updateForm")
	public String updateForm(Model model, @RequestParam("personId") int personId) {
		
		PhoneDao phoneDao = new PhoneDao();
		PersonVo vo = phoneDao.getPerson(personId);
		
		model.addAttribute("vo", vo);
		
		return "/WEB-INF/views/updateForm.jsp";
	}
	
	@RequestMapping("/update")
	public String update(@ModelAttribute PersonVo personVo) {
		System.out.println("/phone/update");
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personUpdate(personVo);
		
		return "redirect: /phonebook3/phone/list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("personId") int personId) {
		
		PhoneDao phoneDao = new PhoneDao();
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
