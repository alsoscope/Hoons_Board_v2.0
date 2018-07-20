package kr.pe.hoon.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.pe.hoon.domain.UserVO;
import kr.pe.hoon.dto.LoginDTO;
import kr.pe.hoon.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String loginGET(HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		request.getSession().setAttribute("redirectURI", referer);
		
		return "/user/login";
	}
	
	@RequestMapping(value="loginPost", method=RequestMethod.POST)
	public void loginPOST(LoginDTO lDTO, Model model, HttpSession session) throws Exception {
		UserVO uVO = userService.login(lDTO);
		
		if (uVO == null) {
			return;
		}
		
		model.addAttribute("uVO", uVO);
		
		if (lDTO.isUsecookie()) {
			int amount = 60 * 60 * 24 * 7;
			Date sessionlimit = new Date(System.currentTimeMillis() + (1000 * amount));
			
			userService.updateForLogin(uVO.getUid(), session.getId(), sessionlimit);
		}
	}
	
	@RequestMapping(value="join", method=RequestMethod.GET)
	public String joinGET() {
		return "/user/join";
	}
	
	@RequestMapping(value="join", method=RequestMethod.POST)
	public String joinPost(UserVO uVO, Model model) throws Exception {
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value="joinIdCheck", method=RequestMethod.POST)
	public ResponseEntity<String> joinIdCheck(@RequestParam("uid") String uid) throws Exception {
		ResponseEntity<String> entity = null;
		
		System.out.println("받은 uid: " + uid);
		
		if (userService.readByUid(uid) != null) {
			entity = new ResponseEntity<>("ID_DUP", HttpStatus.NOT_FOUND);
		} else {
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}
		
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value="joinEmailCheck", method=RequestMethod.POST)
	public ResponseEntity<String> joinEmailCheck(String email) throws Exception {
		ResponseEntity<String> entity = null;
		
		if (userService.readByEmail(email) != null) {
			entity = new ResponseEntity<>("EMAIL_DUP", HttpStatus.NOT_FOUND);
		} else {
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}
		
		return entity;
	}
}
