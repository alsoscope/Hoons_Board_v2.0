package kr.pe.hoon.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.pe.hoon.domain.UserVO;
import kr.pe.hoon.dto.LoginDTO;
import kr.pe.hoon.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public void loginGET(@ModelAttribute("lDTO") LoginDTO lDTO) {
		
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public void loginPOST(LoginDTO lDTO, HttpSession session, Model model) throws Exception {
		UserVO uVO = userService.login(lDTO);
		
		if (uVO == null) {
			return;
		}
		
		model.addAttribute("uVO", uVO);
	}
}
