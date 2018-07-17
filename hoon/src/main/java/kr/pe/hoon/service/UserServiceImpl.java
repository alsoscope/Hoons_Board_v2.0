package kr.pe.hoon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.hoon.domain.UserVO;
import kr.pe.hoon.dto.LoginDTO;
import kr.pe.hoon.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO uDAO;
	
	@Override
	public UserVO login(LoginDTO lDTO) throws Exception {
		return uDAO.read(lDTO);
	}
}
