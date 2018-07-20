package kr.pe.hoon.service;

import java.util.Date;

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
	public void create(UserVO uVO) throws Exception {
		uDAO.create(uVO);
	}
	@Override
	public UserVO login(LoginDTO lDTO) throws Exception {
		return uDAO.read(lDTO);
	}

	@Override
	public UserVO readByUid(String uid) throws Exception {
		return uDAO.readByUid(uid);
	}
	
	@Override
	public UserVO readByEmail(String email) throws Exception {
		return uDAO.readByEmail(email);
	}
	
	@Override
	public UserVO readForCheckSession(String value) throws Exception {
		return uDAO.readForCheckSession(value);
	}

	@Override
	public void updateForLogin(String uid, String sessionid, Date next) throws Exception {
		uDAO.updateForLogin(uid, sessionid, next);
	}

}
