package kr.pe.hoon.service;

import java.util.Date;

import kr.pe.hoon.domain.UserVO;
import kr.pe.hoon.dto.LoginDTO;

public interface UserService {
	public void create(UserVO uVO) throws Exception;
	
	public UserVO login(LoginDTO lDTO) throws Exception;
	
	public UserVO readByUid(String uid) throws Exception;
	
	public UserVO readByEmail(String email) throws Exception;
	
	public UserVO readForCheckSession(String value) throws Exception;
	
	public void updateForLogin(String uid, String sessionid, Date next) throws Exception;
}
