package kr.pe.hoon.persistence;

import java.util.Date;

import kr.pe.hoon.domain.UserVO;
import kr.pe.hoon.dto.LoginDTO;

public interface UserDAO {
	public void create(UserVO uVO) throws Exception;
	
	public UserVO read(LoginDTO lDTO) throws Exception;

	public UserVO readByUid(String uid) throws Exception;
	
	public UserVO readByEmail(String email) throws Exception;
	
	public UserVO readForCheckSession(String value) throws Exception;
	
	public void update(UserVO uVO) throws Exception;
	
	public void updateForCookie(String uid, String sessionid, Date next) throws Exception;
}
